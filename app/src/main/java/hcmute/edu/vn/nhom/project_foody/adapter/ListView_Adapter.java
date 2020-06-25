package hcmute.edu.vn.nhom.project_foody.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.nhom.project_foody.myclass.Tinh_Thanh;
import hcmute.edu.vn.nhom.project_foody.R;

public class ListView_Adapter extends BaseAdapter implements Filterable {

    List<Tinh_Thanh> listPlace;
    List<Tinh_Thanh> listPlace2;
    LayoutInflater inflater;
    Activity activity;
    ViewHolder holder=null;
    ItemFilter filter = new ItemFilter();

    public ListView_Adapter(Activity activity,List<Tinh_Thanh> listPlace)
    {
        this.activity =activity;
        this.listPlace = listPlace;
        this.listPlace2 = listPlace;
        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return listPlace.size();
    }

    @Override
    public Object getItem(int position)
    {
        return listPlace.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_place_view, parent, false);
            holder = new ViewHolder();
            holder.txt_name_place = (TextView) convertView.findViewById(R.id.name_place_id);
            holder.img_check_place = (ImageView) convertView.findViewById(R.id.img_check_place);
            convertView.setTag(holder);
        }
        else holder = (ViewHolder)convertView.getTag();
        //Bind dữ liệu phần tử vào View
        Tinh_Thanh palce = (Tinh_Thanh) getItem(position);
        if(palce.getSelectded())
        {
            holder.txt_name_place.setText(palce.getName_place());
            holder.txt_name_place.setTextColor(Color.parseColor("#0564AF"));
            holder.img_check_place.setImageResource(R.drawable.tick);
        }
        else {
            holder.txt_name_place.setText(palce.getName_place());
            holder.txt_name_place.setTextColor(Color.parseColor("#FF000000"));
            holder.img_check_place.setImageResource(0);
        }
        return convertView;
    }
    public void updateRecords(List<Tinh_Thanh> listPlace){
        this.listPlace = listPlace;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            String filterString = constraint.toString().toLowerCase().trim();
            if (!TextUtils.isEmpty(filterString)) {
                // We perform filtering operation
                final ArrayList<Tinh_Thanh> nlist = new ArrayList<Tinh_Thanh>();
                for (Tinh_Thanh item : listPlace2) {
                    if (item.getName_place().toLowerCase().contains(filterString)) {
                        nlist.add(item);
                    }
                }
                results.values = nlist;
                results.count = nlist.size();
            }

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results != null && results.count > 0) {
                listPlace = (ArrayList<Tinh_Thanh>) results.values;
                // update the filtered data
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
    class ViewHolder{
        TextView txt_name_place;
        ImageView img_check_place;
    }
}
