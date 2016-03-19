package com.dita.dev.memoapp.ui.fragment;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.dita.dev.memoapp.R;
import com.dita.dev.memoapp.utility.DocumentUtils;
import com.dita.dev.memoapp.utility.ExternalStorageUtils;
import com.dita.dev.memoapp.utility.FileUtils;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class DocumentFragment extends Fragment {

    static ArrayAdapter<File> arrayAdapter;
    static ActionMode actionMode = null;
    @Bind(R.id.document_grid)
    GridView gridView;

    public DocumentFragment() {
        // Required empty public constructor
    }

    public static ActionMode.Callback buildCallback(final Context context, final int position) {
        return new ActionMode.Callback() {

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.document_contextual_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                arrayAdapter.getItem(position).delete();
                                arrayAdapter.remove(arrayAdapter.getItem(position));
                                Toast.makeText(context, "Operation completed successfully", Toast.LENGTH_SHORT).show();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                switch (item.getItemId()) {
                    case R.id.item_delete:
                        AlertDialog dialog = DocumentUtils.buildConfirmationDialog(context)
                                .setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener)
                                .create();

                        dialog.show();
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                DocumentFragment.actionMode = null;
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_document, container, false);
        ButterKnife.bind(this, view);
        ExternalStorageUtils.createAppDirectories(getContext());
        List<File> array = ExternalStorageUtils.getAllMedia();
        gridView = (GridView) view.findViewById(R.id.document_grid);
        arrayAdapter = DocumentUtils.buildGridItem(this.getContext(), array);
        gridView.setAdapter(arrayAdapter);
        //gridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);

        ListView listView = (ListView) view.findViewById(R.id.document_list);

        return view;
    }

    @OnItemClick(R.id.document_grid)
    public void onItemClick(int position) {
        File file = arrayAdapter.getItem(position);
        Uri uri = Uri.fromFile(file);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        String mime = FileUtils.getFileType(FileUtils.removeWhitespace(file.getName()));
        intent.setDataAndType(uri, mime);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "No application found", Toast.LENGTH_SHORT).show();
        }

    }

    @OnItemLongClick(R.id.document_grid)
    public boolean onItemLongClick(int position) {
        if (actionMode != null) {
            return false;
        }

        actionMode = getActivity().startActionMode(buildCallback(getContext(), position));
        return true;
    }

}
