package gr.uoa.ec.ismini.storeList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;

import gr.uoa.ec.ismini.R;
import gr.uoa.ec.ismini.models.Store;

public class StoreAdapter extends ArrayAdapter<Store> {

    public StoreAdapter(Context context, List<Store> resource) {
        super(context, R.layout.custom_row, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.custom_row, parent, false);

        String storeName = getItem(position).getName();
        String address = getItem(position).getAddressKey().getDescription();

        TextView storeText = (TextView) customView.findViewById(R.id.storeTextView);
        TextView addressText = (TextView) customView.findViewById(R.id.addressTextView);

        storeText.setText(storeName);
        addressText.setText(address);

        return customView;
    }

}
