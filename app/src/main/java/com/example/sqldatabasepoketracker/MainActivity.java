package com.example.sqldatabasepoketracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText height;
    EditText weight;
    private EditText nationalNum;
    private EditText name;
    private EditText species;
    private EditText hp;
    private EditText attack;
    private EditText defense;
    private TextView nationalNumLabel;
    private TextView nameLabel;
    private TextView speciesLabel;
    private TextView weightLabel;
    private TextView heightLabel;
    private TextView hpLabel;
    private TextView attackLabel;
    private TextView defenseLabel;
    private TextView genderLabel;
    private TextView spinnerLabel;
    private Spinner spinner;
    private Button saveButton, viewButton;
    private Button resetButton;
    private ListView listView;
    RadioGroup gender;
    String[] mProjection;
    String mSelectionClause;
    String[] mSelectionArgs;
    String mOrderBy;
    //fetches data from user and puts into the database
    ContentValues newValues = new ContentValues();

    View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // validate the input fields
            boolean isValid = true;

            // validate national number
            String nationalNumStr = nationalNum.getText().toString();
            try {
                int nationaln = Integer.parseInt(nationalNumStr);
                if (nationaln < 0 || nationaln > 1010) {
                    //nationalNumLabel.setTextColor(Color.RED);
                    nationalNumLabel.append("*");
                    isValid = false;
                } else {
                    nationalNumLabel.setTextColor(Color.BLACK);
//                    nationalNumLabel.setText(nationalNumStr.substring(0,nationalNumLabel.length()-1));
                    newValues.put(MyContentProvider.COLUMN1_NAME, nationalNumStr );
                }
                //make sure the input is a number
            } catch (NumberFormatException e) {
                //nationalNumLabel.setTextColor(Color.RED);
                nationalNumLabel.append("*");
                isValid = false;
            }

            // Validate Name
            String nameET = name.getText().toString();
            boolean isAlphabetical = true;

            if (nameET.isEmpty() || nameET.length() < 3 || nameET.length() > 12) {
                isAlphabetical = false;
            } else {
                for (int i = 0; i < nameET.length(); i++) {
                    char c = nameET.charAt(i);
                    if (!Character.isLetter(c)) {
                        isAlphabetical = false;
                        break;
                    }
                }
            }
            //checks that it is in the alphabet
            if (!isAlphabetical) {
                //nameLabel.setTextColor(Color.RED);
                nameLabel.append("*");
                isValid = false;
            } else {
                nameLabel.setTextColor(Color.BLACK);
//                nameLabel.setText(nameET.substring(0,nameLabel.length()-1));
                newValues.put(MyContentProvider.COLUMN2_NAME, nameET );
            }
            // validate Species
            String speciesStr = species.getText().toString();
            if (speciesStr.isEmpty()) {
                //speciesLabel.setTextColor(Color.RED);
                speciesLabel.append("*");
                isValid = false;
            } else {
                speciesLabel.setTextColor(Color.BLACK);
//                speciesLabel.setText(speciesStr.substring(0,speciesLabel.length()-1));
                newValues.put(MyContentProvider.COLUMN3_NAME, speciesStr );
            }
            // Validate Height
            String heightStr = height.getText().toString();
            if (heightStr.endsWith("m")) {
                heightStr = heightStr.substring(0, heightStr.length() - 1);
            }
            if (heightStr.isEmpty() || Double.parseDouble(heightStr) < 0.3 || Double.parseDouble(heightStr) > 19.99) {
                //heightLabel.setTextColor(Color.RED);
                heightLabel.append("*");
                isValid = false;
            } else {
                heightLabel.setTextColor(Color.BLACK);
//                heightLabel.setText(heightStr.substring(0,heightLabel.length()-1));
                newValues.put(MyContentProvider.COLUMN5_NAME, heightStr );
            }
            // Validate Weight
            String weightStr = weight.getText().toString();
            if (weightStr.endsWith("kg")) {
                weightStr = weightStr.substring(0, weightStr.length() - 2);
            }
            if (weightStr.isEmpty() || Double.parseDouble(weightStr) < 0.1 || Double.parseDouble(weightStr) > 820.00) {
                //weightLabel.setTextColor(Color.RED);
                weightLabel.append("*");
                isValid = false;
            } else {
//                weightLabel.setText(weightStr.substring(0,weightLabel.length()-1));
                newValues.put(MyContentProvider.COLUMN6_NAME, weightStr );
            }
            // Validate HP
            String hpStr = hp.getText().toString();
            boolean isHpValid = false;
            try {
                int hpET = Integer.parseInt(hpStr);
                if (hpET >= 1 && hpET <= 362) {
                    isHpValid = true;
                }
            } catch (NumberFormatException e) {
                //hpLabel.setTextColor(Color.RED);
                hpLabel.append("*");
                isValid = false;
            }
            if (!isHpValid) {
                //hpLabel.setTextColor(Color.RED);
                hpLabel.append("*");
                isValid = false;
            } else {
                hpLabel.setTextColor(Color.BLACK);
//                hpLabel.setText(hpStr.substring(0,hpLabel.length()-1));
                newValues.put(MyContentProvider.COLUMN8_NAME, hpStr );
            }
            // Validate Attack
            String attackStr = attack.getText().toString();
            boolean isAttackValid = false;
            try {
                int attackET = Integer.parseInt(attackStr);
                if (attackET >= 5 && attackET <= 526) {
                    isAttackValid = true;
                }
            } catch (NumberFormatException e) {
                //attackLabel.setTextColor(Color.RED);
                attackLabel.append("*");
                isValid = false;
            }
            if (!isAttackValid) {
                //attackLabel.setTextColor(Color.RED);
                attackLabel.append("*");
                isValid = false;
            } else {
                attackLabel.setTextColor(Color.BLACK);
//                attackLabel.setText(attackStr.substring(0,attackLabel.length()-1));
                newValues.put(MyContentProvider.COLUMN9_NAME, attackStr );
            }
            // Validate Defense
            String defenseStr = defense.getText().toString();
            boolean isDefenseValid = false;
            try {
                int defenseET = Integer.parseInt(defenseStr);
                if (defenseET >= 5 && defenseET <= 614) {
                    isDefenseValid = true;
                }
            } catch (NumberFormatException e) {
                //defenseLabel.setTextColor(Color.RED);
                defenseLabel.append("*");
                isValid = false;
            }
            if (!isDefenseValid) {
               // defenseLabel.setTextColor(Color.RED);
                defenseLabel.append("*");
                isValid = false;
            } else {
                defenseLabel.setTextColor(Color.BLACK);
//                genderLabel.setText(defenseLabel.getText().toString().substring(0,defenseLabel.length()-1));
                newValues.put(MyContentProvider.COLUMN10_NAME, defenseStr );
            }
            // Validate Level (Spinner)
            String selectedLevel = spinner.getSelectedItem().toString();
            if (selectedLevel.isEmpty()) {
                // You may need to adjust this validation based on Spinner's values
                isValid = false;
                spinnerLabel.append("*");
                //spinnerLabel.setTextColor(Color.RED);
            }
            // Validate Gender (Radio buttons)
            int selectedGenderId = gender.getCheckedRadioButtonId();
            if (selectedGenderId == -1) {
                isValid = false;
//                genderLabel.setTextColor(Color.RED);
                genderLabel.append("*");
            } else {
                genderLabel.setTextColor(Color.BLACK);
//                genderLabel.setText(genderLabel.getText().toString().substring(0,genderLabel.length()-1));
            }
            if (isValid) {
                // All checks passed, display a success Toast
                Toast.makeText(MainActivity.this, "Successful! Information stored in the database.", Toast.LENGTH_SHORT).show();
            } else {
                // Notify the user about errors via Toast
                Toast.makeText(MainActivity.this, "Please fix the errors.", Toast.LENGTH_SHORT).show();
            }



