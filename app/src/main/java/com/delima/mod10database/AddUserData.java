package com.delima.mod10database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserData extends AppCompatActivity {
    private EditText username, password, confirmPass;
    private Button saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_data);

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        confirmPass = findViewById(R.id.etPasswordConfirm);
        saveData = findViewById(R.id.btnAddData);

        saveData.setOnClickListener(v -> {

            if(!(password.getText().toString().equals(confirmPass.getText().toString()))){
                Toast.makeText(this, "Password do not match!", Toast.LENGTH_SHORT).show();
                return;
            }

            DBHelper myDB = new DBHelper(AddUserData.this);
            myDB.addAccount(username.getText().toString(), password.getText().toString());
        });

    }
}