package hcmute.edu.vn.nhom.project_foody.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.nhom.project_foody.myclass.Tinh_Thanh;
import hcmute.edu.vn.nhom.project_foody.R;
import hcmute.edu.vn.nhom.project_foody.adapter.RecyclerviewAdapter_search;
import hcmute.edu.vn.nhom.project_foody.myclass.Quan_An;

public class Search_Food_Activity extends AppCompatActivity implements View.OnClickListener {

    List<Tinh_Thanh> listplace = MainActivity.lstplace;
    List<Quan_An> lst;
    TextView txt_Loc_DungNhat,txt_loc_GanToi,txt_setmau_01,txt_setmau_02,txt_name_address_search;
    ImageView img_quaylai_search;
    RecyclerviewAdapter_search myAdapter;
    EditText edit_search_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__food_);

        anhxa();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        lst = (List<Quan_An>) bundle.getSerializable("ListFood");
        edit_search_id.setText(intent.getStringExtra("search"));


        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_search_id);
        myAdapter = new RecyclerviewAdapter_search(this,lst);
        myrv.setLayoutManager(new GridLayoutManager(this,1));
        myrv.setAdapter(myAdapter);
        click();
        edit_search_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    edit_search_id.setVisibility(View.VISIBLE);
                    myAdapter.getFilter().filter(s.toString());
                } else {
                    // edit_search_place.setVisibility(View.GONE);
                    myAdapter.updateRecords(lst);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void click(){
        txt_Loc_DungNhat.setOnClickListener(this);
        txt_loc_GanToi.setOnClickListener(this);
        img_quaylai_search.setOnClickListener(this);
    }
    public void  anhxa(){
        txt_name_address_search = (TextView)findViewById(R.id.txt_name_address_search);
        txt_Loc_DungNhat = (TextView) findViewById(R.id.txt_search_dungnhat);
        txt_loc_GanToi = (TextView) findViewById(R.id.txt_search_gantoi);
        txt_setmau_01 = (TextView) findViewById(R.id.txt_set_click_01);
        txt_setmau_02 = (TextView) findViewById(R.id.txt_set_click_02);
        img_quaylai_search = (ImageView) findViewById(R.id.img_quaylai_search);
        edit_search_id = (EditText) findViewById(R.id.edit_search_id);

        // xet gia font ban dau cho UI
        txt_setmau_01.setBackgroundColor(Color.parseColor("#4f9bf0"));
        txt_setmau_02.setBackgroundColor(Color.parseColor("#f1e2e2"));
        txt_name_address_search.setText(listplace.get(MainActivity.ID_Place).getName_place());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            // xet mau cho kieu search
            case R.id.txt_search_dungnhat:
                txt_setmau_01.setBackgroundColor(Color.parseColor("#4f9bf0"));
                txt_setmau_02.setBackgroundColor(Color.parseColor("#f1e2e2"));
                break;
            case R.id.txt_search_gantoi:
                txt_setmau_02.setBackgroundColor(Color.parseColor("#4f9bf0"));
                txt_setmau_01.setBackgroundColor(Color.parseColor("#f1e2e2"));

                List<Quan_An> list_loc = new ArrayList<>();
                list_loc.addAll(lst);


                break;
            case R.id.img_quaylai_search:
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
        }
    }
}
