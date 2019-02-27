#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.client.common.result;


import ${package}.client.common.error.ErrorInfo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = 5947203836182608272L;

    private boolean success;

    private T value;

    private ErrorInfo error;

    private Throwable throwable;

    private String remark;

    public Result() {

    }

    public static <T> Result<T> success(Result<?> result, T value) {
        return new Result<T>(result.isSuccess(), value, result.getError(), result.getThrowable(), result.getRemark());
    }

    public static <T> Result<T> success() {
        return new Result<T>(true, null, null, null);
    }

    public static <T> Result<T> success(T value) {
        return new Result<T>(true, value, null, null);
    }

    public static <T> Result<T> success(T value, String remark) {
        return new Result<T>(true, value, null, null, remark);
    }

    public static <T> Result<T> success(ErrorInfo error) {
        return new Result<T>(true, null, error, null);
    }

    public static <T> Result<T> success(T value, String remark, ErrorInfo error) {
        return new Result<T>(true, value, error, null, remark);
    }

    public static <T> Result<T> success(T value, ErrorInfo error) {
        return new Result<T>(true, value, error, null);
    }

    public static <T> Result<T> error(ErrorInfo error) {
        return new Result<T>(false, null, error, null);
    }

    public static <T> Result<T> error(ErrorInfo error, Throwable throwable) {
        return new Result<T>(false, null, error, throwable);
    }

    public static <T> Result<T> error(ErrorInfo error, String remark) {
        return new Result<T>(false, null, error, null, remark);
    }

    public static <T> Result<T> error(Result<?> result) {
        return new Result<T>(false, null, result.getError(), result.getThrowable(), result.getRemark());
    }

    public static <T> Result<T> error(Result<?> result, ErrorInfo error) {
        return new Result<T>(false, null, error, result.getThrowable(), result.getRemark());
    }

    public Result(boolean success, T value, ErrorInfo error, Throwable throwable, String remark) {
        this.success = success;
        this.value = value;
        this.error = error;
        this.throwable = throwable;
        this.remark = remark;
    }

    public Result(boolean success, T value, ErrorInfo error, Throwable throwable) {
        this.success = success;
        this.value = value;
        this.error = error;
        this.throwable = throwable;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ErrorInfo getError() {
        return error;
    }

    public void setError(ErrorInfo error) {
        this.error = error;
    }

    @JsonIgnore
    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public String getThrowableStackTrace() {
        if (null != throwable) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            throwable.printStackTrace(new PrintStream(out));
            return out.toString();
        }
        return "无异常被捕获";
    }

    public boolean isEmpty() {
        return null == value;
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public boolean hasValue() {
        return isSuccess() && isNotEmpty();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}

