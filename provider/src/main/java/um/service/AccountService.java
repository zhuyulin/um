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
        if (checkUserName.size() == 1 && checkUserName.get(0).getPassword().equals(DigestUtils.md5Hex(password))){
                return checkUserName.get(0).getId();
        }
        else if (checkUserName.size() == 0) {
            throw new ServiceException("找不到用户名", "10002");

        }
        else if (checkUserName.size() >0) {
            throw new ServiceException("存在多个用户", "10003");

        }
        else if  (StringUtils.isBlank(username)) {
            throw new ServiceException("用户名为空", "10001");

        }
        else throw new ServiceException("密码错误", "10003");
        }



    @Override
    public Integer register(String username, String password, String mobilePhone, String email)
            throws ServiceException{
        List<AccountDO> checkUserName = accountDAO.findByUserName(username);
        if (StringUtils.isBlank(username)) {
            throw new ServiceException("用户名为空", "10004");

        }
        if (checkUserName.size() >0) {
            throw new ServiceException("用户名已存在", "10005");
        }
            else {
            accountDAO.createNewAccount(username,password,mobilePhone,email);
            return accountDAO.findByUserName(username).get(1).getId();

        }

        }

    @Override
    public void resetPassword(Integer id, String oldPassword, String newPassword)throws ServiceException {
        List<AccountDO> checkUserId = accountDAO.findByUserId(id);
        if (id == null) {
            throw new ServiceException("ID为空", "10006");

        }
        else if (checkUserId.size() == 1 && checkUserId.get(0).getPassword().equals(DigestUtils.md5Hex(oldPassword))) {
            accountDAO.updatePasswordById(DigestUtils.md5Hex(newPassword),id);
        }
        else if (checkUserId.size() == 1 && !checkUserId.get(0).getPassword().equals(DigestUtils.md5Hex(oldPassword))){
            throw new ServiceException("旧密码输入错误", "10008");

        }
        else if (checkUserId.size() == 0) {
            throw new ServiceException("找不到该用户ID", "10007");
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
        accountVO.setState(accountDO.getState().getState());
        return accountVO;
    }

}
