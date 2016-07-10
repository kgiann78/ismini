package gr.uoa.ec.ismini.productList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import gr.uoa.ec.ismini.R;
import gr.uoa.ec.ismini.models.Product;

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, Product[] resource) {
        super(context, R.layout.custom_row, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View productView = layoutInflater.inflate(R.layout.product_row, parent, false);

        String productName = getItem(position).getName();
        String price = String.valueOf(getItem(position).getPrice());

        TextView productNameTextView = (TextView) productView.findViewById(R.id.product_name);
        TextView productPriceTextView = (TextView) productView.findViewById(R.id.product_description);

        productNameTextView.setText(productName);
        productPriceTextView.setText(price + "€");

        return productView;
    }
}
