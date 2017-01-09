package service;
import common.AbsSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import um.exception.ServiceException;
import um.service.UserLabelService;

/**
 * Created by Yuleen on 2017-01-10.
 */
@ContextConfiguration(locations = {"classpath:spring/spring-task5.xml"})
public class TestUserLabelService extends AbsSpringTest {
    @Autowired
    private UserLabelService userLabelService;

    @Test
    public void addUserLabel() throws ServiceException {
        boolean addLabel = userLabelService.addUserLabel(1,10000);
        System.out.println(addLabel);

    }
}
