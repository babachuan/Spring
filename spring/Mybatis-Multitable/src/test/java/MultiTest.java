import com.qhc.multi.beans.Book;
import com.qhc.multi.dao.BookDao;
import org.junit.Test;

public class MultiTest {
    @Test
    public void test1(){
        BookDao bookDao = new BookDao();
        Book book = bookDao.getBookAuthor("《茶花女》");
        System.out.println("查询到的结果是："+book);
    }
}
