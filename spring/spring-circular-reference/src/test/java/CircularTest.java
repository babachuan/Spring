import com.qhc.cycle.beans.A;
import com.qhc.cycle.config.MainConfiguration;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainConfiguration.class)
public class CircularTest {
    @Autowired
    private A abean;

    @Test
    public void Test(){
        System.out.println(ToStringBuilder.reflectionToString(abean));
        System.out.println(ToStringBuilder.reflectionToString(abean.getB()));
        System.out.println(ToStringBuilder.reflectionToString(abean.getB().getC()));

    }
}
