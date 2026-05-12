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

public class RegisterActivity extends AppCompatActivity {


    EditText username , password ;
    Button login2 , register2 ;

    DBHelper DB ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = findViewById(R.id.rusername);
        password = findViewById(R.id.rpassword);

        login2 = findViewById(R.id.rlogin);
        register2 = findViewById(R.id.rregister);

        DB = new DBHelper(this);

        register2.setOnClickListener(v->{

            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if(user.isEmpty() || pass.isEmpty()){
                Toast.makeText(RegisterActivity.this , "Please enter all fields",Toast.LENGTH_SHORT).show();
            }else {
                if(DB.checkUsername(user)){
                    Toast.makeText(RegisterActivity.this,"User already exists",Toast.LENGTH_SHORT).show();
                }else {
                    if(DB.insertData(user,pass)){
                        Toast.makeText(RegisterActivity.this,"Registerd SUccessfully",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegisterActivity.this,"Register Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });


        login2.setOnClickListener(v->{
            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
        });


    }
}