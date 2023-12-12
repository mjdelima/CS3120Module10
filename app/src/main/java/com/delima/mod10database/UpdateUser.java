package com.delima.mod10database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateUser extends AppCompatActivity {
    EditText username, password, confirmpass;
    String usernameData, passwordData, userid;
    Button btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        username = findViewById(R.id.etUsername2);
        password = findViewById(R.id.etPassword2);
        confirmpass = findViewById(R.id.etPasswordConfirm2);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        getIntentData();


        btnUpdate.setOnClickListener(v -> {
            if(!(confirmpass.getText().toString().equals(passwordData))){
                Toast.makeText(this, "Please enter your old password correctly", Toast.LENGTH_SHORT).show();
                return;
            }
            DBHelper myDB = new DBHelper(UpdateUser.this);
            myDB.updateData(userid,
                    username.getText().toString(),
                    password.getText().toString());
        finish();
        });

        btnDelete.setOnClickListener(v -> {
            if(!(confirmpass.getText().toString().equals(passwordData))){
                Toast.makeText(this, "Please enter your old password!", Toast.LENGTH_SHORT).show();
                return;
            }
            DBHelper myDB = new DBHelper(UpdateUser.this);
            myDB.removeData(userid);
            finish();
        });
    }

    void getIntentData(){
        if(getIntent().hasExtra("username") &&
                getIntent().hasExtra("password")){

            //getting data from intent
            userid = getIntent().getStringExtra("userid");
            usernameData = getIntent().getStringExtra("username");
            passwordData  = getIntent().getStringExtra("password");
            username.setText(usernameData);


        }else{
            Toast.makeText(this, "No Data Loaded!", Toast.LENGTH_SHORT).show();
        }
    }
}