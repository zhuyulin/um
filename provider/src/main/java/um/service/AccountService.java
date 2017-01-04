package um.service;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.dao.AccountDAO;
import um.dataobject.AccountDO;
import um.exception.ServiceException;
import um.vo.AccountVO;

import java.util.List;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountDAO accountDAO;

    @Override
    public Integer login(String username, String password) throws ServiceException {
        List<AccountDO> checkUserName = accountDAO.findByUserName(username);
        if  (StringUtils.isBlank(username)) {
            throw new ServiceException("用户名为空", "10001");

        }
        else if (checkUserName.size() == 1 && checkUserName.get(0).getPassword().equals(DigestUtils.md5Hex(password))){
            //最后一次登录时间
            accountDAO.lastLoginTimeById(checkUserName.get(0).getId());
            //返回登录用户ID
                return checkUserName.get(0).getId();
        }
        else if (checkUserName.size() == 0) {
            throw new ServiceException("找不到用户名", "10002");
        }
        else if (checkUserName.size() >0) {
            throw new ServiceException("存在多个用户", "10003");

        }
        else throw new ServiceException("密码错误", "10004");
        }



    @Override
    public Integer register(String username, String password, String mobilePhone, String email)
            throws ServiceException{
        List<AccountDO> checkUserName = accountDAO.findByUserName(username);
        if (StringUtils.isBlank(username)) {
            throw new ServiceException("用户名为空", "10005");

        }
        if (checkUserName.size() >0) {
            throw new ServiceException("用户名已存在", "10006");
        }
            else {
            accountDAO.createNewAccount(username,DigestUtils.md5Hex(password),mobilePhone,email);
            accountDAO.gmtCreateById(accountDAO.findByUserName(username).get(0).getId());
            return accountDAO.findByUserName(username).get(0).getId();

        }

        }

    @Override
    public void resetPassword(String userName, String oldPassword, String newPassword)throws ServiceException {
        if (userName == null) {
            throw new ServiceException("用户名为空", "10005");
        }
        List<AccountDO> checkUserName = accountDAO.findByUserName(userName);
        if (login(userName,oldPassword) != null && checkUserName.size() == 1 ){
            accountDAO.updatePasswordByUserName(DigestUtils.md5Hex(newPassword),userName);
        }
        else if (login(userName,oldPassword) == null && checkUserName.size() == 1 ){
            throw new ServiceException("密码错误", "10004");
        }
        else if (checkUserName.size() == 0) {
            throw new ServiceException("找不到该用户ID", "10006");
        }
        else throw new ServiceException("未知错误", "00000");

    }

    @Override
    public AccountVO getAccount(String username) throws ServiceException {
        //检查参数

        AccountDO accountDO = (AccountDO) accountDAO.findByUserName(username);
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
