package hcmute.edu.vn.nhom.project_foody.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import hcmute.edu.vn.nhom.project_foody.adapter.ListView_Adapter;
import hcmute.edu.vn.nhom.project_foody.myclass.Tinh_Thanh;
import hcmute.edu.vn.nhom.project_foody.R;

public class Place_Activity extends AppCompatActivity implements View.OnClickListener {

    List<Tinh_Thanh> listplace = MainActivity.lstplace;
    ListView_Adapter place_Adapter;
    public static ListView listView;
    int ID_place = MainActivity.ID_Place;
    TextView txt_finish_place,txt_place_huy;
    EditText edit_search_place;
    ImageView imageView;
    int res = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_);

        AnhXa();
        place_Adapter = new ListView_Adapter(Place_Activity.this,listplace);
        set_Place(); // xet place ban dau
        listView.setAdapter(place_Adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // kiểm tra người dùng click tỉnh thành khác thì thực hiện thay đổi
                if(res != position) {
                    // thay đổi màu text và đánh tích
                    Tinh_Thanh place_model = listplace.get(position);
                    place_model.setSelectded(true);
                    listplace.set(position, place_model);
                    if (res > -1) {
                        Tinh_Thanh place = listplace.get(res);
                        place.setSelectded(false);
                        listplace.set(res, place);
                    }
                    // view1.setTextColor( Color.parseColor("#0564AF"));
                    res = position;
                    place_Adapter.updateRecords(listplace);
                    ID_place = position;
                }

            }
        });
        // search place
        edit_search_place.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString().trim();
                if (!TextUtils.isEmpty(s)) {
                    edit_search_place.setVisibility(View.VISIBLE);
                } else {
                   // edit_search_place.setVisibility(View.GONE);
                    place_Adapter.updateRecords(listplace);
                }
                place_Adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        click();
    }
    public void AnhXa(){
        imageView = (ImageView) findViewById(R.id.img_check_place);
        listView = (ListView) findViewById(R.id.lstview_place_id);
        txt_finish_place = (TextView) findViewById(R.id.txt_finish_place);
        txt_place_huy = (TextView) findViewById(R.id.txt_place_huy);
        edit_search_place = (EditText) findViewById(R.id.edit_search_place);
    }
    public void set_Place() {
        // thay đổi màu text và đánh tích
        Tinh_Thanh place_model = listplace.get(MainActivity.ID_Place);
        place_model.setSelectded(true);
        listplace.set(MainActivity.ID_Place, place_model);
        if (res > -1) {
            Tinh_Thanh place = listplace.get(res);
            place.setSelectded(false);
            listplace.set(res, place);
        }
        res = MainActivity.ID_Place;
        // view1.setTextColor( Color.parseColor("#0564AF"));
        place_Adapter.updateRecords(listplace);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()) {
            case R.id.txt_finish_place:
                // Quay lại trang chủ trả về id của địa tỉnh thành
                intent = new Intent();
                intent.putExtra("Name_Place",listplace.get(ID_place).getName_place());
                intent.putExtra("ID_Place",String.valueOf(ID_place));
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.txt_place_huy:
                intent = null;
                setResult(RESULT_OK,intent);
                finish();

        }
    }
    public void click(){
        txt_finish_place.setOnClickListener(this);
        txt_place_huy.setOnClickListener(this);
    }
}
