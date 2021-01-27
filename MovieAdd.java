package com.example.whereileftoff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MovieAdd extends AppCompatActivity {

    private EditText mEtEnterTitleM;
    private EditText mEtEnterMinuteM;
    private EditText mEtEnterSecondM;
    private Button mBtnEnterAddM;
    private Button mBtnEnterCancelM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_movie);

        mEtEnterTitleM = findViewById(R.id.etEnterTitleM);
        mEtEnterMinuteM = findViewById(R.id.etEnterMinuteM);
        mEtEnterSecondM = findViewById(R.id.etEnterSecondM);
        mBtnEnterAddM = findViewById(R.id.btnEnterAddM);
        mBtnEnterCancelM = findViewById(R.id.btnEnterCancelM);

        setUpListener();
    }

    private void setUpListener() {
        mBtnEnterAddM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEtEnterTitleM.getText().toString().isEmpty() || mEtEnterMinuteM.getText().toString().isEmpty() || mEtEnterSecondM.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter the missing data", Toast.LENGTH_SHORT).show();
                } else {
                    MyDataBaseHelper myDB = new MyDataBaseHelper(MovieAdd.this);
                    myDB.addNewMovie(mEtEnterTitleM.getText().toString().trim(), Integer.parseInt(mEtEnterMinuteM.getText().toString().trim()), Integer.parseInt(mEtEnterSecondM.getText().toString().trim()));

                    Intent intent = new Intent(MovieAdd.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        mBtnEnterCancelM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
