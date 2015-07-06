package com.example.insind788.myfirstattempt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import android.view.View;





public class MainActivity extends Activity {
    Context ctx;
    String imei = "1234567890";
    String[] lv_arr, lstr;
    int smscount = 1;
    double xpin = 0.0, ypin = 0.0;
    SQLiteDatabase dbb;
    Cursor cur;
    TelephonyManager tmgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GPSTracker gps = new GPSTracker(this);
        if (gps.canGetLocation()) { // gps enabled} // return boolean true/false
            gps.getLatitude(); // returns latitude
            gps.getLongitude(); // returns longitude
            gps.showSettingsAlert();
            gps.stopUsingGPS();
            setContentView(R.layout.activity_main);
        }
    }
}

