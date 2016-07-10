package gr.uoa.ec.ismini.util.resource;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Properties;

import com.google.gson.Gson;
import gr.uoa.ec.ismini.R;

/**
 *
 */
public class ResourceListAdapter extends ArrayAdapter<Object>{
    private final LayoutInflater mInflater;

    public ResourceListAdapter(Context context, int resource, List<Object> objects) {
        super(context, resource, objects);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        Properties properties = new Gson().fromJson(getItem(position).toString(), Properties.class);

        if (convertView == null) {
            switch (properties.getProperty("Type")) {
                case "product":
                    view = mInflater.inflate(R.layout.product_row, parent, false);
                    ((TextView)view.findViewById(R.id.product_name)).setText(properties.getProperty("PName"));
                    ((TextView)view.findViewById(R.id.product_description)).setText(properties.getProperty("PDesc"));
                    break;
                case "store":
                    view = mInflater.inflate(R.layout.store_row, parent, false);
                    ((TextView)view.findViewById(R.id.store_name)).setText(properties.getProperty("SName"));
                    ((TextView)view.findViewById(R.id.store_description)).setText(properties.getProperty("SDesc"));
                    break;
                case "customer":
                    view = mInflater.inflate(R.layout.customer_row, parent, false);
                    ((TextView)view.findViewById(R.id.customer_username)).setText(properties.getProperty("CUsername"));
                    ((TextView)view.findViewById(R.id.customer_email)).setText(properties.getProperty("CEmail"));
                    break;
                default:
                    view = mInflater.inflate(R.layout.list_item_text, parent, false);
                    String item = (String) getItem(position);
                    ((TextView)view.findViewById(R.id.text)).setText(item);
            }
        } else {
            view = convertView;
        }

        return view;
    }
}
