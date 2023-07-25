package au.net.causal.beanbanisher;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

public class BeanBanisherRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor
{
    private static final Logger log = Logger.getLogger(BeanBanisherRegistryPostProcessor.class.getName());

    private final Set<String> banishedBeanNames;
    private final Set<String> banishedBeanClassNames;

    public BeanBanisherRegistryPostProcessor(Collection<String> banishedBeanNames, Collection<String> banishedBeanClassNames)
    {
        this.banishedBeanNames = new LinkedHashSet<>(banishedBeanNames);
        this.banishedBeanClassNames = new LinkedHashSet<>(banishedBeanClassNames);
    }

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
        //Banish beans by name
        for (String beanName : banishedBeanNames)
        {
            try
            {
                BeanDefinition beanDefinition = registry.getBeanDefinition(beanName);
                banishBean(beanName, beanDefinition, registry);
            }
            catch (NoSuchBeanDefinitionException e)
            {
                //No such bean
            }
        }

        //Banish beans by type
        if (!banishedBeanClassNames.isEmpty())
        {
            for (String beanName : registry.getBeanDefinitionNames())
            {
                try
                {
                    BeanDefinition beanDefinition = registry.getBeanDefinition(beanName);
                    Class<?> beanClass = beanDefinition.getResolvableType().getRawClass();
                    if (beanClass != null && banishedBeanClassNames.contains(beanClass.getName()))
                        banishBean(beanName, beanDefinition, registry);
                }
                catch (NoSuchBeanDefinitionException e)
                {
                    //Shouldn't happen since we are using a bean name from the registry, but better safe than sorry
                }

            }
        }
    }

    private void banishBean(String beanName, BeanDefinition beanDefinition, BeanDefinitionRegistry registry)
    {
        log.info("Banishing bean: " + beanDefinition);
        registry.removeBeanDefinition(beanName);
    }
}
