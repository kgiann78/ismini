package gr.uoa.ec.ismini.shoppingList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import gr.uoa.ec.ismini.R;
import gr.uoa.ec.ismini.models.ShoppingListItem;

public class ShoppingCartActivity extends AppCompatActivity {

    ShoppingListItem[] shoppingCart = {};
    ListAdapter mAdapter;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        shoppingCart = DummyShoppingList.getShoppingCart();

        mListView = (ListView) findViewById(R.id.shopping_cart_list);
        mAdapter = new ShoppingListAdapter(ShoppingCartActivity.this, shoppingCart);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!view.isSelected()) view.setSelected(true);
                else view.setSelected(false);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
