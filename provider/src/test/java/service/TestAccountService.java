package service;
import common.AbsSpringTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import um.dataobject.AccountDO;
import um.exception.ServiceException;
import um.service.AccountService;
/**
 * Created by Yuleen on 2017/1/1.
 */
    @ContextConfiguration(locations = {"classpath:spring/spring-task5.xml"})
public class TestAccountService extends AbsSpringTest {
    @Autowired
    private AccountService accountService;

    //@Test
    public void login() throws ServiceException {
        Integer loginCheck = accountService.login("admin","123456");
        System.out.println(loginCheck);
        Assert.assertEquals(loginCheck.toString(),"1");

    }
    @Test
    public void resetPassword() throws ServiceException {
        //todo:debug
        accountService.resetPassword(1,"123456","654321");
        System.out.println("修改的用户名为："+accountService.getAccount(1).getUserName());
       // Assert.assertEquals(accountDO.getPassword().toString(),"654321");
    }
}

