import com.qhc.profile.conditional.ConditionConfiguration;
import com.qhc.profile.conditional.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConditionConfiguration.class)
public class ConditionalTest {
    @Autowired
    private  Person person;

    @Test
    public void test(){
        System.out.println(person);
    }
}
