package um.dataobject;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yuleen on 2017/1/28.
 */
public class UserFriendDO implements RowMapper, Serializable {
    private int userId;
    private int targetUserId;
    private String addTime;
    private String isDeleted;
    private String nickName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(int targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        UserFriendDO userFriendDO = new UserFriendDO();
        userFriendDO.setUserId(rs.getInt("user_id"));
        userFriendDO.setTargetUserId(rs.getInt("target_user_id"));
        userFriendDO.setAddTime(rs.getString("add_time"));
        userFriendDO.setIsDeleted(rs.getString("is_deleted"));
        userFriendDO.setIsDeleted(rs.getString("nick_name"));
        return userFriendDO;
    }
}
