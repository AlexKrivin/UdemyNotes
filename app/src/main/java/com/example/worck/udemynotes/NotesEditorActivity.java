package com.example.worck.udemynotes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NotesEditorActivity extends AppCompatActivity {

    int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_editor_activity);
        EditText editText = (EditText)findViewById(R.id.multiLineEditText);

        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID", -1);
        if (noteID !=-1){
        editText.setText(MainActivity.notes.get(noteID));
        }else {
            MainActivity.notes.add("");
            noteID = MainActivity.notes.size()-1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                MainActivity.notes.set(noteID,String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.worck.udemynotes", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }
}
