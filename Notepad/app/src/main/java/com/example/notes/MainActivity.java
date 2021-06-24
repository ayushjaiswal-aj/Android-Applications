package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<Products> productArrList;

    DatabaseHandler dbHandler = new DatabaseHandler(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        Button addNote;
        recyclerView = findViewById(R.id.notesRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        addNote = (Button) findViewById(R.id.addNote);

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, Notes.class);
                startActivity(intent);
            }
        });

        productArrList = new ArrayList<>();
        List<Products> products = dbHandler.getAllNotes();

        productArrList.addAll(products);

        adapter = new Adapter(MainActivity.this, productArrList);
        recyclerView.setAdapter(adapter);

    }

}
