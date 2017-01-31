package um.dataobject;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yuleen on 2017/1/30.
 */
public class GroupDO implements RowMapper, Serializable {
    private Integer groupId;
    private String groupName;
    private Integer parentId;
    private String isDeleted;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        GroupDO groupDO = new GroupDO();
        groupDO.setGroupId(rs.getInt("id"));
        groupDO.setParentId(rs.getInt("parent_id"));
        groupDO.setGroupName(rs.getString("group_name"));
        groupDO.setIsDeleted(rs.getString("is_deleted"));
        return groupDO;
    }
}
