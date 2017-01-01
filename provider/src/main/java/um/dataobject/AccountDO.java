package um.dataobject;

import com.sun.xml.internal.rngom.parse.host.Base;
import org.springframework.jdbc.core.RowMapper;
import um.emum.AccountStationEmum;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
    private AccountStationEmum station;

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

    public AccountStationEmum getStation() {
        return station;
    }

    public void setStation(AccountStationEmum station) {
        this.station = station;
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
        accountDO.setStation(AccountStationEmum.valueOf(rs.getString("station")));
        return accountDO;

    }
}