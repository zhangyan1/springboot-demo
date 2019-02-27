#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.client.common.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * Created by ${userName} on ${today}.
 */
public class VerifyUtils {
    /**
     * 是否是二的次方
     *
     * @param value
     * @return
     */
    public static boolean isPowerOfTwo(long value) {
        return (value & -value) == value;
    }

    /**
     * 是否是二的次方
     *
     * @param value
     * @return
     */
    public static boolean isPowerOfTwo(Number value) {
        return !hasNull(value) && isPowerOfTwo(value.longValue());
    }

    /**
     * 奇数
     *
     * @param value
     * @return
     */
    private static boolean isOdd(long value) {
        return (value & 1) == 1;
    }

    /**
     * 奇数
     *
     * @param value
     * @return
     */
    public static boolean isOdd(Number value) {
        return !hasNull(value) && isOdd(value.longValue());
    }

    /**
     * 偶数
     *
     * @param value
     * @return
     */
    private static boolean isEven(long value) {
        return (value & 1) == 0;
    }

    /**
     * 偶数
     *
     * @param value
     * @return
     */
    public static boolean isEven(Number value) {
        return !hasNull(value) && isEven(value.longValue());
    }

    /**
     * 是否是质数, 也叫素数
     *
     * @param value
     * @return
     */
    public static boolean isPrime(long value) {
        if (value <= 1) {
            return false;
        }
        if (value <= 3) {
            return true;
        }
        if (isEven(value)) {
            return false;
        }
        for (long i = 3, max = value / 2; i < max; i += 2) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }
//    public static boolean isPrime(long value) {
//        if (value <= 1) {
//            return false;
//        }
//        if (value <= 3) {
//            return true;
//        }
//        if (isEven(value)) {
//            return false;
//        }
//        for (long i = 3, max = Double.valueOf(Math.sqrt(value)).longValue(); i < max; i += 2) {
//            if (value % i == 0) {
//                return false;
//            }
//        }
//        return true;
//    }

    /**
     * 是否是质数, 也叫素数
     *
     * @param value
     * @return
     */
    public static boolean isPrime(Number value) {
        return !hasNull(value) && isPrime(value.longValue());
    }

    /**
     * 判断是否时整数, 小数, 首位可以是正负符号
     *
     * @param value 值
     * @return 判断值
     */
    public static boolean isNumber(String value) {
        return !isEmpty(value) && value.matches("^[\\-+]?\\d+(\\.\\d+)?$");
    }

    /**
     * 判断是否是域名
     *
     * @param value
     * @return
     */
    public static boolean isDomain(String value) {
        return !isEmpty(value) && value.matches("(\\w+\\.)+\\w+");
    }

