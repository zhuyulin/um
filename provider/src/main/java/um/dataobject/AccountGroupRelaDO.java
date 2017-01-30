package um.dataobject;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by Yuleen on 2017/1/30.
 */
public class AccountGroupRelaDO implements RowMapper, Serializable {
    private int userId;
    private int groupId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountGroupRelaDO accountGroupRelaDO = new AccountGroupRelaDO();
        accountGroupRelaDO.setUserId(rs.getInt("user_id"));
        accountGroupRelaDO.setGroupId(rs.getInt("group_id"));
        return accountGroupRelaDO;
    }
}
