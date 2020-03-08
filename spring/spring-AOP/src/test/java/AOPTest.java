import com.qhc.aop.Show;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = com.qhc.aop.AOPConfiguration.class)
public class AOPTest {
    @Autowired
    private Show show;

    @Test
    public void test1(){
        show.show();
    }
}
