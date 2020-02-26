import com.qhc.profile.BeanConfiguration;
import com.qhc.profile.ShowInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanConfiguration.class)
@ActiveProfiles("dev")
public class ProfileTest {
    @Autowired
    private ShowInterface showInterface;
    @Test
    public void Test1(){
        showInterface.show();
    }
}
