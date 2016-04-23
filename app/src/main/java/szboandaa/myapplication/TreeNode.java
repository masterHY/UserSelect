package szboandaa.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhy on 2016/4/16.
 */
public class TreeNode implements Parcelable {

    private TreeNode parent;
    private List<TreeNode> children = new ArrayList<>();
    private String text;
    private String value;

    private boolean isChecked;
    private boolean hasCheckBox;

    public TreeNode() {
        new TreeNode(null, null, null);
    }

    public TreeNode(TreeNode parent, String text, String value) {
        this.parent = parent;
        this.text = text;
        this.value = value;
    }

    public boolean isLeafNode() {
        if (children == null || children.isEmpty()) {
            return true;
        } else {
            return false;
        }
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

    public void addChild(TreeNode treeNode) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(treeNode);
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
        if (!isLeafNode()) {
            for (TreeNode treeNode : children) {
                treeNode.setChecked(checked);
            }
        }
    }


    public boolean isHasCheckBox() {
        return hasCheckBox;
    }

    public void setHasCheckBox(boolean hasCheckBox) {
        this.hasCheckBox = hasCheckBox;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeString(value);

    }

    public static final Creator<TreeNode> CREATOR = new Creator<TreeNode>() {
        @Override
        public TreeNode createFromParcel(Parcel source) {
            TreeNode treeNode = new TreeNode();
            treeNode.setText(source.readString());
            treeNode.setValue(source.readString());
            return treeNode;
        }

        @Override
        public TreeNode[] newArray(int size) {
            return new TreeNode[size];
        }
    };
}
