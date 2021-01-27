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

public class SeriesEdit extends AppCompatActivity {
    
    private EditText mEtTitleS;
    private EditText mEtSeasonS;
    private EditText mEtEpisodeS;
    private EditText mEtMinuteS;
    private EditText mEtSecondS;
    private Button mBtnSaveS;
    private Button mBtnCancelS;
    private Button mBtnDeleteS;

    private String id;
    private String title;
    private String season;
    private String episode;
    private String minute;
    private String second;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_series);

        mEtTitleS = findViewById(R.id.etTitleS);
        mEtSeasonS = findViewById(R.id.etSeasonS);
        mEtEpisodeS = findViewById(R.id.etEpisodeS);
        mEtMinuteS = findViewById(R.id.etMinuteS);
        mEtSecondS = findViewById(R.id.etSecondS);
        mBtnSaveS = findViewById(R.id.btnSaveS);
        mBtnCancelS = findViewById(R.id.btnCancelS);
        mBtnDeleteS = findViewById(R.id.btnDeleteS);

        getSetSeriesData();
        setUpListener();
    }

    private void setUpListener() {
        mBtnSaveS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEtTitleS.getText().toString().isEmpty() || mEtSeasonS.getText().toString().isEmpty() || mEtEpisodeS.getText().toString().isEmpty()
                        || mEtMinuteS.getText().toString().isEmpty() || mEtSecondS.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter the missing data", Toast.LENGTH_SHORT).show();
                } else {
                    MyDataBaseHelper myDB = new MyDataBaseHelper(SeriesEdit.this);
                    myDB.updateSeries(id, mEtTitleS.getText().toString().trim(), mEtSeasonS.getText().toString().trim(), mEtEpisodeS.getText().toString().trim(),
                            mEtMinuteS.getText().toString().trim(), mEtSecondS.getText().toString().trim());
                    Intent intent = new Intent(SeriesEdit.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        mBtnCancelS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtnDeleteS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog();
            }
        });
    }

    private void getSetSeriesData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("season") &&
                getIntent().hasExtra("episode") && getIntent().hasExtra("minute") && getIntent().hasExtra("second")){

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            season = getIntent().getStringExtra("season");
            episode = getIntent().getStringExtra("episode");
            minute = getIntent().getStringExtra("minute");
            second = getIntent().getStringExtra("second");

            mEtTitleS.setText(title);
            mEtSeasonS.setText(season);
            mEtEpisodeS.setText(episode);
            mEtMinuteS.setText(minute);
            mEtSecondS.setText(second);

        } else {
            Toast.makeText(getApplicationContext(),"No data", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteDialog(){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Delete series?")
                .setMessage("Are you sure you want to delete this series?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyDataBaseHelper myDB = new MyDataBaseHelper(SeriesEdit.this);
                        myDB.deleteSeries(id);
                        Intent intent = new Intent(SeriesEdit.this, MainActivity.class);
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
