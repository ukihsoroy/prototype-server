package org.ko.sigma.core.support;

import com.google.common.base.Strings;
import lombok.ToString;
import org.ko.sigma.core.bean.SerializeBean;
import org.ko.sigma.core.type.SystemCode;

/**
 * 
 * Response
 *
 * @author <A>K.O</A>
 *
 */
@ToString
public class Response<T> extends SerializeBean {

    public static final boolean FAILED = false;
    public static final boolean SUCCESS = true;

    /**
     * 成功 OR 失败
     */
    private boolean success;

    /**
     * 消息代码
     */
    private Integer code;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 返回结果
     */
    private T data;

    /**
     * 消息参数
     */
    private Object[] arguments;


    private Response() {}

    /**
     * 构造器
     * @param success
     */
    public Response(boolean success) {
        this.success = success;
    }

    public static Response of (boolean success) {
        return new Response(success);
    }

    /**
     * 构造器
     * 
     * @param data
     */
    public Response(T data) {
        this.data = data;
        this.success = SUCCESS;
        this.code = SystemCode.SUCCESS.getCode();
        this.msg = SystemCode.SUCCESS.getMsg();
    }

    public static <T> Response<T> of (T data) {
        return new Response<>(data);
    }

    /**
     * 构造器
     *
     * @param data
     */
    public Response(boolean success, T data) {
        this.data = data;
        this.success = success;
    }

    public static <T> Response<T> of (boolean success, T data) {
        return new Response<>(success, data);
    }

    /**
     * 构造器
     * @param data
     * @param code
     * @param msg
     * @param success
     */
    public Response(boolean success, T data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public static <T> Response<T> of (boolean success, T data, Integer code, String msg) {
        return new Response<>(success, data, code, msg);
    }

    /**
     * 构造器
     * @param systemCode
     */
    public Response(SystemCode systemCode) {
        this.success = false;
        this.code = systemCode.getCode();
        this.msg = systemCode.getMsg();
    }

    public static <T> Response<T> of (SystemCode systemCode) {
        return new Response<>(systemCode);
    }

    /**
     * 构造器
     * @param data
     * @param code
     * @param msg
     * @param success
     * @param args
     */
    public Response(boolean success, T data, Integer code, String msg, Object... args) {
        this.data = data;
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.arguments = args;
    }

    public static <T> Response<T> of (boolean success, T data, Integer code, String msg, Object... args) {
        return new Response<>(success, data, code, msg, args);
    }

    /**
     * 构造器
     * @param success
     * @param msg
     */
    public Response(boolean success, String msg) {
        this.msg = msg;
        this.success = success;
    }

    public static <T> Response<T> of (boolean success, String msg) {
        return new Response<>(success, msg);
    }

    /**
     * 构造器
     * @param success
     * @param code
     * @param msg
     */
    public Response(boolean success, Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public static <T> Response<T> of (boolean success, Integer code, String msg) {
        return new Response<>(success, code, msg);
    }

    /**
     * 构造器
     * @param success
     * @param code
     * @param msg
     * @param args
     */
    public Response(boolean success, Integer code, String msg, Object... args) {
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.arguments = args;
    }

    public static <T> Response<T> of (boolean success, Integer code, String msg, Object... args) {
        return new Response<>(success, code, msg, args);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        if (this.arguments == null || this.arguments.length == 0) {
            return this.msg;
        } else {
            if (Strings.isNullOrEmpty(this.msg)) {
                return null;
            } else {
                return String.format(this.msg, this.arguments);
            }
        }
    }

    public void setMsg(String msg, Object... args) {
        this.msg = msg;
        this.arguments = args;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}