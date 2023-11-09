package com.example.sqldatabasepoketracker;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.LinkedList;

public class DatabaseActivity extends AppCompatActivity {
    LinkedList<String> list = new LinkedList<>();
    ListView listView;
    String[] mListColumns;
    String[] projection;
    String sortOrder;
    Cursor cursor;
//     = getContentResolver().query(MyContentProvider.contentURI, null, null, null, null);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row);


//        Cursor cursor = getContentResolver().query(MyContentProvider.contentURI, null, null, null, null);
//        mListColumns = new String[] {MyContentProvider.COLUMN1_NAME,MyContentProvider.COLUMN2_NAME, MyContentProvider.COLUMN3_NAME, MyContentProvider.COLUMN4_NAME,
//                MyContentProvider.COLUMN5_NAME, MyContentProvider.COLUMN6_NAME, MyContentProvider.COLUMN7_NAME, MyContentProvider.COLUMN8_NAME,
//                MyContentProvider.COLUMN9_NAME, MyContentProvider.COLUMN10_NAME,};
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.row,
                cursor,
                null,
//                mListColumns,
                new int[] { R.id.listview_id},
                0

        );

        //TODO: initalize the listview
        listView = findViewById(R.id.listview_id);
        listView.setAdapter(cursorAdapter);

        // Notify the adapter that the data has changed (for example, after syncing the database)
        cursor = getContentResolver().query(MyContentProvider.contentURI, projection, null, null, sortOrder);
        cursorAdapter.swapCursor(cursor);




    }

}