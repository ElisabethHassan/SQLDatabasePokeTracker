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
        setContentView(R.layout.activity_database);


        Cursor cursor = getContentResolver().query(MyContentProvider.contentURI, null, null, null, null);
        mListColumns = new String[] {Pokemon.COLUMN2_NAME, Pokemon.COLUMN1_NATIONAL_NUMBER, Pokemon.COLUMN3_SPECIES, Pokemon.COLUMN5_HEIGHT, Pokemon.COLUMN6_WEIGHT, Pokemon.COLUMN7_LEVEL, Pokemon.COLUMN8_HP, Pokemon.COLUMN9_ATTACK, Pokemon.COLUMN10_DEFENSE, Pokemon.COLUMN4_GENDER};
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.row,
                cursor,
                mListColumns,
                new int[] { R.id.pokeName_id,R.id.natNum_id, R.id.species_id, R.id.height_id, R.id.weight_id, R.id.level_id, R.id.hp_id, R.id.attack_id, R.id.defense_id, R.id.gender_id},
                0
        );

        //TODO: initalize the listview
        listView = findViewById(R.id.listview);
        listView.setAdapter(cursorAdapter);

        // Notify the adapter that the data has changed (for example, after syncing the database)
        cursor = getContentResolver().query(MyContentProvider.contentURI, projection, null, null, sortOrder);
        cursorAdapter.swapCursor(cursor);


    }

}