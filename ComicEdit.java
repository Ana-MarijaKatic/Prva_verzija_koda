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

public class ComicEdit extends AppCompatActivity {

    private EditText mEtTitleC;
    private EditText mEtAuthorC;
    private EditText mEtVolumeC;
    private EditText mEtIssueC;
    private EditText mEtPageC;
    private Button mBtnSaveC;
    private Button mBtnCancelC;
    private Button mBtnDeleteC;

    private String id;
    private String title;
    private String author;
    private String volume;
    private String issue;
    private String page;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_comic);

        mEtTitleC = findViewById(R.id.etTitleC);
        mEtAuthorC = findViewById(R.id.etAuthorC);
        mEtVolumeC = findViewById(R.id.etVolumeC);
        mEtIssueC = findViewById(R.id.etIssueC);
        mEtPageC = findViewById(R.id.etPageC);
        mBtnSaveC = findViewById(R.id.btnSaveC);
        mBtnCancelC = findViewById(R.id.btnCancelC);
        mBtnDeleteC = findViewById(R.id.btnDeleteC);

        getSetBookData();
        setUpListener();
    }
    private void setUpListener() {
        mBtnSaveC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEtTitleC.getText().toString().isEmpty() || mEtAuthorC.getText().toString().isEmpty() || mEtVolumeC.getText().toString().isEmpty()
                        || mEtIssueC.getText().toString().isEmpty() || mEtPageC.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter the missing data", Toast.LENGTH_SHORT).show();
                } else {
                    MyDataBaseHelper myDB = new MyDataBaseHelper(ComicEdit.this);
                    myDB.updateComic(id, mEtTitleC.getText().toString().trim(), mEtAuthorC.getText().toString().trim(),
                            mEtVolumeC.getText().toString().trim(), mEtIssueC.getText().toString().trim(), mEtPageC.getText().toString().trim());

                    Intent intent = new Intent(ComicEdit.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        mBtnCancelC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtnDeleteC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog();
            }
        });
    }
    private void getSetBookData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author")
                && getIntent().hasExtra("volume") && getIntent().hasExtra("issue") && getIntent().hasExtra("page")){

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            volume = getIntent().getStringExtra("volume");
            issue = getIntent().getStringExtra("issue");
            page = getIntent().getStringExtra("page");

            mEtTitleC.setText(title);
            mEtAuthorC.setText(author);
            mEtVolumeC.setText(volume);
            mEtIssueC.setText(issue);
            mEtPageC.setText(page);

        } else {
            Toast.makeText(getApplicationContext(),"No data", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Delete comic?")
                .setMessage("Are you sure you want to delete this comic?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyDataBaseHelper myDB = new MyDataBaseHelper(ComicEdit.this);
                        myDB.deleteComic(id);
                        Intent intent = new Intent(ComicEdit.this, MainActivity.class);
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
