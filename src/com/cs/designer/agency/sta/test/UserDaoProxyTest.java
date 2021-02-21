import com.cs.designer.agency.UserDao;
import com.cs.designer.agency.sta.UserDaoProxy;
import org.junit.Test;

public class UserDaoProxyTest {
    @Test
    public void test(){
        UserDaoProxy userDaoProxy = new UserDaoProxy(new UserDao());
        userDaoProxy.save();
    }
}