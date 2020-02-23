import com.qhc.mix.java2java.Java2javaDiscDriverConfiguration;
import com.qhc.mix.java2xml.Java2xmlDiscDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:java2xml-spring-context.txt.xml")
public class Java2xmlTest {
    @Autowired
    private Java2xmlDiscDriver java2xmlDiscDriver;

    @Test
    public void java2xmlTest(){
        java2xmlDiscDriver.paly();
    }
}
