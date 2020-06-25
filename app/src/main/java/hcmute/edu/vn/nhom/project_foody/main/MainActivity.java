package hcmute.edu.vn.nhom.project_foody.main;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.nhom.project_foody.R;
import hcmute.edu.vn.nhom.project_foody.adapter.RecyclerviewAdapter;
import hcmute.edu.vn.nhom.project_foody.database.database;
import hcmute.edu.vn.nhom.project_foody.myclass.Quan_An;
import hcmute.edu.vn.nhom.project_foody.myclass.Tinh_Thanh;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static List<Tinh_Thanh> lstplace;
    public static int ID_Place=0;
    List<Quan_An> lstFood;
    EditText edit_search_main;
    TextView txt_place_search;
    RecyclerView recyclerView;
    RecyclerviewAdapter myAdapter;
    Intent intent;
    RecyclerView myrv;
    boolean isLoading = false;
    int REQUEST_CODE_PLACE = 100;
    int REQUEST_CODE_SEARCH_FOOD = 101;
    int REQUEST_CODE_FOOD=102;
    database db = new database(this, "foody",null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_search_main = (EditText) findViewById(R.id.edit_search_main);
        txt_place_search = (TextView) findViewById(R.id.txt_place_search) ;
        recyclerView =(RecyclerView)findViewById(R.id.recyclerview_id);
        myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        lstFood = new ArrayList<>();

        if(lstFood.size()==0){
            db.Khoitaotatcabang();
            db.ThemdulieuALL();
        }


        lstplace = new ArrayList<>();
        Cursor cursor = db.GetListDataTinhThanh();
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false) {
            lstplace.add(new Tinh_Thanh( false,cursor.getString(1)+"", cursor.getInt(0)));
            cursor.moveToNext();
        }

        lstFood.addAll(db.DanhSachTATCAQUAN(0,9,ID_Place));
        myAdapter = new RecyclerviewAdapter(this,lstFood);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);
        initScrollListener();
        click();
        edit_search_main.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
                {
                    intent = new Intent(MainActivity.this, Search_Food_Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ListFood", (Serializable) lstFood);
                    intent.putExtras(bundle);
                    intent.putExtra("search",edit_search_main.getText());
                    startActivityForResult(intent,REQUEST_CODE_SEARCH_FOOD);
                    return true;
                }
                return false;
            }
        });

    }
    public void click(){
        //edit_search_id.setOnClickListener(this);
        txt_place_search.setOnClickListener(this);

    }
    public void Load(){
        lstFood.clear();
        lstFood.addAll(db.DanhSachTATCAQUAN(0,9,ID_Place));
        myAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_PLACE && resultCode == RESULT_OK && data !=null){
            // lấy dữ liệu từ place
            txt_place_search.setText(data.getStringExtra("Name_Place"));
            ID_Place = Integer.parseInt( data.getStringExtra("ID_Place"));
            Load();

        }
        if (requestCode == REQUEST_CODE_SEARCH_FOOD && resultCode == RESULT_OK && data !=null){

        }
        if (requestCode == REQUEST_CODE_FOOD && resultCode == RESULT_OK && data !=null){
            Toast.makeText(this, "12344",Toast.LENGTH_SHORT).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    // sự kiện click
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
//            case R.id.edit_search_id:
//                break;
            case R.id.txt_place_search:
                intent = new Intent(MainActivity.this, Place_Activity.class);
                startActivityForResult(intent,REQUEST_CODE_PLACE);
        }
    }
    //load dữ liệu
    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == lstFood.size() - 1) {
                        //bottom of list!
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }
    // load more
    private void loadMore() {
        lstFood.add(null);
        myAdapter.notifyItemInserted(lstFood.size() - 1);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                lstFood.remove(lstFood.size() - 1);
                int scrollPosition = lstFood.size();
                myAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 10;
                populateData(MainActivity.this,currentSize,nextLimit);

                myAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }
    // Lấy dữ liệu foody
    private void populateData(Activity activity, int start, int end) {
        int currentSize = lstFood.size();
        int nextlimit =currentSize +10;
        List<Quan_An> list= db.DanhSachTATCAQUAN(currentSize-1,nextlimit,ID_Place);
        lstFood.addAll(list);
    }
}
