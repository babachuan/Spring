import com.qhc.xml.DiscDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-context.xml") //这里这两种写法都是可以的
//@ContextConfiguration("/spring-context.xml")
public class XMLTest {
    @Autowired
    private DiscDriver discDriver;

    @Test
    public void xmlTest(){
        discDriver.paly();
    }

}
