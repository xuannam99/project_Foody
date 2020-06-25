package hcmute.edu.vn.nhom.project_foody.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.nhom.project_foody.myclass.Mon_An;
import hcmute.edu.vn.nhom.project_foody.myclass.Thuc_Don;
import hcmute.edu.vn.nhom.project_foody.myclass.Quan_An;

public class database extends SQLiteOpenHelper {
    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void QueryData(String sql){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql){
        SQLiteDatabase database = this.getReadableDatabase();
        return  database.rawQuery(sql, null);
    }

    //===============================================TẠO CSDL=================================================
     public  void TaoCSDL_QUAN_AN(){
        String sql = "CREATE TABLE IF NOT EXISTS QUAN_AN(maquan INTEGER PRIMARY KEY , tenquan NVARCHAR(200), diachi NVARCHAR(500), doangioithieu NVARCHAR(500),anhdaidien VARCHAR(500) , thoigiandongcua NVARCHAR(200), thoigianmocua NVARCHAR(200), matinh INTEGER, maloaihinhkinhdoanh INTEGER, mathucdon INTEGER, mawifi INTEGER)";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(sql);
     }
    public  void TaoCSDL_TINH_THANH(){
        String sql = "CREATE TABLE IF NOT EXISTS TINH_THANH(matinh INTEGER PRIMARY KEY , tentinh NVARCHAR(200) )";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(sql);
    }
    public  void TaoCSDL_LOAI_HINH_KINH_DOANH(){
        String sql = "CREATE TABLE IF NOT EXISTS LOAI_HINH_KINH_DOANH(maloaihinhkinhhdoanh INTEGER PRIMARY KEY , tenloaihinhinhdoanh NVARCHAR(200) )";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(sql);
    }
    public  void TaoCSDL_LOAI_MON(){
        String sql = "CREATE TABLE IF NOT EXISTS LOAI_MON(maloaimon INTEGER PRIMARY KEY , tenloaimon NVARCHAR(200) )";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(sql);
    }
    public  void TaoCSDL_THUC_DON(){
        String sql = "CREATE TABLE IF NOT EXISTS THUC_DON(mathucdon INTEGER, maloaimon INTEGER, tenthucdon NVARCHAR(200), PRIMARY KEY (mathucdon, maloaimon))";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(sql);
    }
    public  void TaoCSDL_DANH_SACH_MON(){
        String sql = "CREATE TABLE IF NOT EXISTS DANH_SACH_MON(mamonan INTEGER , maloaimon INTEGER , tenmonan NVARCHAR(200), anh NVACHAR(500), gia FLOAT DEFAULT (0), PRIMARY KEY (mamonan, maloaimon))";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(sql);
    }
    public  void TaoCSDL_WIFI(){
        String sql = "CREATE TABLE IF NOT EXISTS WIFI(mawifi INTEGER NOT NULL, maquan INTEGER NOT NULL, tenwifi NVARCHAR(200), password VARCHAR(200), PRIMARY KEY (mawifi, maquan))";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(sql);
    }

//    public Cursor GetDataQuanAn(){
//        String sql = "SELECT * FROM QUAN_AN";
//        SQLiteDatabase database = getReadableDatabase();
//        return  database.rawQuery(sql, null);
//    }

    public void Khoitaotatcabang(){
        TaoCSDL_QUAN_AN();
        TaoCSDL_TINH_THANH();
        TaoCSDL_LOAI_HINH_KINH_DOANH();
        TaoCSDL_THUC_DON();
        TaoCSDL_LOAI_MON();
        TaoCSDL_DANH_SACH_MON();
        TaoCSDL_WIFI();
    }
    //========================================================================================================

    //===============================================THEM_CSDL================================================
    //                              maquan         , tenquan      , diachi       , doangioithieu        ,anhdaidien        , thoigiandongcua      , thoigianmocua       , matinh   , maloaihinhkinhdoanh     , mathucdon     , mawifi
    public void ThemMoi_QUAN_AN(int maquan , String tenquan ,String diachi, String doangioithieu, String anhdaidien , String thoigiandongcua , String thoigianmocua , int matinh , int maloaihinhkinhdoanh , int mathucdon, int mawifi){
//        String sql = "INSERT INTO QUAN_AN(maquan, tenquan, doangioithieu, thoigiandongcua, thoigianmocua, matinh , maloaihinhkinhdoanh, mathucdon)" +
//                "VALUES(?,?,?,?,?,?,?)";
//                new String[]{maquan, tenquan, doangioithieu, thoigiandongcua, thoigianmocua, matinh , maloaihinhkinhdoanh, mathucdon)}

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maquan", maquan);
        values.put("tenquan", tenquan);
        values.put("diachi", diachi);
        values.put("doangioithieu", doangioithieu);
        values.put("anhdaidien", anhdaidien);
        values.put("thoigiandongcua", thoigiandongcua);
        values.put("thoigianmocua", thoigianmocua);
        values.put("matinh", matinh);
        values.put("maloaihinhkinhdoanh", maloaihinhkinhdoanh);
        values.put("mathucdon", mathucdon);
        values.put("mawifi", mawifi);

        database.insert("QUAN_AN",null,values);
        database.close();

    }

