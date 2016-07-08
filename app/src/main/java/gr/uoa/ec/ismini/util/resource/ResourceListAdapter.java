package gr.uoa.ec.ismini.util.resource;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

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
        View view;
        Log.i(ResourceListAdapter.class.toString(), "getting View");

        if (convertView == null) {
            view = mInflater.inflate(R.layout.list_item_text, parent, false);
        } else {
            view = convertView;
        }

        String item = (String) getItem(position);
        ((TextView)view.findViewById(R.id.text)).setText(item);

        return view;
    }
}
