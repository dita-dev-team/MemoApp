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
                String title = list.get(position).getName();
                String fileType = FileUtils.getFileType2(list.get(position));
                String genFileType = FileUtils.getGenericFileType(fileType);

                if (genFileType != null) {
                    int iconId = FileUtils.mimeTypes.get(genFileType);
                    ImageView imageView = (ImageView) view.findViewById(R.id.document_grid_icon);
                    imageView.setImageResource(iconId);
                }

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
                String fileType = FileUtils.getFileType2(list.get(position));
                String genFileType = FileUtils.getGenericFileType(fileType);

                if (genFileType != null) {
                    int iconId = FileUtils.mimeTypes.get(genFileType);
                    ImageView imageView = (ImageView) view.findViewById(R.id.document_list_icon);
                    imageView.setImageResource(iconId);
                }

                TextView titleTextView = (TextView) view.findViewById(R.id.document_list_title);
                titleTextView.setText(title);
                String date = FileUtils.getLastModifiedDate(list.get(position));
                TextView detailsTextView = (TextView) view.findViewById(R.id.document_list_details);
                detailsTextView.setText(date);
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
