package um.dao;
import java.util.List;

import org.springframework.stereotype.Repository;
import um.dataobject.AccountDO;
import java.util.Date;
import java.text.SimpleDateFormat;

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


    public void updatePasswordByUserName(String newPassword,String userName){
        StringBuilder sql = new StringBuilder("update account set password=? where user_name=?");
        getJdbcTemplate().update(sql.toString(),
                new Object[]{newPassword,userName});
    }

    public void createNewAccount(String userName, String password, String mobilePhone, String email){
        StringBuilder sql = new StringBuilder("insert into account (user_name,password,mobile_phone,email) values (?,?,?,?)");
        getJdbcTemplate().update(sql.toString(),
                new Object[]{userName,password,mobilePhone,email});
    }

    public void  gmtCreateById(Integer id){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = dateFormat.format(now);
        StringBuilder sql = new StringBuilder("update account set gmt_create=? where id=?");
        getJdbcTemplate().update(sql.toString(),
                new Object[]{time,id});
    }

    public void  lastLoginTimeById(Integer id){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = dateFormat.format(now);
        StringBuilder sql = new StringBuilder("update account set last_login_time=? where id=?");
        getJdbcTemplate().update(sql.toString(),
                new Object[]{time,id});
    }

}
