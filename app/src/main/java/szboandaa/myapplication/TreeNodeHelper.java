package szboandaa.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhy on 2016/4/16.
 */
public class TreeNodeHelper {

    private List<TreeNode> mDataList;

    public TreeNodeHelper(List<TreeNode> mDataList) {
        this.mDataList = mDataList;
    }

    public List<TreeNode> getNodeListByParentNode(TreeNode parentNode) {
        List<TreeNode> nodeList = new ArrayList<>();
        for (int i = 0; i < mDataList.size(); i++) {
            TreeNode tempNode = mDataList.get(i);
            if (tempNode.getParent() == parentNode) {
                nodeList.add(tempNode);
            }
        }
        return nodeList;
    }
    public ArrayList<TreeNode> filterCheckedTreeNode() {
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        for (TreeNode treeNode : mDataList) {
            if (treeNode.isChecked()) {
                treeNodes.add(treeNode);
            }
        }
        return treeNodes;
    }
    public ArrayList<TreeNode> filterCheckedLeafNode() {
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        for (TreeNode treeNode : mDataList) {
            if (treeNode.isChecked()&&treeNode.isLeafNode()) {
                treeNodes.add(treeNode);
            }
        }
        return treeNodes;
    }
    public void buildTree(TreeNode rootNode){
        for(TreeNode treeNode : mDataList){
            TreeNode parentNode = treeNode.getParent();
            parentNode.addChild(treeNode);
        }
    }
}
