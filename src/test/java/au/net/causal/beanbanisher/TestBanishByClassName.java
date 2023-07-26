package au.net.causal.beanbanisher;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(properties =
        "beanBanisher.beanClassNames=au.net.causal.beanbanisher.AustralianHelloGenerator" //Get rid of aussie generator
)
class TestBanishByClassName extends AbstractBanisherTestCase
{
    @Test
    void test()
    {
        List<String> result = myService.doSomething();
        assertThat(result).containsExactlyInAnyOrder(
                "Hello"
        );
    }

}
