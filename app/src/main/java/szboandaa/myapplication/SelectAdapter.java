package szboandaa.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhy on 2016/4/16.
 */
public class SelectAdapter extends BaseAdapter {


    private List<TreeNode> mNodeList;
    private LayoutInflater mInflater;
    private Context context;
    private OnSelectListener onSelectListener;


    public interface OnSelectListener {
        void onSelectNotificate();
    }

    public SelectAdapter(Context context, List<TreeNode> mNodeList, OnSelectListener onSelectListener) {
        this.context = context;
        this.onSelectListener = onSelectListener;
        this.mNodeList = mNodeList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mNodeList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNodeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        ViewHolder holder = null;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = this.mInflater.inflate(R.layout.item_listview_select, null);
            holder = new ViewHolder();
            holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.personnel_listview_checkbox);
            holder.mText = (TextView) convertView.findViewById(R.id.personnel_listview_content);
            convertView.setTag(holder);
        }

        holder.mText.setText(mNodeList.get(position).getText());
        if(mNodeList.get(position).isChecked()){
            holder.mText.setTextColor(Color.BLACK);
        }else {
            holder.mText.setTextColor(context.getResources().getColor(R.color.dark_grey));
        }
        holder.mCheckBox.setChecked(mNodeList.get(position).isChecked());
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mNodeList.get(pos).setChecked(isChecked);
                onSelectListener.onSelectNotificate();
            }
        });

        return convertView;
    }

    public class ViewHolder {
        public TextView mText;
        public CheckBox mCheckBox;
    }
}
