package au.net.causal.beanbanisher;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;
import java.util.List;

public class BeanBanisherListener implements SpringApplicationRunListener
{
    public BeanBanisherListener(SpringApplication application, String[] args)
    {
        this();
    }

    public BeanBanisherListener()
    {
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context)
    {
        ConfigurableEnvironment env = context.getEnvironment();
        List<String> beanNames = Arrays.asList(env.getProperty("beanBanisher.beanNames", String[].class, new String[0]));
        List<String> beanClassNames = Arrays.asList(env.getProperty("beanBanisher.beanClassNames", String[].class, new String[0]));

        ((BeanDefinitionRegistry)context).registerBeanDefinition("beanBanisher", BeanDefinitionBuilder.genericBeanDefinition(BeanBanisherRegistryPostProcessor.class,
             () -> new BeanBanisherRegistryPostProcessor(beanNames, beanClassNames)).getBeanDefinition());
    }
}
