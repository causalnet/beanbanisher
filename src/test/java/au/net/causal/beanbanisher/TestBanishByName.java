package au.net.causal.beanbanisher;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(properties =
        "beanBanisher.beanNames=genericHelloGenerator" //Get rid of generic generator
)
class TestBanishByName extends AbstractBanisherTestCase
{
    @Test
    void test()
    {
        List<String> result = myService.doSomething();
        assertThat(result).containsExactlyInAnyOrder(
                "G'day mate"
        );
    }

}
