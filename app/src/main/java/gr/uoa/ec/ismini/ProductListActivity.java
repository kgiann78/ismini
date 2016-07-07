package gr.uoa.ec.ismini;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import gr.uoa.ec.ismini.helpers.ProductAdapter;
import gr.uoa.ec.ismini.helpers.ProductsDummy;
import gr.uoa.ec.ismini.helpers.ShoppingCart;
import gr.uoa.ec.ismini.models.Product;
import gr.uoa.ec.ismini.webservices.AddressWebService;
import gr.uoa.ec.ismini.webservices.CustomerWebService;
import gr.uoa.ec.ismini.webservices.StoreWebService;

public class ProductListActivity extends AppCompatActivity {

    static private boolean isStarting = true;
    private String soapResult = "";
    private Product[] products = {};

    private StoreWebService storeWebService;
    private AddressWebService addressWebService;
    private CustomerWebService customerWebService;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private ListAdapter mAdapter;
    private Button shoppingCartButton;

    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        shoppingCartButton = (Button) findViewById(R.id.view_shopping_cart);

        storeWebService = new StoreWebService(this);
        addressWebService = new AddressWebService(this);
        customerWebService = new CustomerWebService(this);

        extras = getIntent().getExtras();

        if (isStarting) {
//            storeWebService.execute("findAll");
            isStarting = false;
        }

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        mListView = (ListView) findViewById(R.id.activity_main_listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent productDetailIntent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                productDetailIntent.putExtra("product", adapterView.getItemAtPosition(i).toString());
                startActivity(productDetailIntent);
            }
        });
        updateProducts();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });

        shoppingCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShoppingCart.getShoppingCart().length > 0) {
                    Intent addToBasketIntent = new Intent(ProductListActivity.this, ShoppingCartActivity.class);
                    startActivity(addToBasketIntent);
                }
                else {
                    Toast.makeText(ProductListActivity.this, "Your cart is empty", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void refreshContent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ProductListActivity.this.storeWebService.execute("findAll");
                updateProducts();

                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }

    public void updateProducts() {
//        if (extras != null) {
//            soapResult = extras.getString("result");
//            Log.i("soap_response", soapResult);
            try {
                products = ProductsDummy.getProducts();

                mListView.setAdapter(null);
                mAdapter = new ProductAdapter(ProductListActivity.this, products);
                mListView.setAdapter(mAdapter);
            } catch (Exception e) {
                Log.e("soap_response", e.toString());
            }
//        }
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
