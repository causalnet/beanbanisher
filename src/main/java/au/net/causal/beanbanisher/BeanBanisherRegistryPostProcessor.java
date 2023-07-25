package au.net.causal.beanbanisher;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.logging.Logger;

public class BeanBanisherRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor
{
    private static final Logger log = Logger.getLogger(BeanBanisherRegistryPostProcessor.class.getName());

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
    throws BeansException
    {
        //Only bean definitions are post-processed
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
    throws BeansException
    {
        try
        {
            BeanDefinition beanDefinition = registry.getBeanDefinition("myBean");
            log.info("Banishing bean: " + beanDefinition);
            registry.removeBeanDefinition("myBean");
        }
        catch (NoSuchBeanDefinitionException e)
        {
            //No such bean
        }

    }
}
