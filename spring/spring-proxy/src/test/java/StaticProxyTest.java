import com.qhc.proxy.staticproxy.Box;
import com.qhc.proxy.staticproxy.BoxProxy;
import com.qhc.proxy.staticproxy.MainConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainConfiguration.class)
public class StaticProxyTest {
    @Autowired
    @Qualifier("box")
    private Box box;

    @Test
    public void test(){
        BoxProxy boxProxy = new BoxProxy(box);
        boxProxy.buybox();
    }
}
