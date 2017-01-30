package um.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import um.dataobject.GroupDO;

import java.util.List;

/**
 * Created by Yuleen on 2017/1/30.
 */
@Repository
public class GroupDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 得到群组的所有数据
     * @return 返回子群组list
     */
    public List<GroupDO> getGroupAll(){
        StringBuilder sql = new StringBuilder("select * from group where is_deleted='N'");
        List<GroupDO> checkList = jdbcTemplate.query(sql.toString(),new GroupDO(),
                new Object[]{null});
        return checkList;
    }

    /**
     *
     * @param parentId  查询某个父群组下的所有子群组
     * @return 返回子群组list
     */
    public List<GroupDO> getGroupByParentId(int parentId){
        StringBuilder sql = new StringBuilder("select * from group where parent_id=? and is_deleted='N'");
        List<GroupDO> checkList = jdbcTemplate.query(sql.toString(),new GroupDO(),
                new Object[]{parentId});
        return checkList;
    }


    /**
     *
     * @param groupId  查询某个群组是否存在
     * @return 返回子群组list
     */
    public List<GroupDO> getGroupByGroupId(int groupId){
        StringBuilder sql = new StringBuilder("select * from group where id=? and is_deleted='N'");
        List<GroupDO> checkList = jdbcTemplate.query(sql.toString(),new GroupDO(),
                new Object[]{groupId});
        return checkList;
    }


    /**
     *
     * @param groupName  通过群组名称查找群组ID
     * @return 返回该群组的id
     */
    public List<GroupDO> getGroupIdByGroupName(String groupName){
        StringBuilder sql = new StringBuilder("select * from group where group_name=?  and is_deleted='N'");
        List<GroupDO> checkList = jdbcTemplate.query(sql.toString(),new GroupDO(),
                new Object[]{groupName});
        return checkList;
    }

    /**
     *
     * @param groupName 群组名称
     * @param parentId 该群组的父群组ID
     */
    public void addGroup(String groupName,int parentId){
        StringBuilder sql = new StringBuilder("insert into group (group_name,parent_id) values (?,?)");
        jdbcTemplate.update(sql.toString(),
                new Object[]{groupName,parentId});
    }

    /**
     *
     * @param groupId 群组ID
     */
    public void delGroup(int groupId){
        StringBuilder sql= new StringBuilder("update group set is_deleted='Y' where id=?");
        jdbcTemplate.update(sql.toString(),
                new Object[]{groupId});
    }

    /**
     *
     * @param groupId 群组ID
     * @param newParentId 修改后的父群组ID
     */
    public void modifiedParentIdByGroupId(int groupId,int newParentId){
        StringBuilder sql= new StringBuilder("update group set parent_id=? where id=?");
        jdbcTemplate.update(sql.toString(),
                new Object[]{newParentId,groupId});
    }


    /**
     *  @param groupId 群组ID
     * @param newGroupName 修改后的父群组ID
     */
    public void modifiedGroupNameByGroupId(int groupId, String newGroupName){
        StringBuilder sql= new StringBuilder("update group set group_name=? where id=?");
        jdbcTemplate.update(sql.toString(),
                new Object[]{newGroupName,groupId});
    }

}
