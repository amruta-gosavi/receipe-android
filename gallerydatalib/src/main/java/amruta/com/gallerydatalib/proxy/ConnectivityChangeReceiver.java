package amruta.com.gallerydatalib.proxy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by AMRUTA on 1/10/17.
 */

public class ConnectivityChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("*** Action: " + intent.getAction());
        if(intent.getAction().equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE")) {
            Toast.makeText(context, "Connection changed", Toast.LENGTH_SHORT).show();
        }

        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        boolean isConnected = wifi != null && wifi.isConnectedOrConnecting() ||
                mobile != null && mobile.isConnectedOrConnecting();
        if (isConnected) {
            Log.d("Network Available ", "YES");
            Toast.makeText(context, "Network available", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("Network Available ", "NO");
            Toast.makeText(context, "Offline mode", Toast.LENGTH_SHORT).show();
        }
    }
}