package com.example.gmailgui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;

public class MainActivity extends AppCompatActivity {
    List<MessageModel> listEmails, searchList, favList;
    SearchView searchView;
    MessageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listEmails = new ArrayList<>();
        searchList = new ArrayList<>();
        favList = new ArrayList<>();
        for(int i = 0; i < 25 ; i++){
            Faker faker = new Faker();
            String sender = faker.name.name();
            String subject = "Subject: " + faker.lorem.word();
            String content = faker.lorem.paragraph(40);
            String peakContent = content.substring(0, 25).concat("...");
            listEmails.add(new MessageModel(sender, subject, peakContent));
        }
        adapter = new MessageAdapter(this, listEmails);
        ListView listView = findViewById(R.id.list_message);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        listView.setLongClickable(true);
        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        searchView = (SearchView) menu.findItem(R.id.search_action).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.equals("")){
                    searchList.clear();
                    adapter.setList(listEmails);
                    adapter.notifyDataSetChanged();
                }else{
                    searchList.clear();
                    for(int i = 0; i < listEmails.size(); i++){
                        if(listEmails.get(i).getSender().contains(query) || listEmails.get(i).getSubject().contains(query))
                            searchList.add(listEmails.get(i));
                    }
                    adapter.setList(searchList);
                    adapter.notifyDataSetChanged();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals("")){
                    searchList.clear();
                    adapter.setList(listEmails);
                    adapter.notifyDataSetChanged();
                }else{
                    searchList.clear();
                    for(int i = 0; i < listEmails.size(); i++){
                        if(listEmails.get(i).getSender().contains(newText) || listEmails.get(i).getSubject().contains(newText))
                            searchList.add(listEmails.get(i));
                    }
                    adapter.setList(searchList);
                    adapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.favorite_action){
            Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
            Bundle bundle = new Bundle();
            for(MessageModel x : listEmails){
                if(x.isCheckFavourite()){
                    favList.add(x);
                }
            }
            bundle.putSerializable("abc", (Serializable) favList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 177);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = item.getItemId();
        if(id == R.id.delete_action){
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setMessage("Do you want to remove this email ?")
                    .setTitle("Remove email")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int pos = info.position;
                            listEmails.remove(pos);
                            adapter.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("No", null)
                    .create();
            dialog.show();
            dialog.setCanceledOnTouchOutside(true);
        }else if(id == R.id.reply_action){
            Intent intent = new Intent(MainActivity.this, ReplyActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("name", listEmails.get(info.position).sender);
            intent.putExtras(bundle);
            startActivityForResult(intent, 221);
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 177){
            favList.clear();
        }
        if(requestCode == 221){
            if(resultCode == RESULT_OK){
                Toast.makeText(MainActivity.this, "Successfully Reply !!!", Toast.LENGTH_LONG).show();
            }else if (resultCode == 1){
                Toast.makeText(MainActivity.this, "Message saved as draft", Toast.LENGTH_LONG).show();
            }
        }
    }
}