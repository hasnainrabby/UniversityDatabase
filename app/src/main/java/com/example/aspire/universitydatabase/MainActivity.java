package com.example.aspire.universitydatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    DataBaseHelper mydb;
    EditText editID, editName, editDepartment, editUniversity, editSession;
    String gender;
    Button btnAddData;
    Button btnViewAll;
    Button btnDelete;
    Button btnUpdate;
    RadioGroup rdgrp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DataBaseHelper(this);


        editID = findViewById(R.id.editText_ID);
        editName = findViewById(R.id.editText_name);
        editDepartment = findViewById(R.id.editText_department);
        editUniversity = findViewById(R.id.editText_university);
        editSession = findViewById(R.id.editText_session);


        rdgrp=(RadioGroup)findViewById(R.id.Radiogroupgender);
        rdgrp.setOnCheckedChangeListener(this);

        //edit text er id find//

        //btn er id find//

        btnAddData = findViewById(R.id.button_add);
        btnViewAll = findViewById(R.id.button_viewAll);
        btnUpdate = findViewById(R.id.button_update);
        btnDelete = findViewById(R.id.button_delete);

        AddData();
        viewAll();
        Delete();
        Update();


    }
   public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_male:
                if (checked)
                    // male rule
                    break;
            case R.id.radio_female:
                if (checked)
                    // female rule
                    break;
        }
    }

    private void Update() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = mydb.updateData(editID.getText().toString(),
                        editName.getText().toString(),
                        editDepartment.getText().toString(),
                        editUniversity.getText().toString(),
                        editSession.getText().toString(),
                                gender.toString());


                if (isUpdate == true){
                    Toast.makeText(MainActivity.this, "Data is Updated", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data is not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Delete() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows = mydb.deleteData(editID.getText().toString());
                if (deleteRows >0){
                    Toast.makeText(MainActivity.this, "Data is Deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data is not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void viewAll() {
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mydb.getAllData();
                        if (res.getCount() == 0) {
                            showMessage("Error", "Nothing Found");
                            return;

                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id:" + res.getString(0) + "\n");
                            buffer.append("Name:" + res.getString(1) + "\n");
                            buffer.append("Department:" + res.getString(2) + "\n");
                            buffer.append("University:" + res.getString(3) + "\n");
                            buffer.append("Session:" + res.getString(4) + "\n");
                            buffer.append("Gender:" + res.getString(5 ) + "\n\n");
                        }
                        showMessage("Data", buffer.toString());

                    }
                });
    }

//show message er method implement

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    private void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = mydb.insertData(
                                editID.getText().toString(),
                                editName.getText().toString(),
                                editDepartment.getText().toString(),
                                editUniversity.getText().toString(),
                                editSession.getText().toString(),
                                gender.toString());
                        if (isInserted = true)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }



    @Override
    public void onCheckedChanged(RadioGroup rdgrp, int i ) {
        int radioButtonId = rdgrp.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton)rdgrp.findViewById(radioButtonId);
        gender = radioButton.getText().toString();

    }
}

