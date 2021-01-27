package com.example.whereileftoff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BookAdd extends AppCompatActivity {

    private EditText mEtEnterTitleB;
    private EditText mEtEnterAuthorB;
    private EditText mEtEnterPageB;
    private Button mBtnEnterAddB;
    private Button mBtnEnterCancelB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book);

        mEtEnterTitleB = findViewById(R.id.etEnterTitleB);
        mEtEnterAuthorB = findViewById(R.id.etEnterAuthorB);
        mEtEnterPageB = findViewById(R.id.etEnterPageB);
        mBtnEnterAddB = findViewById(R.id.btnEnterAddB);
        mBtnEnterCancelB = findViewById(R.id.btnEnterCancelB);

        setUpListener();
    }

    private void setUpListener() {
        mBtnEnterAddB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEtEnterTitleB.getText().toString().isEmpty() || mEtEnterAuthorB.getText().toString().isEmpty() || mEtEnterPageB.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter the missing data", Toast.LENGTH_SHORT).show();
                } else {
                    MyDataBaseHelper myDB = new MyDataBaseHelper(BookAdd.this);
                    myDB.addNewBook(mEtEnterTitleB.getText().toString().trim(), mEtEnterAuthorB.getText().toString().trim(), Integer.parseInt(mEtEnterPageB.getText().toString().trim()));

                    Intent intent = new Intent(BookAdd.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        mBtnEnterCancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
