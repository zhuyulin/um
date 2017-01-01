package um.dataobject;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Yuleen on 2016/12/31.
 */
public abstract class BaseDO implements RowMapper, Serializable {
    private int id;
    private Date gmtCreate;
    private Date gmtModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
