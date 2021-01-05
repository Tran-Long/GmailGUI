package com.example.gmailgui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    List<MessageModel> favList;
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        Bundle bundle = getIntent().getExtras();
        favList = (List<MessageModel>) bundle.getSerializable("abc");
        MessageAdapter adapter = new MessageAdapter(FavoriteActivity.this, favList);
        listView = findViewById(R.id.list_fav);
        listView.setAdapter(adapter);

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
