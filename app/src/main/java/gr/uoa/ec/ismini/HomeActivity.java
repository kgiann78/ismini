package gr.uoa.ec.ismini;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import gr.uoa.ec.ismini.helpers.ProductAdapter;
import gr.uoa.ec.ismini.helpers.ProductsDummy;
import gr.uoa.ec.ismini.models.Product;
import gr.uoa.ec.ismini.models.Store;
import gr.uoa.ec.ismini.helpers.StoreAdapter;
import gr.uoa.ec.ismini.webservices.AddressWebService;
import gr.uoa.ec.ismini.webservices.CustomerWebService;
import gr.uoa.ec.ismini.webservices.StoreWebService;

public class HomeActivity extends AppCompatActivity {

    static private boolean isStarting = true;
    private String soapResult = "";
    //    Store[] stores = {};
    private Product[] products = {};

    private StoreWebService storeWebService;
    private AddressWebService addressWebService;
    private CustomerWebService customerWebService;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private ListAdapter mAdapter;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        storeWebService = new StoreWebService(this);
        addressWebService = new AddressWebService(this);
        customerWebService = new CustomerWebService(this);

        extras = getIntent().getExtras();

        if (isStarting) {
            storeWebService.execute("findAll");
            isStarting = false;
        }

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        mListView = (ListView) findViewById(R.id.activity_main_listview);

        updateStores();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
    }

    public void refreshContent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                HomeActivity.this.storeWebService.execute("findAll");
                updateStores();

                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }

    public void updateStores() {
        if (extras != null) {
            soapResult = extras.getString("result");
            Log.i("soap_response", soapResult);
            try {
                products = ProductsDummy.getProducts();
//                stores = new Gson().fromJson(soapResult, Store[].class);

                mListView.setAdapter(null);
//                mAdapter = new StoreAdapter(HomeActivity.this, stores);
                mAdapter = new ProductAdapter(HomeActivity.this, products);
                mListView.setAdapter(mAdapter);
            } catch (Exception e) {
                Log.e("soap_response", e.toString());
            }
        }
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
