package ${package}.client.common.error;


/**
 * Created by ${userName} on ${today}.
 */
public interface Errors{


    ErrorInfo PARAMS_ERROR = new ErrorInfo(10001,"PARAMS_ERROR","param is error");
    ErrorInfo SERVICE_ERROR = new ErrorInfo(10002,"SERVICE_ERROR","service is error");
    ErrorInfo FAILURE = new ErrorInfo(10003,"FAILURE","FAILURE");

}