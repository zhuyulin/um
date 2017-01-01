package service;
import common.AbsSpringTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import um.dao.AccountDAO;
import um.exception.ServiceException;
import um.service.AccountService;

import java.util.List;

/**
 * Created by Yuleen on 2017/1/1.
 */
    @ContextConfiguration(locations = {"classpath:spring/spring-task5.xml"})
public class TestAccountDAO extends AbsSpringTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void accountServiceTest() throws ServiceException {
        Integer logincheck = accountService.login("admin","123456");
        System.out.println(logincheck);
        Assert.assertTrue(logincheck.equals(1));

    }
}

