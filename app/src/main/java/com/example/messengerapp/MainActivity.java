package com.example.messengerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private final HashMap<String,String> loginList = new HashMap<String,String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }
    public void btnLoginPageClick(View v){
        int id = v.getId();
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);//Skip authorisation process
//        if(id == R.id.btn_login){
//            EditText loginEditTxt = findViewById(R.id.loginEditTxt);
//            EditText passwordEditTxt = findViewById(R.id.passwordEditTxt);
//            for(Map.Entry<String,String> l:loginList.entrySet()){
//                if(l.getKey().equals(loginEditTxt.getText())&&l.getValue().equals(passwordEditTxt.getText())){
//                    Intent intent = new Intent(this, MainPage.class);
//                    startActivity(intent);
//                }else{
//                    //'Wrong password or login' message
//                }
//            }
//        } else if (id == R.id.btn_registration) {
//            Intent intent = new Intent(this, RegisterPage.class);
//            startActivity(intent);
//        }
    }
//    public void getLoginPassword(HashMap<String,String> loginList){
//
//    }
}