package com.dita.dev.memoapp.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dita.dev.memoapp.R;

import java.util.ArrayList;
import java.util.List;

public class ImagePickUp {
    public static int IMAGE_FILE_CODE = 1;

    /**
     * Detect the available intent and open a new dialog.
     *
     * @param context
     */
    public static void openMediaSelector(Activity context) {

        Intent camIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        Intent gallIntent = new Intent(Intent.ACTION_GET_CONTENT);
        gallIntent.setType("image/*");

        // look for available intents
        List<ResolveInfo> info = new ArrayList<ResolveInfo>();
        List<Intent> yourIntentsList = new ArrayList<Intent>();
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(camIntent, 0);
        for (ResolveInfo res : listCam) {
            final Intent finalIntent = new Intent(camIntent);
            finalIntent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            yourIntentsList.add(finalIntent);
            info.add(res);
        }
        List<ResolveInfo> listGall = packageManager.queryIntentActivities(gallIntent, 0);
        for (ResolveInfo res : listGall) {
            final Intent finalIntent = new Intent(gallIntent);
            finalIntent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            yourIntentsList.add(finalIntent);
            info.add(res);
        }

        // show available intents
        openDialog(context, yourIntentsList, info);
    }

    /**
     * Open a new dialog with the detected items.
     *
     * @param context
     * @param intents
     * @param activitiesInfo
     */
    private static void openDialog(final Activity context, final List<Intent> intents,
                                   List<ResolveInfo> activitiesInfo) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(context.getResources().getString(R.string.select_an_action));
        dialog.setAdapter(buildAdapter(context, activitiesInfo),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = intents.get(id);
                        context.startActivityForResult(intent, IMAGE_FILE_CODE);

                    }
                });

        dialog.setNeutralButton(context.getResources().getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }


    /**
     * Build the list of items to show using the intent_listview_row layout.
     *
     * @param context
     * @param activitiesInfo
     * @return
     */
    private static ArrayAdapter<ResolveInfo> buildAdapter(final Context context, final List<ResolveInfo> activitiesInfo) {
        return new ArrayAdapter<ResolveInfo>(context, R.layout.intent_listview_row, R.id.title, activitiesInfo) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ResolveInfo res = activitiesInfo.get(position);
                ImageView image = (ImageView) view.findViewById(R.id.icon);
                image.setImageDrawable(res.loadIcon(context.getPackageManager()));
                TextView textview = (TextView) view.findViewById(R.id.title);
                textview.setText(res.loadLabel(context.getPackageManager()).toString());
                return view;
            }
        };
    }
}
