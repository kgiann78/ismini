package gr.uoa.ec.ismini.shoppingList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import gr.uoa.ec.ismini.R;
import gr.uoa.ec.ismini.models.ShoppingListItem;

public class ShoppingListAdapter extends ArrayAdapter<ShoppingListItem> {

    public ShoppingListAdapter(Context context, ShoppingListItem[] resource) {
        super(context, R.layout.custom_row, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View shoppingCartView = layoutInflater.inflate(R.layout.shopping_cart_row, parent, false);

        if (getItem(position) != null) {
            String amount = String.valueOf(getItem(position).getAmount());
            String name = getItem(position).getProduct().getName();
            String totalPrice = String.valueOf(getItem(position).getAmount() * getItem(position).getProduct().getPrice());

            TextView itemAmount = (TextView) shoppingCartView.findViewById(R.id.item_amount);
            TextView itemName = (TextView) shoppingCartView.findViewById(R.id.item_name);
            TextView itemPrice = (TextView) shoppingCartView.findViewById(R.id.total_item_price);

            itemAmount.setText(amount);
            itemName.setText(name);
            itemPrice.setText(totalPrice + "€");
        }

        return shoppingCartView;
    }
}
