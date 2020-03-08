import com.qhc.proxy.cglibdynamic.CglibBoxes;
import com.qhc.proxy.cglibdynamic.CglibHandler;
import com.qhc.proxy.cglibdynamic.CglibMainConfiguration;
import com.qhc.proxy.jdkdynamic.Boxes;
import com.qhc.proxy.jdkdynamic.BuyFromWorld;
import com.qhc.proxy.jdkdynamic.JDKHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CglibMainConfiguration.class)
public class CglibProxyTest {
    @Autowired
    public CglibHandler cglibHandler;

    @Autowired
    @Qualifier("cglibboxes")
    public CglibBoxes cglibBoxes;

    @Test
    public void test(){
        CglibBoxes boxes= (CglibBoxes) cglibHandler.getCglibBuyer(cglibBoxes);
        boxes.cglibbuyFromEngland();
        System.out.println("--------");
        boxes.cglibbuyFromUAS();
    }
}
