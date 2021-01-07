package com.cos.source;

import org.bouncycastle.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 服务统一返回值处理
 * Created by Akuma on 2017/2/5.
 */
public class ZSYResult<T> implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ZSYResult.class);

    private static final long serialVersionUID = -5007728971756456859L;
    /**
     * 00 代表成功
     * 01 代表失败
     */
    private String errCode; //操作码

    private String errMsg; //操作原因

    private T data; //附加数据

    private ZSYResult(){}

    private ZSYResult(String errCode){
        this.errCode = errCode;
    }


    /**
     * 接口返回状态
     */
    public final static class RESPONSE {

        /**
         * 成功
         */
        public static final String SUCCESS = "00";

        /**
         * 通用失败(业务异常)
         */
        public static final String FAIL = "01";


        /**
         * 没有登录
         */
        public static final String NO_SESSION_ERROR = "02";

        /**
         * 请求参数错误
         */
        public static final String PARAM_ERROR = "03";

        /**
         * 数据异常
         */
        public static final String CODE_ERROR = "04";

        /**
         * 数据库操作异常
         */
        public static final String DB_ERROR = "05";

        /**
         * 调用外部http接口异常
         */
        public static final String API_ERROR = "06";

        /**
         * 服务异常
         */
        public static final String SERVER_ERROR = "07";

        /**
         * 没有权限
         */
        public static final String AUTH_FORBIDDEN_ERROR = "08";

        /**
         * 请求成功通用返回信息
         */
        public final static String ERR_OK_MSG = "执行成功";

        /**
         * 请求失败通用返回信息
         */
        public final static String ERR_FAIL_MSG = "执行失败";

    }

    /**
     * 成功
     * @return
     */
    public static ZSYResult success(){
        ZSYResult result = new ZSYResult(RESPONSE.SUCCESS);
        result.errMsg = RESPONSE.ERR_OK_MSG;
        return result;
    }

    /**
     * 失败
     * @return
     */
    public static ZSYResult fail(){
        ZSYResult result = new ZSYResult(RESPONSE.FAIL);
        result.errMsg = RESPONSE.ERR_FAIL_MSG;
        return result;
    }
    public static ZSYResult fail(String errCode){
        ZSYResult result = new ZSYResult(errCode);
        result.errMsg = RESPONSE.ERR_FAIL_MSG;
        return result;
    }


    /**
     * 设置返回结果
     * @param errMsg
     * @return
     */
    public ZSYResult msg(String errMsg){
        this.errMsg = errMsg;
        return this;
    }

    /**
     * 设置返回数据
     * @return
     */
    public ZSYResult data(Object data){
        this.data = (T) data;
        return this;
    }

    /**
     * 内部服务数据处理
     * @return
     */
    public static ZSYResult attach(Object t){
        return success().data(t);
    }



    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public Object getData() {
        return data;
    }

}