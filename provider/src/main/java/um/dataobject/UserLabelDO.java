package um.dataobject;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yuleen on 2017-01-10.
 */
public class UserLabelDO  implements RowMapper, Serializable {
    private int userId;
    private int labelId;
    private String labelName;
    private String isDeleted;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        UserLabelDO userLabelDO = new UserLabelDO();
        userLabelDO.setUserId(rs.getInt("user_id"));
        userLabelDO.setLabelId(rs.getInt("label_id"));
        userLabelDO.setLabelName(rs.getString("label_name"));
        userLabelDO.setIsDeleted(rs.getString("is_deleted"));
        return null;
    }
}
