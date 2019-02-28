#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.core.configuration;



import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.Ordered;


/**
 * Created by ${userName} on ${today}.
 */

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopConfiguration implements BeanDefinitionRegistryPostProcessor {


    @Bean
    public PerformanceOuter performanceOuter() {
        return new PerformanceOuter();
    }

    /**
     * 性能监控内部方法调用的 AOP
     *
     * @return com.shinemo.client.aop.performance.PerformanceOuter
     * @author Harold Luo
     * @date 2018-08-16
     **/
    @Bean
    public PerformanceInner performanceInner() {
        return new PerformanceInner();
    }

    /**
     * 打印出入参的 AOP
     *
     * @return com.shinemo.client.aop.log.PrintParamResult
     * @author Harold Luo
     * @date 2018-08-16
     **/
    @Bean
    public PrintParamResult printParamResult() {
        return new PrintParamResult();
    }


    @Bean
    public FacadeExceptionAop facadeExceptionAop() {
        FacadeExceptionAop facadeExceptionAop = new FacadeExceptionAop();
        facadeExceptionAop.setError(OrderCenterErrors.SERVICE_ERROR);
        facadeExceptionAop.setParamError(OrderCenterErrors.PARAM_ERROR);
        return facadeExceptionAop;
    }


    @Bean
    public Pointcut applicationFacadePointcut() {
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression("execution(* (${package}.*.client..facade.*FacadeService+).*(..))");
        return aspectJExpressionPointcut;
    }


    @Bean
    @DependsOn({"performanceOuter", "applicationFacadePointcut"})
    public Advisor performanceOuterAdvisor(@Qualifier("performanceOuter") PerformanceOuter performanceOuter,
                                           @Qualifier("applicationFacadePointcut") Pointcut pointcut) {
        DefaultBeanFactoryPointcutAdvisor advisor = new DefaultBeanFactoryPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(performanceOuter);
        // advisor.setOrder(1000);
        return advisor;
    }


    @Bean
    @DependsOn({"performanceInner"})
    public Advisor performanceInnerAdvisor(@Qualifier("performanceInner") PerformanceInner performanceInner) {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* ${package}.*.client.core..*.*(..))");
        advisor.setAdvice(performanceInner);
        // advisor.setOrder(1000);
        return advisor;
    }


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        SpringAopUtils.registerAround(
                "around",
                "printParamResult",
                Ordered.HIGHEST_PRECEDENCE,
                "applicationFacadePointcut",
                registry);
        SpringAopUtils.registerAround(
                "around",
                "facadeExceptionAop",
                Ordered.HIGHEST_PRECEDENCE,
                "applicationFacadePointcut",
                registry);
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

}
