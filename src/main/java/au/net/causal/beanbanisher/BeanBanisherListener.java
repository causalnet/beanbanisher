package au.net.causal.beanbanisher;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;
import java.util.List;

public class BeanBanisherListener implements SpringApplicationRunListener
{
    @Override
    public void contextPrepared(ConfigurableApplicationContext context)
    {
        ConfigurableEnvironment env = context.getEnvironment();
        List<String> beanNames = Arrays.asList(env.getProperty("beanbanisher.beanNames", String[].class, new String[0]));
        List<String> beanClassNames = Arrays.asList(env.getProperty("beanbanisher.beanClassNames", String[].class, new String[0]));

        ((BeanDefinitionRegistry)context).registerBeanDefinition("beanbanisher", BeanDefinitionBuilder.genericBeanDefinition(BeanBanisherRegistryPostProcessor.class,
             () -> new BeanBanisherRegistryPostProcessor(beanNames, beanClassNames)).getBeanDefinition());
    }
}
