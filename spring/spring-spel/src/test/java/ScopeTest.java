import com.qhc.sp.scope.PrototypeBean;
import com.qhc.sp.scope.ScopeMainConfiguration;
import com.qhc.sp.scope.SingetonBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ScopeMainConfiguration.class)
public class ScopeTest {
    @Autowired
    private SingetonBean singetonBean1;

    @Autowired
    private SingetonBean singetonBean2;

    @Autowired
    private PrototypeBean prototypeBean1;
    @Autowired
    private PrototypeBean prototypeBean2;
    @Test
    public void SingletonTest(){
        System.out.println(singetonBean1==singetonBean2);
    }

    @Test
    public void ProtoTypeTest(){
        System.out.println(prototypeBean1==prototypeBean2);
    }

}
