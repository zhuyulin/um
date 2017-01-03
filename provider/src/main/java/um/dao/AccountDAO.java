package um.dao;
import java.util.List;

import org.springframework.stereotype.Repository;
import um.dataobject.AccountDO;
import um.vo.AccountVO;

/**
 * Created by Yuleen on 2016/12/31.
 */
@Repository
public class AccountDAO extends BaseDAO  {
    public List<AccountDO> findByUserName(String username){
        StringBuilder sql = new StringBuilder("select * from account where user_name = ?");
        List<AccountDO> findbyusername = getJdbcTemplate().query(sql.toString(),new AccountDO(),
                new Object[]{username});
        return findbyusername;
    }

    public List<AccountDO> findByUserId(Integer id){
        StringBuilder sql = new StringBuilder("select * from account where id = ?");
        List<AccountDO> findbyuserid = getJdbcTemplate().query(sql.toString(),new AccountDO(),
                new Object[]{id});
        return findbyuserid;
    }

    public void updatePasswordById(String newpassword,Integer id){
        StringBuilder sql = new StringBuilder("update account set password=\"?\" where id=?");
        getJdbcTemplate().query(sql.toString(),new AccountDO(),
                new Object[]{newpassword,id});
    }

    public void createNewAccount(String username, String password, String mobile_phone, String email){
        StringBuilder sql = new StringBuilder("insert into account (user_name,password,mobile_phone,emaill) values (?,?,?,?)");
        getJdbcTemplate().query(sql.toString(),new AccountDO(),
                new Object[]{username,password,mobile_phone,email});
    }

}
