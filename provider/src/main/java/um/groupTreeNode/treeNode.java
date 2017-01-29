package um.groupTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuleen on 2017/1/29.
 */
public class treeNode<T> {
    private T t;
    private treeNode<T> parent;
    private List<treeNode<T>> nodeList;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public treeNode<T> getParent() {
        return parent;
    }

    public void setParent(treeNode<T> parent) {
        this.parent = parent;
    }

    public List<treeNode<T>> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<treeNode<T>> nodeList) {
        this.nodeList = nodeList;
    }

    public treeNode(T stype){
        t = stype;
        parent = null;
        nodeList = new ArrayList<treeNode<T>>();
    }
}
