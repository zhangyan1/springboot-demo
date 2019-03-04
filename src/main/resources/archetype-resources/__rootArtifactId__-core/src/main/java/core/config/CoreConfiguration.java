#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )


package ${package}.core.config;

import ${package}.core.common.InternalEventBus;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;


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


}
