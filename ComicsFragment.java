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

public class ComicsFragment extends Fragment {

    private ImageButton mImageButton;
    private RecyclerView mRecyclerView;

    private MyDataBaseHelper myDB;
    private ArrayList<String> comic_id;
    private ArrayList<String> comic_title;
    private ArrayList<String> comic_author;
    private ArrayList<String> comic_volume;
    private ArrayList<String> comic_issue;
    private ArrayList<String> comic_page;
    private ComicAdapter mComicAdapter;

    public ComicsFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comics, container, false);
        mImageButton = view.findViewById(R.id.ibAddComic);
        mRecyclerView = view.findViewById(R.id.recyclerViewComics);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        myDB = new MyDataBaseHelper(getActivity());
        comic_id = new ArrayList<>();
        comic_title = new ArrayList<>();
        comic_author = new ArrayList<>();
        comic_volume = new ArrayList<>();
        comic_issue = new ArrayList<>();
        comic_page = new ArrayList<>();

        mComicAdapter = new ComicAdapter(getContext(), comic_id, comic_title, comic_author, comic_volume, comic_issue, comic_page);
        mRecyclerView.setAdapter(mComicAdapter);
        setUpListener();
        storeComicData();
        return view;
    }

    private void setUpListener() {
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ComicAdd.class);
                startActivity(intent);
            }
        });
    }

    private void storeComicData(){
        Cursor cursor = myDB.readAllComicData();
        if(cursor.getCount() == 0){
            //Toast.makeText(getContext(), "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                comic_id.add(cursor.getString(0));
                comic_title.add(cursor.getString(1));
                comic_author.add(cursor.getString(2));
                comic_volume.add(cursor.getString(3));
                comic_issue.add(cursor.getString(4));
                comic_page.add(cursor.getString(5));
            }
        }
    }
}