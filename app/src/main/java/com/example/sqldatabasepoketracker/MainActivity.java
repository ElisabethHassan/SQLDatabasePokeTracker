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
    MyContentProvider.MainDatabaseHelper dbHelp = new MyContentProvider.MainDatabaseHelper(MainActivity.this);
    ContentValues newValues = new ContentValues();

    View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            validateEntries();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

//        spinner.setOnItemSelectedListener(spinListener);
        resetButton.setOnClickListener(resetButtonListener);
        saveButton.setOnClickListener(submitListener);
        viewButton.setOnClickListener(viewButtonListener);

    }



    public void validateEntries(){
        // validate the input fields
        boolean isValid = true;

        // validate national number
        String nationalNumStr = nationalNum.getText().toString();
        try {
            int nationaln = Integer.parseInt(nationalNumStr);
            if (nationaln < 0 || nationaln > 1010) {
                nationalNumLabel.setTextColor(Color.RED);
                isValid = false;
            } else {
                nationalNumLabel.setTextColor(Color.BLACK);
                newValues.put(MyContentProvider.COLUMN1_NATIONAL_NUMBER, nationalNumStr );
            }
            //make sure the input is a number
        } catch (NumberFormatException e) {
            nationalNumLabel.setTextColor(Color.RED);
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
            nameLabel.setTextColor(Color.RED);
            isValid = false;
        } else {
            nameLabel.setTextColor(Color.BLACK);
            newValues.put(MyContentProvider.COLUMN2_NAME, nameET );
        }
        // validate Species
        String speciesStr = species.getText().toString();
        if (speciesStr.isEmpty()) {
            speciesLabel.setTextColor(Color.RED);
            isValid = false;
        } else {
            speciesLabel.setTextColor(Color.BLACK);
            newValues.put(MyContentProvider.COLUMN3_SPECIES, speciesStr );
        }
        // Validate Height
        String heightStr = height.getText().toString();
        if (heightStr.endsWith("m")) {
            heightStr = heightStr.substring(0, heightStr.length() - 1);
        }
        if (heightStr.isEmpty() || Double.parseDouble(heightStr) < 0.3 || Double.parseDouble(heightStr) > 19.99) {
            heightLabel.setTextColor(Color.RED);
            isValid = false;
        } else {
            heightLabel.setTextColor(Color.BLACK);
            newValues.put(MyContentProvider.COLUMN5_HEIGHT, heightStr );
        }
        // Validate Weight
        String weightStr = weight.getText().toString();
        if (weightStr.endsWith("kg")) {
            weightStr = weightStr.substring(0, weightStr.length() - 2);
        }
        if (weightStr.isEmpty() || Double.parseDouble(weightStr) < 0.1 || Double.parseDouble(weightStr) > 820.00) {
            weightLabel.setTextColor(Color.RED);
            isValid = false;
        } else {
            newValues.put(MyContentProvider.COLUMN6_WEIGHT, weightStr );
        }
        // Validate HP
        String hpStr = hp.getText().toString();
        int hpET = Integer.parseInt(hpStr);
        boolean isHpValid = false;
        try {
            if (hpET >= 1 && hpET <= 362) {
                isHpValid = true;
            }
        } catch (NumberFormatException e) {
            hpLabel.setTextColor(Color.RED);
            isValid = false;
        }
        if (!isHpValid) {
            hpLabel.setTextColor(Color.RED);
            isValid = false;
        } else {
            hpLabel.setTextColor(Color.BLACK);
            newValues.put(MyContentProvider.COLUMN8_HP, hpStr );
        }
        // Validate Attack
        String attackStr = attack.getText().toString();
        boolean isAttackValid = false;
        int attackET = Integer.parseInt(attackStr);
        try {
            if (attackET >= 5 && attackET <= 526) {
                isAttackValid = true;
            }
        } catch (NumberFormatException e) {
            attackLabel.setTextColor(Color.RED);
            isValid = false;
        }
        if (!isAttackValid) {
            attackLabel.setTextColor(Color.RED);
            attackLabel.append("*");
            isValid = false;
        } else {
            attackLabel.setTextColor(Color.BLACK);
            newValues.put(MyContentProvider.COLUMN9_ATTACK, attackStr );
        }
        // Validate Defense
        String defenseStr = defense.getText().toString();
        boolean isDefenseValid = false;
        int defenseET = Integer.parseInt(defenseStr);
        try {
            if (defenseET >= 5 && defenseET <= 614) {
                isDefenseValid = true;
            }
        } catch (NumberFormatException e) {
            defenseLabel.setTextColor(Color.RED);
            isValid = false;
        }
        if (!isDefenseValid) {
            defenseLabel.setTextColor(Color.RED);
            isValid = false;
        } else {
            defenseLabel.setTextColor(Color.BLACK);
            newValues.put(MyContentProvider.COLUMN10_DEFENSE, defenseStr );
        }
        // Validate Level (Spinner)
        String selectedLevel = spinner.getSelectedItem().toString();
        if (selectedLevel.isEmpty()) {
            isValid = false;
            spinnerLabel.setTextColor(Color.RED);
        }
        // Validate Gender (Radio buttons)
        int selectedGenderId = gender.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            isValid = false;
            genderLabel.setTextColor(Color.RED);
        } else {
            genderLabel.setTextColor(Color.BLACK);
        }

        //adds values into the database
//        boolean isInserted = dbHelp.putP(nationalNumStr, nameET, speciesStr, "gender", Double.parseDouble(heightStr), Double.parseDouble(weightStr),selectedGenderId, hpET, attackET, defenseET);
        boolean isInserted = dbHelp.putP(nationalNumStr, nameET, speciesStr, "gender", heightStr, weightStr,Integer.toString(selectedGenderId), hpStr, attackStr, defenseStr);


        if (isValid && isInserted) {
            Toast.makeText(MainActivity.this, "Successful! Information stored in the database.", Toast.LENGTH_SHORT).show();
        } else if (!isInserted){
            Toast.makeText(MainActivity.this, "Failed to add data to database.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Please fix errors.", Toast.LENGTH_SHORT).show();
        }


        //inserts data into the database through content URI
//            getContentResolver().insert(MyContentProvider.contentURI, newValues);
//            Cursor result = provider.query(MyContentProvider.contentURI, null, null, null, null);
        boolean exits = false;


    }

}