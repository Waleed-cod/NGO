package com.codembeded.ngo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.codembeded.ngo.Adapters.AdapterRead;
import com.codembeded.ngo.models.CollectReadNumbers;
import com.codembeded.ngo.models.ModelRead;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadContact extends AppCompatActivity implements CollectReadNumbers {
    StringBuilder text = new StringBuilder();
    Button load_btn;
    int PERMISSION_REQUEST_STORAGE = 1000;
    int READ_REQUEST_CODE = 42;
    StringBuilder sb = new StringBuilder();
    int p2 = 11;
    int counter;
    int r2;
    RecyclerView rv;
    ArrayList<ModelRead> data = new ArrayList<>();
    AdapterRead adapterRead;
    EditText msg_et;
    ImageButton send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_conatact);
        msg_et = findViewById(R.id.msg_et);
        send = findViewById(R.id.btn_send);

        load_btn = findViewById(R.id.load_btn);
        rv = findViewById(R.id.rv);
        request();
        load_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performFileSearch();

            }
        });


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Permission Not Granted", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


    private void request() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_STORAGE);
        }
    }

    public void Read(String Input) {


        File file = new File(Environment.getExternalStorageDirectory(), Input);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null && line.length()==11 ) {
                text.append(line);
                text.append("\n");
                Log.i("length of text", String.valueOf(text.length()));
            }
            sb = text;
            for (int i = 0; i <= text.length(); i += 12) {
                if (i != 0) {
                    counter = i;
                    Log.i(" value of counter", String.valueOf(counter));
                    p2 = counter;
                    r2 = p2 - 12;
                    data.add(new ModelRead(sb.substring(r2, p2),false));

                    adapterRead = new AdapterRead(data, getApplicationContext(),this);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    rv.setAdapter(adapterRead);
                }
            }

            br.close();


        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    void performFileSearch(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent,READ_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            if(data!=null){

                Uri uri = data.getData();
                String path = uri.getPath();
                path =path.substring(path.indexOf(":") + 1);
                if(path.contains("emulated")){
                    path = path.substring(path.indexOf("0")+1);
                }

                Read(path);
            }
        }

    }


    @Override
    public void GetPhoneNumbers(ArrayList<String> collectedNumbers) {
        send.setOnClickListener(new View.OnClickListener() {
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
        String msg = msg_et.getText().toString();
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
            msg_et.setText("");

        } else {
            //when fields are blank
            Toast.makeText(getApplicationContext(), "Fill the Fields",
                    Toast.LENGTH_SHORT).show();
        }
    }
}

