package gr.uoa.ec.ismini.webservices;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import gr.uoa.ec.ismini.ProductListActivity;
import gr.uoa.ec.ismini.models.Address;
import gr.uoa.ec.ismini.models.Customer;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class CustomerWebService extends SoapThread {
    private Activity parent;

    public CustomerWebService(Activity parent) {
        this.parent = parent;
        NAMESPACE = "http://customer.ws/";
        URL = "http://snf-649502.vm.okeanos.grnet.gr:8080/CustomerWebService/CustomerWebService";
        SERVICE = "CustomerWebService";
    }

    @Override
    protected Object doInBackground(String... strings) {
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
                }else if (strings[0].equals(FIND)) {
                    HashMap<String, Object> properties = new HashMap<>();
                    properties.put("id", Integer.parseInt(strings[1]));

                    SoapObject result = (SoapObject) soapCallWithProperties(FIND, NAMESPACE + SERVICE + "/findRequest", properties);
                    response = RetrieveFromSoap(result).toString();
                } else if (strings[0].equals(COUNT)) {
                    SoapPrimitive result = (SoapPrimitive) soapCall(COUNT, NAMESPACE + SERVICE + "/countRequest");
                    response = RetrieveFromSoap(result).toString();
                }
            }

        } catch (Exception e) {
            Log.e(CustomerWebService.class.toString() + ": soap_response", e.toString());
        }
        finally {
            return response;
        }
    }

    @Override
    protected void onPostExecute(Object s) {
        Intent resultIntent = new Intent(parent, ProductListActivity.class);
        resultIntent.putExtra("result", s.toString());
        parent.startActivity(resultIntent);
    }



    private Customer RetrieveFromSoap(SoapObject soapObject)
    {
        Log.i("soap_response", soapObject.toString());
        SoapObject soapAddress = (SoapObject) soapObject.getProperty(0);
        String description = soapAddress.getProperty(0).toString();
        int addressKey = Integer.parseInt(soapAddress.getProperty(1).toString());
        Address address = new Address(description, addressKey);

        int key = Integer.parseInt(soapObject.getProperty(2).toString());
        String firstName = soapObject.getProperty(1).toString();
        String lastName = soapObject.getProperty(3).toString();
        String username = soapObject.getProperty(5).toString();
        String password = soapObject.getProperty(4).toString();

        return new Customer(address, key, firstName, lastName, username, password);
    }

    private List<Customer> RetrieveFromSoap(Vector<SoapObject> soapObjectVector) {
        List<Customer> customerList = new ArrayList<Customer>();
        Log.i("soap_response", "mphka kai edw");
        for (SoapObject soapCustomer : soapObjectVector) {
            // get address
            SoapObject soapAddress = (SoapObject) soapCustomer.getProperty(0);
            String description = soapAddress.getProperty(0).toString();
            int addressKey = Integer.parseInt(soapAddress.getProperty(1).toString());
            Address address = new Address(description, addressKey);

            int key = Integer.parseInt(soapCustomer.getProperty(2).toString());
            String firstName = soapCustomer.getProperty(1).toString();
            String lastName = soapCustomer.getProperty(3).toString();
            String username = soapCustomer.getProperty(5).toString();
            String password = soapCustomer.getProperty(4).toString();

            customerList.add(new Customer(address, key, firstName, lastName, username, password));
        }
        return customerList;
    }

    private Object RetrieveFromSoap(SoapPrimitive soapPrimitive) {
        return soapPrimitive.getValue();
    }
}
