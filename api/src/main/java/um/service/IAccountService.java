package um.service;

import um.exception.ServiceException;
import um.vo.AccountVO;

/**
 * 账号类服务接口
 * Created by Yuleen on 2016/12/25.
 */
public interface IAccountService {
    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @param mobile_phone 手机号码
     * @param email 电子邮箱
     * @return 账号ID
     */
    Integer register(String username,String password,String mobile_phone,String email) throws ServiceException;

    /**
     * 重置密码
     * @param id 用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void resetPassword(Integer id,String oldPassword,String newPassword) throws ServiceException;

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 账号ID
     */
    Integer login(String username,String password) throws ServiceException;

    /**
     * 获取账号
     * @param username
     * @return
     * @throws ServiceException
     */
    public AccountVO getAccount(String username) throws ServiceException;
}

