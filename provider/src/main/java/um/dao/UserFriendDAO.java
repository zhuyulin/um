package um.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import um.dataobject.UserFriendDO;

import java.util.List;

/**
 * Created by Yuleen on 2017/1/28.
 */
@Repository
public class UserFriendDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 输入用户ID、好友用户ID，检查是否加过好友(包含已经删除好友)。
     */
    public boolean checkFriend(int userId,int targetFriendUserId){
        StringBuilder sql1 = new StringBuilder("select * from user_friend where user_id=? and target_user_id=? ");
        List<UserFriendDO> checkList1 = jdbcTemplate.query(sql1.toString(),new UserFriendDO(),
                new Object[]{userId,targetFriendUserId});

        StringBuilder sql2 = new StringBuilder("select * from user_friend where target_user_id=? and user_id=?");
        List<UserFriendDO> checkList2 = jdbcTemplate.query(sql2.toString(),new UserFriendDO(),
                new Object[]{userId,targetFriendUserId});

        if(checkList1.size()==0 && checkList2.size()==0 ){
            return true;
        }
        return false;
    }


    /**
     * 输入用户ID、好友用户ID，检查好友是否有效。
     */
    public boolean checkFriendisValid(int userId,int targetFriendUserId){
        StringBuilder sql1 = new StringBuilder("select * from user_friend where user_id=? and target_user_id=? and is_deleted='N'");
        List<UserFriendDO> checkList1 = jdbcTemplate.query(sql1.toString(),new UserFriendDO(),
                new Object[]{userId,targetFriendUserId});

        StringBuilder sql2 = new StringBuilder("select * from user_friend where target_user_id=? and user_id=? and is_deleted='N'");
        List<UserFriendDO> checkList2 = jdbcTemplate.query(sql2.toString(),new UserFriendDO(),
                new Object[]{userId,targetFriendUserId});

        if(checkList1.size()==0 && checkList2.size()==0 ){
            return false;
        }
        return true;
    }

    /**
     * 输入用户ID、好友用户ID，检查是否已经被删除好友。
     */
    public boolean checkFriendIsDeleted(int userId,int targetFriendUserId){
        StringBuilder sql1 = new StringBuilder("select * from user_friend where user_id=? and target_user_id=? and is_deleted='Y'");
        List<UserFriendDO> checkList1 = jdbcTemplate.query(sql1.toString(),new UserFriendDO(),
                new Object[]{userId,targetFriendUserId});

        StringBuilder sql2 = new StringBuilder("select * from user_friend where target_user_id=? and user_id=? and is_deleted='Y'");
        List<UserFriendDO> checkList2 = jdbcTemplate.query(sql2.toString(),new UserFriendDO(),
                new Object[]{userId,targetFriendUserId});

        if(checkList1.size()==0 && checkList2.size()==0 ){
            return false;
        }
        return true; //代表之前添加过好友
    }


    /**
     * 输入用户ID、好友用户ID，添加全新好友。
     * 添加好友时产生两行记录
     */
    public UserFriendDO addUserNewFriend(int userId,int targetFriendUserId){
        StringBuilder sql1 = new StringBuilder("insert into user_friend (user_id,target_user_id,add_time) values (?,?,now())");
        StringBuilder sql2 = new StringBuilder("insert into user_friend (target_user_id,user_id,add_time) values (?,?,now())");
        jdbcTemplate.update(sql1.toString(), new Object[]{userId,targetFriendUserId});
        jdbcTemplate.update(sql2.toString(), new Object[]{userId,targetFriendUserId});
        return null;
    }

    /**
     * 输入用户ID、好友用户ID，当两个用户之前添加过好友，并且删除过，通过recoverFriend方法进行操作
     * 添加好友时产生两行记录
     */

    public UserFriendDO recoverFriend(int userId,int targetFriendUserId){
        StringBuilder sql1 = new StringBuilder("update user_friend set is_deleted='N' where target_user_id=? and user_id=? ");
        StringBuilder sql2 = new StringBuilder("update user_friend set is_deleted='N' where user_id=? and target_user_id=?");
        jdbcTemplate.update(sql1.toString(), new Object[]{userId,targetFriendUserId});
        jdbcTemplate.update(sql2.toString(), new Object[]{userId,targetFriendUserId});
        return null;
    }


    /**
     * 输入用户ID,输出该用户的所有好友ID
     */
    public List<UserFriendDO> findByUserId(int userId){
        StringBuilder sql = new StringBuilder("select * from user_friend where user_id=?");
        List<UserFriendDO> list = jdbcTemplate.query(sql.toString(),new UserFriendDO(),
                new Object[]{userId});
        return list;
    }


    /**
     * 输入用户ID、好友用户ID，删除好友。
     */
    public UserFriendDO delUserFriend(int userId,int targetFriendUserId){
        StringBuilder sql1 = new StringBuilder("update user_friend set is_deleted='Y' where target_user_id=? and user_id=? ");
        StringBuilder sql2 = new StringBuilder("update user_friend set is_deleted='Y' where user_id=? and target_user_id=? ");
        jdbcTemplate.update(sql1.toString(), new Object[]{userId,targetFriendUserId});
        jdbcTemplate.update(sql2.toString(), new Object[]{userId,targetFriendUserId});
        return null;
    }

    /**
     * 输入用户ID、好友用户ID、昵称，实现昵称的设置和修改
    */
    public UserFriendDO modifiedNickName(String nickName, int userId,int targetFriendUserId){
        StringBuilder sql = new StringBuilder("update user_friend set nick_name=? where user_id=? and target_user_id=? ");
        jdbcTemplate.update(sql.toString(), new Object[]{nickName,userId,targetFriendUserId});
        return null;
    }
}
