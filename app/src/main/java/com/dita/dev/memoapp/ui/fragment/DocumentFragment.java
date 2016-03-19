package com.dita.dev.memoapp.ui.fragment;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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

    final String[] files = {
            "1.mp3",
            "2.mp4",
            "3.pdf",
            "5.mp3",
            "6.pdf",
            "7.docx",
            "8.m4v",
            "9.doc",
            "10.odt"
    };
    @Bind(R.id.document_grid)
    GridView gridView;
    ArrayAdapter<File> arrayAdapter;
    public DocumentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_document, container, false);
        getActivity().getActionBar().setTitle("Documennts");

        ButterKnife.bind(this, view);
        ExternalStorageUtils.createAppDirectories(getContext());
        List<File> array = ExternalStorageUtils.getAllMedia();
        GridView gridView = (GridView) view.findViewById(R.id.document_grid);
        ListView listView = (ListView) view.findViewById(R.id.document_list);
        arrayAdapter = DocumentUtils.buildGridItem(this.getContext(), array);
        gridView.setAdapter(arrayAdapter);

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
        System.out.println("Long clicked");
        return true;
    }

}
