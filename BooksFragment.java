package com.example.whereileftoff;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class BooksFragment extends Fragment {

    private ImageButton mImageButton;
    private RecyclerView mRecyclerView;

    private MyDataBaseHelper myDB;
    private ArrayList<String> book_id;
    private ArrayList<String> book_title;
    private ArrayList<String> book_author;
    private ArrayList<String> book_page;
    private BookAdapter mBookAdapter;

    public BooksFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books, container, false);
        mImageButton = view.findViewById(R.id.ibAddBook);
        mRecyclerView = view.findViewById(R.id.recyclerViewBooks);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        myDB = new MyDataBaseHelper(getActivity());
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_page = new ArrayList<>();

        mBookAdapter = new BookAdapter(getContext(), book_id, book_title, book_author, book_page);
        mRecyclerView.setAdapter(mBookAdapter);
        setUpListener();
        storeBookData();
        return view;
    }

    private void setUpListener() {
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),BookAdd.class);
                startActivity(intent);
            }
        });
    }

    private void storeBookData(){
        Cursor cursor = myDB.readAllBookData();
        if(cursor.getCount() == 0){
            //Toast.makeText(getContext(), "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_page.add(cursor.getString(3));
            }
        }
    }
}