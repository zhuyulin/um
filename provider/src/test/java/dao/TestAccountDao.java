package dao;

import common.AbsSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import um.dao.AccountDAO;

/**
 * Created by Yuleen on 2017/1/8.
 */
@ContextConfiguration(locations = {"classpath:spring/spring-task5.xml"})
public class TestAccountDao extends AbsSpringTest {
    @Autowired
    private AccountDAO accountDAO;

    @Test
    public void test(){
        accountDAO.lastLoginTimeById(1);
    }
}
