package hcmute.edu.vn.nhom.project_foody.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import hcmute.edu.vn.nhom.project_foody.myclass.Mon_An;
import hcmute.edu.vn.nhom.project_foody.R;

public class List_menu_Adapter extends BaseExpandableListAdapter {

    private static final String TAG = "CustomAdapter";
    private Context mContext;
    private List<String> mHeaderGroup;
    private HashMap<String, List<Mon_An>> mDataChild;

    public List_menu_Adapter(Context context, List<String> headerGroup, HashMap<String, List<Mon_An>> datas) {
        mContext = context;
        mHeaderGroup = headerGroup;
        mDataChild = datas;
    }

    @Override
    public int getGroupCount() {
        return mHeaderGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mDataChild.get(mHeaderGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mHeaderGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mDataChild.get(mHeaderGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater li = LayoutInflater.from(mContext);
            convertView = li.inflate(R.layout.list_group_menu, parent, false);
        }

        TextView tvHeader = (TextView) convertView.findViewById(R.id.listTitle);
        tvHeader.setText(mHeaderGroup.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater li = LayoutInflater.from(mContext);
            convertView = li.inflate(R.layout.list_item_menu, parent, false);
        }

        TextView tvStudentName = (TextView) convertView.findViewById(R.id.expandedListItem_name);
        TextView tvStudentMediumScore = (TextView) convertView.findViewById(R.id.expandedListItem_price);
        tvStudentName.setText(((Mon_An) getChild(groupPosition, childPosition)).getName_food());
        tvStudentMediumScore.setText(String.valueOf(((Mon_An) getChild(groupPosition, childPosition)).getPrice_food()));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}