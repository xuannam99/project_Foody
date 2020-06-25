package hcmute.edu.vn.nhom.project_foody.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import hcmute.edu.vn.nhom.project_foody.R;
import hcmute.edu.vn.nhom.project_foody.main.Food_Activity;
import hcmute.edu.vn.nhom.project_foody.myclass.Quan_An;


public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 58;
    private final int VIEW_TYPE_LOADING = 1;
    private List<Quan_An> lstFood;
    private Context mContext;
    int REQUEST_CODE_FOOD=102;


    public RecyclerviewAdapter(Context mContext,List<Quan_An> itemList) {
        this.mContext = mContext;
        this.lstFood = itemList;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_food, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading_food, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemViewHolder) {
            populateItemRows((ItemViewHolder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }

    }

    @Override
    public int getItemCount() {
        return lstFood == null ? 0 : lstFood.size();
    }

    /**
     * The following method decides the type of ViewHolder to display in the RecyclerView
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return lstFood.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView name_food, content;
        ImageView img_food;
        CardView cardView;
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name_food = (TextView) itemView.findViewById(R.id.food_name_id);
            content = (TextView) itemView.findViewById(R.id.food_content_id);
            img_food = (ImageView) itemView.findViewById(R.id.food_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            img_food.setClipToOutline(true);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }

    private void populateItemRows(ItemViewHolder viewHolder, final int position) {

        Quan_An item = lstFood.get(position);
        viewHolder.name_food.setText(ChangeLongText(lstFood.get(position).getTenquan(),20));
        viewHolder.content.setText(ChangeLongText(lstFood.get(position).getDoangioithieu(),45));

        Glide.with(mContext)
                .load(lstFood.get(position).getAnhdaidien())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.img_food);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Food_Activity.class);
                intent.putExtra("maquan",lstFood.get(position).getMaquan());
                ((Activity)mContext).startActivity(intent);
            }
        });
    }
    public String ChangeLongText(String text, int len){
        String  tam="";
        String chuoicong="...";
        if(len > text.length()){
            len = text.length();
            chuoicong="";
        }

        for (int i = 0; i<len; i++){
            tam = tam+text.charAt(i);
        }
        tam = tam +chuoicong;
        return tam;
    }
}



