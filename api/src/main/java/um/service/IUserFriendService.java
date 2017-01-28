package um.service;

import um.exception.ServiceException;
import um.vo.UserFriendVO;

import java.util.List;

/**
 * Created by Yuleen on 2017/1/28.
 */
public interface IUserFriendService {
    /**
     *
     * @param userId 用户ID
     * @param targetUserId  被添加的用户ID
     * @return 返回true代表添加成功，返回false代表添加失败
     */
    boolean addUserFriend(int userId,int targetUserId) throws ServiceException;

    /**
     *
     * @param userId 用户ID
     * @param targetUserId  被删除好友的用户ID
     * @return 返回true代表添加成功，返回false代表添加失败
     */
    boolean delUserFriend(int userId,int targetUserId) throws ServiceException;

    /**
     *
     * @param userId 用户ID
     * @param targetUserId  被添加的用户ID
     * @param nickName  备注名
     * @return 返回true代表添加成功，返回false代表添加失败
     */
    void modifiedNickName(int userId,int targetUserId,String nickName) throws ServiceException;

    public List<UserFriendVO> getFriend(int userId) throws ServiceException;
}
