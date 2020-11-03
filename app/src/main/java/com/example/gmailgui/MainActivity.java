package com.example.gmailgui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    List<MessageModel> listMessage;
    String firstName[] = {"Long", "Lam", "Chinh", "Dung", "Vinh", "Khang", "Ngoc", "Thu", "Ngoc", "Trinh", "Uyen", "Quang", "Son"};
    String lastName[] = {"Tran", "Nguyen", "Pham", "Dang", "Le", "Ly"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listMessage = new ArrayList<>();
        for(int i = 0; i < 50 ; i++){
            Random random = new Random();
            String fullName = firstName[random.nextInt(firstName.length)] + lastName[random.nextInt(lastName.length)];
            listMessage.add(new MessageModel(fullName, "Peak content from " + fullName));
        }
        MessageAdapter adapter = new MessageAdapter(this, listMessage);
        ListView listView = findViewById(R.id.list_message);
        listView.setAdapter(adapter);
    }
}