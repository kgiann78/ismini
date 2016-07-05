package gr.uoa.ec.ismini.webservices;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import gr.uoa.ec.ismini.HomeActivity;
import gr.uoa.ec.ismini.entities.Address;
import gr.uoa.ec.ismini.entities.Store;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Created by constantine on 7/4/16.
 */
public class AddressWebService extends SoapThread {
    private Activity parent;

    public AddressWebService(Activity parent) {
        this.parent = parent;
        NAMESPACE = "http://address.ws/";
        URL = "http://snf-649502.vm.okeanos.grnet.gr:8080/AddressWebService/AddressWebService";
        SERVICE = "AddressWebService";
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
                    Log.i("soap_response", "result : " + result.toString());
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

    public Address RetrieveFromSoap(SoapObject soapObject)
    {
        String description = soapObject.getProperty(0).toString();
        int addressKey = Integer.parseInt(soapObject.getProperty(1).toString());
        Address address = new Address(description, addressKey);

        return address;
    }

    public List<Address> RetrieveFromSoap(Vector<SoapObject> soapObjectVector) {
        List<Address> addressList = new ArrayList<Address>();

        for (int i = 0; i < soapObjectVector.size(); i++) {
            SoapObject soapAddress = soapObjectVector.get(i);
            String description = soapAddress.getProperty(0).toString();
            int addressKey = Integer.parseInt(soapAddress.getProperty(1).toString());
            Address address = new Address(description, addressKey);
            addressList.add(address);
        }
        return addressList;
    }

    public Object RetrieveFromSoap(SoapPrimitive soapPrimitive) {
        return soapPrimitive.getValue();
    }
}
