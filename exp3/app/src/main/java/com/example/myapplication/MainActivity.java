package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText username , password ;
    Button login1 , register1 ;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        username = findViewById(R.id.lusername);
        password = findViewById(R.id.lpassword);
        login1 = findViewById(R.id.llogin);
        register1 = findViewById(R.id.lregister);

        DB = new DBHelper(this);


        login1.setOnClickListener(v->{
            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();
            if(user.isEmpty() || pass.isEmpty()){
                Toast.makeText(MainActivity.this,"Enter all fields",Toast.LENGTH_SHORT).show();
            }else{
                if(DB.checkLogin(user,pass)){
                    Toast.makeText(MainActivity.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Invalid Credentials" ,Toast.LENGTH_SHORT).show();
                }
            }
        });

        register1.setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        });


    }
}