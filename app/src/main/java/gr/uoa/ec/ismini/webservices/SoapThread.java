package gr.uoa.ec.ismini.webservices;

import android.os.AsyncTask;
import android.util.Log;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;

public abstract class SoapThread extends AsyncTask<String, Void, Object> {
    String NAMESPACE ;
    String URL;
    String SERVICE;
    String FIND_ALL = "findAll";
    String FIND = "find";
    String COUNT = "count";

    Object soapCallWithProperties(String methodName, String soapAction, HashMap<String, Object> properties) throws IOException, XmlPullParserException {
        SoapObject request = new SoapObject(NAMESPACE, methodName);

        for (String key : properties.keySet()) {
            request.addProperty(key, properties.get(key));
        }

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = false;

        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        httpTransport.debug = true;
        httpTransport.call(soapAction, envelope);

        return envelope.getResponse();
    }

    Object soapCall(String methodName, String soapAction) throws IOException, XmlPullParserException {
        SoapObject request = new SoapObject(NAMESPACE, methodName);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = false;

        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        httpTransport.debug = true;
        httpTransport.call(soapAction, envelope);

        return envelope.getResponse();
    }
}