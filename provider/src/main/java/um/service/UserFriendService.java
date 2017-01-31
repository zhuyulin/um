package um.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.dao.UserFriendDAO;
import um.dataobject.UserFriendDO;
import um.exception.ServiceException;
import um.vo.UserFriendVO;

import java.util.List;

/**
 * Created by Yuleen on 2017/1/28.
 */
@Service
public class UserFriendService implements IUserFriendService {
    @Autowired
    private UserFriendDAO userFriendDAO;


    @Override
    public boolean addUserFriend(int userId, int targetUserId) throws ServiceException {
        if (userId == 0 || targetUserId == 0) {
            throw new ServiceException("用户ID或者需要添加的好友ID为空", "10011");
        }
        //检查之前添加过好友，但是好友被删除。
        boolean checkFriendIsDeleted = userFriendDAO.checkFriendIsDeleted(userId,targetUserId);
        //检查之前从未添加过好友。
        boolean checkFriend = userFriendDAO.checkFriend(userId,targetUserId);

        if (checkFriendIsDeleted == true){

            userFriendDAO.recoverFriend(userId,targetUserId);
            System.out.println("好友添加成功（恢复好友）");
            return true;

        }

        if (checkFriend == true){
            userFriendDAO.addUserNewFriend(userId,targetUserId);
            System.out.println("好友添加成功（全新添加）");
            return true;
        }
        return false;
    }

    @Override
    public boolean delUserFriend(int userId, int targetUserId) throws ServiceException {
        if (userId == 0 || targetUserId == 0) {
            throw new ServiceException("用户ID或者需要添加的好友ID为空", "10011");
        }
        boolean list = userFriendDAO.checkFriend(userId, targetUserId);
        if (list == false)
        {
            userFriendDAO.delUserFriend(userId,targetUserId);
            System.out.println("好友解除成功");
            return true;
        }
        System.out.println("未找到该好友");
        return false;
    }

    @Override
    public void modifiedNickName(int userId, int targetUserId, String nickName) throws ServiceException {
        if (userId == 0 || targetUserId == 0) {
            throw new ServiceException("用户ID或者需要添加的好友ID为空", "10011");
        }
        boolean list = userFriendDAO.checkFriendisValid(userId, targetUserId);
        if (list == true)
        {
            userFriendDAO.modifiedNickName(nickName,userId,targetUserId);
            System.out.println("备注已经修改为:"+nickName);
        }
        else {
            throw new ServiceException("没有添加好友", "10012");
        }
    }

    @Override
    public List<UserFriendVO> getFriend(int userId) throws ServiceException {

        List<UserFriendDO> userFriendDO = userFriendDAO.findByUserId(userId);
        List<UserFriendVO> userFriendVO = (List<UserFriendVO>) new UserFriendVO();

        for (int i=0; i< userFriendDO.size();i++){
            userFriendVO.get(i).setAddTime(userFriendDO.get(i).getAddTime());
            userFriendVO.get(i).setIsDeleted(userFriendDO.get(i).getIsDeleted());
            userFriendVO.get(i).setNickName(userFriendDO.get(i).getNickName());
            userFriendVO.get(i).setTargetUserId(userFriendDO.get(i).getTargetUserId());
            userFriendVO.get(i).setTargetUserId(userFriendDO.get(i).getTargetUserId());
            userFriendVO.get(i).setUserId(userFriendDO.get(i).getUserId());
        }
        return userFriendVO;
    }
}
