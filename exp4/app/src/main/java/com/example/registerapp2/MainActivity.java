package com.example.registerapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button submit ;
    EditText email , name ;
    RadioGroup radiogroup;
    Spinner subject ;
    CheckBox ssc , hsc , grad ;

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

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        radiogroup = findViewById(R.id.radiogroup);
        ssc= findViewById(R.id.ssc);
        hsc = findViewById(R.id.hsc);
        grad = findViewById(R.id.grad);
        subject = findViewById(R.id.subject);
        submit = findViewById(R.id.submit);
        String[] sub = {"Maths","Science","Physics"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,sub
        );
        subject.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strname = name.getText().toString();
                String stremail = email.getText().toString();
                int index = radiogroup.getCheckedRadioButtonId();

                String gender = "";
                if(index!=-1){
                    RadioButton rb  = findViewById(index);
                    gender = rb.getText().toString();
                }

                String qualification ="";
                if(ssc.isChecked()) qualification += "SSC" ;
                if(hsc.isChecked()) qualification += "HSC" ;
                if(grad.isChecked()) qualification += "Graduation";

                String strsubject = subject.getSelectedItem().toString();

                if(strname.isEmpty()|| stremail.isEmpty()||gender.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(MainActivity.this,ShowActivity.class);
                String data = "Name: "+ strname +"Email: "+ "\n" + stremail + "\n" +"Subject: "+ strsubject + "\n" +
                        "Qualification: "+ qualification +"\nGender: "+gender;
                i.putExtra("Info" , data);
                startActivity(i);



            }
        });


    }
}