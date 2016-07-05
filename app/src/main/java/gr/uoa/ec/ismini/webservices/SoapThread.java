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

public abstract class SoapThread extends AsyncTask<String, Void, String> {
    protected String NAMESPACE ;
    protected String URL;
    protected String SERVICE;
    protected String FIND_ALL = "findAll";
    protected String FIND = "find";
    protected String COUNT = "count";

    public Object soapCallWithProperties(String methodName, String soapAction, HashMap<String, Object> properties) throws IOException, XmlPullParserException {
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

    public Object soapCall(String methodName, String soapAction) throws IOException, XmlPullParserException {
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