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
import org.ko.sigma.core.util.JacksonHelper;
import org.ko.sigma.rest.basic.service.SmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(rollbackFor = Throwable.class)
public class AliyunSMSServiceImpl implements SmsService {

    @Value("${aliyun.sms.region-id}")
    private String regionId;

    @Value("${aliyun.sms.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.sms.access-secret}")
    private String accessSecret;

    @Value("${aliyun.sms.sign-name}")
    private String signName;

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
     * @param mobile
     * @param type: 对应发送模板类型
     */
    @Override
    public void sendCode(String mobile, Short type) {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        Map<String, String> params = new HashMap<>();
        params.put(CODE, "123456");

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(DOMAIN_ADDRESS);
        request.setAction(ACTION);
        request.setVersion(VERSION);
        request.putQueryParameter(REGION_ID, regionId);
        request.putQueryParameter(PHONE_NUMBERS, mobile);
        request.putQueryParameter(SIGN_NAME, signName);
        request.putQueryParameter(TEMPLATE_CODE, "");
        request.putQueryParameter(TEMPLATE_PARAM, JacksonHelper.obj2String(params));
        try {
            CommonResponse response = client.getCommonResponse(request);
            Map<String, String> aliyunSmsResponse = JacksonHelper.string2Map(response.getData());
            if (aliyunSmsResponse == null || !OK.equalsIgnoreCase(aliyunSmsResponse.get("Code"))) {
                throw new TransactionalException(SystemCode.SEND_ERROR);
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
