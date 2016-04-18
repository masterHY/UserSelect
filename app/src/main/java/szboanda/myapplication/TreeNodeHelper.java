package szboanda.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张浩宇 on 2016/4/16.
 */
public class TreeNodeHelper {

    private List<TreeNode> mDataList;

    public TreeNodeHelper(List<TreeNode> mDataList) {
        this.mDataList = mDataList;
    }

    public List<TreeNode> getNodeListByParentNode(TreeNode parentNode){
        List<TreeNode> nodeList = new ArrayList<>();
        for (int i = 0; i < mDataList.size(); i++) {
            TreeNode tempNode = mDataList.get(i);
            if(tempNode.getParent()==parentNode){
                nodeList.add(tempNode);
            }
        }
        return nodeList;
    }

    public  List<String> getTextListByNodeList(List<TreeNode> dataList) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            TreeNode tempNode = dataList.get(i);
            list.add(tempNode.getText());
        }
        return list;
    }


    public  List<String> getTextListByParentNode(TreeNode parentNode) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < mDataList.size(); i++) {
            TreeNode tempNode = mDataList.get(i);
            if (tempNode.getParent() == parentNode) {
                list.add(tempNode.getText());
            }
        }
        return list;
    }

}
