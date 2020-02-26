import com.qhc.ambiguous.primary.PrimaryConfiguration;
import com.qhc.ambiguous.qualifier.Myfavorite3;
import com.qhc.ambiguous.qualifier.QualifierConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = QualifierConfiguration.class)
public class QualifierTest {
    @Autowired
    private Myfavorite3 myfavorite3;

    @Test
    public void Test(){
        myfavorite3.show();
    }
}

