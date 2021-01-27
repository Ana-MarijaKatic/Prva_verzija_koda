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

public class SeriesFragment extends Fragment {

    private ImageButton mImageButton;
    private RecyclerView mRecyclerView;

    private MyDataBaseHelper myDB;
    private ArrayList<String> series_id;
    private ArrayList<String> series_title;
    private ArrayList<String> series_season;
    private ArrayList<String> series_episode;
    private ArrayList<String> series_minute;
    private ArrayList<String> series_second;
    private SeriesAdapter mSeriesAdapter;

    public SeriesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_series, container, false);
        mImageButton = view.findViewById(R.id.ibAddSeries);
        mRecyclerView = view.findViewById(R.id.recyclerViewSeries);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        myDB = new MyDataBaseHelper(getActivity());
        series_id = new ArrayList<>();
        series_title = new ArrayList<>();
        series_season = new ArrayList<>();
        series_episode = new ArrayList<>();
        series_minute = new ArrayList<>();
        series_second = new ArrayList<>();

        mSeriesAdapter = new SeriesAdapter(getContext(), series_id, series_title, series_season, series_episode, series_minute, series_second);
        mRecyclerView.setAdapter(mSeriesAdapter);
        setUpListener();
        storeSeriesData();
        return view;
    }

    private void setUpListener() {
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SeriesAdd.class);
                startActivity(intent);
            }
        });
    }

    private void storeSeriesData(){
        Cursor cursor = myDB.readAllSeriesData();
        if(cursor.getCount() == 0){
            //Toast.makeText(getContext(), "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                series_id.add(cursor.getString(0));
                series_title.add(cursor.getString(1));
                series_season.add(cursor.getString(2));
                series_episode.add(cursor.getString(3));
                series_minute.add(cursor.getString(4));
                series_second.add(cursor.getString(5));
            }
        }
    }
}