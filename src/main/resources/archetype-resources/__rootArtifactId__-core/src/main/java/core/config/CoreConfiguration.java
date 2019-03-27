import javax.annotation.Resource;#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )


package ${package}.core.config;

import ${package}.core.common.InternalEventBus;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import ${package}.client.common.util.EnvUtil;


/**
 * Created by ${userName} on ${today}.
 */
@Configuration
@ComponentScan(basePackages = {
        "${package}.core"
})
public class CoreConfiguration {

    @Bean
    public InternalEventBus internalEventBus() {
        return new InternalEventBus();
    }

    @Bean
    public EnvUtil envUtil(@Value("${env}") Integer env,@Value("${env-name}") String envName) {
        EnvUtil envUtil = new EnvUtil();
        envUtil.setEnv(env);
        envUtil.setEnvName(envName);
        return envUtil;
    }


}
