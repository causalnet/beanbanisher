package au.net.causal.beanbanisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
abstract class AbstractBanisherTestCase
{
    @Autowired
    protected MyService myService;

    @TestConfiguration
    @SpringBootApplication
    static class TestConfig
    {
        @Bean
        public MyService myService(List<GreetingGenerator> greetingGenerators)
        {
            return new MyService(greetingGenerators);
        }

        @Bean
        public GreetingGenerator genericHelloGenerator()
        {
            return () -> "Hello";
        }

        @Bean
        public AustralianHelloGenerator australianHelloGenerator()
        {
            return new AustralianHelloGenerator();
        }
    }

    static class MyService
    {
        private final List<GreetingGenerator> greetingGenerators;

        public MyService(List<GreetingGenerator> greetingGenerators)
        {
            this.greetingGenerators = new ArrayList<>(greetingGenerators);
        }

        public List<String> doSomething()
        {
            return greetingGenerators.stream()
                                     .map(GreetingGenerator::generateGreeting)
                                     .collect(Collectors.toList());
        }
    }

}
