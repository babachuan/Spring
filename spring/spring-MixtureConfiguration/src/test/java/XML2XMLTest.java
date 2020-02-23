import com.qhc.mix.xml2java.XML2javaDiscDriver;
import com.qhc.mix.xml2java.XML2javaDiscDriverConfiguration;
import com.qhc.mix.xml2xml.XML2XMLDiscDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:xml2xmlDiscDriver-spring-context.xml")
public class XML2XMLTest {
    @Autowired
    private XML2XMLDiscDriver xml2XMLDiscDriver;

    @Test
    public void xml2xmlTest(){
        xml2XMLDiscDriver.paly();
    }
}
