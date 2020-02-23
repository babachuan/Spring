import com.qhc.mix.xml2java.XML2javaDiscDriver;
import com.qhc.mix.xml2java.XML2javaDiscDriverConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XML2javaDiscDriverConfiguration.class)
public class XML2javaTest {
    @Autowired
    private XML2javaDiscDriver xml2javaDiscDriver;

    @Test
    public void xml2javaTest(){
        xml2javaDiscDriver.paly();
    }
}
