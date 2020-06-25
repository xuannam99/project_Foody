package hcmute.edu.vn.nhom.project_foody.myclass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class GetDistance {
    private  String Address;
    private Context context;
    private double Latitude_Store,Longtitude_Store,Latitude_User,Longtitude_User;
    AppLocationService appLocationService;
    public GetDistance(String Address, Context context) {
        this.Address = Address;
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public double Distance() {

        appLocationService = new AppLocationService(
                context);
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        String result = null;
        try {
            List addressList = geocoder.getFromLocationName(Address, 1);
            if (addressList != null && addressList.size() > 0) {
                android.location.Address address = (Address) addressList.get(0);
                Latitude_Store = address.getLatitude();
                Longtitude_Store = address.getLongitude();
            }
        } catch (IOException e) {
            Log.e(TAG, "Unable to connect to Geocoder", e);
        }

        Location gpsLocation = appLocationService
                .getLocation(LocationManager.GPS_PROVIDER);
        if (gpsLocation != null) {
            Latitude_User = gpsLocation.getLatitude();
            Longtitude_User = gpsLocation.getLongitude();

        } else {
            showSettingsAlert();
        }

        double pk = (double) (180.f/Math.PI);

        double a1 = Latitude_Store / pk;
        double a2 = Longtitude_Store / pk;
        double b1 = Latitude_User / pk;
        double b2 = Longtitude_User / pk;

        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double dot = Math.acos(t1 + t2 + t3) * 6366 ;
        dot = (double)Math.round(dot * 10) / 10;
        return dot;

    }
    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                context);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                       context.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

}
