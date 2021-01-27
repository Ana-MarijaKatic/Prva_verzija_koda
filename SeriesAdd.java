package com.example.whereileftoff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SeriesAdd extends AppCompatActivity{

    private EditText mEtEnterTitleS;
    private EditText mEtEnterSeasonS;
    private EditText mEtEnterEpisodeS;
    private EditText mEtEnterMinuteS;
    private EditText mEtEnterSecondS;
    private Button mBtnEnterAddS;
    private Button mBtnEnterCancelS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_series);

        mEtEnterTitleS = findViewById(R.id.etEnterTitleS);
        mEtEnterSeasonS = findViewById(R.id.etEnterSeasonS);
        mEtEnterEpisodeS = findViewById(R.id.etEnterEpisodeS);
        mEtEnterMinuteS = findViewById(R.id.etEnterMinuteS);
        mEtEnterSecondS = findViewById(R.id.etEnterSecondS);
        mBtnEnterAddS = findViewById(R.id.btnEnterAddS);
        mBtnEnterCancelS = findViewById(R.id.btnEnterCancelS);

        setUpListener();
    }

    private void setUpListener() {
        mBtnEnterAddS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEtEnterTitleS.getText().toString().isEmpty() || mEtEnterSeasonS.getText().toString().isEmpty() || mEtEnterEpisodeS.getText().toString().isEmpty()
                || mEtEnterMinuteS.getText().toString().isEmpty() || mEtEnterSecondS.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter the missing data", Toast.LENGTH_SHORT).show();
                } else {
                    MyDataBaseHelper myDB = new MyDataBaseHelper(SeriesAdd.this);
                    myDB.addNewSeries(mEtEnterTitleS.getText().toString().trim(), Integer.parseInt(mEtEnterSeasonS.getText().toString().trim()),
                            Integer.parseInt(mEtEnterEpisodeS.getText().toString().trim()), Integer.parseInt(mEtEnterMinuteS.getText().toString().trim()),
                            Integer.parseInt(mEtEnterSecondS.getText().toString().trim()));

                    Intent intent = new Intent(SeriesAdd.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        mBtnEnterCancelS.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
