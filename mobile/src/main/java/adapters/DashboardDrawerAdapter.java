package adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.douglas.clockin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglas on 9/30/2015.
 */
public class DashboardDrawerAdapter extends ArrayAdapter<String> {

    public Context mAdapterContext;
    public String[] mItems;

    public DashboardDrawerAdapter(Context context, String[] objects) {
        super(context, R.layout.dashboard_drawer_item, objects);
        mAdapterContext = context;
        mItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            //inflate the layout
            LayoutInflater inflater = ((Activity) mAdapterContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.dashboard_drawer_item, parent, false);
        }

        TextView dashitemText = (TextView) convertView.findViewById(R.id.dashboard_item_text);
        dashitemText.setText(mItems[position]);

        return convertView;
    }
}
