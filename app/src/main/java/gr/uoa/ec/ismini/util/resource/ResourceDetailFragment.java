package gr.uoa.ec.ismini.util.resource;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import com.google.gson.Gson;
import gr.uoa.ec.ismini.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ResourceDetailFragment
        extends Fragment
        implements SearchView.OnQueryTextListener, SearchView.OnCloseListener,
        LoaderManager.LoaderCallbacks<Object>{

    String resource;

    @Override
    public Loader<Object> onCreateLoader(int id, Bundle args) {
        Bundle arguments = getArguments();
        if(arguments != null) {
            id = arguments.getInt("NAV_KEY");

            Log.i("resource", arguments.get("resource").toString());
        }
        Properties resource = new Gson().fromJson(arguments.get("resource").toString(), Properties.class);

        if (resource.getProperty("Type").equals("store"))
            return new ResourceDetailLoader(getActivity(), R.id.nav_stores);
        else
            return new ResourceDetailLoader(getActivity(), id);
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object data) {


    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {
    }

    @Override
    public boolean onClose() {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        getLoaderManager().initLoader(0, savedInstanceState, this);
    }
}
