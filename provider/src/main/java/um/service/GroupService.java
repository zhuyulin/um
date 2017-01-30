package um.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.dao.GroupDAO;
import um.dataobject.GroupDO;
import um.exception.ServiceException;

import java.util.List;

/**
 * Created by Yuleen on 2017/1/30.
 */
@Service
public class GroupService implements IGroupService {
    @Autowired
    GroupDAO groupDAO;


    @Override
    public int addGroup(String groupName, int parentId) throws ServiceException {
        if (groupName == null || parentId == 0) {
            throw new ServiceException("群组名或者父群组ID为空", "10014");
        }
        List<GroupDO> checkList = groupDAO.getGroupIdByGroupName(groupName);
        if (checkList == null){
            groupDAO.addGroup(groupName,parentId);
            int groupId=groupDAO.getGroupIdByGroupName(groupName).get(0).getGroupId();
            return groupId;
        }
        return 0;
    }

    @Override
    public boolean delGroup(int groupId) throws ServiceException {
        if (groupId == 0) {
            throw new ServiceException("找不到该群组", "10015");
        }
        groupDAO.delGroup(groupId);
        List<GroupDO> checkList = groupDAO.getGroupByGroupId(groupId);
        if (checkList == null){
            return true;
        }
        return false;
    }

    @Override
    public boolean modifiedGroupParentId(int groupId, int newParentId) throws ServiceException {
        if (groupId == 0 || newParentId == 0) {
            throw new ServiceException("输入的群组ID或者新的父群组ID为空", "10016");
        }
        groupDAO.modifiedParentIdByGroupId(groupId, newParentId);
        List<GroupDO> checkList = groupDAO.getGroupByGroupId(groupId);
        if (checkList.get(0).getParentId() == newParentId){
            return true;
        }
        return false;
    }

    @Override
    public boolean modifiedGroupName(int groupId, String newGroupName) throws ServiceException {
        if (groupId == 0 || newGroupName == null) {
            throw new ServiceException("输入的群组ID或者新的群组名为空", "10017");
        }
        groupDAO.modifiedGroupNameByGroupId(groupId,newGroupName);
        List<GroupDO> checkList = groupDAO.getGroupByGroupId(groupId);
        if (checkList.get(0).getGroupName() == newGroupName){
            return true;
        }
        return false;
    }
}
