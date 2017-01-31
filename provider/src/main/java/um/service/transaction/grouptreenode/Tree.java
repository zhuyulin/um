package um.service.transaction.grouptreenode;

/**
 * Created by Yuleen on 2017/1/30.
 */
public class Tree<T> {
    public TreeNode<T> root;


    /**
     *
     * @param parentNode   父节点
     * @param newNode  新节点的值
     */
    public void addNode(TreeNode<T> parentNode, T newNode){
        //增加根节点
        if(null == parentNode){
            if(null == root){
                root = new TreeNode(newNode);
            }
        }else{
            TreeNode<T> temp = new TreeNode(newNode);
            parentNode.getNodeList().add(temp);
            temp.setParent(parentNode);
            temp.setT(newNode);
        }
    }

    /**
     *
     * @param nodeValue 需要删除目标节点的值
     */
    public void delNode(T nodeValue){
        //删除节点
        TreeNode<T> temp = getNode(nodeValue);
        for (int i=0; i < temp.getParent().getNodeList().size(); i++){
            T value = temp.getParent().getNodeList().get(i).getT();
            if (value == nodeValue) {
                temp.getParent().getNodeList().remove(i);
                break;
            }
        }
    }

    /**
     *
     * @param nodeValue  需要修改的节点值
     * @param newParent 需要修改后目标父节点
     */
    public void mofidiedNode(T nodeValue,TreeNode<T> newParent){
        TreeNode<T> toModifiedNode = getNode(nodeValue);
        delNode(nodeValue);
        newParent.getNodeList().add(toModifiedNode);
        toModifiedNode.setParent(newParent);
    }


    /*    查找nodeValue这个节点 */
    public TreeNode<T> search(TreeNode<T> input, T nodeValue){

        TreeNode<T> temp = null;

        if(input.getT().equals(nodeValue)){
            return input;
        }

        for(int i = 0; i < input.getNodeList().size(); i++){

            temp = search(input.getNodeList().get(i), nodeValue);

            if(null != temp){
                break;
            }
        }

        return temp;
    }

    public TreeNode<T> getNode(T nodeValue){

        return search(root, nodeValue);
    }

    public void showNode(TreeNode<T> node){
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