    public void ThemMoi_DANH_SACH_MON(int mamonan, int maloaimon, String tenmonan, String anh, float gia){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("mamonan", mamonan);
        values.put("maloaimon", maloaimon);
        values.put("tenmonan", tenmonan);
        values.put("anh", anh);
        values.put("gia", gia);

        db.insert("DANH_SACH_MON", null, values);
    }

    public void ThemMoi_TINH_THANH(int matinh , String tentinh){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("matinh", matinh);
        values.put("tentinh", tentinh);

        db.insert("TINH_THANH", null, values);
    }
    public void ThemMoi_LOAI_MON(int maloaimon , String tenloaimon){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("maloaimon", maloaimon);
        values.put("tenloaimon", tenloaimon);

        db.insert("LOAI_MON", null, values);
    }

    public void ThemMoi_LOAI_HINH_KINH_DOANH(int maloaihinhkinhhdoanh , String tenloaihinhinhdoanh){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("maloaihinhkinhhdoanh", maloaihinhkinhhdoanh);
        values.put("tenloaihinhinhdoanh", tenloaihinhinhdoanh);

        db.insert("LOAI_HINH_KINH_DOANH", null, values);
    }
        //mathudon INTEGER, maloaimonan INTEGER, tenthucdon NVARCHAR(200)
    public void ThemMoi_THUC_DON(int mathucdon , int maloaimon, String tenthucdon){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("mathucdon", mathucdon);
        values.put("maloaimon", maloaimon);
        values.put("tenthucdon", tenthucdon);

        db.insert("THUC_DON", null, values);
    }
    //========================================================================================================



    //=========================================THÊM DỮ LIỆU==================================================

