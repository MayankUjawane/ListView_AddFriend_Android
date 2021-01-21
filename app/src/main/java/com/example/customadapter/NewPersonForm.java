package com.example.customadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewPersonForm extends AppCompatActivity {

    Button btn_ok, btn_cancel;
    EditText et_name, et_age, et_gender;
    int positionToEdit = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person_form);

        btn_ok = findViewById(R.id.btn_ok);
        et_age = findViewById(R.id.et_age);
        et_name = findViewById(R.id.et_name);
        et_gender = findViewById(R.id.et_gender);

        // listen for incoming data
        Bundle incomingIntent = getIntent().getExtras();

        if(incomingIntent != null){

            // capture incoming data
            String name = incomingIntent.getString("name");
            int age = incomingIntent.getInt("age");
            String gender = incomingIntent.getString("gender");
            positionToEdit = incomingIntent.getInt("edit");

            // fill in the form
            et_name.setText(name);
            et_age.setText(Integer.toString(age));
            et_gender.setText(gender);
        }

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get strings from et_view objects
                String newName = et_name.getText().toString();
                String newAge = et_age.getText().toString();
                String newGender = et_gender.getText().toString();

                // put the strings inta a message for MainActivity
                Intent i = new Intent(view.getContext(), MainActivity.class);

                i.putExtra("edit", positionToEdit);
                i.putExtra("name", newName);
                i.putExtra("age", newAge);
                i.putExtra("gender", newGender);

                // start MainActivity again
                startActivity(i);


            }
        });


    }
}