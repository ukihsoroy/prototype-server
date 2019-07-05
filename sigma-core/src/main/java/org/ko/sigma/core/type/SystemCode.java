package org.ko.sigma.core.type;

/**
 * 系统错误码
 */
public enum SystemCode {

    REQUIRE_AUTHENTICATION("0", "Require Authentication!"),
    SUCCESS("200", "request success!"),
    EMPTY_DATA("201", "empty data!"),
    INSERT_ERROR("202", "insert error!"),
    UPDATE_ERROR("202", "update error!"),
    DELETE_ERROR("202", "delete error!"),
    SYSTEM_ERROR("500", "system error!"),
    CONVERTER_ERROR("501", "converter error!"),
    VALIDATOR_ERROR_CODE("800", "validator error code!")
    ;

    /**
     * 200（OK）- 如果现有资源已被更改
     201（created）- 如果新资源被创建
     202（accepted）- 已接受处理请求但尚未完成（异步处理）
     301（Moved Permanently）- 资源的URI被更新
     303（See Other）- 其他（如，负载均衡）
     400（bad request）- 指代坏请求
     404 （not found）- 资源不存在
     406 （not acceptable）- 服务端不支持所需表示
     409 （conflict）- 通用冲突
     412 （Precondition Failed）- 前置条件失败（如执行条件更新时的冲突）
     415 （unsupported media type）- 接受到的表示不受支持
     500 （internal server error）- 通用错误响应
     503 （Service Unavailable）- 服务当前无法处理请求
     */

    private String code;

    private String msg;

    SystemCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
