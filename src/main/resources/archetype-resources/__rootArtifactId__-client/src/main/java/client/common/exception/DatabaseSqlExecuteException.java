#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.common.exception;

import ${package}.client.common.error.ErrorInfo;

/**
 * Created by ${userName} on ${today}.
 */
public class DatabaseSqlExecuteException extends BaseException {
    public DatabaseSqlExecuteException(ErrorInfo errorInfo) {
        super(errorInfo);
    }

    public DatabaseSqlExecuteException(ErrorInfo errorInfo, Throwable cause) {
        super(errorInfo, cause);
    }

    public DatabaseSqlExecuteException(ErrorInfo errorInfo, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(errorInfo, cause, enableSuppression, writableStackTrace);
    }
}
