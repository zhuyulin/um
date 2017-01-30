package um.service;

import um.exception.ServiceException;
import um.vo.AccountGroupRelaVO;

/**
 * Created by Yuleen on 2017/1/30.
 */
public interface IAccountGroupRelaService {
    /**
     *
     * @param userId 用户ID
     * @param groupId 群组ID
     * @return 返回boolean值，true代表修改成功，false代表修改失败
     */
    boolean modifiedAccountGroup(int userId,int groupId) throws ServiceException;

    public AccountGroupRelaVO getAccountGroup(int userId) throws ServiceException;
}
