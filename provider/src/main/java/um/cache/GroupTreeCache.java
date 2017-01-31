package um.cache;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.dao.GroupDAO;
import um.dataobject.GroupDO;
import um.service.transaction.grouptreenode.Tree;

import java.util.List;

/**
 * Created by Yuleen on 2017/1/31.
 */
@Component
public class GroupTreeCache implements InitializingBean {
    @Autowired
    private GroupDAO groupDAO;

    private List<GroupDO> groupDOList;

    private Tree<Integer> Tree = new Tree();

    @Override
    public void afterPropertiesSet() throws Exception {
        groupDOList = groupDAO.getGroupAll();
        for (int i=0;i < groupDOList.size();i++){

            Integer parentId = groupDOList.get(i).getParentId();
            Integer groupId = groupDOList.get(i).getGroupId();

            if (parentId ==0) {
                Tree.addNode(null, groupId);
            }
            Tree.addNode(Tree.getNode(parentId), groupId);

        }
    }

    public Tree<Integer> getTree() {
        return Tree;
    }

    public void setTree(Tree<Integer> tree) {
        Tree = tree;
    }
}
