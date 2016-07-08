package gr.uoa.ec.ismini.util;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import gr.uoa.ec.ismini.util.resource.ResourceKeyParameter;
import gr.uoa.ec.ismini.util.resource.ResourceOptions;
import gr.uoa.ec.ismini.util.resource.ResourceOperation;


/**
 *
 */
public class IsminiRSClient {

    private ResourceOptions OPTIONS;
    private String BASE_URL;

    public IsminiRSClient(ResourceOptions options) {
        OPTIONS = options;
        BASE_URL = "http://192.168.1.3:8080/Ismini-RS/ws/";
    }

    public IsminiRSClient(String host, int port, ResourceOptions options){
        OPTIONS = options;
        BASE_URL = String.format("http://%s:%d/Ismini-RS/ws/", host, port);
    }

    private Uri buildUri(){
        ResourceOperation op = OPTIONS.getOperation();
        Uri uri = null;
        switch (op){
            case findAll:
                uri = Uri.parse(BASE_URL).buildUpon().appendPath(OPTIONS.getType().toString()).build();
                break;
            case find:
                uri = Uri.parse(BASE_URL).buildUpon().appendPath(OPTIONS.getType().toString()).appendPath(OPTIONS.getParameter(ResourceKeyParameter.key)).build();
                break;
            case findRange:
                uri = Uri.parse(BASE_URL).buildUpon().appendPath(OPTIONS.getType().toString()).appendPath(OPTIONS.getParameter(ResourceKeyParameter.keyFrom)).appendPath("/").appendPath(OPTIONS.getParameter(ResourceKeyParameter.keyTo)).build();
                break;
            case count:
                uri = Uri.parse(BASE_URL).buildUpon().appendPath(OPTIONS.getType().toString()).appendPath("count").build();
                break;
            default: uri = null;
        }
        return uri;
    }

    private  String getResult(Uri uri){
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String jsonStr = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            URL url = new URL(uri.toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            jsonStr = buffer.toString();
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
        return jsonStr;
    }

    public String requestResult(){
        return getResult(buildUri());
    }
}
