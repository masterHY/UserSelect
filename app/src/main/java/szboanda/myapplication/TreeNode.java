package szboanda.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张浩宇 on 2016/4/16.
 */
public class TreeNode {

    private TreeNode parent;
    private List<TreeNode> children = new ArrayList<>();
    private String text;
    private String value;

    private boolean isChecked;
    private boolean isExpanded;
    private boolean hasCheckBox;

    public TreeNode(TreeNode parent, String text, String value) {
        this.parent = parent;
        this.text = text;
        this.value = value;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public boolean isHasCheckBox() {
        return hasCheckBox;
    }

    public void setHasCheckBox(boolean hasCheckBox) {
        this.hasCheckBox = hasCheckBox;
    }

}
