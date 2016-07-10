package gr.uoa.ec.ismini.util.resource;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import gr.uoa.ec.ismini.ResourceDetailActivity;
import gr.uoa.ec.ismini.util.IsminiRSClient;

public class ResourceDetailLoader extends AsyncTaskLoader<Object> {
    private ResourceOptions OPTIONS;

    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    public ResourceDetailLoader(Context context, int id) {
        super(context);
        OPTIONS = ResourceOptions.convertBundleToOptions(id);
    }

    @Override
    public Object loadInBackground() {
        IsminiRSClient isminiRSClient = new IsminiRSClient(OPTIONS);
        String result = isminiRSClient.requestResult();
        return null;
    }
}
