package um.groupTreeNode;

/**
 * Created by Yuleen on 2017/1/30.
 */
public class tree<T> {
    public treeNode<T> root;

    public void addNode(treeNode<T> node, T newNode){
        //增加根节点
        if(null == node){
            if(null == root){
                root = new treeNode(newNode);
            }
        }else{
            treeNode<T> temp = new treeNode(newNode);
            node.getNodeList().add(temp);
            temp.setParent(node);
            temp.setT(newNode);
        }
    }

    /*    查找newNode这个节点 */
    public treeNode<T> search(treeNode<T> input, T newNode){

        treeNode<T> temp = null;

        if(input.getT().equals(newNode)){
            return input;
        }

        for(int i = 0; i < input.getNodeList().size(); i++){

            temp = search(input.getNodeList().get(i), newNode);

            if(null != temp){
                break;
            }
        }

        return temp;
    }

    public treeNode<T> getNode(T newNode){

        return search(root, newNode);
    }

    public void showNode(treeNode<T> node){
        if(null != node ){
            //循环遍历node的节点
            if ( node.getParent() == null) {
                System.out.println("【value】:"+node.getT().toString() + ",【parent】: null");
            }
            else {
                System.out.println("【value】:"+node.getT().toString() + ",【parent】:"+node.getParent().getT().toString());
            }

            for(int i = 0; i < node.getNodeList().size(); i++){
                showNode(node.getNodeList().get(i));
            }
        }
    }
}

