package um.service;
import um.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.dao.UserLabelDAO;
import um.dataobject.UserLabelDO;
import um.vo.UserLabelVO;

import java.util.List;

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
        List<UserLabelDO> list = userLabelDAO.checkUserIdAndLabelId(userId, userLabelId);
        if (list.size() != 0) {
            throw new ServiceException("该标签已经存在", "10008");
        }
        if (list.size() == 0) {
            userLabelDAO.addUserLabel(userId, userLabelId);
        }

        List<UserLabelDO> list2 = userLabelDAO.checkUserIdAndLabelId(userId, userLabelId);
        if (list2.size() != 0){
            System.out.println("标签添加成功");
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserLabel(int userId, int userLabelId) throws ServiceException {
        if (userId == 0 || userLabelId == 0) {
            throw new ServiceException("用户名或者标签ID为空", "10007");
        }
        List<UserLabelDO> list = userLabelDAO.checkUserIdAndLabelId(userId, userLabelId);
        if (list.size() == 0 ) {
            throw new ServiceException("该标签不存在", "10009");
        }

        if (list.size() ==1 && list.get(0).getIsDeleted().equals("Y")) {
            throw new ServiceException("该标签无效", "10010");
        }
        userLabelDAO.deleteUserLabel(userId, userLabelId);

        //debug
        List<UserLabelDO> list2 = userLabelDAO.checkUserIdAndLabelId(userId, userLabelId);
        if (list2.get(0).getIsDeleted().equals("Y")){
            System.out.println("标签完成删除");
            return true;
        }
        return false;
    }

    @Override
    public List<UserLabelVO> getLabel(int userId) throws ServiceException {
            List<UserLabelDO> userLabelDO =userLabelDAO.findByUserId(userId);
            List<UserLabelVO> userLabelVO = (List<UserLabelVO>) new UserLabelVO();

            for (int i=0; i < userLabelDO.size(); i++) {
                userLabelVO.get(i).setLabelName(userLabelDO.get(i).getLabelName());
                userLabelVO.get(i).setUserId(userLabelDO.get(i).getUserId());
                userLabelVO.get(i).setLabelId(userLabelDO.get(i).getLabelId());
                userLabelVO.get(i).setIsDeleted(userLabelDO.get(i).getIsDeleted());
            }
            return userLabelVO;
        }
    }
