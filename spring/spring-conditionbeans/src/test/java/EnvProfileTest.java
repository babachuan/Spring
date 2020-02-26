import com.qhc.profile.BeanConfiguration;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EnvProfileTest {
    @Test
    public void envTest() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("prod");
        ctx.register(BeanConfiguration.class);
        ctx.refresh();

    }
}
