package gr.uoa.ec.ismini.storeList;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import gr.uoa.ec.ismini.productList.ProductListActivity;
import gr.uoa.ec.ismini.models.Address;
import gr.uoa.ec.ismini.models.Store;
import gr.uoa.ec.ismini.util.SoapThread;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.util.*;

public class StoreWebService extends SoapThread {
    private Activity parent;
    private String ESTIMATE_COMPLETION_TIME_BY_ORDER = "estimateCompletionTimeByOrder";
    private String ESTIMATE_COMPLETION_TIME_BY_STORE = "estimateCompletionTimeByStore";
    private StoreAdapter adapter;

    public StoreWebService(Activity parent, StoreAdapter sa) {
        this.parent = parent;
        NAMESPACE = "http://store.ws/";
        URL = "http://snf-649502.vm.okeanos.grnet.gr:8080/StoreWebService/StoreWebService";
        SERVICE = "StoreWebService";
        adapter = sa;
    }

    @Override
    protected Object doInBackground(String... strings) {
        Object response = null;
        try {
            if (strings.length > 0) {
                if (strings[0].equals(FIND_ALL)) {
                    Object result = soapCall(FIND_ALL, NAMESPACE + SERVICE + "/findAllRequest");

                    if (result instanceof Vector) {
                        response = RetrieveFromSoap((Vector<SoapObject>)result);
                    } else if (result instanceof SoapObject) {
                        response = RetrieveFromSoap((SoapObject)result);
                    }

//                    Log.i("soap_response", response.toString());
                }else if (strings[0].equals(FIND)) {
                    HashMap<String, Object> properties = new HashMap<>();
                    properties.put("id", Integer.parseInt(strings[1]));

                    SoapObject result = (SoapObject) soapCallWithProperties(FIND, NAMESPACE + SERVICE + "/findRequest", properties);
                    response = RetrieveFromSoap(result);
//                    Log.i("soap_response", response.toString());
                } else if (strings[0].equals(COUNT)) {
                    SoapPrimitive result = (SoapPrimitive) soapCall(COUNT, NAMESPACE + SERVICE + "/countRequest");
                    response = RetrieveFromSoap(result);
//                    Log.i("soap_response", response.toString());
                } else if (strings[0].equals(ESTIMATE_COMPLETION_TIME_BY_ORDER)) {
                    HashMap<String, Object> properties = new HashMap<>();
                    properties.put("orderKey", Integer.parseInt(strings[1]));

                    SoapPrimitive result = (SoapPrimitive) soapCallWithProperties(ESTIMATE_COMPLETION_TIME_BY_ORDER, NAMESPACE + SERVICE + "/estimateCompletionTimeByOrderRequest", properties);
                    response = RetrieveFromSoap(result);
//                    Log.i("soap_response", response.toString());
                } else if (strings[0].equals(ESTIMATE_COMPLETION_TIME_BY_STORE)) {
                    HashMap<String, Object> properties = new HashMap<>();
                    properties.put("storeKey", Integer.parseInt(strings[1]));

                    SoapPrimitive result = (SoapPrimitive) soapCallWithProperties(ESTIMATE_COMPLETION_TIME_BY_STORE, NAMESPACE + SERVICE + "/estimateCompletionTimeByStoreRequest", properties);
                    response = RetrieveFromSoap(result);
//                    Log.i("soap_response", response.toString());
                }
            }

        } catch (Exception e) {
            Log.e(StoreWebService.class.toString() + ": soap_response", e.toString());
        } finally {
            return response;
        }
    }

    @Override
    protected void onPostExecute(Object s) {
//        Intent resultIntent = new Intent(parent, ProductListActivity.class);
        if (s != null) {
//            resultIntent.putExtra("result", s.toString());
            adapter.addAll((List<Store>) s);
            adapter.notifyDataSetChanged();
//            parent.startActivity(resultIntent);
        }
    }

    private Store RetrieveFromSoap(SoapObject soapObject)
    {
        SoapObject soapAddress = (SoapObject) soapObject.getProperty(0);
        String description = soapAddress.getProperty(0).toString();
        int addressKey = Integer.parseInt(soapAddress.getProperty(1).toString());
        Address address = new Address(description, addressKey);

        int key = Integer.parseInt(soapObject.getProperty(1).toString());
        String name = soapObject.getProperty(2).toString();
        float radius = Float.parseFloat(soapObject.getProperty(3).toString());

        return new Store(address, key, name, radius);
    }

    private List<Store> RetrieveFromSoap(Vector<SoapObject> soapObjectVector) {
        List<Store> storeList = new ArrayList<Store>();

        for (SoapObject soapStore : soapObjectVector) {
            // get address
            SoapObject soapAddress = (SoapObject) soapStore.getProperty(0);
            String description = soapAddress.getProperty(0).toString();
            int addressKey = Integer.parseInt(soapAddress.getProperty(1).toString());
            Address address = new Address(description, addressKey);

            int key = Integer.parseInt(soapStore.getProperty(1).toString());
            String name = soapStore.getProperty(2).toString();
            float radius = Float.parseFloat(soapStore.getProperty(3).toString());

            storeList.add(new Store(address, key, name, radius));
        }
        return storeList;
    }

    private Object RetrieveFromSoap(SoapPrimitive soapPrimitive) {
        return soapPrimitive.getValue();
    }
}
