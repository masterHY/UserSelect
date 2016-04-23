package szboandaa.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class BootActivity extends AppCompatActivity {

    private final static int SELECT_PEOPLE = 1;
    private TextView testTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);
        testTxt = (TextView) findViewById(R.id.test_tv);
    }

    public void doClick(View v) {
        Intent intent = new Intent(this, SelectActivity.class);
        startActivityForResult(intent, SELECT_PEOPLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data==null)
            return;
        testTxt.setText("");
        ArrayList<TreeNode> mData = data.getParcelableArrayListExtra(SelectActivity.SELECT_PEOPLE_DATA);
        for (TreeNode treeNode : mData) {
            String text = treeNode.getText();
            String value = treeNode.getValue();
            testTxt.append(text+"  "+value+"\n");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
