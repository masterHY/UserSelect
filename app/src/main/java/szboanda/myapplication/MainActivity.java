package szboanda.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout rootView;
    private List<TreeNode> mDataList;
    private TreeNode rootnode;

    private TreeNode aDepartNode;
    private TreeNode bDepartNode;
    private TreeNode cDepartNode;
    private TreeNode aaDepartNode;
    private TreeNode abDepartNode;
    private TreeNode acDepartNode;
    private TreeNode aaaDepartNode;
    private TreeNode aabDepartNode;
    private TreeNode aacDepartNode;
    private TreeNode baDepartNode;
    private TreeNode bbDepartNode;
    private TreeNode bcDepartNode;



    private List<List<TreeNode>> displayStateList;
    private TreeNodeHelper mHelper;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    private int selectHierarchyNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mHelper = new TreeNodeHelper(mDataList);
        displayStateList = new ArrayList<>();
        selectHierarchyNum = 1;
        rootView = (LinearLayout) findViewById(R.id.root_view);

        mOnItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int level = (int) parent.getTag();
                List<TreeNode> cuurentList = displayStateList.get(level - 1);
                refreshView(level, cuurentList.get(position));

            }

        };

        rootView.addView(createNewListView(mHelper.getNodeListByParentNode(rootnode)));

    }

    private ListView createNewListView(List<TreeNode> dataList) {
        ListView mListView = new ListView(this);
        SelectAdapter selectAdapter = new SelectAdapter(this,dataList);

        mListView.setAdapter(selectAdapter);
        mListView.setTag(selectHierarchyNum);
        mListView.setVerticalScrollBarEnabled(false);

        mListView.setOnItemClickListener(mOnItemClickListener);
        //记录数据
        displayStateList.add(dataList);
        return mListView;
    }


    private void refreshView(int clickLevel, TreeNode cuurentNode) {
        selectHierarchyNum = clickLevel + 1;
        int childCount = rootView.getChildCount();
        for (int i = childCount; i > clickLevel; i--) {
            View a = rootView.findViewWithTag(i);
            rootView.removeView(a);
            displayStateList.remove(i - 1);
        }
        List<TreeNode> nextNodeList = mHelper.getNodeListByParentNode(cuurentNode);
        rootView.addView(createNewListView(nextNodeList));
    }

    private void initData() {

        mDataList = new ArrayList<>();
        rootnode = new TreeNode(null, "root", "rootvalue");
        aDepartNode = new TreeNode(rootnode, "部门a", "a");
        bDepartNode = new TreeNode(rootnode, "部门b", "b");
        cDepartNode = new TreeNode(rootnode, "人员c", "c");

        aaDepartNode = new TreeNode(aDepartNode, "部门aa", "aa");
        abDepartNode = new TreeNode(aDepartNode, "人员ab", "ab");
        acDepartNode = new TreeNode(aDepartNode, "人员ac", "ac");
        baDepartNode = new TreeNode(bDepartNode, "人员ba", "ba");
        bbDepartNode = new TreeNode(bDepartNode, "人员bb", "bb");
        bcDepartNode = new TreeNode(bDepartNode, "人员bc", "bc");
        aaaDepartNode = new TreeNode(aaDepartNode, "人员aaa", "aaa");
        aabDepartNode = new TreeNode(aaDepartNode, "人员aab", "aab");
        aacDepartNode = new TreeNode(aaDepartNode, "人员aac", "aac");
        mDataList.add(aDepartNode);
        mDataList.add(bDepartNode);
        mDataList.add(cDepartNode);
        mDataList.add(aaDepartNode);
        mDataList.add(abDepartNode);
        mDataList.add(acDepartNode);
        mDataList.add(aaaDepartNode);
        mDataList.add(aabDepartNode);
        mDataList.add(aacDepartNode);
        mDataList.add(baDepartNode);
        mDataList.add(bbDepartNode);
        mDataList.add(bcDepartNode);
    }
}
