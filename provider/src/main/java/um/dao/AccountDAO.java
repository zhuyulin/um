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
        List<AccountDO> findByUserName = getJdbcTemplate().query(sql.toString(),new AccountDO(),
                new Object[]{username});
        return findByUserName;
    }

    public List<AccountDO> findByUserId(Integer id){
        StringBuilder sql = new StringBuilder("select * from account where id = ?");
        List<AccountDO> findByUserId = getJdbcTemplate().query(sql.toString(),new AccountDO(),
                new Object[]{id});
        return findByUserId;
    }

    public void updatePasswordById(String newPassword,Integer id){
        StringBuilder sql = new StringBuilder("update account set password=? where id=?");
        getJdbcTemplate().update(sql.toString(),new AccountDO(),
                new Object[]{newPassword,id});
    }

    public void createNewAccount(String userName, String password, String mobilePhone, String email){
        StringBuilder sql = new StringBuilder("insert into account (user_name,password,mobile_phone,emaill) values (?,?,?,?)");
        getJdbcTemplate().update(sql.toString(),new AccountDO(),
                new Object[]{userName,password,mobilePhone,email});
    }

}
