package com.codembeded.ngo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.codembeded.ngo.Adapters.ContactAdapter;
import com.codembeded.ngo.models.ContactModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView contact_rv;
    ContactAdapter adapter;
    ArrayList<ContactModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contact_rv = findViewById(R.id.contact_rv);
        data.add(new ContactModel("U","umer","03248780332"));
        adapter = new ContactAdapter(data,getApplicationContext());
        contact_rv.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        contact_rv.setAdapter(adapter);
    }
}