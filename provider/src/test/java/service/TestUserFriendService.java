package service;

import common.AbsSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import um.exception.ServiceException;
import um.service.UserFriendService;

/**
 * Created by Yuleen on 2017/1/28.
 */

@ContextConfiguration(locations = {"classpath:spring/spring-task5.xml"})
public class TestUserFriendService extends AbsSpringTest {
    @Autowired
    private UserFriendService userFriendService;

    @Test
    public void addUserFriend() throws ServiceException {
        userFriendService.addUserFriend(1, 4);
    }

    @Test
    public void delUserFriend() throws ServiceException{
        userFriendService.delUserFriend(1,3);
    }

    @Test
    public void modifiedNickName()throws ServiceException{
        userFriendService.modifiedNickName(1,4,"nickname");
    }
}