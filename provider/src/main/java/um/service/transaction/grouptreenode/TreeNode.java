package um.service.transaction.grouptreenode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuleen on 2017/1/29.
 */
public class TreeNode<T> {
    private T t;
    private TreeNode<T> parent;
    private List<TreeNode<T>> nodeList;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public List<TreeNode<T>> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<TreeNode<T>> nodeList) {
        this.nodeList = nodeList;
    }

    public TreeNode(T stype){
        t = stype;
        parent = null;
        nodeList = new ArrayList<TreeNode<T>>();
    }
}
