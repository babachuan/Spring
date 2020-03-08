import com.qhc.sp.spel.Inventor;
import com.qhc.sp.spel.Spel1;
import com.qhc.sp.spel.SpelMainConfiguration;
import com.qhc.sp.spel.SpelSimple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.GregorianCalendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpelMainConfiguration.class)
public class Spel1Test {
    @Autowired
    private Spel1 spel1;
    @Test
    public void Spel1Test(){
        spel1.test();
//        ExpressionParser parser = new SpelExpressionParser();
//        Expression exp = parser.parseExpression("'Hello world'");
//        String message = (String) exp.getValue();
//        System.out.println(message);
    }

    @Test
    public void Spel1Test2(){
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello world'.bytes");
        byte[] bytes = (byte[]) exp.getValue();
        System.out.println(bytes);
    }

    @Test
    public void SpelTest3(){
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'.bytes.length");
        int length = (int) exp.getValue();
        System.out.println(length);
    }

    @Test
    public void SpelTest4(){
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("new String('hello world').toUpperCase()");
        String message = exp.getValue(String.class);
        System.out.println(message);
    }

    @Test
    public void SpelTest5(){
        GregorianCalendar c = new GregorianCalendar();
        c.set(1987,7,12);

        Inventor tesla = new Inventor("Nikola Tesla",c.getTime(),"German");
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("name");
        String name = (String) exp.getValue(tesla);
        System.out.println(name);

        exp = parser.parseExpression("name == 'Nikola Tesla'");
        boolean result = exp.getValue(tesla,Boolean.class);
        System.out.println(result);
    }

    @Test
    public void SimpleTest(){
        SpelSimple simple = new SpelSimple();
        simple.booleanList.add(true);

        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        ExpressionParser parser = new SpelExpressionParser();
        parser.parseExpression("booleanList[0]").setValue(context,simple,"false");
        Boolean b = simple.booleanList.get(0);
        System.out.println(b);
    }
}
