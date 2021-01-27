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


public class MoviesFragment extends Fragment {

    private ImageButton mImageButton;
    private RecyclerView mRecyclerView;

    private MyDataBaseHelper myDB;
    private ArrayList<String> movie_id;
    private ArrayList<String> movie_title;
    private ArrayList<String> movie_minute;
    private ArrayList<String> movie_second;
    private MovieAdapter mMovieAdapter;

    public MoviesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        mImageButton = view.findViewById(R.id.ibAddMovie);
        mRecyclerView = view.findViewById(R.id.recyclerViewMovies);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        myDB = new MyDataBaseHelper(getActivity());
        movie_id = new ArrayList<>();
        movie_title = new ArrayList<>();
        movie_minute = new ArrayList<>();
        movie_second = new ArrayList<>();

        mMovieAdapter = new MovieAdapter(getContext(), movie_id, movie_title, movie_minute, movie_second);
        mRecyclerView.setAdapter(mMovieAdapter);
        setUpListener();
        storeMovieData();
        return view;
    }

    private void setUpListener() {
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MovieAdd.class);
                startActivity(intent);
            }
        });
    }

    private void storeMovieData(){
        Cursor cursor = myDB.readAllMovieData();
        if(cursor.getCount() == 0){
            //Toast.makeText(getContext(), "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                movie_id.add(cursor.getString(0));
                movie_title.add(cursor.getString(1));
                movie_minute.add(cursor.getString(2));
                movie_second.add(cursor.getString(3));
            }
        }
    }
}