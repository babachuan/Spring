import com.qhc.cdplayer.DiscDriver;
import com.qhc.cdplayer.DiscDriverConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DiscDriverConfig.class)
public class DiscDriverTest {
    @Autowired
    private DiscDriver discDriver;

    @Test
    public void Test(){
        discDriver.paly();
    }
}
