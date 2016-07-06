package gr.uoa.ec.ismini.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import gr.uoa.ec.ismini.R;
import gr.uoa.ec.ismini.models.Product;
import gr.uoa.ec.ismini.models.Store;

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, Product[] resource) {
        super(context, R.layout.custom_row, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.custom_row, parent, false);

        String productName = getItem(position).getName();
        String price = String.valueOf(getItem(position).getPrice());

        TextView storeText = (TextView) customView.findViewById(R.id.storeTextView);
        TextView addressText = (TextView) customView.findViewById(R.id.addressTextView);

        storeText.setText(productName);
        addressText.setText(price);

        return customView;
    }
}
