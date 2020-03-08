import com.qhc.cycle.errorbeans.ErrorA;
import com.qhc.cycle.errorbeans.ErrorMainConfiguration;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ErrorMainConfiguration.class)
public class ErrorTest {
    @Autowired
    private ErrorA abean;

    @Test
    public void Test(){
        System.out.println(ToStringBuilder.reflectionToString(abean));
        System.out.println(ToStringBuilder.reflectionToString(abean.getB()));
        System.out.println(ToStringBuilder.reflectionToString(abean.getB().getC()));

        //报错信息：
        //Requested bean is currently in creation: Is there an unresolvable circular reference?
    }
}
