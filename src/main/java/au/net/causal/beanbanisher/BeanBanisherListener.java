package au.net.causal.beanbanisher;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;

public class BeanBanisherListener implements SpringApplicationRunListener
{
    @Override
    public void contextPrepared(ConfigurableApplicationContext context)
    {
        ((BeanDefinitionRegistry)context).registerBeanDefinition("beanbanisher", BeanDefinitionBuilder.genericBeanDefinition(BeanBanisherRegistryPostProcessor.class).getBeanDefinition());
    }
}
