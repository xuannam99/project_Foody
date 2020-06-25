package hcmute.edu.vn.nhom.project_foody.main;
// Activity giao dien khi chon vao do an

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import hcmute.edu.vn.nhom.project_foody.R;
import hcmute.edu.vn.nhom.project_foody.database.database;
import hcmute.edu.vn.nhom.project_foody.myclass.AppLocationService;
import hcmute.edu.vn.nhom.project_foody.myclass.GetDistance;
import hcmute.edu.vn.nhom.project_foody.myclass.Quan_An;

public class Food_Activity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "GeocodingLocation";
    Button btn_list_menu_food;
    private GoogleMap mMap;
    double Latitude_Store,Longtitude_Store,Longtitude_User,Latitude_User;
    TextView txt_Address, txt_Add_wifi,txt_status,txt_time_ckeck, txtName, txt_Address_Top,txt_dot;
    ImageView img_quaylai_food, img_avatarpicture,img_dot;
    int REQUEST_CODE_ITEM_MENU = 100;
    Quan_An store = null;
    String address;
    AppLocationService appLocationService;
//    ResultReceiver resultReceiver;
//    private static final int REQUET_CODE_LOCATION_PERMISSION =1;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_);


        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(Food_Activity.this);

        Anhxa();
        Intent intent = getIntent();
        int maquan = (int) intent.getSerializableExtra("maquan");

        database db = new database(this, "foody",null, 1);
        store = db.DanhSachQUANTHEOMAQUAN(maquan);

        // xet thoi gian
        check_time();

        // xet khoang cach
        address = store.getDiachi();
        GetDistance getDistance = new GetDistance(address,Food_Activity.this);
        txt_dot.setText(String.valueOf(getDistance.Distance()));

        txt_Add_wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLogin();
            }
        });
        btn_list_menu_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Food_Activity.this, Menu_Food_Activity.class);
                intent.putExtra("mathucdon", store.getMathucdon());
                intent.putExtra("tenquan",store.getTenquan());
                startActivityForResult(intent, REQUEST_CODE_ITEM_MENU);
            }
        });
        img_quaylai_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        ExtractData();

    }

    private void ExtractData() {

        txt_Address.setText(store.getDiachi());
        txtName.setText(store.getTenquan());
        txt_Address_Top.setText( MainActivity.lstplace.get(MainActivity.ID_Place).getName_place());

        Glide.with(this)
                .load(store.getAnhdaidien())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(this.img_avatarpicture);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(Latitude_Store, Longtitude_Store);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_ITEM_MENU && resultCode == RESULT_OK && data !=null){
            int maquan = data.getIntExtra("maquan",0);
            database db = new database(this, "foody",null, 1);
            store = db.DanhSachQUANTHEOMAQUAN(maquan);
            Toast.makeText(this, store.getTenquan(), Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void DialogLogin(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.login_wifi);
        dialog.setCanceledOnTouchOutside(true);

        // code ánh xạ + sự kiện submit ở đây

        dialog.show();
    }
    public void Anhxa(){
        txt_Address = (TextView) findViewById(R.id.txt_location);
        btn_list_menu_food = (Button) findViewById(R.id.btn_list_menu_food);
        txt_Add_wifi = (TextView) findViewById(R.id.txt_Add_wifi);
        img_quaylai_food = (ImageView) findViewById(R.id.img_quaylai_food);
        txt_time_ckeck = (TextView) findViewById(R.id.txt_time_ckeck);
        txt_status = (TextView) findViewById(R.id.txt_status);
        txt_dot = (TextView) findViewById(R.id.txt_dot);
        txtName =(TextView)findViewById(R.id.txt_detail_namefood_id);
        txt_Address_Top = (TextView)findViewById(R.id.txt_detail_addressfood_id);
        img_avatarpicture =(ImageView)findViewById(R.id.img_avatarpicture);
        img_dot = (ImageView) findViewById(R.id.img_dot);
    }
    // xet trang thay mo cua
    public void check_time(){
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+ c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String format_time = df.format(c.getTime());

        String h = String.valueOf(format_time.charAt(0));
        h = h.concat(String.valueOf(format_time.charAt(1)));
        int hours = Integer.parseInt(h);

        String m = String.valueOf(format_time.charAt(3));
        m = m.concat(String.valueOf(format_time.charAt(4)));
        int minute = Integer.parseInt(m);

        int summinute = hours*60 + minute ;

        // tính giờ data mở cửa
        String h_open = String.valueOf(store.getThoigianmocua().charAt(0));
        h_open = h_open.concat(String.valueOf(store.getThoigianmocua().charAt(1)));

        int hours_open = Integer.parseInt(h_open);

        String m_open = String.valueOf(store.getThoigianmocua().charAt(3));
        m_open = m_open.concat(String.valueOf(store.getThoigianmocua().charAt(4)));
        int minute_open = Integer.parseInt(m_open);


        int summinute_open = hours_open*60 + minute_open;

        // tính giờ data đóng cửa
        String h_close = String.valueOf(store.getThoigiandongcua().charAt(0));
        h_close = h_close.concat(String.valueOf(store.getThoigiandongcua().charAt(1)));
        int hours_close = Integer.parseInt(h_close);

        String m_close = String.valueOf(store.getThoigiandongcua().charAt(3));
        m_close = m_close.concat(String.valueOf(store.getThoigiandongcua().charAt(4)));
        int minute_close = Integer.parseInt(m_close);

        int summinute_close = hours_close*60 + minute_close;

        if(summinute >= summinute_open && summinute <= summinute_close )
        {
            txt_status.setText("Đang mở cửa");
        }
        else
        {
            txt_status.setText("Chưa mở cửa");
        }

        txt_time_ckeck.setText(store.getThoigianmocua() +" - " + store.getThoigiandongcua());
    }
}

