package com.example.whereileftoff;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BookEdit extends AppCompatActivity {

    private EditText mEtTitleB;
    private EditText mEtAuthorB;
    private EditText mEtPageB;
    private Button mBtnSaveB;
    private Button mBtnCancelB;
    private Button mBtnDeleteB;

    private String id;
    private String title;
    private String author;
    private String page;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_book);

        mEtTitleB = findViewById(R.id.etTitleB);
        mEtAuthorB = findViewById(R.id.etAuthorB);
        mEtPageB = findViewById(R.id.etPageB);
        mBtnSaveB = findViewById(R.id.btnSaveB);
        mBtnCancelB = findViewById(R.id.btnCancelB);
        mBtnDeleteB = findViewById(R.id.btnDeleteB);

        getSetBookData();
        setUpListener();
    }

    private void setUpListener() {
        mBtnSaveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEtTitleB.getText().toString().isEmpty() || mEtAuthorB.getText().toString().isEmpty() || mEtPageB.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter the missing data", Toast.LENGTH_SHORT).show();
                } else {
                    MyDataBaseHelper myDB = new MyDataBaseHelper(BookEdit.this);
                    myDB.updateBook(id, mEtTitleB.getText().toString().trim(), mEtAuthorB.getText().toString().trim(), mEtPageB.getText().toString().trim());

                    Intent intent = new Intent(BookEdit.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        mBtnCancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtnDeleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog();
            }
        });
    }

    private void getSetBookData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("page")){

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            page = getIntent().getStringExtra("page");

            mEtTitleB.setText(title);
            mEtAuthorB.setText(author);
            mEtPageB.setText(page);

        } else {
            Toast.makeText(getApplicationContext(),"No data", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Delete book?")
                .setMessage("Are you sure you want to delete this book?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyDataBaseHelper myDB = new MyDataBaseHelper(BookEdit.this);
                        myDB.deleteBook(id);
                        Intent intent = new Intent(BookEdit.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

}
