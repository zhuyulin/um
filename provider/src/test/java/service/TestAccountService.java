package service;
import common.AbsSpringTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import um.exception.ServiceException;
import um.service.AccountService;
/**
 * Created by Yuleen on 2017/1/1.
 */
@ContextConfiguration(locations = {"classpath:spring/spring-task5.xml"})
public class TestAccountService extends AbsSpringTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void login() throws ServiceException {
        Integer loginCheck = accountService.login("admin","123456");
        System.out.println(loginCheck);
        Assert.assertEquals(loginCheck.toString(),"1");

    }
    @Test
    public void resetPassword() throws ServiceException {
        accountService.resetPassword("admin","123456","123456");
        System.out.println();
    }
    @Test
    public void register()throws ServiceException {

        try {
            accountService.register("admin123","123456","123456789",
                    "123456@qq.com");
        } catch (ServiceException e) {
            if(!e.getErrorCode().equals("10005")) throw e;
        }
    }

    @Test
    public void batchRegister() throws ServiceException{
        for (int i=1; i<=1000; i++) {
            String username = "testuser";
            username = username.concat(String.valueOf(i));
            accountService.register(username, "123456", "13888888888", "13888888888@qq.com");
        }
    }
}

