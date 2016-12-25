package um.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import um.exception.ServiceException;
import um.vo.AccountVO;

@Service
public class AccountService implements IAccountService {


    @Override
    public Integer register(String username, String password, String mobile_phone, String email) throws ServiceException{
        return null;
    }

    @Override
    public void resetPassword(Integer id, String oldPassword, String newPassword)throws ServiceException {

    }

    @Override
    public Integer login(String username, String password)throws ServiceException {
        if(StringUtils.isBlank(username)){
            throw new ServiceException("用户名为空","10001");
        }
        return null;
    }

    @Override
    public AccountVO getAccount(String username) throws ServiceException {
        return null;
    }

}
