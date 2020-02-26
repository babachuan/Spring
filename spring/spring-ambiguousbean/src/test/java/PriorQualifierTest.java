import com.qhc.ambiguous.priorqualifier.Myfavorite4;
import com.qhc.ambiguous.priorqualifier.PriorQualifierConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PriorQualifierConfiguration.class)
public class PriorQualifierTest {
    @Autowired
    private Myfavorite4 myfavorite4;

    @Test
    public void Test(){
        myfavorite4.show();
    }
}

