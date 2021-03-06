package um.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.dao.AccountDAO;
import um.dataobject.AccountDO;
import um.exception.ServiceException;
import um.vo.AccountVO;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountDAO accountDAO;

    @Override
    public Integer login(String username, String password) throws ServiceException {
        if (StringUtils.isBlank(username)) {
            throw new ServiceException("用户名为空", "10001");
        }
        AccountDO accountDO = accountDAO.findByUserName(username);
        password = DigestUtils.md5Hex(password);
        if (accountDO == null) {
            throw new ServiceException("找不到用户名", "10002");
        } else if (!accountDO.getPassword().equals(password)) {
            throw new ServiceException("密码错误", "10003");
        }

        //最后一次登录时间
        accountDAO.lastLoginTimeById(accountDO.getId());
        //返回登录用户ID
        return accountDO.getId();
    }


    @Override
    public Integer register(String username, String password, String mobilePhone, String email)
            throws ServiceException {
        AccountDO accountDO = accountDAO.findByUserName(username);
        if (StringUtils.isBlank(username)) {
            throw new ServiceException("用户名为空", "10004");

        }
        if (accountDO != null) {
            throw new ServiceException("用户名已存在", "10005");
        } else {
            //注册
            accountDAO.createNewAccount(username, DigestUtils.md5Hex(password), mobilePhone, email);
            accountDAO.gmtCreateById(accountDAO.findByUserName(username).getId());
            return accountDAO.findByUserName(username).getId();

        }
    }

    @Override
    public void resetPassword(String userName, String oldPassword, String newPassword) throws ServiceException {
        if (userName == null) {
            throw new ServiceException("用户名为空", "10004");
        }
        AccountDO accountDO = accountDAO.findByUserName(userName);
        if (login(userName, oldPassword) == null ) {
            throw new ServiceException("密码错误", "10003");
        }
        if (accountDO == null ){
            throw new ServiceException("用户名输入错误", "10006");
        }

        if (login(userName, oldPassword) != null && accountDO != null) {
            accountDAO.updatePasswordByUserName(DigestUtils.md5Hex(newPassword), userName);
        }
    }

    @Override
    public AccountVO getAccount(String userName) throws ServiceException {
        //检查参数

        AccountDO accountDO = accountDAO.findByUserName(userName);
        AccountVO accountVO = new AccountVO();
        accountVO.setUserName(accountDO.getUserName());
        accountVO.setNickName(accountDO.getNickName());
        accountVO.setMobilePhone(accountDO.getMobilePhone());
        accountVO.setLastLoginTime(accountDO.getLastLoginTime());
        accountVO.setEmail(accountDO.getEmail());
        accountVO.setState(accountDO.getState());
        return accountVO;
    }

}
