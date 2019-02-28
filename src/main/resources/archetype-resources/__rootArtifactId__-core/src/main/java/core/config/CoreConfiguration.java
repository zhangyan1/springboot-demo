#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )


package ${package}.core.configuration;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.Map;

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