//
//            if(!newValues.containsKey(nationalNumStr))  newValues.put(MyContentProvider.COLUMN1_NAME, nationalNumStr );
//            if(!newValues.containsKey(nameET)) newValues.put(MyContentProvider.COLUMN2_NAME, nameET );
//            if(!newValues.containsKey(speciesStr)) newValues.put(MyContentProvider.COLUMN3_NAME, speciesStr );
////            if(!newValues.containsKey(selectedGenderId)) newValues.put(MyContentProvider.COLUMN4_NAME, selectedGenderId);
//            if(!newValues.containsKey(heightStr)) newValues.put(MyContentProvider.COLUMN5_NAME, heightStr );
//            if(!newValues.containsKey(weightStr))newValues.put(MyContentProvider.COLUMN6_NAME, weightStr );
//            if(!newValues.containsKey(selectedLevel)) newValues.put(MyContentProvider.COLUMN7_NAME, selectedLevel );
//            if(!newValues.containsKey(hpStr)) newValues.put(MyContentProvider.COLUMN8_NAME, hpStr );
//            if(!newValues.containsKey(attackStr)) newValues.put(MyContentProvider.COLUMN9_NAME, attackStr );
//            if(!newValues.containsKey(defenseStr)) newValues.put(MyContentProvider.COLUMN10_NAME, defenseStr );

            //inserts data into the database through content URI
            getContentResolver().insert(MyContentProvider.contentURI, newValues);
