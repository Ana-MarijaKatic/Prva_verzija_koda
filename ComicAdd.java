package com.example.whereileftoff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ComicAdd extends AppCompatActivity {

    private EditText mEtEnterTitleC;
    private EditText mEtEnterAuthorC;
    private EditText mEtEnterVolumeC;
    private EditText mEtEnterIssueC;
    private EditText mEtEnterPageC;
    private Button mBtnEnterAddC;
    private Button mBtnEnterCancelC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_comic);

        mEtEnterTitleC = findViewById(R.id.etEnterTitleC);
        mEtEnterAuthorC = findViewById(R.id.etEnterAuthorC);
        mEtEnterVolumeC = findViewById(R.id.etEnterVolumeC);
        mEtEnterIssueC = findViewById(R.id.etEnterIssueC);
        mEtEnterPageC = findViewById(R.id.etEnterPageC);
        mBtnEnterAddC = findViewById(R.id.btnEnterAddC);
        mBtnEnterCancelC = findViewById(R.id.btnEnterCancelC);

        setUpListener();
    }

    private void setUpListener() {
        mBtnEnterAddC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEtEnterTitleC.getText().toString().isEmpty() || mEtEnterAuthorC.getText().toString().isEmpty() || mEtEnterVolumeC.getText().toString().isEmpty()
                        || mEtEnterIssueC.getText().toString().isEmpty()|| mEtEnterPageC.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter the missing data", Toast.LENGTH_SHORT).show();
                } else {
                    MyDataBaseHelper myDB = new MyDataBaseHelper(ComicAdd.this);
                    myDB.addNewComic(mEtEnterTitleC.getText().toString().trim(), mEtEnterAuthorC.getText().toString().trim(),Integer.parseInt(mEtEnterVolumeC.getText().toString().trim()),
                            Integer.parseInt(mEtEnterIssueC.getText().toString().trim()), Integer.parseInt(mEtEnterPageC.getText().toString().trim()));

                    Intent intent = new Intent(ComicAdd.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        mBtnEnterCancelC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
