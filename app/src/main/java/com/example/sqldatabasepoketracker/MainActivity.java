package com.example.sqldatabasepoketracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
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
    private Button saveButton;
    private Button resetButton;
    private ListView listView;
    RadioGroup gender;



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
                    nationalNumLabel.setTextColor(Color.RED);
                    isValid = false;
                } else {
                    nationalNumLabel.setTextColor(Color.BLACK);
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
            }
            // validate Species
            String speciesStr = species.getText().toString();
            if (speciesStr.isEmpty()) {
                speciesLabel.setTextColor(Color.RED);
                isValid = false;
            } else {
                speciesLabel.setTextColor(Color.BLACK);
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
                weightLabel.setTextColor(Color.BLACK);
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
                hpLabel.setTextColor(Color.RED);
                isValid = false;
            }
            if (!isHpValid) {
                hpLabel.setTextColor(Color.RED);
                isValid = false;
            } else {
                hpLabel.setTextColor(Color.BLACK);
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
                attackLabel.setTextColor(Color.RED);
                isValid = false;
            }
            if (!isAttackValid) {
                attackLabel.setTextColor(Color.RED);
                isValid = false;
            } else {
                attackLabel.setTextColor(Color.BLACK);
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
                defenseLabel.setTextColor(Color.RED);
                isValid = false;
            }
            if (!isDefenseValid) {
                defenseLabel.setTextColor(Color.RED);
                isValid = false;
            } else {
                defenseLabel.setTextColor(Color.BLACK);
            }
            // Validate Level (Spinner)
            String selectedLevel = spinner.getSelectedItem().toString();
            if (selectedLevel.isEmpty()) {
                // You may need to adjust this validation based on Spinner's values
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
            if (isValid) {
                // All checks passed, display a success Toast
                Toast.makeText(MainActivity.this, "Successful! Information stored in the database.", Toast.LENGTH_SHORT).show();
            } else {
                // Notify the user about errors via Toast
                Toast.makeText(MainActivity.this, "Please fix the errors in red.", Toast.LENGTH_SHORT).show();
            }
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

//    View.OnClickListener switchLayoutListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            switchLayout();
//        }
//    };


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
        listView = findViewById();




        spinner.setOnItemSelectedListener(spinListener);
        resetButton.setOnClickListener(resetButtonListener);
        saveButton.setOnClickListener(submitListener);

    }
}