#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.common.util;

public class EnvUtil {
    public static int ENV_DEV = 0;
    public static int ENV_DAILY = 1;
    public static int ENV_PREF = 2;
    public static int ENV_ONLINE = 3;
    public static int ENV_SANDBOX = 4;
    private static int current_env = 3;
    private static String current_env_name = "";

    public EnvUtil() {
    }

    public void setEnv(int env) {
        current_env = env;
    }

    public void setEnvName(String envName) {
        current_env_name = envName;
    }

    public static int getCurrentEnv() {
        return current_env;
    }

    public static String getCurrentEnvName() {
        return current_env_name;
    }

    public static boolean isDev() {
        return ENV_DEV == current_env;
    }

    public static boolean isDaily() {
        return ENV_DAILY == current_env;
    }

    public static boolean isPre() {
        return ENV_PREF == current_env;
    }

    public static boolean isOnline() {
        return ENV_ONLINE == current_env;
    }

    public static boolean isSandbox() {
        return ENV_SANDBOX == current_env;
    }
}