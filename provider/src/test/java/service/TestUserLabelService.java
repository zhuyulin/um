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
        try{
        userLabelService.addUserLabel(3,15);
        } catch (ServiceException e) {
            if(!e.getErrorCode().equals("10008")) throw e;

        }
    }
    @Test
    public void deleteUserLabel() throws ServiceException {
        try{
        userLabelService.deleteUserLabel(3,15);
    } catch (ServiceException e) {
        if(!e.getErrorCode().equals("10010")) throw e;

    }
}

}
