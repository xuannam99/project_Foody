package hcmute.edu.vn.nhom.project_foody.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hcmute.edu.vn.nhom.project_foody.R;
import hcmute.edu.vn.nhom.project_foody.adapter.List_menu_Adapter;
import hcmute.edu.vn.nhom.project_foody.database.database;
import hcmute.edu.vn.nhom.project_foody.myclass.Mon_An;
import hcmute.edu.vn.nhom.project_foody.myclass.Thuc_Don;

public class Menu_Food_Activity extends AppCompatActivity {

    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<Mon_An>> expandableListDetail;
    ImageView img_back_menu;
    private static final String TAG = "MainActivity";
    private ExpandableListView expandable_list_food;
    private HashMap<String, List<Mon_An>> mData;

    TextView txttenquan ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__food_);

        img_back_menu = (ImageView) findViewById(R.id.img_back_menu);

        final Intent intent = getIntent();
        int mathucdon = (int) intent.getSerializableExtra("mathucdon");
        String tenquan = intent.getStringExtra("tenquan");
        txttenquan= (TextView)findViewById(R.id.textViewTenquan);
        txttenquan.setText(tenquan);

        database db = new database(this, "foody",null, 1);

        expandable_list_food = (ExpandableListView) findViewById(R.id.expandable_list_food);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);


        final List<String> listHeader = new ArrayList<>();
        List<Thuc_Don> menuFoods = db.DanhSachThucDonTheoMaThucDon(mathucdon);
        for (Thuc_Don menus: menuFoods) {
            listHeader.add(db.TenLoaiMonTheoMaMon(menus.getMaloaimon()));
        }


        //data for child
        mData = new HashMap<>();
        int i = 0;
        for (Thuc_Don menus: menuFoods) {
            List<Mon_An> listBo = new ArrayList<>();
            listBo.addAll(db.DanhSachMonTheoLoaiMonAn(menus.getMaloaimon()));
            mData.put(listHeader.get(i), listBo);
            i++;
        }
        List_menu_Adapter adapter = new List_menu_Adapter(this, listHeader, mData);
        expandable_list_food.setAdapter(adapter);

        expandable_list_food.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        expandable_list_food.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        expandable_list_food.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });


        img_back_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
