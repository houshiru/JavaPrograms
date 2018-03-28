import java.util.ArrayList;
import java.util.List;
/*
Define TreeNode structure. Include node value,
parent and  use ArrayList store the children.
 */
public class TreeNode {
    private String value;
    private TreeNode parent;
    private List<TreeNode> children;
    /*
    constructor
     */
    public TreeNode(String value) {
        this.value = value;
        children = new ArrayList<TreeNode>();
    }
    //get value
    public String getValue() {
        return value;
    }
    //set value
    public void setValue(String value) {
        this.value = value;
    }
    //get parent
    public TreeNode getParent() {
        return parent;
    }
    //set parent
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
    //get children
    public List<TreeNode> getChildren() {
        return children;
    }
    //insert child maintain alphabetical order
    public void insertChild(TreeNode child){
        int i = 0;
        while (i < children.size() && children.
                get(i).getValue().compareTo(child.getValue()) < 0){
            i++;
        }
        children.add(i, child);
    }
    //append child to maintain order of addition
    public void appendChild(TreeNode child){
        children.add(child);
    }
}
