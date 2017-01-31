package service.tree;

import um.service.transaction.grouptreenode.Tree;

/**
 * Created by Yuleen on 2017/1/29.
 */
public class TreeTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Tree<String> Tree = new Tree();
        Tree.addNode(null, "string");
        Tree.addNode(Tree.getNode("string"), "hello");
        Tree.addNode(Tree.getNode("string"), "world");
        Tree.addNode(Tree.getNode("hello"), "sinny");
        Tree.addNode(Tree.getNode("hello"), "fredric");
        Tree.addNode(Tree.getNode("world"), "Hi");
        Tree.addNode(Tree.getNode("world"), "York");
        Tree.showNode(Tree.root);
        System.out.println("end of the test");
    }

}