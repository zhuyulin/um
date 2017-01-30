package um.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.dao.AccountGroupRelaDAO;
import um.dataobject.AccountGroupRelaDO;
import um.exception.ServiceException;
import um.vo.AccountGroupRelaVO;

import java.util.List;

/**
 * Created by Yuleen on 2017/1/30.
 */
@Service
public class AccountGroupRelaService implements IAccountGroupRelaService {
    @Autowired
    AccountGroupRelaDAO accountGroupRelaDAO;

    @Override
    public boolean modifiedAccountGroup(int userId, int groupId) throws ServiceException {
        if (userId == 0 || groupId == 0) {
            throw new ServiceException("用户ID或者需要添加的群组ID为空", "10013");

        }
        List<AccountGroupRelaDO>  checkList = accountGroupRelaDAO.checkAccountGroupRela(userId, groupId);
        if (checkList != null) {
            throw new ServiceException("该用户已经添加到该群组中", "10014");
        }
        else{
            accountGroupRelaDAO.modifiedAccountGroupRela(userId, groupId);
            System.out.println("用户已经添加到该群组中");
            return true;
        }

    }

    @Override
    public AccountGroupRelaVO getAccountGroup(int userId) throws ServiceException {
        AccountGroupRelaDO accountGroupRelaDO = accountGroupRelaDAO.getAccountGroupRela(userId);
        AccountGroupRelaVO accountGroupRelaVO = new AccountGroupRelaVO();
        accountGroupRelaVO.setGroupId(accountGroupRelaDO.getGroupId());
        accountGroupRelaVO.setUserId(accountGroupRelaDO.getUserId());
        return accountGroupRelaVO;
    }
}
