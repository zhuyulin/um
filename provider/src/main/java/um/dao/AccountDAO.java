package um.dao;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import um.dataobject.AccountDO;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Yuleen on 2016/12/31.
 */
@Repository
public class AccountDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AccountDO findByUserName(String username){
        StringBuilder sql = new StringBuilder("select * from account where user_name = ?");
        List<AccountDO> findByUserName = jdbcTemplate.query(sql.toString(),new AccountDO(),
                new Object[]{username});
        if(findByUserName.size() >= 1)
            return findByUserName.get(0);
        return null;
    }

    public List<AccountDO> findByUserId(Integer id){
        StringBuilder sql = new StringBuilder("select * from account where id = ?");
        List<AccountDO> findByUserId = jdbcTemplate.query(sql.toString(),new AccountDO(),
                new Object[]{id});
        return findByUserId;
    }

    public void updatePasswordById(String newPassword,Integer id){
        StringBuilder sql = new StringBuilder("update account set password=? where id=?");
        jdbcTemplate.update(sql.toString(),new AccountDO(),
                new Object[]{newPassword,id});
    }


    public void updatePasswordByUserName(String newPassword,String userName){
        StringBuilder sql = new StringBuilder("update account set password=? where user_name=?");
        jdbcTemplate.update(sql.toString(),
                new Object[]{newPassword,userName});
    }

    public void createNewAccount(String userName, String password, String mobilePhone, String email){
        StringBuilder sql = new StringBuilder("insert into account (user_name,password,mobile_phone,email) values (?,?,?,?)");
        jdbcTemplate.update(sql.toString(),
                new Object[]{userName,password,mobilePhone,email});
    }

    public void  gmtCreateById(Integer id){
        StringBuilder sql = new StringBuilder("update account set gmt_create=now() where id=?");
        jdbcTemplate.update(sql.toString(),
                new Object[]{id});
    }

    public void  lastLoginTimeById(Integer id){
        StringBuilder sql = new StringBuilder("update account set last_login_time=now() where id=?");
        jdbcTemplate.update(sql.toString(),
                new Object[]{id});
    }

}
