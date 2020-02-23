import com.qhc.mix.java2java.Java2javaDiscDriver;
import com.qhc.mix.java2java.Java2javaDiscDriverConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Java2javaDiscDriverConfiguration.class)
public class Java2javaTest {
    @Autowired
    private Java2javaDiscDriver java2javaDiscDriver;

    @Test
    public void java2javaTest(){
        java2javaDiscDriver.paly();
    }
}
