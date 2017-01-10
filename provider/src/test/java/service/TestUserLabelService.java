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

    //@Test
    public void addUserLabel() throws ServiceException {
        boolean addLabel = userLabelService.addUserLabel(3,10004);
        System.out.println(addLabel);

    }
    @Test
    public void deleteUserLabel() throws ServiceException {
        boolean deleteLabel = userLabelService.deleteUserLabel(3,10014);
        System.out.println(deleteLabel);

    }

}
