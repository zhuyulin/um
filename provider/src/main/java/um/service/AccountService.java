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
    public Integer login(String username, String password)throws ServiceException {
        List<AccountDO> checkusername = accountDAO.findByUserName(username);
        if (StringUtils.isBlank(username)) {
            throw new ServiceException("用户名为空", "10001");

        } else if (checkusername.size() == 0) {
            throw new ServiceException("找不到用户名", "10002");

        } else if (checkusername.size() == 1 && checkusername.get(1).getPassword().equals(DigestUtils.md5Hex(password))) {
            return checkusername.get(1).getId();
        }
        else throw new ServiceException("未知异常", "10003");
    }

    @Override
    public Integer register(String username, String password, String mobile_phone, String email)
            throws ServiceException{
        List<AccountDO> checkusername = accountDAO.findByUserName(username);
        if (StringUtils.isBlank(username)) {
            throw new ServiceException("用户名为空", "10009");

        }
        else if (checkusername.size() >0) {
            throw new ServiceException("用户名已存在", "10008");
        }
            else {
            accountDAO.createNewAccount(username,password,mobile_phone,email);
            return accountDAO.findByUserName(username).get(1).getId();

        }

        }

    @Override
    public void resetPassword(Integer id, String oldpassword, String newpassword)throws ServiceException {
        List<AccountDO> checkuserid = accountDAO.findByUserId(id);
        if (id == null) {
            throw new ServiceException("ID为空", "10004");

        } else if (checkuserid.size() == 0) {
            throw new ServiceException("找不到该用户ID", "10005");

        } else if (checkuserid.size() == 1 && !checkuserid.get(1).getPassword().equals(DigestUtils.md5Hex(oldpassword))){
            throw new ServiceException("旧密码输入错误", "10007");

        } else if (checkuserid.size() == 1 && checkuserid.get(1).getPassword().equals(DigestUtils.md5Hex(oldpassword))) {
            accountDAO.updatePasswordById(DigestUtils.md5Hex(newpassword),id);
            throw new ServiceException("密码修改成功", "10006");
        }
        else throw new ServiceException("未知异常", "10003");


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
        accountVO.setStation(accountDO.getStation().getStation());
        return accountVO;
    }

}
