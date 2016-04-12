package com.dita.dev.memoapp.adapters;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.dita.dev.memoapp.R;
import com.dita.dev.memoapp.data.database.MemosColumns;

import de.hdodenhof.circleimageview.CircleImageView;


public class customAdapter extends CursorAdapter {
    private LayoutInflater cursorInflater;

    public customAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        cursorInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return cursorInflater.inflate(R.layout.memo_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewFrom = (TextView) view.findViewById(R.id.txtv_from);
        TextView textViewSubject = (TextView) view.findViewById(R.id.txtv_subject);
        TextView textViewMessage = (TextView) view.findViewById(R.id.txtv_message);
        CircleImageView programmeImage = (CircleImageView) view.findViewById(R.id.memo_image);

        textViewFrom.setText(cursor.getString(cursor.getColumnIndex(MemosColumns.USERNAME)));
        textViewSubject.setText(cursor.getString(cursor.getColumnIndex(MemosColumns.SUBJECT)));
        textViewMessage.setText(cursor.getString(cursor.getColumnIndex(MemosColumns.MESSAGE)));
        programmeImage.setImageResource(R.drawable.profile);


    }
}
