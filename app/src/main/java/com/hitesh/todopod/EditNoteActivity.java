package com.hitesh.todopod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

public class EditNoteActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText editNote;
    String note;
    String newNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        editNote = (EditText) findViewById(R.id.editNote);
        db = new DatabaseHelper(this);

        editNote.setText(getIntent().getStringExtra("text"));

        Intent intent = getIntent();
        note = intent.getStringExtra("text");
        Toast.makeText(getApplicationContext(), "" + note, Toast.LENGTH_SHORT).show();

        editNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                db.delete(note);
            }

            @Override
            public void afterTextChanged(Editable s) {
                newNote = editNote.getText().toString();
            }
        });


    }
    public void onBackPressed() {

        if(newNote != null){
            db.insertData(newNote);
        }
        Intent MainActivity = new Intent(getApplicationContext(), com.hitesh.todopod.MainActivity.class);
        startActivity(MainActivity);
            super.onBackPressed();

    }
}
