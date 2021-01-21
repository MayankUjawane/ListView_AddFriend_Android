package com.example.customadapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyFriends {
    List<Person> myFriendsList;

    public MyFriends(List<Person> myFriendsList) {
        this.myFriendsList = myFriendsList;
    }

    public MyFriends() {
        String[] startingNames = {"Ankit","Rahul","Kajal"};
        String[] startingGender = {"Male","Male","Female"};
        this.myFriendsList = new ArrayList<>();
        Random rand = new Random();
        for (int i=0; i<startingNames.length; i++){
            Person p = new Person(startingNames[i], startingGender[i], rand.nextInt(50)+15);
            myFriendsList.add(p);
        }
    }

    public List<Person> getMyFriendsList() {
        return myFriendsList;
    }

    public void setMyFriendsList(List<Person> myFriendsList) {
        this.myFriendsList = myFriendsList;
    }
}