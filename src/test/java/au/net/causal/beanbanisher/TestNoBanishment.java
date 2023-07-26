package au.net.causal.beanbanisher;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class TestNoBanishment extends AbstractBanisherTestCase
{
    @Test
    void test()
    {
        List<String> result = myService.doSomething();
        assertThat(result).containsExactlyInAnyOrder(
                "Hello",
                "G'day mate"
        );
    }

}
