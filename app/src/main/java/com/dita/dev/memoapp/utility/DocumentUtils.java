package com.dita.dev.memoapp.utility;


import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dita.dev.memoapp.R;

import java.io.File;
import java.util.List;

public class DocumentUtils {

    public static ArrayAdapter<File> buildGridItem(final Context context, final List<File> list) {
        return new ArrayAdapter<File>(context, R.layout.document_grid_item, R.id.document_grid_title, list) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                String title = FileUtils.removeWhitespace(list.get(position).getName());
                String fileType = FileUtils.getFileType(title);
                String genFileType = FileUtils.getGenericFileType(fileType);
                int iconId = FileUtils.mimeTypes.get(genFileType);
                ImageView imageView = (ImageView) view.findViewById(R.id.document_grid_icon);
                imageView.setImageResource(iconId);
                TextView textview = (TextView) view.findViewById(R.id.document_grid_title);
                textview.setText(title);
                return view;
            }
        };
    }

    public static ArrayAdapter<File> buildListItem(final Context context, final List<File> list) {
        return new ArrayAdapter<File>(context, R.layout.document_list_item, R.id.document_list_title, list) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                String title = list.get(position).getName();
                ImageView imageView = (ImageView) view.findViewById(R.id.document_list_icon);
                imageView.setImageResource(R.drawable.file_general);
                TextView textview = (TextView) view.findViewById(R.id.document_list_title);
                textview.setText(title);
                return view;
            }
        };
    }

    public static AlertDialog.Builder buildConfirmationDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmation").setMessage("Are you sure?");

        return builder;
    }
}
