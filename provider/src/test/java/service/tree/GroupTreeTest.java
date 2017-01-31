package service.tree;

import common.AbsSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import um.cache.GroupTreeCache;
import um.service.transaction.grouptreenode.Tree;

/**
 * Created by Yuleen on 2017/1/31.
 */
@ContextConfiguration(locations = {"classpath:spring/spring-task5.xml"})
public class GroupTreeTest extends AbsSpringTest {
    @Autowired
    GroupTreeCache groupTreeCache;

    @Test
    public void initialTreeTest(){
        Tree<Integer> integerTree = groupTreeCache.getTree();
        integerTree.showNode(integerTree.root);
    }

}
