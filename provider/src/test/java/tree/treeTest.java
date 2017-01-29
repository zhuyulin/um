package tree;

import um.groupTreeNode.tree;

/**
 * Created by Yuleen on 2017/1/29.
 */
public class treeTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        tree<String> tree = new tree();
        tree.addNode(null, "string");
        tree.addNode(tree.getNode("string"), "hello");
        tree.addNode(tree.getNode("string"), "world");
        tree.addNode(tree.getNode("hello"), "sinny");
        tree.addNode(tree.getNode("hello"), "fredric");
        tree.addNode(tree.getNode("world"), "Hi");
        tree.addNode(tree.getNode("world"), "York");
        tree.showNode(tree.root);
        System.out.println("end of the test");
    }

}