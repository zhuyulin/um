package um.service;
import um.exception.ServiceException;
import um.vo.UserLabelServiceVO;

/**
 * Created by Yuleen on 2017-01-10.
 */
public interface IUserLabelService {
    /**
     *
     * @param id 用户ID
     * @param userLabelId  标签ID
     * @return 返回true代表添加成功，返回false代表添加失败
     */
    boolean addUserLabel(int id,int userLabelId) throws ServiceException;

    /**
     *
     * @param id 用户ID
     * @param userLabelId  标签ID
     * @return 返回true代表删除成功，返回false代表删除失败
     */
    boolean deleteUserLabel(int id,int userLabelId) throws ServiceException;

    /**
     * 获取标签
     * @param userId
     * @return
     * @throws ServiceException
     */
    public UserLabelServiceVO getLabel(int userId) throws ServiceException;
}
