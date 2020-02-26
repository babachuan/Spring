import com.qhc.ambiguous.primary.Myfavorite2;
import com.qhc.ambiguous.primary.PrimaryConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PrimaryConfiguration.class)
public class PrimaryTest {
    @Autowired
    private Myfavorite2 myfavorite2;

    @Test
    public void Test(){
        myfavorite2.show();
    }
}

