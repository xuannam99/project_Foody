package hcmute.edu.vn.nhom.project_foody.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.nhom.project_foody.R;
import hcmute.edu.vn.nhom.project_foody.main.Food_Activity;
import hcmute.edu.vn.nhom.project_foody.myclass.GetDistance;
import hcmute.edu.vn.nhom.project_foody.myclass.Quan_An;

public class RecyclerviewAdapter_search extends RecyclerView.Adapter<RecyclerviewAdapter_search.MyviewHolder> implements Filterable {

    private Context mContext;
    private List<Quan_An> lstFood;
    private List<Quan_An> lstFoodFiltered;
    itemFilter filter = new itemFilter();

    public RecyclerviewAdapter_search(Context mContext, List<Quan_An> lstFood) {
        this.mContext = mContext;
        this.lstFood = lstFood;
    }

    @NonNull
    @Override
    public RecyclerviewAdapter_search.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_food_log,parent,false);
        return new RecyclerviewAdapter_search.MyviewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int position) {

        GetDistance getDistance = new GetDistance(lstFood.get(position).getDiachi(),mContext);

        holder.name_food.setText(lstFood.get(position).getTenquan());
        holder.addres.setText(lstFood.get(position).getDiachi());
//        holder.img_food.setImageResource(lstFood.get(position).getImage());
        holder.species.setText(lstFood.get(position).getTenquan());
        holder.range_food.setText(getDistance.Distance()+" km");
        Glide.with(mContext)
                .load(lstFood.get(position).getAnhdaidien())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img_food);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Food_Activity.class);
                intent.putExtra("maquan",lstFood.get(position).getMaquan());

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstFood.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{

        TextView name_food,addres,species,range_food;
        ImageView img_food;
        CardView cardView;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            name_food = (TextView) itemView.findViewById(R.id.food_name_search_id);
            img_food = (ImageView) itemView.findViewById(R.id.food_img_search_id);
            species = (TextView) itemView.findViewById(R.id.food_species_search_id);
            addres = (TextView) itemView.findViewById(R.id.food_address_search_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            range_food = (TextView) itemView.findViewById(R.id.range_food_id);
        }
    }

    public void updateRecords(List<Quan_An> listPlace){
        this.lstFood = listPlace;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    private class itemFilter extends Filter
    {
        @Override
        protected Filter.FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            String charString = charSequence.toString().toLowerCase().trim();
            if (charString.isEmpty()) {
                lstFood = lstFoodFiltered;
            } else {
                ArrayList<Quan_An> filteredList = new ArrayList<>();
                for (Quan_An row : lstFood) {
                    // name match condition. this might differ depending on your requirement
                    // here we are looking for name or phone number match
                    if (row.getTenquan().toLowerCase().contains(charString) || row.getDiachi().contains(charString)) {
                        filteredList.add(row);
                    }
                }
                lstFood = filteredList;
                results.values = filteredList;
                results.count = filteredList.size();
            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults
        filterResults) {
            if (filterResults != null && filterResults.count > 0) {
                lstFood = (ArrayList<Quan_An>) filterResults.values;
                // update the filtered data
                notifyDataSetChanged();
            } else {
              //  notifyDataSetInvalidated();
            }
        }
    };
}
