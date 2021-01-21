package com.example.customadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    Button btn_add, btn_sortName, btn_sortAge;
    ListView lv_friendsList;
    PersonAdapter adapter;
    MyFriends myFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_sortName = findViewById(R.id.btn_sortName);
        btn_sortAge = findViewById(R.id.btn_sortAge);
        lv_friendsList = findViewById(R.id.lv_listOfNames);

        myFriends = ((MyApplication) this.getApplication()).getMyFriends();

        adapter = new PersonAdapter(MainActivity.this, myFriends);

        lv_friendsList.setAdapter(adapter);

        // listen for incoming messages
        Bundle incomingMessages = getIntent().getExtras();

        if(incomingMessages != null){

            // capture incoming data
            String name = incomingMessages.getString("name");
            int age = Integer.parseInt(incomingMessages.getString("age"));
            String gender = incomingMessages.getString("gender");
            int positionEdited = incomingMessages.getInt("edit");

            // create new Persons object
            Person p = new Person(name, gender, age);

            // add Person to the list and update adapter
            if(positionEdited > -1){
                myFriends.getMyFriendsList().remove(positionEdited);
            }
            myFriends.getMyFriendsList().add(p);

            // notifyDataSetChanged should be invoked every time you add, remove or update an item in the myFriends arrayList
            adapter.notifyDataSetChanged();

        }

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), NewPersonForm.class );
                startActivity(i);
            }
        });

        btn_sortName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(myFriends.getMyFriendsList());
                adapter.notifyDataSetChanged();
            }
        });

        btn_sortAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(myFriends.getMyFriendsList(), new Comparator<Person>() {
                    @Override
                    public int compare(Person p1, Person p2) {
                        return p1.getAge() - p2.getAge();
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });

        lv_friendsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editPerson(i);
            }
        });

    }
    public void editPerson(int position){
        Intent i = new Intent(getApplicationContext(), NewPersonForm.class);

        // get the contents of person at position
        Person p = myFriends.getMyFriendsList().get(position);

        i.putExtra("edit", position);
        i.putExtra("name", p.getName());
        i.putExtra("age", p.getAge());
        i.putExtra("gender", p.getGender());

        startActivity(i);
    }

}