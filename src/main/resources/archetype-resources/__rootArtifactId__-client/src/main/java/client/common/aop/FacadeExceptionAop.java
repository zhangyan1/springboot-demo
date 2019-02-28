#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.client.common.aop;
import ${package}.client.common.util;
import ${package}.client.common.error;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;


/**
 * Created by ${userName} on ${today}.
 */
@Slf4j
@Setter
public class FacadeExceptionAop {

    private ErrorInfo paramError = Errors.PARAMS_ERROR;
    private ErrorInfo error = Errors.SERVICE_ERROR;

    public ErrorInfo getParamError() {
        if (paramError == null) {
            paramError = Errors.PARAMS_ERROR;
        }
        return paramError;
    }

    public ErrorInfo getError() {
        if (error == null) {
            error = Errors.FAILURE;
        }
        return error;
    }

    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object result = null;
        try {
            if (VerifyUtils.isEmpty(args)) {
                result = joinPoint.proceed();
            } else {
                result = joinPoint.proceed(args);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error(String.format("message=[%s],IllegalArgumentException is ", e.getMessage()), e);
            Class<?> resultType = getResultType(joinPoint);
            if (resultType != null && Result.class.isAssignableFrom(resultType)) {
                return Result.error(new ErrorInfo(getParamError().getCode(), e.getMessage(), e.getMessage()), getThrowable(e));
            }
            throw e;
        } catch (BizException e) {
            log.error(String.format("message=[%s],BizException is ", e.getMessage()), e);
            Class<?> resultType = getResultType(joinPoint);
            if (resultType != null && Result.class.isAssignableFrom(resultType)) {
                return Result.error(e.getError(), getThrowable(e));
            }
            throw e;
        } catch (Throwable e) {
            log.error(String.format("message=[%s],Exception is ", e.getMessage()), e);
            Class<?> resultType = getResultType(joinPoint);
            if (resultType != null && Result.class.isAssignableFrom(resultType)) {
                String error = String.format("Exception is %s[%s]", e.getClass().getSimpleName(), e.getMessage());
                return Result.error(new ErrorInfo(getError().getCode(), error, error), getThrowable(e));
            }
            throw e;
        }
    }

    private Class<?> getResultType(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            return ((MethodSignature) signature).getReturnType();
        }
        return null;
    }

    private Throwable getThrowable(Throwable e) {
        return EnvUtil.isOnline() ? null : e;
    }
}
