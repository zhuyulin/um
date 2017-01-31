package service;

import common.AbsSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import um.exception.ServiceException;
import um.service.AccountGroupRelaService;

/**
 * Created by Yuleen on 2017/2/1.
 */
@ContextConfiguration(locations = {"classpath:spring/spring-task5.xml"})
public class TestAccountGroupRelaService extends AbsSpringTest {
    @Autowired
    AccountGroupRelaService accountGroupRelaService;

    @Test
    public void modifiedAccountGroup() throws ServiceException {
        accountGroupRelaService.modifiedAccountGroup(11,3);
    }

}
