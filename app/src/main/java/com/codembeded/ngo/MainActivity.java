package com.codembeded.ngo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.codembeded.ngo.Adapters.ContactAdapter;
import com.codembeded.ngo.models.CollectPhoneNumbers;
import com.codembeded.ngo.models.ContactModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CollectPhoneNumbers {
    RecyclerView contact_rv;
    ContactAdapter adapter;
    ArrayList<ContactModel> data = new ArrayList<>();
    ImageButton sendBtn;
    EditText msgEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contact_rv = findViewById(R.id.contact_rv);
        sendBtn = findViewById(R.id.sendBtn);
        msgEt = findViewById(R.id.msgEt);
        getSupportActionBar().setTitle("AL-HUDA SARFRAZ COLONY");
        getContactList();


    }



    void getContactList() {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;

        Cursor cursor = getContentResolver().query(
                uri, null, null, null, sort
        );

        if (cursor.getCount() > 0) {
            //When count is greater then 0
            //Use While Loop
            while (cursor.moveToNext()) {
                //cursor move to next
                //Get contact id
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                //Get contact name
                String name = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME
                ));
                //Initialize phone uri
                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                //Initialize selection
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";

                //Initialize phone cursor
                Cursor phoneCursor = getContentResolver().query(
                        uriPhone, null, selection, new String[]{id}, null
                );
                // check condition
                if (phoneCursor.moveToNext()) {
                    //When Phone cursor move to next
                    String number = phoneCursor.getString(phoneCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    ));
                    //Initialize contact model
                    data.add(new ContactModel(name, number, false));


                    phoneCursor.close();
                }
            }
            cursor.close();
        }
        //set Layout
        adapter = new ContactAdapter(this, data, this);
        contact_rv.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        contact_rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menunext,menu);


         return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
          case R.id.next_item:
            Intent intent = new Intent(getApplicationContext(),ReadContact.class);
            startActivity(intent);
            finish();
            break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void phoneNumbers(int pos, ArrayList<String> collectedNumbers) {

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(collectedNumbers);
            }
        });
    }

    private void sendMessage(ArrayList<String> collectedNumbers) {
        //get values from fields

     //   Toast.makeText(getApplicationContext(), "Phone Numbers: " + collectedNumbers, Toast.LENGTH_SHORT).show();

        // String numbers[] = phoneNumber.split(", *");
        String msg = msgEt.getText().toString();
        //checking conditions
        if (!collectedNumbers.equals("") && !msg.equals("")) {
            // when values are not empty
            //initialize the sms manager
            SmsManager smsManager = SmsManager.getDefault();

            //send msg
            for (String num : collectedNumbers) {
                smsManager.sendTextMessage((num), null, msg,
                        null, null);

            }

            //display toast
            Toast.makeText(getApplicationContext(), "sent successfully",
                    Toast.LENGTH_SHORT).show();
            msgEt.setText("");

        } else {
            //when fields are blank
            Toast.makeText(getApplicationContext(), "Fill the Fields",
                    Toast.LENGTH_SHORT).show();
        }
    }
}