package um.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import um.dataobject.AccountDO;
import um.dataobject.UserLabelDO;

import java.util.List;

/**
 * Created by Yuleen on 2017-01-10.
 */
public class UserLabelDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 输入用户ID，可输出该用户ID、标签ID、标签名称
     */
    public List<UserLabelDO> findByUserId(int userId){
        StringBuilder sql = new StringBuilder("select\n" +
                "ul.user_id;\n" +
                "ul.label_id;\n" +
                "l.label_name;\n" +
                "ul.is_deleted\n" +
                "from\n" +
                "label l\n" +
                "left join\n" +
                "user_label ul on ul.label_id =l.id \n" +
                "where ul.user_id =?");
        List<UserLabelDO> findByUserId = jdbcTemplate.query(sql.toString(),new UserLabelDO(),
                new Object[]{userId});
        if(findByUserId.size() >= 1)
            return findByUserId;
        return null;
    }


    /**
     * 输入用户ID、标签ID，可检查标签是否存在
     */
    public List<UserLabelDO> checkUserIdAndLabelId(int userId,int labelId){
        StringBuilder sql = new StringBuilder("select\n" +
                "ul.user_id;\n" +
                "ul.label_id;\n" +
                "l.label_name;\n" +
                "ul.is_deleted\n" +
                "from\n" +
                "label l\n" +
                "left join\n" +
                "user_label ul on ul.label_id =l.id \n" +
                "where ul.user_id =? and ul.label_id=?");
        List<UserLabelDO> checkUserIdAndLabelId = jdbcTemplate.query(sql.toString(),new UserLabelDO(),
                new Object[]{userId,labelId});
            return checkUserIdAndLabelId;
    }
    /**
     * 输入用户ID，标签ID,对该用户进行添加标签。
     */

    public  UserLabelDO addUserLabel(int userId,int labelId){
        StringBuilder sql = new StringBuilder("insert into user_label (user_id,label_id) values (?,?)");
        jdbcTemplate.update(sql.toString(),
                new Object[]{userId,labelId});
        return null;
    }
    /**
     * 输入用户ID，标签ID,对该用户进行删除标签。
     */

    public  UserLabelDO deleteUserLabel(int userId,int labelId){
        StringBuilder sql = new StringBuilder("update user_label set is_deleter='Y' where user_id=? and label_ud=?");
        jdbcTemplate.update(sql.toString(),
                new Object[]{userId,labelId});
        return null;
    }
}
