package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Notes extends AppCompatActivity {

    EditText title, note;
    Button addBtn;

    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        title = (EditText) findViewById(R.id.title);
        note = (EditText) findViewById(R.id.note);
        addBtn = (Button) findViewById(R.id.add);

        dbHandler = new DatabaseHandler(this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Products products = new Products();
                products.setTitle(title.getText().toString());
                products.setNote(note.getText().toString());
                dbHandler.addProduct(products);
                dbHandler.close();
                Intent intent = new Intent(Notes.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }



}