    public void ThemdulieuQUAN_AN(){
        ThemMoi_QUAN_AN(1,"Quán bún thịt nướng Hoàng Diệu","123 Hoàng Diệu 2, quận Thủ Đức","bún ngon tuyệt vời", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_1_B%C3%BAn%20th%E1%BB%8Bt%20n%C6%B0%E1%BB%9Bng%2Fb%C3%BAn%2Fb%C3%BAn%20th%E1%BB%8Bt%20n%C6%B0%E1%BB%9Bng.jpg?alt=media&token=e5ff7f73-1e04-4195-800e-5f6cf20e3ada","20:00","06:00",0,1,1,1);
        ThemMoi_QUAN_AN(2,"Quán Trà sữa ","484 Lê Văn Việt, quận 9, tp Hồ Chí Minh","tocotoco nhấp nhô từng nhịp", "https://firebasestorage.googleapis.com/v0/b/dien-toan-dam-may-353aa.appspot.com/o/HCM%2Fquan2-tr%C3%A0%20s%E1%BB%AFa%2Ftra-sua-1038.jpg?alt=media&token=b3f9d395-2055-4ba8-80e5-921a5eef6ebb","20:00","06:00",0,1,2,1);
        ThemMoi_QUAN_AN(3,"Quán cơm gà ","17 Hoàng Diệu 2, quận Thủ Đức","cơm gà ngon đảm bảo vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_3_c%C6%A1m%20g%C3%A0%2Fanh%20%C4%91%E1%BA%A1i%20di%E1%BB%87n.jpg?alt=media&token=50cd2b9f-0a97-4cf3-bdf0-892169e3bb36","20:00","06:00",0,1,3,1);
        ThemMoi_QUAN_AN(4,"Gỏi Cuốn - 19 Trần Huy Liệu "," 19 Trần Huy Liệu, Quận Phú Nhuận, TP. HCM","gỏi cuốn ", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_4-g%E1%BB%8Fi%20cu%E1%BB%91n%2F%E1%BA%A3nh%20%C4%91%E1%BA%A1i%20di%E1%BB%87n.jpg?alt=media&token=1a5e3a73-67e7-4ba3-87b9-020087bec24a","20:00","06:00",0,1,4,1);
        ThemMoi_QUAN_AN(5,"Bao Bei - Chinese Restaurant - Terra Royal ","280 Nam Kỳ Khởi Nghĩa, P. 8, Quận 3","Tầng M, Tòa Nhà Terra Royal, 280 Nam Kỳ Khởi Nghĩa, P. 8, Quận 3", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_5-%C4%91%E1%BB%93%20%C4%83n%20trung%20qu%E1%BB%91c%2Ffoody-upload-api-foody-mobile-wdwd-200110113827.jpg?alt=media&token=d29471cb-8396-4dce-bfca-692fbf8c90c3","20:00","06:00",0,1,5,1);
        ThemMoi_QUAN_AN(6,"Dừa Dầm Trân Châu - Hồ Tùng Mậu","108 Hồ Tùng Mậu, P. Bến Nghé, Quận 1, TP. HCM","đồ ăn ngon đậm đà hợ vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_6%20n%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa%2Fn%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa.jpg?alt=media&token=115e4320-f6d4-44d1-b605-53d686861b6c","20:00","06:00",0,1,6,1);

        ThemMoi_QUAN_AN(7,"Dừa Dầm Trân Châu - Hồ Tùng Mậu","108 Hồ Tùng Mậu, P. Bến Nghé, Quận 1, TP. HCM","đồ ăn ngon đậm đà hợ vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_6%20n%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa%2Fn%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa.jpg?alt=media&token=115e4320-f6d4-44d1-b605-53d686861b6c","20:00","06:00",0,1,6,1);
        ThemMoi_QUAN_AN(8,"Dừa Dầm Trân Châu - Hồ Tùng Mậu","108 Hồ Tùng Mậu, P. Bến Nghé, Quận 1, TP. HCM","đồ ăn ngon đậm đà hợ vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_6%20n%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa%2Fn%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa.jpg?alt=media&token=115e4320-f6d4-44d1-b605-53d686861b6c","20:00","06:00",0,1,6,1);
        ThemMoi_QUAN_AN(9,"Dừa Dầm Trân Châu - Hồ Tùng Mậu","108 Hồ Tùng Mậu, P. Bến Nghé, Quận 1, TP. HCM","đồ ăn ngon đậm đà hợ vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_6%20n%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa%2Fn%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa.jpg?alt=media&token=115e4320-f6d4-44d1-b605-53d686861b6c","20:00","06:00",0,1,6,1);
        ThemMoi_QUAN_AN(10,"Dừa Dầm Trân Châu - Hồ Tùng Mậu","108 Hồ Tùng Mậu, P. Bến Nghé, Quận 1, TP. HCM","đồ ăn ngon đậm đà hợ vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_6%20n%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa%2Fn%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa.jpg?alt=media&token=115e4320-f6d4-44d1-b605-53d686861b6c","20:00","06:00",0,1,6,1);
        ThemMoi_QUAN_AN(11,"Dừa Dầm Trân Châu - Hồ Tùng Mậu","108 Hồ Tùng Mậu, P. Bến Nghé, Quận 1, TP. HCM","đồ ăn ngon đậm đà hợ vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_6%20n%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa%2Fn%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa.jpg?alt=media&token=115e4320-f6d4-44d1-b605-53d686861b6c","20:00","06:00",0,1,6,1);
        ThemMoi_QUAN_AN(12,"Dừa Dầm Trân Châu - Hồ Tùng Mậu","108 Hồ Tùng Mậu, P. Bến Nghé, Quận 1, TP. HCM","đồ ăn ngon đậm đà hợ vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_6%20n%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa%2Fn%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa.jpg?alt=media&token=115e4320-f6d4-44d1-b605-53d686861b6c","20:00","06:00",0,1,6,1);
        ThemMoi_QUAN_AN(13,"Dừa Dầm Trân Châu - Hồ Tùng Mậu","108 Hồ Tùng Mậu, P. Bến Nghé, Quận 1, TP. HCM","đồ ăn ngon đậm đà hợ vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_6%20n%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa%2Fn%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa.jpg?alt=media&token=115e4320-f6d4-44d1-b605-53d686861b6c","20:00","06:00",0,1,6,1);
        ThemMoi_QUAN_AN(14,"Dừa Dầm Trân Châu - Hồ Tùng Mậu","108 Hồ Tùng Mậu, P. Bến Nghé, Quận 1, TP. HCM","đồ ăn ngon đậm đà hợ vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_6%20n%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa%2Fn%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa.jpg?alt=media&token=115e4320-f6d4-44d1-b605-53d686861b6c","20:00","06:00",0,1,6,1);
        ThemMoi_QUAN_AN(15,"Dừa Dầm Trân Châu - Hồ Tùng Mậu","108 Hồ Tùng Mậu, P. Bến Nghé, Quận 1, TP. HCM","đồ ăn ngon đậm đà hợ vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_6%20n%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa%2Fn%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa.jpg?alt=media&token=115e4320-f6d4-44d1-b605-53d686861b6c","20:00","06:00",0,1,6,1);
        ThemMoi_QUAN_AN(16,"Dừa Dầm Trân Châu - Hồ Tùng Mậu","108 Hồ Tùng Mậu, P. Bến Nghé, Quận 1, TP. HCM","đồ ăn ngon đậm đà hợ vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_6%20n%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa%2Fn%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa.jpg?alt=media&token=115e4320-f6d4-44d1-b605-53d686861b6c","20:00","06:00",0,1,6,1);
        ThemMoi_QUAN_AN(17,"Dừa Dầm Trân Châu - Hồ Tùng Mậu","108 Hồ Tùng Mậu, P. Bến Nghé, Quận 1, TP. HCM","đồ ăn ngon đậm đà hợ vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_6%20n%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa%2Fn%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa.jpg?alt=media&token=115e4320-f6d4-44d1-b605-53d686861b6c","20:00","06:00",0,1,6,1);
        ThemMoi_QUAN_AN(18,"Dừa Dầm Trân Châu - Hồ Tùng Mậu","108 Hồ Tùng Mậu, P. Bến Nghé, Quận 1, TP. HCM","đồ ăn ngon đậm đà hợ vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_6%20n%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa%2Fn%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa.jpg?alt=media&token=115e4320-f6d4-44d1-b605-53d686861b6c","20:00","06:00",0,1,6,1);
        ThemMoi_QUAN_AN(19,"Dừa Dầm Trân Châu - Hồ Tùng Mậu","108 Hồ Tùng Mậu, P. Bến Nghé, Quận 1, TP. HCM","đồ ăn ngon đậm đà hợ vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_6%20n%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa%2Fn%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa.jpg?alt=media&token=115e4320-f6d4-44d1-b605-53d686861b6c","20:00","06:00",0,1,6,1);
        ThemMoi_QUAN_AN(20,"Dừa Dầm Trân Châu - Hồ Tùng Mậu","108 Hồ Tùng Mậu, P. Bến Nghé, Quận 1, TP. HCM","đồ ăn ngon đậm đà hợ vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_6%20n%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa%2Fn%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa.jpg?alt=media&token=115e4320-f6d4-44d1-b605-53d686861b6c","20:00","06:00",0,1,6,1);
        ThemMoi_QUAN_AN(21,"Dừa Dầm Trân Châu - Hồ Tùng Mậu","108 Hồ Tùng Mậu, P. Bến Nghé, Quận 1, TP. HCM","đồ ăn ngon đậm đà hợ vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_6%20n%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa%2Fn%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa.jpg?alt=media&token=115e4320-f6d4-44d1-b605-53d686861b6c","20:00","06:00",0,1,6,1);
        ThemMoi_QUAN_AN(22,"Dừa Dầm Trân Châu - Hồ Tùng Mậu","108 Hồ Tùng Mậu, P. Bến Nghé, Quận 1, TP. HCM","đồ ăn ngon đậm đà hợ vệ sinh", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_6%20n%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa%2Fn%C6%B0%E1%BB%9Bc%20d%E1%BB%ABa.jpg?alt=media&token=115e4320-f6d4-44d1-b605-53d686861b6c","20:00","06:00",0,1,6,1);
//        ThemMoi_QUAN_AN(7,"Quán cơm gà ","17 Hoàng Diệu 2, quận Thủ Đức","cơm gà ngon đảm bảo vệ sinh", "","20:00:00","6:00:00",58,1,7,1);
//        ThemMoi_QUAN_AN(8,"Quán cơm gà ","17 Hoàng Diệu 2, quận Thủ Đức","cơm gà ngon đảm bảo vệ sinh", "","20:00:00","6:00:00",58,1,8,1);
//        ThemMoi_QUAN_AN(9,"Quán cơm gà ","17 Hoàng Diệu 2, quận Thủ Đức","cơm gà ngon đảm bảo vệ sinh", "","20:00:00","6:00:00",58,1,9,1);
//        ThemMoi_QUAN_AN(10,"Quán cơm gà ","17 Hoàng Diệu 2, quận Thủ Đức","cơm gà ngon đảm bảo vệ sinh", "","20:00:00","6:00:00",58,1,10,1);

    }
    public void ThemdulieuTINH_THANH(){
        ThemMoi_TINH_THANH(0,"TP.HCM");
        ThemMoi_TINH_THANH(1,"Hà Nội");
        ThemMoi_TINH_THANH(2,"Bà Rịa – Vũng Tàu");
        ThemMoi_TINH_THANH(3,"Bắc Giang");
        ThemMoi_TINH_THANH(4,"Bắc Kạn");
        ThemMoi_TINH_THANH(5,"Bạc Liêu");
        ThemMoi_TINH_THANH(6,"Bắc Ninh");
        ThemMoi_TINH_THANH(7,"Bến Tre");
        ThemMoi_TINH_THANH(8,"Bình Định");
        ThemMoi_TINH_THANH(9,"Bình Dương");
        ThemMoi_TINH_THANH(10,"Bình Phước");
        ThemMoi_TINH_THANH(11,"Bình Thuận");
        ThemMoi_TINH_THANH(12,"Cà Mau");
        ThemMoi_TINH_THANH(13,"Cần Thơ");
        ThemMoi_TINH_THANH(14,"Cao Bằng");
        ThemMoi_TINH_THANH(15,"Đà Nẵng");
        ThemMoi_TINH_THANH(16,"Đắk Lắk");
        ThemMoi_TINH_THANH(17,"Đắk Nông");
        ThemMoi_TINH_THANH(18,"Điện Biên");
        ThemMoi_TINH_THANH(19,"Đồng Nai");
        ThemMoi_TINH_THANH(20,"Đồng Tháp");
        ThemMoi_TINH_THANH(21,"Gia Lai");
        ThemMoi_TINH_THANH(22,"Hà Giang");
        ThemMoi_TINH_THANH(23,"Hà Nam");
        ThemMoi_TINH_THANH(24,"An Giang");
        ThemMoi_TINH_THANH(25,"Hà Tĩnh");
        ThemMoi_TINH_THANH(26,"Hải Dương");
        ThemMoi_TINH_THANH(27,"Hải Phòng");
        ThemMoi_TINH_THANH(28,"Hậu Giang");
        ThemMoi_TINH_THANH(29,"Hòa Bình");
        ThemMoi_TINH_THANH(30,"Hưng Yên");
        ThemMoi_TINH_THANH(31,"Khánh Hòa");
        ThemMoi_TINH_THANH(32,"Kiên Giang");
        ThemMoi_TINH_THANH(33,"Kon Tum");
        ThemMoi_TINH_THANH(34,"Lai Châu");
        ThemMoi_TINH_THANH(35,"Lâm Đồng");
        ThemMoi_TINH_THANH(36,"Lạng Sơn");
        ThemMoi_TINH_THANH(37,"Lào Cai");
        ThemMoi_TINH_THANH(38,"Long An");
        ThemMoi_TINH_THANH(39,"Nam Định");
        ThemMoi_TINH_THANH(40,"Nghệ An");
        ThemMoi_TINH_THANH(41,"Ninh Bình");
        ThemMoi_TINH_THANH(42,"Ninh Thuận");
        ThemMoi_TINH_THANH(43,"Phú Thọ");
        ThemMoi_TINH_THANH(44,"Phú Yên");
        ThemMoi_TINH_THANH(45,"Quảng Bình");
        ThemMoi_TINH_THANH(46,"Quảng Nam");
        ThemMoi_TINH_THANH(47,"Quảng Ngãi");
        ThemMoi_TINH_THANH(48,"Quảng Ninh");
        ThemMoi_TINH_THANH(49,"Quảng Trị");
        ThemMoi_TINH_THANH(50,"Sóc Trăng");
        ThemMoi_TINH_THANH(51,"Sơn La");
        ThemMoi_TINH_THANH(52,"Tây Ninh");
        ThemMoi_TINH_THANH(53,"Thái Bình");
        ThemMoi_TINH_THANH(54,"Thái Nguyên");
        ThemMoi_TINH_THANH(55,"Thanh Hóa");
        ThemMoi_TINH_THANH(56,"Thừa Thiên Huế");
        ThemMoi_TINH_THANH(57,"Tiền Giang");
        ThemMoi_TINH_THANH(58,"Trà Vinh");
        ThemMoi_TINH_THANH(69,"Tuyên Quang");
        ThemMoi_TINH_THANH(60,"Vĩnh Long");
        ThemMoi_TINH_THANH(61,"Vĩnh Phúc");
        ThemMoi_TINH_THANH(62,"Yên Bái");
    }

    public void ThemdulieuLOAI_MON(){
        //HCM - quán 1_bún thịt nướng
        ThemMoi_LOAI_MON(1,"bún");
        ThemMoi_LOAI_MON(2,"nước");



        //HCM - quán 2_Trà sửa
        ThemMoi_LOAI_MON(3,"trà sữa");
        ThemMoi_LOAI_MON(4,"đồ ăn vặt");
    }

    public void Themdulieu_THUC_DON(){
        //HCM- quán 1 _bún thịt nướng
        ThemMoi_THUC_DON(1,1,"");
        ThemMoi_THUC_DON(1,2,"");

        //HCM - quán 2_Trà sửa
        ThemMoi_THUC_DON(2,3,"");
        ThemMoi_THUC_DON(2,4,"");
    }

    public void ThemdulieuDANH_SACH_MON(){
        //HCM- quán 1 _bún thịt nướng
        ThemMoi_DANH_SACH_MON(1,1,"Bún thị nướng Hoàng Diệu","https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_1_B%C3%BAn%20th%E1%BB%8Bt%20n%C6%B0%E1%BB%9Bng%2Fb%C3%BAn%2Fb%C3%BAn%20th%E1%BB%8Bt%20n%C6%B0%E1%BB%9Bng.jpg?alt=media&token=e5ff7f73-1e04-4195-800e-5f6cf20e3ada",20000);
        ThemMoi_DANH_SACH_MON(2,1,"Bún riêu cua","https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_1_B%C3%BAn%20th%E1%BB%8Bt%20n%C6%B0%E1%BB%9Bng%2Fb%C3%BAn%2Fbun-rieu-cua.png?alt=media&token=429b3b51-cbcc-4480-b44c-bd693916c9d5",25000);
        ThemMoi_DANH_SACH_MON(3,1,"Bún bò huế","https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_1_B%C3%BAn%20th%E1%BB%8Bt%20n%C6%B0%E1%BB%9Bng%2Fb%C3%BAn%2Fbun_bo_hue.jpg?alt=media&token=a2563dcb-4ce1-4543-a426-a35eb9321eda",30000);
        ThemMoi_DANH_SACH_MON(4,1,"Bún chả","https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_1_B%C3%BAn%20th%E1%BB%8Bt%20n%C6%B0%E1%BB%9Bng%2Fb%C3%BAn%2Fb%C3%BAn%20ch%E1%BA%A3.jpg?alt=media&token=0f014609-9f85-4109-b2d5-58ab5232b329",30000);
        ThemMoi_DANH_SACH_MON(5,1,"Bún xào","https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_1_B%C3%BAn%20th%E1%BB%8Bt%20n%C6%B0%E1%BB%9Bng%2Fb%C3%BAn%2Fb%C3%BAn%20ch%E1%BA%A3.jpg?alt=media&token=0f014609-9f85-4109-b2d5-58ab5232b329",20000);
        ThemMoi_DANH_SACH_MON(6,1,"Bún đậu mắm tôm","https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_1_B%C3%BAn%20th%E1%BB%8Bt%20n%C6%B0%E1%BB%9Bng%2Fb%C3%BAn%2Fb%C3%BAn%20%C4%91%E1%BA%ADu%20m%E1%BA%AFm%20t%C3%B4m.jpg?alt=media&token=1cc5382f-89ef-4732-9e32-fa10dd89c533",40000);

        ThemMoi_DANH_SACH_MON(7,2, "Trà đá","https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_1_B%C3%BAn%20th%E1%BB%8Bt%20n%C6%B0%E1%BB%9Bng%2Fn%C6%B0%E1%BB%9Bc%2Ftr%C3%A0%20%C4%91%C3%A1.jpg?alt=media&token=b38481aa-6ac9-4fcc-b0c5-63544a3567ab",2000);
        ThemMoi_DANH_SACH_MON(8,2, "Coca-Cola","https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_1_B%C3%BAn%20th%E1%BB%8Bt%20n%C6%B0%E1%BB%9Bng%2Fn%C6%B0%E1%BB%9Bc%2Fcoca.jpg?alt=media&token=6ebcf218-390d-4f26-b7b0-67915dc529b4",8000);
        ThemMoi_DANH_SACH_MON(9,2, "Nước sâm","https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_1_B%C3%BAn%20th%E1%BB%8Bt%20n%C6%B0%E1%BB%9Bng%2Fn%C6%B0%E1%BB%9Bc%2Fnuoc-sam-159.jpg?alt=media&token=70b21606-aff4-49f4-bf6b-b23282c65a1f",5000);
        ThemMoi_DANH_SACH_MON(10,2, "Sting","https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_1_B%C3%BAn%20th%E1%BB%8Bt%20n%C6%B0%E1%BB%9Bng%2Fn%C6%B0%E1%BB%9Bc%2Fsting.jpg?alt=media&token=33d503e6-93a6-41e1-8721-d9975ff7a787",10000);

        // HCM-quán 2 trà sữa
        ThemMoi_DANH_SACH_MON(11,3, "Trà sữa matcha", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_2_Tr%C3%A0%20s%E1%BB%AFa%2FTr%C3%A0%20s%E1%BB%AFa%2Fmat%20cha.jpg?alt=media&token=e6e5cac4-a498-4db8-8f82-77ba84a267ed",30000);
        ThemMoi_DANH_SACH_MON(12,3, "Trà sữa ChoCoLate", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_2_Tr%C3%A0%20s%E1%BB%AFa%2FTr%C3%A0%20s%E1%BB%AFa%2FTr%C3%A0-S%E1%BB%AFa-Chocolate-1.jpg?alt=media&token=90106839-2479-4f7a-83c5-dc0b45f4b580",35000);
        ThemMoi_DANH_SACH_MON(13,3, "Trà sữa Trân châu", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_2_Tr%C3%A0%20s%E1%BB%AFa%2FTr%C3%A0%20s%E1%BB%AFa%2Fmon-tra-sua-tran-chau.jpg?alt=media&token=8c2da91b-0ed2-43f8-a1ba-21b2668416f9",35000);
        ThemMoi_DANH_SACH_MON(14,3, "Trà sữa trái cây", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_2_Tr%C3%A0%20s%E1%BB%AFa%2FTr%C3%A0%20s%E1%BB%AFa%2Ftra-sua-1038.jpg?alt=media&token=b8b0ebe7-e3f8-48fa-903b-68e91b7b2b72",40000);
        ThemMoi_DANH_SACH_MON(15,3, "Trà sữa anh đào", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_2_Tr%C3%A0%20s%E1%BB%AFa%2FTr%C3%A0%20s%E1%BB%AFa%2Ftrasuasau.jpg?alt=media&token=08c15ff0-5124-47a7-b65e-c547458b4c9e",30000);
        ThemMoi_DANH_SACH_MON(16,3, "Trà sữa đặc biêt", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_2_Tr%C3%A0%20s%E1%BB%AFa%2FTr%C3%A0%20s%E1%BB%AFa%2Ftr%C3%A0%20s%E1%BB%AFa%201.jpg?alt=media&token=4196474e-8e45-4ed9-a267-9407cbfdd8a2",50000);

        ThemMoi_DANH_SACH_MON(17,4,"Thạch rau câu", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_2_Tr%C3%A0%20s%E1%BB%AFa%2Fv%E1%BA%B7t%2Frau-cau-la-dua-nhan-pho-mai.jpg?alt=media&token=c51a0ed4-9077-4924-912c-8a2b35f76348", 10000);
        ThemMoi_DANH_SACH_MON(18,4,"Kem", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_2_Tr%C3%A0%20s%E1%BB%AFa%2Fv%E1%BA%B7t%2Fkem.jpg?alt=media&token=4fcc2cf7-fad6-44ba-87cc-882fa48b0630", 22000);
        ThemMoi_DANH_SACH_MON(19,4,"Bánh lăng", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_2_Tr%C3%A0%20s%E1%BB%AFa%2Fv%E1%BA%B7t%2Fcach-lam-kem-lang-ngon-1.jpg?alt=media&token=df42a202-cd24-42f3-a702-11894b037369", 10000);
        ThemMoi_DANH_SACH_MON(20,4,"Cá viên chiên", "https://firebasestorage.googleapis.com/v0/b/foody-bf983.appspot.com/o/TP.HCM%2Fquan_2_Tr%C3%A0%20s%E1%BB%AFa%2Fv%E1%BA%B7t%2Fcach-lam-ca-vien-chien-5-600x398.jpg?alt=media&token=79f9660d-67ea-48ac-bd8f-f1180d287eff", 20000);

    }

    public void ThemdulieuALL(){
        ThemdulieuQUAN_AN();
        ThemdulieuTINH_THANH();
        ThemdulieuLOAI_MON();
        Themdulieu_THUC_DON();
        ThemdulieuDANH_SACH_MON();
    }
    //=======================================================================================================


    //=============================================LẤY CSDL===================================================

    // lấy danh sách quán
    public Cursor GetDataQuanAnall(int matinh){
        String sql = "SELECT * FROM QUAN_AN";
        SQLiteDatabase database = getReadableDatabase();
        return  database.rawQuery(sql, null);
    }

    // lấy danh sách quán theo tỉnh thành
    public Cursor GetDataQuanAn(int matinh){
        String sql = "SELECT * FROM QUAN_AN WHERE matinh = ?";
        SQLiteDatabase database = getReadableDatabase();
        return  database.rawQuery(sql, new String[]{String.valueOf(matinh)});
    }
    // lấy quán ăn theo maquan
    public Cursor GetDataQuanAnbyid(int maquan){
        String sql = "SELECT * FROM QUAN_AN WHERE maquan = ?";
        SQLiteDatabase database = this.getReadableDatabase();
        return  database.rawQuery(sql, new String[]{String.valueOf(maquan)});
    }
    // lấy thực đon của quán bằng mã quán
    public Cursor GetDataThucDonAnbyid(int maquan){
        String sql = "SELECT * FROM THUC_DON WHERE maquan = ?";
        SQLiteDatabase database = getReadableDatabase();
        return  database.rawQuery(sql, new String[]{String.valueOf(maquan)});
    }
    // lấy danh sách loại món ăn của thực đơn
    public Cursor GetDataOLoaiMonAnbymathucdon(int mathucdon){
        String sql = "SELECT * FROM THUC_DON WHERE mathucdon = ?";
        SQLiteDatabase database = getReadableDatabase();
        return  database.rawQuery(sql, new String[]{String.valueOf(mathucdon)});
    }
    // lấy danh sách món ăn của bằng mã loại món ăn
    public Cursor GetDataODANHSACHMONbymaloaimonan(int maloaimonan){
        String sql = "SELECT * FROM DANH_SACH_MON WHERE maloaimonan = ?";
        SQLiteDatabase database = getReadableDatabase();
        return  database.rawQuery(sql, new String[]{String.valueOf(maloaimonan)});
    }
    public  Cursor GetListDataTinhThanh(){
        String sql = "SELECT * FROM TINH_THANH";
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    //Lấy danh sách các quán
    //int maquan , String tenquan ,String diachi, String doangioithieu, String anhdaidien , String thoigiandongcua ,
    // String thoigianmocua , int matinh , int maloaihinhkinhdoanh , int mathucdon, int mawifi
    public List<Quan_An> DanhSachTATCAQUAN(){
        String sql = "SELECT * FROM QUAN_AN";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        List<Quan_An> stores = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            int maquan = cursor.getInt(0);
            String tenquan = cursor.getString(1);
            String diachi = cursor.getString(2);
            String doangioithieu = cursor.getString(3);
            String anhdaidien = cursor.getString(4);
            String thoigiandongcua = cursor.getString(5);
            String thoigianmocua = cursor.getString(6);
            int matinh = cursor.getInt(7);
            int maloaihinhkinhdoanh = cursor.getInt(8);
            int mathucdon = cursor.getInt(9);
            int mawifi = cursor.getInt(10);

            stores.add(new Quan_An(maquan,tenquan,diachi,doangioithieu,anhdaidien,thoigiandongcua,thoigianmocua,matinh,maloaihinhkinhdoanh,mathucdon,mawifi));
            cursor.moveToNext();
        }
        return stores;
    }

    public List<Quan_An> DanhSachTATCAQUAN(int start, int end, int matinhhientai){
        int i = 0;
        String sql = "SELECT * FROM QUAN_AN WHERE matinh = ?";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(matinhhientai)});
        List<Quan_An> stores = new ArrayList<>();
        cursor.move(start-1);
        while (cursor.moveToNext()&& i <=end-start) {

            int maquan = cursor.getInt(0);
            String tenquan = cursor.getString(1);
            String diachi = cursor.getString(2);
            String doangioithieu = cursor.getString(3);
            String anhdaidien = cursor.getString(4);
            String thoigiandongcua = cursor.getString(5);
            String thoigianmocua = cursor.getString(6);
            int matinh = cursor.getInt(7);
            int maloaihinhkinhdoanh = cursor.getInt(8);
            int mathucdon = cursor.getInt(9);
            int mawifi = cursor.getInt(10);

            stores.add(new Quan_An(maquan,tenquan,diachi,doangioithieu,anhdaidien,thoigiandongcua,thoigianmocua,matinh,maloaihinhkinhdoanh,mathucdon,mawifi));
            i++;
        }
        cursor.close();
        return stores;
    }
    //Lấy quán bằng mà quán
    public Quan_An DanhSachQUANTHEOMAQUAN(int maquanan){
        String sql = "SELECT * FROM QUAN_AN WHERE maquan =?";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(maquanan)});

        cursor.moveToFirst();

        int maquan = cursor.getInt(0);
        String tenquan = cursor.getString(1);
        String diachi = cursor.getString(2);
        String doangioithieu = cursor.getString(3);
        String anhdaidien = cursor.getString(4);
        String thoigiandongcua = cursor.getString(5);
        String thoigianmocua = cursor.getString(6);
        int matinh = cursor.getInt(7);
        int maloaihinhkinhdoanh = cursor.getInt(8);
        int mathucdon = cursor.getInt(9);
        int mawifi = cursor.getInt(10);

        Quan_An stores = new Quan_An(maquan,tenquan,diachi,doangioithieu,anhdaidien,thoigiandongcua,thoigianmocua,matinh,maloaihinhkinhdoanh,mathucdon,mawifi);

        return stores;
    }

    public List<Thuc_Don> DanhSachThucDonTheoMaThucDon(int mathucdon){
        String sql = "SELECT * FROM THUC_DON WHERE mathucdon = ?";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(mathucdon)});
        List<Thuc_Don> menufoods = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            int mathucdon1 = cursor.getInt(0);
            int maloaimonan = cursor.getInt(1);
            String tenthucdon = cursor.getString(2);

            menufoods.add(new Thuc_Don(mathucdon1,maloaimonan,tenthucdon));
            cursor.moveToNext();
        }
        return menufoods;
    }
    public List<String> DanhSachTenThucDonTheoMaThucDon(int mathucdon){
        String sql = "SELECT * FROM THUC_DON WHERE mathucdon = ?";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(mathucdon)});
        List<String> menufoods = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            int maloaimonan = cursor.getInt(1);

            menufoods.add(this.TenLoaiMonTheoMaMon(maloaimonan));
            cursor.moveToNext();
        }
        return menufoods;
    }
    public String TenLoaiMonTheoMaMon(int maloaimon){
        String sql = "SELECT * FROM LOAI_MON WHERE maloaimon = ?";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(maloaimon)});
        cursor.moveToFirst();
        return cursor.getString(1);
    }

    public List<Mon_An> DanhSachMonTheoLoaiMonAn(int maloaimonan){
        String sql = "SELECT * FROM DANH_SACH_MON WHERE maloaimon = ?";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(maloaimonan)});
        List<Mon_An> listfood = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            String name_food = cursor.getString(2);
            float price_food = cursor.getFloat(4);

            listfood.add(new Mon_An(name_food,price_food));
            cursor.moveToNext();
        }
        return listfood;
    }
    //=======================================================================================================
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
