package um.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import um.dataobject.AccountGroupRelaDO;
import um.dataobject.UserFriendDO;

import java.util.List;

/**
 * Created by Yuleen on 2017/1/30.
 */
@Repository
public class AccountGroupRelaDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param userId 用户ID
     * @param groupId 群组ID
     * @return 返回如果是空，则说明该用户并没有关联到该群组。该方法用来检测群组是否添加成功
     */
    public List<AccountGroupRelaDO> checkAccountGroupRela(int userId,int groupId){
        StringBuilder sql = new StringBuilder("select * from account_group_rela where user_id=? and group_id=? ");
        List<AccountGroupRelaDO> checkList = jdbcTemplate.query(sql.toString(),new AccountGroupRelaDO(),
                new Object[]{userId,groupId});
        return checkList;
    }


    /**
     *
     * @param userId 用户ID
     * @return 返回如果是空，则说明该用户并没有关联到该群组。该方法用来检测群组是否添加成功
     */
    public AccountGroupRelaDO getAccountGroupRela(int userId){
        StringBuilder sql = new StringBuilder("select * from account_group_rela where user_id=?");
        List<AccountGroupRelaDO> checkList = jdbcTemplate.query(sql.toString(),new AccountGroupRelaDO(),
                new Object[]{userId});
        return checkList.get(0);
    }

    /**
     *
     * @param userId 用户ID
     * @param groupId 群组ID
     * @return 返回群组ID
     */
    public int modifiedAccountGroupRela(int userId,int groupId){
        StringBuilder sql = new StringBuilder("update account_group_rela set group_id=? where user_id=?");
        jdbcTemplate.update(sql.toString(), new Object[]{groupId,userId});
        return groupId;
    }



}
