package gr.uoa.ec.ismini;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import gr.uoa.ec.ismini.entities.Store;
import gr.uoa.ec.ismini.webservices.AddressWebService;
import gr.uoa.ec.ismini.webservices.CustomerWebService;
import gr.uoa.ec.ismini.webservices.StoreWebService;



import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    static boolean isStarting = true;
    String soapResult = "";
    final List<String> value = new ArrayList<>();
    Store[] stores = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final StoreWebService service = new StoreWebService(this);
        final AddressWebService addressWebService = new AddressWebService(this);
        final CustomerWebService customerWebService = new CustomerWebService(this);

        // WebServer Request URL
        String serverURL = "http://androidexample.com/media/webservice/JsonReturn.php";

        // AddressWebService
        //http://snf-649502.vm.okeanos.grnet.gr:8080/AddressWebService/AddressWebService?WSDL





        if (isStarting) {
            service.execute("findAll");
//            addressWebService.execute("findAll");
//            customerWebService.execute("findAll");
            isStarting = false;
        }


        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            soapResult = extras.getString("result");
            Log.i("soap_response", soapResult);
            try {
                stores = new Gson().fromJson(soapResult, Store[].class);
            } catch (Exception e) {
                Log.e("soap_response", e.toString());
            }

            value.add(extras.getString("result"));
        }

        ListAdapter listAdapter = new CustomAdapter(this, stores);
        ListView listView = (ListView) findViewById(R.id.ListViewCatalog);
        listView.setAdapter(listAdapter);

        Button fab = (Button) findViewById(R.id.ButtonViewCart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSmthing(view, value);
            }
        });
    }

    public void doSmthing(View view, List<String> value) {

//        try {
//            Store[] stores = new Gson().fromJson(value.toString(), Store[].class);
//            for (Store store : stores)
//                Log.i("soap_response", store.toString());
//        } catch (Exception e) {
//            Log.e("soap_response", e.toString());
//        } finally {
//            Snackbar.make(view, value.get(0), Snackbar.LENGTH_LONG)//"Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
//        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
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
