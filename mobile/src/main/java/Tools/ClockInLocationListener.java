package Tools;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.douglas.clockin.R;

import java.io.IOException;
import java.util.List;

/**
 * Created by Douglas on 6/18/2015.
 */
public class ClockInLocationListener implements LocationListener {

    LocationManager mLocationManager;
    Context mContext;

    public ClockInLocationListener(Context context){
        this.mContext = context;
        mLocationManager = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        String locationProvider = LocationManager.GPS_PROVIDER;
        mLocationManager.requestLocationUpdates(locationProvider, 0, 0, this); // get the initial location for the user
    }

    @Override
    public void onLocationChanged(Location location) { //if the user changes location and is within thier set clock in location clock them in
        String locationProvider = LocationManager.NETWORK_PROVIDER;
        if(location != null){
            if(location == mLocationManager.getLastKnownLocation(locationProvider)){ //Testing set it to work location
                Toast.makeText(mContext, mContext.getResources().getString(R.string.clocked_in_message), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) { //on provider enabled set the first initial location from first screens
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public Location getLocationFromAddress(String strAddress) {
        Geocoder coder = new Geocoder(mContext);
        Location location = new Location("dummyprovider");
        try {
            List<Address> addresses = (List<Address>) coder.getFromLocationName(strAddress, 1);
            for(Address address : addresses) {
                location.setLatitude(address.getLatitude());
                location.setLongitude(address.getLongitude());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return location;
    }
}