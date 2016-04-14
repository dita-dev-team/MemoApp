package com.dita.dev.memoapp.ui.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dita.dev.memoapp.R;
import com.dita.dev.memoapp.adapters.customAdapter;
import com.dita.dev.memoapp.data.database.MemosColumns;
import com.dita.dev.memoapp.ui.activity.ComposeActivity;
import com.dita.dev.memoapp.ui.activity.MemoContract;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemosFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int URL_LOADER = 0;
    customAdapter cAdapter;
    @Bind(R.id.memos_list)
    ListView memoList;

    public MemosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_memos, container, false);
        ButterKnife.bind(this, view);

        if (cAdapter == null) {
            memoList.setAdapter(cAdapter);
        }
        getLoaderManager().initLoader(URL_LOADER, null, this);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @OnClick(R.id.newMemo)
    public void MemoClick(View view) {
        startActivity(new Intent(getActivity(), ComposeActivity.class));


    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projectionFields = new String[]{MemosColumns.ID, MemosColumns.USERNAME, MemosColumns.SUBJECT, MemosColumns.MESSAGE};
        // Construct the loader
        CursorLoader cursorLoader = new CursorLoader(getActivity(),
                MemoContract.Memos.CONTENT_URI, // URI
                projectionFields, // projection fields
                null, // the selection criteria
                null, // the selection args
                null // the sort order
        );
        // Return the loader for use
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (cAdapter == null) {
            cAdapter = new customAdapter(getActivity(), data, 0);
            memoList.setAdapter(cAdapter);
        } else {
            cAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (cAdapter != null) {
            cAdapter.changeCursor(null);
        }
        cAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(0, null, this);
//        cAdapter.notifyDataSetChanged();
    }
}
