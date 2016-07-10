package gr.uoa.ec.ismini.util.resource;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import gr.uoa.ec.ismini.MainActivity;
import gr.uoa.ec.ismini.R;
import gr.uoa.ec.ismini.ResourceDetailActivity;

/**
 *
 */
public class ResourceListFragment
        extends ListFragment
        implements SearchView.OnQueryTextListener, SearchView.OnCloseListener,
        LoaderManager.LoaderCallbacks<List<Object>>{

    ResourceListAdapter mAdapter;
    SearchView mSearchView;
    String mCurFilter;

    @Override
    public Loader<List<Object>> onCreateLoader(int id, Bundle args) {
        Bundle arguments = getArguments();
        if(arguments != null) id = arguments.getInt("NAV_KEY");

        return new ResourceListLoader(getActivity(), id);
    }

    @Override
    public void onLoadFinished(Loader<List<Object>> loader, List<Object> data) {
        // Set the new data in the adapter.
        mAdapter.clear();
        mAdapter.addAll(data);

        // The list should now be shown.
        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Object>> loader) {
        mAdapter.clear();
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
        if(mAdapter != null){
            mAdapter.clear();
        }

        mAdapter = new ResourceListAdapter(getActivity(), R.layout.list_item_text, new ArrayList<>());
        setListAdapter(mAdapter);
        getLoaderManager().initLoader(0, savedInstanceState, this);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent detailIntent = new Intent(ResourceListFragment.this.getActivity(), ResourceDetailActivity.class);
                detailIntent.putExtra("resource", mAdapter.getItem(i).toString());
                startActivity(detailIntent);
            }
        });
    }
}
