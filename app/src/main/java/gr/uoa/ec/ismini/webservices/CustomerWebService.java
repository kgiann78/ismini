package gr.uoa.ec.ismini.webservices;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import gr.uoa.ec.ismini.HomeActivity;
import gr.uoa.ec.ismini.entities.Address;
import gr.uoa.ec.ismini.entities.Customer;
import gr.uoa.ec.ismini.entities.Store;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
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
    protected String doInBackground(String... strings) {
        String response = "";

        try {
            if (strings.length > 0) {
                if (strings[0].equals(FIND_ALL)) {
                    Vector<SoapObject> result = (Vector<SoapObject>)soapCall(FIND_ALL, NAMESPACE + SERVICE + "/findAllRequest");
                    response = RetrieveFromSoap(result).toString();
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



    public Customer RetrieveFromSoap(SoapObject soapObject)
    {
        Log.i("soap_response", soapObject.toString());
        SoapObject soapAddress = (SoapObject) soapObject.getProperty(0);
        String description = soapAddress.getProperty(0).toString();
        int addressKey = Integer.parseInt(soapAddress.getProperty(1).toString());
        Address address = new Address(description, addressKey);

        int key = Integer.parseInt(soapObject.getProperty(1).toString());
        String name = soapObject.getProperty(2).toString();
        String lastname = soapObject.getProperty(3).toString();
        String username = soapObject.getProperty(4).toString();
        String password = soapObject.getProperty(5).toString();

        Customer customer = new Customer(address, key, name, lastname, username, password);

        return customer;
    }

    public List<Customer> RetrieveFromSoap(Vector<SoapObject> soapObjectVector) {
        List<Customer> customerList = new ArrayList<Customer>();

        for (int i = 0; i < soapObjectVector.size(); i++) {
            SoapObject soapCustomer = soapObjectVector.get(i);

            // get address
            SoapObject soapAddress = (SoapObject) soapCustomer.getProperty(0);
            String description = soapAddress.getProperty(0).toString();
            int addressKey = Integer.parseInt(soapAddress.getProperty(1).toString());
            Address address = new Address(description, addressKey);

            int key = Integer.parseInt(soapCustomer.getProperty(1).toString());
            String name = soapCustomer.getProperty(2).toString();
            String lastname = soapCustomer.getProperty(3).toString();
            String username = soapCustomer.getProperty(4).toString();
            String password = soapCustomer.getProperty(5).toString();

            Customer customer = new Customer(address, key, name, lastname, username, password);
            customerList.add(customer);
        }
        return customerList;
    }

    public Object RetrieveFromSoap(SoapPrimitive soapPrimitive) {
        return soapPrimitive.getValue();
    }
}