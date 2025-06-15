package com.example.messengerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RegisterPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_content);
    }
    public ArrayList<String> CorrectRegisterData(){

        EditText name = findViewById(R.id.nameEditTxt);
        EditText surename = findViewById(R.id.surenameEditTxt);
        EditText birthdate = findViewById(R.id.bDateEditTxt);
        EditText username = findViewById(R.id.usernameEditTxt);
        ArrayList<String> userData= new ArrayList<>();



        return userData;
    }
    public boolean CheckPassword(){
        boolean check = false;
        String str = "!,.%$#@&*()";
        EditText passwordEditTxt = findViewById(R.id.passwordEditTxt);
        String password = passwordEditTxt.getText().toString();
        EditText repeatPasswordEditTxt = findViewById(R.id.repeatPasswordEditTxt);
        //Проверка пароля
        if( password.equals(repeatPasswordEditTxt.getText().toString()) &&
            password.length() > 8 &&
            password.contains(str)
        ) {
            check = true;
        }

        return check;
    }
}