    /**
     * 判断是否是IPv4
     *
     * @param value
     * @return
     */
    public static boolean isIPv4(String value) {
        if (!isEmpty(value) && value.matches("^([0-9]{1,3}\\.){3}[0-9]{1,3}")) {
            String[] values = value.split("\\.");
            if (!hasEmpty(values) && values.length == 4) {
                int ip = 0;
                for (String s : values) {
                    ip = Integer.valueOf(s);
                    if (ip < 0 || ip > 255) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 是否是整数, 首位可以是正负符号
     *
     * @param value 值
     * @return 判断值
     */
    public static boolean isInteger(String value) {
        return !isEmpty(value) && value.matches("^[\\-\\+]?\\d+$");
    }

    /**
     * 是否是小数, 首位可以是正负符号
     *
     * @param value 值
     * @return 判断值
     */
    public static boolean isFloat(String value) {
        return !isEmpty(value) && value.matches("^[\\-\\+]?\\d+\\.\\d+$");
    }

    /**
     * 是否是数字, 不包含正负符号
     *
     * @param value 值
     * @return 判断值
     */
    public static boolean isDigital(String value) {
        return !isEmpty(value) && value.matches("^\\d+$");
    }

    /**
     * 判断是否是手机号码
     *
     * @param value 值
     * @return 判断值
     */
    public static boolean isPhone(String value) {
        return !isEmpty(value) && value.matches("^\\d{11}$");
    }

    /**
     * 验证是否是手机号码
     *
     * @param value 值
     * @return 判断值
     */
    public static boolean isTelPhone(String value) {
        return !isEmpty(value) && value.matches("^(\\+86\\s*)?\\d{3}((\\s|-)*\\d{4}){2}$");
    }

    /**
     * 验证是否是邮箱
     *
     * @param value 值
     * @return 判断值
     */
    public static boolean isEmail(String value) {
        return !isEmpty(value) && value.matches("^(\\w+\\.)*\\w+@(\\w+\\.)+\\w+$");
    }

    /**
     * 验证是否是纯字母
     *
     * @param value 值
     * @return 判断值
     */
    public static boolean isLetter(String value) {
        return !isEmpty(value) && value.matches("^[a-zA-Z]+$");
    }

    /**
     * 验证是否是字母与数字组合
     *
     * @param value 值
     * @return 判断值
     */
    public static boolean isLetterOrDigital(String value) {
        return !isEmpty(value) && value.matches("^[a-zA-Z0-9]+$");
    }

    /**
     * 验证是否是字母与数字组合, 必须都包含
     *
     * @param value 值
     * @return 判断值
     */
    public static boolean isLetterAndDigital(String value) {
        return hasLetterAndDigital(value) && !value.matches("[^a-zA-Z0-9]+");
    }

    /**
     * 验证是否是大写字母, 小写字母与数字组合, 必须都包含
     *
     * @param value 值
     * @return 判断值
     */
    public static boolean isUpperAndLowerAndDigital(String value) {
        return hasUpperAndLowerAndDigital(value) && !value.matches("[^a-zA-Z0-9]+");
    }

    /**
     * 验证value长度是否是length
     *
     * @param value  值
     * @param length 长度
     * @return 判断值
     */
    public static boolean isLength(String value, int length) {
        return !isEmpty(value) && value.length() == length;
    }

    /**
     * 验证value长度是否在min与max之间
     *
     * @param value 值
     * @param min   最小长度
     * @param max   最大长度
     * @return 判断值
     */
    public static boolean isBetween(String value, int min, int max) {
        return !isEmpty(value) && value.length() >= min && value.length() <= max;
    }

    /**
     * 验证value长度是否小于等于length
     *
     * @param value  值
     * @param length 最大长度
     * @return 判断值
     */
    public static boolean isLeLength(String value, int length) {
        return !isEmpty(value) && value.length() <= length;
    }

    /**
     * 验证value长度是否大于等于length
     *
     * @param value  值
     * @param length 最小长度
     * @return 判断值
     */
    public static boolean isGeLength(String value, int length) {
        return !isEmpty(value) && value.length() >= length;
    }

    /**
     * 验证是否包含字母与数字, 必须都包含
     *
     * @param value 值
     * @return 判断值
     */
    public static boolean hasLetterAndDigital(String value) {
        return !isEmpty(value) && value.matches("[a-zA-Z]+") && value.matches("[0-9]+");
    }

    /**
     * 验证是否包含大写字母, 小写字母与数字, 必须都包含
     *
     * @param value 值
     * @return 判断值
     */
    public static boolean hasUpperAndLowerAndDigital(String value) {
        return !isEmpty(value) && value.matches("[a-z]+") && value.matches("[A-Z]+") && value.matches("[0-9]+");
    }

    /**
     * 验证是否是十六进制字符串
     *
     * @param value 值
     * @return 判断值
     */
    public static boolean isHex(String value) {
        return !isEmpty(value) && value.matches("^0[xX][a-fA-F0-9]+$");
    }

    /**
     * @param value 值
     * @return 判断值 value == null || value.trim().isEmpty()
     */
    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * 判断array是否为空
     *
     * @param values 多值
     * @param <T>    通用对象
     * @return 判断值
     */
    public static <T> boolean isEmpty(T[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 判断array是否为空
     *
     * @param values 多值
     * @return 判断值
     */
    public static boolean isEmpty(int[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 判断array是否为空
     *
     * @param values 多值
     * @return 判断值
     */
    public static boolean isEmpty(short[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 判断array是否为空
     *
     * @param values 多值
     * @return 判断值
     */
    public static boolean isEmpty(long[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 判断array是否为空
     *
     * @param values 多值
     * @return 判断值
     */
    public static boolean isEmpty(byte[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 判断array是否为空
     *
     * @param values 多值
     * @return 判断值
     */
    public static boolean isEmpty(boolean[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 判断array是否为空
     *
     * @param values 多值
     * @return 判断值
     */
    public static boolean isEmpty(double[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 判断array是否为空
     *
     * @param values 多值
     * @return 判断值
     */
    public static boolean isEmpty(float[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 判断array是否为空
     *
     * @param values 多值
     * @return 判断值
     */
    public static boolean isEmpty(char[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 判断collection是否为空
     *
     * @param values 多值
     * @param <T>    通用对象
     * @return 判断值
     */
    public static <T> boolean isEmpty(Collection<T> values) {
        return values == null || values.isEmpty();
    }

    /**
     * 判断map是否为空
     *
     * @param values 多值
     * @param <K>    通用对象
     * @param <V>    通用对象
     * @return 判断值
     */
    public static <K, V> boolean isEmpty(Map<K, V> values) {
        return values == null || values.isEmpty();
    }

    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean isLt(T defaultValue, T value) {
        return !hasNull(defaultValue, value) && value.compareTo(defaultValue) < 0;
    }

    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLt(T defaultValue, int value) {
        return !hasNull(defaultValue) && isLt(defaultValue.longValue(), (long) value);
    }

    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLt(T defaultValue, byte value) {
        return !hasNull(defaultValue) && isLt(defaultValue.longValue(), (long) value);
    }


    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLt(T defaultValue, short value) {
        return !hasNull(defaultValue) && isLt(defaultValue.longValue(), (long) value);
    }


    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLt(T defaultValue, char value) {
        return !hasNull(defaultValue) && isLt(defaultValue.longValue(), (long) value);
    }


    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLt(T defaultValue, long value) {
        return !hasNull(defaultValue) && isLt(defaultValue.longValue(), value);
    }


    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLt(T defaultValue, float value) {
        return !hasNull(defaultValue) && isLt(defaultValue, Float.valueOf(value));
    }


    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLt(T defaultValue, double value) {
        return !hasNull(defaultValue) && isLt(defaultValue, Double.valueOf(value));
    }

    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLt(int defaultValue, T value) {
        return isGt(value, defaultValue);
    }

    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLt(byte defaultValue, T value) {
        return isGt(value, defaultValue);
    }


    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLt(short defaultValue, T value) {
        return isGt(value, defaultValue);
    }


    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLt(char defaultValue, T value) {
        return isGt(value, defaultValue);
    }


    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLt(long defaultValue, T value) {
        return isGt(value, defaultValue);
    }


    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLt(float defaultValue, T value) {
        return isGt(value, defaultValue);
    }


    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLt(double defaultValue, T value) {
        return isGt(value, defaultValue);
    }

    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isLt(int defaultValue, int value) {
        return isLt((long) defaultValue, (long) value);
    }

    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isLt(long defaultValue, long value) {
        return value < defaultValue;
    }

    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isLt(short defaultValue, short value) {
        return isLt((long) defaultValue, (long) value);
    }

    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isLt(byte defaultValue, byte value) {
        return isLt((long) defaultValue, (long) value);
    }

    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isLt(char defaultValue, char value) {
        return isLt((long) defaultValue, (long) value);
    }

    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isLt(double defaultValue, double value) {
        return isLt(Double.valueOf(defaultValue), Double.valueOf(value));
    }

    /**
     * 小于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isLt(float defaultValue, float value) {
        return isLt(Float.valueOf(defaultValue), Float.valueOf(value));
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean isLe(T defaultValue, T value) {
        return !hasNull(defaultValue, value) && value.compareTo(defaultValue) <= 0;
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLe(T defaultValue, int value) {
        return !hasNull(defaultValue) && isLe(defaultValue.longValue(), (long) value);
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLe(T defaultValue, byte value) {
        return !hasNull(defaultValue) && isLe(defaultValue.longValue(), (long) value);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLe(T defaultValue, short value) {
        return !hasNull(defaultValue) && isLe(defaultValue.longValue(), (long) value);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLe(T defaultValue, char value) {
        return !hasNull(defaultValue) && isLe(defaultValue.longValue(), (long) value);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLe(T defaultValue, long value) {
        return !hasNull(defaultValue) && isLe(defaultValue.longValue(), value);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLe(T defaultValue, float value) {
        return !hasNull(defaultValue) && isLe(defaultValue, Float.valueOf(value));
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLe(T defaultValue, double value) {
        return !hasNull(defaultValue) && isLe(defaultValue, Double.valueOf(value));
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLe(int defaultValue, T value) {
        return isGe(value, defaultValue);
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLe(byte defaultValue, T value) {
        return isGe(value, defaultValue);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLe(short defaultValue, T value) {
        return isGe(value, defaultValue);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLe(char defaultValue, T value) {
        return isGe(value, defaultValue);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLe(long defaultValue, T value) {
        return isGe(value, defaultValue);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLe(float defaultValue, T value) {
        return isGe(value, defaultValue);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isLe(double defaultValue, T value) {
        return isGe(value, defaultValue);
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isLe(int defaultValue, int value) {
        return isLe((long) defaultValue, (long) value);
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isLe(long defaultValue, long value) {
        return value <= defaultValue;
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isLe(short defaultValue, short value) {
        return isLe((long) defaultValue, (long) value);
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isLe(byte defaultValue, byte value) {
        return isLe((long) defaultValue, (long) value);
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isLe(char defaultValue, char value) {
        return isLe((long) defaultValue, (long) value);
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isLe(double defaultValue, double value) {
        return isLe(Double.valueOf(defaultValue), Double.valueOf(value));
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isLe(float defaultValue, float value) {
        return isLe(Float.valueOf(defaultValue), Float.valueOf(value));
    }

    /**
     * 相等
     *
     * @param value1 值1
     * @param value2 值2
     * @param <T>    通用对象
     * @return 判断值
     */
    public static <T> boolean isEq(T value1, T value2) {
        return !hasNull(value1, value2) && value1.equals(value2);
    }

    /**
     * 相等
     *
     * @param value1 值1
     * @param value2 值2
     * @param <T>    通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean isEq(T value1, T value2) {
        return !hasNull(value1, value2) && value1.compareTo(value2) == 0;
    }

    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isEq(T defaultValue, int value) {
        return !hasNull(defaultValue) && isEq(defaultValue.longValue(), (long) value);
    }

    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isEq(T defaultValue, byte value) {
        return !hasNull(defaultValue) && isEq(defaultValue.longValue(), (long) value);
    }


    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isEq(T defaultValue, short value) {
        return !hasNull(defaultValue) && isEq(defaultValue.longValue(), (long) value);
    }


    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isEq(T defaultValue, char value) {
        return !hasNull(defaultValue) && isEq(defaultValue.longValue(), (long) value);
    }


    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isEq(T defaultValue, long value) {
        return !hasNull(defaultValue) && isEq(defaultValue.longValue(), value);
    }


    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isEq(T defaultValue, float value) {
        return !hasNull(defaultValue) && isEq(defaultValue, Float.valueOf(value));
    }


    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isEq(T defaultValue, double value) {
        return !hasNull(defaultValue) && isEq(defaultValue, Double.valueOf(value));
    }

    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isEq(int defaultValue, T value) {
        return isEq(value, defaultValue);
    }

    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isEq(byte defaultValue, T value) {
        return isEq(value, defaultValue);
    }


    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isEq(short defaultValue, T value) {
        return isEq(value, defaultValue);
    }


    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isEq(char defaultValue, T value) {
        return isEq(value, defaultValue);
    }


    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isEq(long defaultValue, T value) {
        return isEq(value, defaultValue);
    }


    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isEq(float defaultValue, T value) {
        return isEq(value, defaultValue);
    }


    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isEq(double defaultValue, T value) {
        return isEq(value, defaultValue);
    }

    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isEq(int defaultValue, int value) {
        return isEq((long) defaultValue, (long) value);
    }

    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isEq(long defaultValue, long value) {
        return value == defaultValue;
    }

    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isEq(short defaultValue, short value) {
        return isEq((long) defaultValue, (long) value);
    }

    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isEq(byte defaultValue, byte value) {
        return isEq((long) defaultValue, (long) value);
    }

    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isEq(char defaultValue, char value) {
        return isEq((long) defaultValue, (long) value);
    }

    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isEq(double defaultValue, double value) {
        return isEq(Double.valueOf(defaultValue), Double.valueOf(value));
    }

    /**
     * 等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isEq(float defaultValue, float value) {
        return isEq(Float.valueOf(defaultValue), Float.valueOf(value));
    }

    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean isGe(T defaultValue, T value) {
        return !hasNull(defaultValue, value) && value.compareTo(defaultValue) >= 0;
    }



    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGe(T defaultValue, int value) {
        return !hasNull(defaultValue) && isGe(defaultValue.longValue(), (long) value);
    }

    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGe(T defaultValue, byte value) {
        return !hasNull(defaultValue) && isGe(defaultValue.longValue(), (long) value);
    }


    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGe(T defaultValue, short value) {
        return !hasNull(defaultValue) && isGe(defaultValue.longValue(), (long) value);
    }


    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGe(T defaultValue, char value) {
        return !hasNull(defaultValue) && isGe(defaultValue.longValue(), (long) value);
    }


    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGe(T defaultValue, long value) {
        return !hasNull(defaultValue) && isGe(defaultValue.longValue(), value);
    }


    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGe(T defaultValue, float value) {
        return !hasNull(defaultValue) && isGe(defaultValue, Float.valueOf(value));
    }


    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGe(T defaultValue, double value) {
        return !hasNull(defaultValue) && isGe(defaultValue, Double.valueOf(value));
    }

    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGe(int defaultValue, T value) {
        return isLe(value, defaultValue);
    }

    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGe(byte defaultValue, T value) {
        return isLe(value, defaultValue);
    }


    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGe(short defaultValue, T value) {
        return isLe(value, defaultValue);
    }


    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGe(char defaultValue, T value) {
        return isLe(value, defaultValue);
    }


    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGe(long defaultValue, T value) {
        return isLe(value, defaultValue);
    }


    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGe(float defaultValue, T value) {
        return isLe(value, defaultValue);
    }


    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGe(double defaultValue, T value) {
        return isLe(value, defaultValue);
    }

    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isGe(int defaultValue, int value) {
        return isGe((long) defaultValue, (long) value);
    }

    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isGe(long defaultValue, long value) {
        return value >= defaultValue;
    }

    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isGe(short defaultValue, short value) {
        return isGe((long) defaultValue, (long) value);
    }

    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isGe(byte defaultValue, byte value) {
        return isGe((long) defaultValue, (long) value);
    }

    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isGe(char defaultValue, char value) {
        return isGe((long) defaultValue, (long) value);
    }

    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isGe(double defaultValue, double value) {
        return isGe(Double.valueOf(defaultValue), Double.valueOf(value));
    }

    /**
     * 大于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isGe(float defaultValue, float value) {
        return isGe(Float.valueOf(defaultValue), Float.valueOf(value));
    }

    /**
     * 大于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean isGt(T defaultValue, T value) {
        return !hasNull(defaultValue, value) && value.compareTo(defaultValue) > 0;
    }



    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGt(T defaultValue, int value) {
        return !hasNull(defaultValue) && isGt(defaultValue.longValue(), (long) value);
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGt(T defaultValue, byte value) {
        return !hasNull(defaultValue) && isGt(defaultValue.longValue(), (long) value);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGt(T defaultValue, short value) {
        return !hasNull(defaultValue) && isGt(defaultValue.longValue(), (long) value);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGt(T defaultValue, char value) {
        return !hasNull(defaultValue) && isGt(defaultValue.longValue(), (long) value);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGt(T defaultValue, long value) {
        return !hasNull(defaultValue) && isGt(defaultValue.longValue(), value);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGt(T defaultValue, float value) {
        return !hasNull(defaultValue) && isGt(defaultValue, Float.valueOf(value));
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGt(T defaultValue, double value) {
        return !hasNull(defaultValue) && isGt(defaultValue, Double.valueOf(value));
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGt(int defaultValue, T value) {
        return isLt(value, defaultValue);
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGt(byte defaultValue, T value) {
        return isLt(value, defaultValue);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGt(short defaultValue, T value) {
        return isLt(value, defaultValue);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGt(char defaultValue, T value) {
        return isLt(value, defaultValue);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGt(long defaultValue, T value) {
        return isLt(value, defaultValue);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGt(float defaultValue, T value) {
        return isLt(value, defaultValue);
    }


    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Number> boolean isGt(double defaultValue, T value) {
        return isLt(value, defaultValue);
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isGt(int defaultValue, int value) {
        return isGt((long) defaultValue, (long) value);
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isGt(long defaultValue, long value) {
        return value > defaultValue;
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isGt(short defaultValue, short value) {
        return isGt((long) defaultValue, (long) value);
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isGt(byte defaultValue, byte value) {
        return isGt((long) defaultValue, (long) value);
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isGt(char defaultValue, char value) {
        return isGt((long) defaultValue, (long) value);
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isGt(double defaultValue, double value) {
        return isGt(Double.valueOf(defaultValue), Double.valueOf(value));
    }

    /**
     * 小于或等于
     *
     * @param defaultValue 比较值
     * @param value        值
     * @return 判断值
     */
    public static boolean isGt(float defaultValue, float value) {
        return isGt(Float.valueOf(defaultValue), Float.valueOf(value));
    }

    /**
     * values中是否存在小于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean hasLt(T defaultValue, T... values) {
        return !isEmpty(values) ? hasLt(defaultValue, Arrays.asList(values)) : false;
    }

    /**
     * values中是否存在小于或等于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean hasLe(T defaultValue, T... values) {
        return !isEmpty(values) ? hasLe(defaultValue, Arrays.asList(values)) : false;
    }

    /**
     * values中是否存在等于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T> boolean hasEq(T defaultValue, T... values) {
        return !isEmpty(values) ? hasEq(defaultValue, Arrays.asList(values)) : false;
    }

    /**
     * values中是否存在等于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean hasEq(T defaultValue, T... values) {
        return !isEmpty(values) ? hasEq(defaultValue, Arrays.asList(values)) : false;
    }

    /**
     * values中是否存在大于或等于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean hasGe(T defaultValue, T... values) {
        return !isEmpty(values) ? hasGe(defaultValue, Arrays.asList(values)) : false;
    }

    /**
     * values中是否存在大于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean hasGt(T defaultValue, T... values) {
        return !isEmpty(values) ? hasGt(defaultValue, Arrays.asList(values)) : false;
    }

    /**
     * values中是否存在小于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean hasLt(T defaultValue, Collection<T> values) {
        if (!isEmpty(values)) {
            for (T value : values) {
                if (isLt(defaultValue, value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * values中是否存在小于或等于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean hasLe(T defaultValue, Collection<T> values) {
        if (!isEmpty(values)) {
            for (T value : values) {
                if (isLe(defaultValue, value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * values中是否存在等于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T> boolean hasEq(T defaultValue, Collection<T> values) {
        if (!isEmpty(values)) {
            for (T value : values) {
                if (isEq(defaultValue, value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * values中是否存在等于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean hasEq(T defaultValue, Collection<T> values) {
        if (!isEmpty(values)) {
            for (T value : values) {
                if (isEq(defaultValue, value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * values中是否存在大于或等于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean hasGe(T defaultValue, Collection<T> values) {
        if (!isEmpty(values)) {
            for (T value : values) {
                if (isGe(defaultValue, value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * values中是否存在大于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean hasGt(T defaultValue, Collection<T> values) {
        if (!isEmpty(values)) {
            for (T value : values) {
                if (isGt(defaultValue, value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * if isEmpty(values) return false
     *
     * @param values 多值
     * @return 判断值 foreach if(isEmpty(value)) return true
     */
    public static boolean hasEmpty(String... values) {
        return !isEmpty(values) ? hasEmpty(Arrays.asList(values)) : false;
    }

    /**
     * if isEmpty(values) return false
     *
     * @param values 多值
     * @return 判断值 foreach if(isEmpty(value)) return true
     */
    public static boolean hasEmpty(Collection<String> values) {
        if (!isEmpty(values)) {
            for (String value : values) {
                if (isEmpty(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * if isEmpty(values) return false
     *
     * @param values 多值
     * @return 判断值
     */
    public static boolean hasNull(Object... values) {
        return !isEmpty(values) ? hasNull(Arrays.asList(values)) : false;
    }

    /**
     * if isEmpty(values) return false
     *
     * @param values 多值
     * @return 判断值
     */
    public static boolean hasNull(Collection<Object> values) {
        if (!isEmpty(values)) {
            for (Object value : values) {
                if (value == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * values中是否存在小于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean eachLt(T defaultValue, T... values) {
        return !isEmpty(values) ? eachLt(defaultValue, Arrays.asList(values)) : false;
    }

    /**
     * values中是否存在小于或等于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean eachLe(T defaultValue, T... values) {
        return !isEmpty(values) ? eachLe(defaultValue, Arrays.asList(values)) : false;
    }

    /**
     * values中是否存在等于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T> boolean eachEq(T defaultValue, T... values) {
        return !isEmpty(values) ? eachEq(defaultValue, Arrays.asList(values)) : false;
    }

    /**
     * values中是否存在等于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean eachEq(T defaultValue, T... values) {
        return !isEmpty(values) ? eachEq(defaultValue, Arrays.asList(values)) : false;
    }

    /**
     * values中是否存在大于或等于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean eachGe(T defaultValue, T... values) {
        return !isEmpty(values) ? eachGe(defaultValue, Arrays.asList(values)) : false;
    }

    /**
     * values中是否存在大于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean eachGt(T defaultValue, T... values) {
        return !isEmpty(values) ? eachGt(defaultValue, Arrays.asList(values)) : false;
    }

    /**
     * values中是否存在小于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean eachLt(T defaultValue, Collection<T> values) {
        if (!isEmpty(values)) {
            for (T value : values) {
                if (!isLt(defaultValue, value)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * values中是否存在小于或等于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean eachLe(T defaultValue, Collection<T> values) {
        if (!isEmpty(values)) {
            for (T value : values) {
                if (!isLe(defaultValue, value)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * values中是否存在等于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T> boolean eachEq(T defaultValue, Collection<T> values) {
        if (!isEmpty(values)) {
            for (T value : values) {
                if (!isEq(defaultValue, value)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * values中是否存在等于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean eachEq(T defaultValue, Collection<T> values) {
        if (isEmpty(values)) {
            return false;
        } else {
            for (T value : values) {
                if (!isEq(defaultValue, value)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * values中是否存在大于或等于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean eachGe(T defaultValue, Collection<T> values) {
        if (!isEmpty(values)) {
            for (T value : values) {
                if (!isGe(defaultValue, value)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * values中是否存在大于defaultValue的值
     *
     * @param defaultValue 比较值
     * @param values       多值
     * @param <T>          通用对象
     * @return 判断值
     */
    public static <T extends Comparable<? super T>> boolean eachGt(T defaultValue, Collection<T> values) {
        if (!isEmpty(values)) {
            for (T value : values) {
                if (!isGt(defaultValue, value)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * if isEmpty(values) return false
     *
     * @param values 多值
     * @return 判断值 foreach if(isEmpty(value)) return true
     */
    public static boolean eachEmpty(String... values) {
        return !isEmpty(values) ? eachEmpty(Arrays.asList(values)) : false;
    }

    /**
     * if isEmpty(values) return false
     *
     * @param values 多值
     * @return 判断值 foreach if(isEmpty(value)) return true
     */
    public static boolean eachEmpty(Collection<String> values) {
        if (!isEmpty(values)) {
            for (String value : values) {
                if (!isEmpty(value)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * if isEmpty(values) return false
     *
     * @param values 多值
     * @return 判断值
     */
    public static boolean eachNull(Object... values) {
        if (!isEmpty(values)) {
            return eachNull(Arrays.asList(values));
        } else {
            return false;
        }
    }

    /**
     * if isEmpty(values) return false
     *
     * @param values 多值
     * @return 判断值
     */
    public static boolean eachNull(Collection<Object> values) {
        if (!isEmpty(values)) {
            for (Object value : values) {
                if (value != null) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
