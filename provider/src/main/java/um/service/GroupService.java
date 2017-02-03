package um.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import um.cache.GroupTreeCache;
import um.dao.GroupDAO;
import um.dataobject.GroupDO;
import um.exception.ServiceException;
import um.service.transaction.grouptreenode.Tree;
import um.vo.GroupVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuleen on 2017/1/30.
 */
@Service
public class GroupService implements IGroupService {
    @Autowired
    GroupDAO groupDAO;
    @Autowired
    GroupTreeCache groupTreeCache;

    @Override
    @Transactional
    public int addGroup(String groupName, int parentId) throws ServiceException {
        if (groupName == null || parentId == 0) {
            throw new ServiceException("群组名或者父群组ID为空", "10014");
        }
        //查找重名
        List<GroupDO> checkList = groupDAO.getGroupIdByGroupName(groupName);
        //如果没有重名，则执行
        if (checkList.size() == 0){
            groupDAO.addGroup(groupName,parentId);
            int groupId = groupDAO.getGroupIdByGroupName(groupName).get(0).getGroupId();
            Tree<Integer> integerTree = groupTreeCache.getTree();
            integerTree.addNode(integerTree.getNode(parentId),groupId);
            return groupId;
        }
        return 0;
    }

    @Override
    @Transactional
    public boolean delGroup(int groupId) throws ServiceException {
        if (groupId == 0) {
            throw new ServiceException("找不到该群组", "10015");
        }
        groupDAO.delGroup(groupId);
        Tree<Integer> integerTree = groupTreeCache.getTree();
        integerTree.delNode(groupId);
        List<GroupDO> checkList = groupDAO.getGroupByGroupId(groupId);
        if (checkList == null){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean modifiedGroupParentId(int groupId, int newParentId) throws ServiceException {
        if (groupId == 0 || newParentId == 0) {
            throw new ServiceException("输入的群组ID或者新的父群组ID为空", "10016");
        }
        groupDAO.modifiedParentIdByGroupId(groupId, newParentId);
        Tree<Integer> integerTree = groupTreeCache.getTree();
        integerTree.mofidiedNode(groupId,integerTree.getNode(newParentId));
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
    @Override
    public List<GroupVO> getGroup(int groupId) throws ServiceException {
        List<GroupDO> groupDOList =groupDAO.getGroupByGroupId(groupId);
        List<GroupVO> groupVOList = new ArrayList<>();

        for (GroupDO groupDO : groupDOList) {
            GroupVO groupVO = new GroupVO();
            groupVO.setGroupId(groupDO.getGroupId());
            groupVO.setGroupName(groupDO.getGroupName());
            groupVO.setParentId(groupDO.getParentId());
            groupVO.setIsDeleted(groupDO.getIsDeleted());
            groupDOList.add(groupDO);
        }
        return groupVOList;
    }
}

