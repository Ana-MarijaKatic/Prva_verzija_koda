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

public class MovieEdit extends AppCompatActivity {

    private EditText mEtTitleM;
    private EditText mEtMinuteM;
    private EditText mEtSecondM;
    private Button mBtnSaveM;
    private Button mBtnCancelM;
    private Button mBtnDeleteM;

    private String id;
    private String title;
    private String minute;
    private String second;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_movie);

        mEtTitleM = findViewById(R.id.etTitleM);
        mEtMinuteM = findViewById(R.id.etMinuteM);
        mEtSecondM = findViewById(R.id.etSecondM);
        mBtnSaveM = findViewById(R.id.btnSaveM);
        mBtnCancelM = findViewById(R.id.btnCancelM);
        mBtnDeleteM = findViewById(R.id.btnDeleteM);

        getSetMovieData();
        setUpListener();
    }

    private void setUpListener() {
        mBtnSaveM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEtTitleM.getText().toString().isEmpty() || mEtMinuteM.getText().toString().isEmpty() || mEtSecondM.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter the missing data", Toast.LENGTH_SHORT).show();
                } else {
                    MyDataBaseHelper myDB = new MyDataBaseHelper(MovieEdit.this);
                    myDB.updateMovie(id, mEtTitleM.getText().toString().trim(), mEtMinuteM.getText().toString().trim(), mEtSecondM.getText().toString().trim());

                    Intent intent = new Intent(MovieEdit.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        mBtnCancelM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtnDeleteM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog();
            }
        });
    }

    private void getSetMovieData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("minute") && getIntent().hasExtra("second")){

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            minute = getIntent().getStringExtra("minute");
            second = getIntent().getStringExtra("second");

            mEtTitleM.setText(title);
            mEtMinuteM.setText(minute);
            mEtSecondM.setText(second);

        } else {
            Toast.makeText(getApplicationContext(),"No data", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Delete movie?")
                .setMessage("Are you sure you want to delete this movie?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyDataBaseHelper myDB = new MyDataBaseHelper(MovieEdit.this);
                        myDB.deleteMovie(id);
                        Intent intent = new Intent(MovieEdit.this, MainActivity.class);
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
