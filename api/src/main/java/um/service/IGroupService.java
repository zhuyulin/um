package um.service;

import um.exception.ServiceException;
import um.vo.GroupVO;

import java.util.List;

/**
 * Created by Yuleen on 2017/1/30.
 */
public interface IGroupService {
    /**
     *
     * @param groupName 新增群组名称
     * @param parentId 新增群组父群组ID
     * @return 返回新添加群组的id
     */
    int addGroup(String groupName,int parentId) throws ServiceException;

    /**
     *
     * @param groupId 群组ID
     * @return 返回boolean值，true代表删除成功，false代表失败。
     * @throws ServiceException
     */

    boolean delGroup(int groupId) throws ServiceException;

    /**
     *
     * @param groupId 群组ID
     * @param newParentId 该群组新的父群组ID
     * @return 返回boolean值，true代表修改成功，false代表失败。
     * @throws ServiceException
     */
    boolean modifiedGroupParentId(int groupId,int newParentId) throws ServiceException;

    /**
     *
     * @param groupId 群组ID
     * @param newGroupName  修改之后的群组名称
     * @return 返回boolean值，true代表修改成功，false代表失败。
     * @throws ServiceException
     */
    boolean modifiedGroupName(int groupId,String newGroupName) throws ServiceException;

    public List<GroupVO> getGroup(int userId) throws ServiceException;
}
