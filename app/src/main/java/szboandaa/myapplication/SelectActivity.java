package szboandaa.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity implements SelectAdapter.OnSelectListener {

    public static final String SELECT_PEOPLE_DATA = "SELECT_PEOPLE_DATA";
    private LinearLayout rootView;
    private List<TreeNode> mDataList;
    private TreeNode rootnode;
    //保存显示状态
    private List<List<TreeNode>> displayStateList;
    private TreeNodeHelper mHelper;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    //当前层级
    private int selectHierarchyNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mHelper = new TreeNodeHelper(mDataList);
        mHelper.buildTree(rootnode);
        rootView = (LinearLayout) findViewById(R.id.root_view);
        mOnItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int level = (int) parent.getTag();
                List<TreeNode> cuurentList = displayStateList.get(level - 1);
                refreshView(level, cuurentList.get(position));
            }

        };
        initState();
    }

    private void initState() {
        displayStateList = new ArrayList<>();
        selectHierarchyNum = 1;
        rootView.addView(createNewListView(mHelper.getNodeListByParentNode(rootnode)));
    }

    private ListView createNewListView(List<TreeNode> dataList) {
        ListView mListView = new ListView(this);
        SelectAdapter selectAdapter = new SelectAdapter(this, dataList, this);
        mListView.setAdapter(selectAdapter);
        mListView.setTag(selectHierarchyNum);
        mListView.setVerticalScrollBarEnabled(false);
        mListView.setBackgroundResource(R.drawable.listview_left_strock_bg);
        mListView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mListView.setOnItemClickListener(mOnItemClickListener);
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
        if (nextNodeList.size() <= 0) return;
        rootView.addView(createNewListView(nextNodeList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.select_commit:
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra(SELECT_PEOPLE_DATA, mHelper.filterCheckedLeafNode());
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.select_clear:
                rootView.removeAllViews();
                initState();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelectNotificate() {
        int childCount = rootView.getChildCount();
        for (int i = childCount; i > 0; i--) {
            View temp = rootView.findViewWithTag(i);
            if (temp instanceof ListView) {
                ((SelectAdapter) ((ListView) temp).getAdapter()).notifyDataSetChanged();
            }
        }
    }


    private void initData() {
        mDataList = new ArrayList<>();
        rootnode = new TreeNode(null, "root", "rootvalue");
        TreeNode aDepartNode = new TreeNode(rootnode, "部门a", "a");
        TreeNode bDepartNode = new TreeNode(rootnode, "部门b", "b");
        TreeNode cDepartNode = new TreeNode(rootnode, "人员c", "c");
        TreeNode aaDepartNode = new TreeNode(aDepartNode, "部门aa", "aa");
        TreeNode abDepartNode = new TreeNode(aDepartNode, "人员ab", "ab");
        TreeNode acDepartNode = new TreeNode(aDepartNode, "人员ac", "ac");
        TreeNode baDepartNode = new TreeNode(bDepartNode, "人员ba", "ba");
        TreeNode bbDepartNode = new TreeNode(bDepartNode, "人员bb", "bb");
        TreeNode bcDepartNode = new TreeNode(bDepartNode, "人员bc", "bc");
        TreeNode aaaDepartNode = new TreeNode(aaDepartNode, "人员aaa", "aaa");
        TreeNode aabDepartNode = new TreeNode(aaDepartNode, "人员aab", "aab");
        TreeNode aacDepartNode = new TreeNode(aaDepartNode, "人员aac", "aac");
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
