package gr.uoa.ec.ismini.util.resource;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import gr.uoa.ec.ismini.util.IsminiRSClient;

/**
 *
 */
public class ResourceListLoader extends AsyncTaskLoader<List<Object>> {

    private ResourceOptions OPTIONS;

    public ResourceListLoader(Context context, int id) {
        super(context);

        OPTIONS = ResourceOptions.convertBundleToOptions(id);
    }

    private static boolean validateOptions(){

        return true;
    }

    @Override
    public List<Object> loadInBackground() {

        List resultList = new ArrayList<>();
        if(validateOptions()) {

            IsminiRSClient isminiRSClient = new IsminiRSClient(OPTIONS);
            String result = isminiRSClient.requestResult();
            ByteArrayInputStream input = new ByteArrayInputStream(result.getBytes());
            try {

                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = builder.parse(input);
                NodeList nodeList = document.getElementsByTagName(OPTIONS.getType().toString());
                for (int i = 0; i < nodeList.getLength(); ++i) {

                    StringBuilder sb = new StringBuilder();
                    NodeList childNodes = nodeList.item(i).getChildNodes();
                    for(int j = 0; j < childNodes.getLength(); j++){
                        sb.append("|");
                        sb.append(childNodes.item(j).getFirstChild().getNodeValue().toString());
                    }
                    sb.append("|");
                    resultList.add(sb.toString());
                }
            } catch (ParserConfigurationException | IOException | SAXException e) {
                Log.e(ResourceListLoader.class.toString(), "", e);
            } finally {
                if(input != null) try {
                    input.close();
                } catch (IOException e) {
                    Log.e(ResourceListLoader.class.toString(), "", e);
                }
            }
        }
        return resultList;
    }


    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
