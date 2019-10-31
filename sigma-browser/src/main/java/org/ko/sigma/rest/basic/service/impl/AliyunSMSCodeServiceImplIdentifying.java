package org.ko.sigma.rest.basic.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.ko.sigma.core.exception.TransactionalException;
import org.ko.sigma.core.type.SystemCode;
import org.ko.sigma.core.util.GeneratorCodeUtils;
import org.ko.sigma.core.util.JacksonHelper;
import org.ko.sigma.data.entity.Dict;
import org.ko.sigma.data.entity.SendCodeLog;
import org.ko.sigma.rest.basic.service.IdentifyingCodeService;
import org.ko.sigma.rest.dict.service.DictService;
import org.ko.sigma.rest.log.repository.SendCodeLogRepository;
import org.ko.sigma.rest.log.service.SendCodeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service("sms")
@Transactional(rollbackFor = Throwable.class)
public class AliyunSMSCodeServiceImplIdentifying implements IdentifyingCodeService {

    @Value("${sms.aliyun.regionId}")
    private String regionId;

    @Value("${sms.aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${sms.aliyun.accessSecret}")
    private String accessSecret;

    @Value("${sms.aliyun.signName}")
    private String signName;

    @Value("${sms.dictKey}")
    private String smsDictKey;

    private static final String SEND_TYPE = "sms";

    @Autowired
    private DictService dictService;

    @Autowired
    private SendCodeLogService sendCodeLogService;

    private static final String DOMAIN_ADDRESS = "dysmsapi.aliyuncs.com";

    private static final String ACTION = "SendSms";

    private static final String REGION_ID = "RegionId";
    private static final String PHONE_NUMBERS = "PhoneNumbers";
    private static final String SIGN_NAME = "SignName";
    private static final String TEMPLATE_CODE = "TemplateCode";
    private static final String TEMPLATE_PARAM = "TemplateParam";
    private static final String VERSION = "2017-05-25";
    private static final String CODE = "code";
    private static final String OK = "ok";
    private static final String SMS_UP_EXTEND_CODE = "SmsUpExtendCode";
    private static final String OUT_ID = "OutId";

    /**
     * https://dysms.console.aliyun.com/dysms.htm?spm=a2c81.54da98d.aliyun_sidebar.159.1fbd1127we1TzO#/quickStart
     * @param address 手机号
     * @param messageType: 对应发送模板类型
     */
    @Override
    public void send(String address, String messageType) {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        Dict dict = dictService.findDictByCodeAndType(smsDictKey, messageType);

        String code = GeneratorCodeUtils.numberCode();

        Map<String, String> params = new HashMap<>();
        params.put(CODE, code);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(DOMAIN_ADDRESS);
        request.setAction(ACTION);
        request.setVersion(VERSION);
        request.putQueryParameter(REGION_ID, regionId);
        request.putQueryParameter(PHONE_NUMBERS, address);
        request.putQueryParameter(SIGN_NAME, signName);
        request.putQueryParameter(TEMPLATE_CODE, dict.getValue());
        request.putQueryParameter(TEMPLATE_PARAM, JacksonHelper.obj2String(params));
        try {
            CommonResponse response = client.getCommonResponse(request);
            Map<String, String> aliyunSmsResponse = JacksonHelper.string2Map(response.getData());
            if (aliyunSmsResponse == null || !OK.equalsIgnoreCase(aliyunSmsResponse.get("Code"))) {
                throw new TransactionalException(SystemCode.SEND_ERROR);
            }
            SendCodeLog sendCodeLog = new SendCodeLog();
            sendCodeLog.setReceiveAddress(address);
            sendCodeLog.setSendType(SEND_TYPE);
            sendCodeLog.setMessageCode(code);
            sendCodeLog.setMessageType(messageType);
            sendCodeLog.setExpireIn(180L);
            sendCodeLogService.createSendCodeLog(sendCodeLog);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
