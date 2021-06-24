package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNote extends AppCompatActivity {

    EditText title, note;
    Button edit, remove;
    int rId;
    DatabaseHandler dbHandler = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        title = (EditText) findViewById(R.id.title);
        note = (EditText) findViewById(R.id.note);
        remove = (Button) findViewById(R.id.remove);
        edit = (Button) findViewById(R.id.edit);

        Intent intent = getIntent();
        rId = Integer.parseInt(intent.getStringExtra("Rid"));
        String rTitle = intent.getStringExtra("Rtitle");
        String rNote = intent.getStringExtra("Rnote");

        title.setText(rTitle);
        note.setText(rNote);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Products products = new Products();
                products.setTitle(title.getText().toString());
                products.setNote(note.getText().toString());
                dbHandler.updateProduct(products);
                dbHandler.close();

                Intent intent1 = new Intent(EditNote.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.deleteProduct(rId);

                Intent intent1 = new Intent(EditNote.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}