//            Cursor result = provider.query(MyContentProvider.contentURI, null, null, null, null);
            boolean exits = false;


        }
    };

    View.OnClickListener viewButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
            startActivity(intent);


        }
    };

    View.OnClickListener resetButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            name.setText("Glastrier");
            nationalNum.setText("896");
            species.setText("Wild Horse Pokémon");
            weight.setText("2.2");
            height.setText("800.0");
            hp.setText("0");
            attack.setText("0");
            defense.setText("0");
            nameLabel.setTextColor(Color.BLACK);
            nationalNumLabel.setTextColor(Color.BLACK);
            speciesLabel.setTextColor(Color.BLACK);
            weightLabel.setTextColor(Color.BLACK);
            heightLabel.setTextColor(Color.BLACK);
            hpLabel.setTextColor(Color.BLACK);
            attackLabel.setTextColor(Color.BLACK);
            defenseLabel.setTextColor(Color.BLACK);
            genderLabel.setTextColor(Color.BLACK);
        }
    };

    AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            String name = cursor.getString(cursor.getColumnIndexOrThrow(MyContentProvider.COLUMN1_NAME));
        }
    };



    AdapterView.OnItemSelectedListener spinListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            // adapterView.getSelectedItem(); //
//            String message = adapterView.getItemAtPosition(i).toString();
//            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.row);
//        setContentView(R.layout.linear);
        //setContentView(R.layout.table);

        height = findViewById(R.id.height_editText);
        weight = findViewById(R.id.weight_editText);
        nationalNum = findViewById(R.id.national_editview);
        nationalNumLabel = findViewById(R.id.nationalLabel_tv);
        name = findViewById(R.id.name_editText);
        nameLabel = findViewById(R.id.nameLabel_tv);
        species = findViewById(R.id.species_editText);
        speciesLabel = findViewById(R.id.speciesLabel_tv);
        weightLabel = findViewById(R.id.weightLabel_tv);
        heightLabel = findViewById(R.id.heightLabel_tv);
        hp = findViewById(R.id.hp_editText);
        hpLabel = findViewById(R.id.hpLabel_tv);
        attack = findViewById(R.id.attack_editText);
        attackLabel = findViewById(R.id.attackLabel_tv);
        defense = findViewById(R.id.defense_editText);
        defenseLabel = findViewById(R.id.defenseLabel_tv);
        spinner = findViewById(R.id.spinner_box);
        resetButton = findViewById(R.id.button);
        gender = findViewById(R.id.radioGroup);
        saveButton = findViewById(R.id.save_button);
        genderLabel = findViewById(R.id.genderLabel_tv);
        spinnerLabel = findViewById(R.id.levelLabel_tv);
        viewButton = findViewById(R.id.viewButton_id);
//        listView = findViewById(R.id.);

        spinner.setOnItemSelectedListener(spinListener);
        resetButton.setOnClickListener(resetButtonListener);
        saveButton.setOnClickListener(submitListener);
        viewButton.setOnClickListener(viewButtonListener);




//        registerForContextMenu(listView);

    }
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        if (v.getId() == R.id.listview_id) {
//            MenuInflater inflater = getMenuInflater();
//            inflater.inflate(R.menu.context_menu, menu); // Create a context menu resource (e.g., context_menu.xml)
//        }
//    }


}