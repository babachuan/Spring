import com.qhc.proxy.jdkdynamic.Boxes;
import com.qhc.proxy.jdkdynamic.BuyFromWorld;
import com.qhc.proxy.jdkdynamic.JDKHandler;
import com.qhc.proxy.jdkdynamic.JKDMainConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JKDMainConfiguration.class)
public class JDKProxyTest {
    @Autowired
    public JDKHandler jdkHandler;

    @Autowired
    @Qualifier("boxes")
    public Boxes boxes;

    @Test
    public void test(){
        BuyFromWorld buyFromWorld = (BuyFromWorld) jdkHandler.getBuyer(boxes);
        buyFromWorld.buyFromEngland();
        System.out.println("--------");
        buyFromWorld.buyFromUAS();
    }
}
