#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.common.exception;

import ${package}.client.common.error.ErrorInfo;

/**
 * Created by ${userName} on ${today}.
 */
public class ParamterInvalidException extends BaseException {
    public ParamterInvalidException(ErrorInfo errorInfo) {
        super(errorInfo);
    }

    public ParamterInvalidException(ErrorInfo errorInfo, Throwable cause) {
        super(errorInfo, cause);
    }

    public ParamterInvalidException(ErrorInfo errorInfo, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(errorInfo, cause, enableSuppression, writableStackTrace);
    }
}
