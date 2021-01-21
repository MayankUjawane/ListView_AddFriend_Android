package com.example.customadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonAdapter extends BaseAdapter {

    Activity mActivity;
    MyFriends myFriends;

    public PersonAdapter(Activity mActivity, MyFriends myFriends) {
        this.mActivity = mActivity;
        this.myFriends = myFriends;
    }

    @Override
    public int getCount() {
        return myFriends.getMyFriendsList().size();
    }

    @Override
    public Object getItem(int i) {
        return myFriends.getMyFriendsList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View personOneLine;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        personOneLine = inflater.inflate(R.layout.person_one_line, viewGroup , false);

        TextView tv_name = personOneLine.findViewById(R.id.tv_name);
        TextView tv_age = personOneLine.findViewById(R.id.tv_ageValue);
        ImageView iv_icon = personOneLine.findViewById(R.id.iv_icon);

        Person p = (Person) this.getItem(i);

        tv_name.setText(p.getName());
        tv_age.setText(Integer.toString(p.getAge()));
        iv_icon.setImageResource(R.drawable.ic_launcher_background);

        return personOneLine;
    }
}