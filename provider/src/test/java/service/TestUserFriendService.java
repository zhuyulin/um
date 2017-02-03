package service;

import common.AbsSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import um.dataobject.UserFriendDO;
import um.exception.ServiceException;
import um.service.UserFriendService;
import um.vo.UserFriendVO;

import java.util.List;

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


    @Test
    public void batchAddUserFriend() throws ServiceException {
        for (int i=1; i< 100 ; i++) {
            for (int j=1; j<100; j++) {
                userFriendService.addUserFriend(i, j);
            }
        }
    }


    @Test
    public void getFriend() throws ServiceException{
        List<UserFriendVO> friendList = userFriendService.getFriend(2);
        for (int i = 0; i< friendList.size(); i++){
            System.out.println(friendList.get(i).getTargetUserId());
        }
    }
}