package service;

import common.AbsSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import um.cache.GroupTreeCache;
import um.exception.ServiceException;
import um.service.GroupService;
import um.service.transaction.grouptreenode.Tree;

/**
 * Created by Yuleen on 2017/1/31.
 */
@ContextConfiguration(locations = {"classpath:spring/spring-task5.xml"})
public class TestGroupTree extends AbsSpringTest {
    @Autowired
    GroupTreeCache groupTreeCache;

    @Autowired
    GroupService groupService;

    @Test
    public void addGroupTest() throws ServiceException {
        Tree<Integer> integerTree = groupTreeCache.getTree();
        groupService.addGroup("十一班",3);
        integerTree.showNode(integerTree.root);
    }


    @Test
    public void delGroup() throws ServiceException{
        Tree<Integer> integerTree = groupTreeCache.getTree();
        groupService.delGroup(14);
        integerTree.showNode(integerTree.root);
    }

    @Test
    public void modifiedGroupParentId() throws ServiceException{
        Tree<Integer> integerTree = groupTreeCache.getTree();
        groupService.modifiedGroupParentId(13,3);
        integerTree.showNode(integerTree.root);
    }

    @Test
    public void modifiedGroupName() throws ServiceException{
        groupService.modifiedGroupName(14,"11班");
    }



}
