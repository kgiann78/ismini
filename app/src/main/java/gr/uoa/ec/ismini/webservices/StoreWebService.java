package gr.uoa.ec.ismini.webservices;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import gr.uoa.ec.ismini.HomeActivity;
import gr.uoa.ec.ismini.entities.Address;
import gr.uoa.ec.ismini.entities.Store;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class StoreWebService extends SoapThread {
    private Activity parent;
    private String ESTIMATE_COMPLETION_TIME_BY_ORDER = "estimateCompletionTimeByOrder";
    private String ESTIMATE_COMPLETION_TIME_BY_STORE = "estimateCompletionTimeByStore";

    public StoreWebService(Activity parent) {
        this.parent = parent;
        NAMESPACE = "http://store.ws/";
        URL = "http://snf-649502.vm.okeanos.grnet.gr:8080/StoreWebService/StoreWebService";
        SERVICE = "StoreWebService";
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";

        try {
            if (strings.length > 0) {
                if (strings[0].equals(FIND_ALL)) {
                    Object result = soapCall(FIND_ALL, NAMESPACE + SERVICE + "/findAllRequest");

                    if (result instanceof Vector) {
                        response = RetrieveFromSoap((Vector<SoapObject>)result).toString();
                    } else if (result instanceof SoapObject) {
                        response = RetrieveFromSoap((SoapObject)result).toString();
                    }

                    Log.i("soap_response", response.toString());
                }else if (strings[0].equals(FIND)) {
                    HashMap<String, Object> properties = new HashMap<>();
                    properties.put("id", Integer.parseInt(strings[1]));

                    SoapObject result = (SoapObject) soapCallWithProperties(FIND, NAMESPACE + SERVICE + "/findRequest", properties);
                    response = RetrieveFromSoap(result).toString();
                    Log.i("soap_response", response.toString());
                } else if (strings[0].equals(COUNT)) {
                    SoapPrimitive result = (SoapPrimitive) soapCall(COUNT, NAMESPACE + SERVICE + "/countRequest");
                    response = RetrieveFromSoap(result).toString();
                    Log.i("soap_response", response.toString());
                } else if (strings[0].equals(ESTIMATE_COMPLETION_TIME_BY_ORDER)) {
                    HashMap<String, Object> properties = new HashMap<>();
                    properties.put("orderKey", Integer.parseInt(strings[1]));

                    SoapPrimitive result = (SoapPrimitive) soapCallWithProperties(ESTIMATE_COMPLETION_TIME_BY_ORDER, NAMESPACE + SERVICE + "/estimateCompletionTimeByOrderRequest", properties);
                    response = RetrieveFromSoap(result).toString();
                    Log.i("soap_response", response.toString());
                } else if (strings[0].equals(ESTIMATE_COMPLETION_TIME_BY_STORE)) {
                    HashMap<String, Object> properties = new HashMap<>();
                    properties.put("storeKey", Integer.parseInt(strings[1]));

                    SoapPrimitive result = (SoapPrimitive) soapCallWithProperties(ESTIMATE_COMPLETION_TIME_BY_STORE, NAMESPACE + SERVICE + "/estimateCompletionTimeByStoreRequest", properties);
                    response = RetrieveFromSoap(result).toString();
                    Log.i("soap_response", response.toString());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        finally {
            return response;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        Intent resultIntent = new Intent(parent, HomeActivity.class);
        String msg = "SOAP result: " + s;
        resultIntent.putExtra("result", msg);
        parent.startActivity(resultIntent);
    }



    public Store RetrieveFromSoap(SoapObject soapObject)
    {
        SoapObject soapAddress = (SoapObject) soapObject.getProperty(0);
        String description = soapAddress.getProperty(0).toString();
        int addressKey = Integer.parseInt(soapAddress.getProperty(1).toString());
        Address address = new Address(description, addressKey);

        int key = Integer.parseInt(soapObject.getProperty(1).toString());
        String name = soapObject.getProperty(2).toString();
        float radius = Float.parseFloat(soapObject.getProperty(3).toString());

        Store store = new Store(address, key, name, radius);

        return store;
    }

    public List<Store> RetrieveFromSoap(Vector<SoapObject> soapObjectVector) {
        List<Store> storeList = new ArrayList<Store>();

        for (int i = 0; i < soapObjectVector.size(); i++) {
            SoapObject soapStore = soapObjectVector.get(i);

            // get address
            SoapObject soapAddress = (SoapObject) soapStore.getProperty(0);
            String description = soapAddress.getProperty(0).toString();
            int addressKey = Integer.parseInt(soapAddress.getProperty(1).toString());
            Address address = new Address(description, addressKey);

            int key = Integer.parseInt(soapStore.getProperty(1).toString());
            String name = soapStore.getProperty(2).toString();
            float radius = Float.parseFloat(soapStore.getProperty(3).toString());

            Store store = new Store(address, key, name, radius);
            storeList.add(store);
        }
        return storeList;
    }

    public Object RetrieveFromSoap(SoapPrimitive soapPrimitive) {
        return soapPrimitive.getValue();
    }
}
