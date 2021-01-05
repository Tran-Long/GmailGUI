package com.example.gmailgui;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReplyActivity extends AppCompatActivity {

    TextView receiver;
    EditText subject, content;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        receiver = findViewById(R.id.reply_to);
        subject = findViewById(R.id.reply_subject);
        content = findViewById(R.id.reply_content);
        Bundle bundle = getIntent().getExtras();
        receiver.setText(bundle.getString("name"));
        findViewById(R.id.btn_reply_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reply, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.send_action){
            setResult(RESULT_OK);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
