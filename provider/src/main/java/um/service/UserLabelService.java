package um.service;
import um.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.dao.UserLabelDAO;
import um.dataobject.UserLabelDO;
import um.vo.UserLabelVO;

/**
 * Created by Yuleen on 2017-01-10.
 */
@Service
public class UserLabelService implements IUserLabelService {
    @Autowired
    private UserLabelDAO userLabelDAO;

    @Override
    public boolean addUserLabel(int userId, int userLabelId) throws ServiceException {
        if (userId == 0 || userLabelId == 0) {
            throw new ServiceException("用户名或者标签ID为空", "10007");
        }
        if (userLabelDAO.checkUserIdAndLabelId(userId, userLabelId).size() != 0) {
            throw new ServiceException("该标签已经存在", "10008");
        } else userLabelDAO.addUserLabel(userId, userLabelId);

        if (userLabelDAO.checkUserIdAndLabelId(userId, userLabelId).size() != 0){
            System.out.println("标签已经添加成功");
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserLabel(int userId, int userLabelId) throws ServiceException {
        if (userId == 0 || userLabelId == 0) {
            throw new ServiceException("用户名或者标签ID为空", "10007");
        }
        if (userLabelDAO.checkUserIdAndLabelId(userId, userLabelId).size() == 0) {
            throw new ServiceException("该标签不存在", "10008");
        }
        else userLabelDAO.deleteUserLabel(userId, userLabelId);

        if (userLabelDAO.checkUserIdAndLabelId(userId, userLabelId).get(0).getIsDeleted().equals("Y")){
            System.out.println("标签已经删除");
            return true;
        }
        return false;
    }

    @Override
    public UserLabelVO getLabel(int userId) throws ServiceException {
            UserLabelDO userLabelDO = (UserLabelDO) userLabelDAO.findByUserId(userId);
            UserLabelVO userLabelVO = new UserLabelVO();
            userLabelVO.setLabelName(userLabelDO.getLabelName());
            userLabelVO.setUserId(userLabelDO.getUserId());
            userLabelVO.setLabelId(userLabelDO.getLabelId());
            userLabelVO.setIsDeleted(userLabelDO.getIsDeleted());
            return userLabelVO;
        }
    }
