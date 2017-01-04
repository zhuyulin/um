package um.dataobject;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import um.enums.AccountStateEnum;

/**
 * Created by Yuleen on 2016/12/31.
 */
public class AccountDO extends BaseDO {
    private String userName;
    private String password;
    private String nickName;
    private String mobilePhone;
    private String email;
    private Date lastLoginTime;
    private String state;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountDO accountDO = new AccountDO();
        accountDO.setId(rs.getInt("id"));
        accountDO.setGmtCreate(rs.getDate("gmt_create"));
        accountDO.setGmtModified(rs.getDate("gmt_modified"));
        accountDO.setUserName(rs.getString("user_name"));
        accountDO.setPassword(rs.getString("password"));
        accountDO.setNickName(rs.getString("nick_name"));
        accountDO.setMobilePhone(rs.getString("mobile_phone"));
        accountDO.setEmail(rs.getString("mobile_phone"));
        accountDO.setLastLoginTime(rs.getDate("last_login_time"));
        accountDO.setState((rs.getString("state")));
        return accountDO;

    }
}