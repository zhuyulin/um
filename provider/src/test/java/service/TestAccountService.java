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
        Integer registerCheck = null;
        try {
            registerCheck = accountService.register("zhuyulin","123456","123456789",
                    "123456@qq.com");
        } catch (ServiceException e) {
            if(!e.getErrorCode().equals("10006")) throw e;
        }
        System.out.println(registerCheck);
    }
}

