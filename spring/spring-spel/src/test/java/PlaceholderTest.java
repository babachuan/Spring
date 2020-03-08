import com.qhc.sp.placeholder.Person;
import com.qhc.sp.placeholder.PlaceHolderConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PlaceHolderConfiguration.class)
public class PlaceholderTest {
    @Autowired
    private Person person;

    @Test
    public void PlaceholerTest(){
        System.out.println(person);
    }

}
