package com.example.whereileftoff;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList movie_id;
    private ArrayList movie_title;
    private ArrayList movie_minute;
    private ArrayList movie_second;
    private Animation translate_animation;

    MovieAdapter( Context context, ArrayList movie_id, ArrayList movie_title, ArrayList movie_minute, ArrayList movie_second){
        this.context = context;
        this.movie_id = movie_id;
        this.movie_title = movie_title;
        this.movie_minute = movie_minute;
        this.movie_second = movie_second;
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.mTvTitleM.setText(String.valueOf(movie_title.get(position)));
        holder.mTvMinuteM.setText("Min : " + String.valueOf(movie_minute.get(position)));
        holder.mTvSecondM.setText("Sec : " + String.valueOf(movie_second.get(position)));

        holder.mMainMovieLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MovieEdit.class);
                intent.putExtra("id",String.valueOf(movie_id.get(position)));
                intent.putExtra("title",String.valueOf(movie_title.get(position)));
                intent.putExtra("minute",String.valueOf(movie_minute.get(position)));
                intent.putExtra("second",String.valueOf(movie_second.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movie_id.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvTitleM;
        private TextView mTvMinuteM;
        private TextView mTvSecondM;
        private RelativeLayout mMainMovieLayout;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitleM = itemView.findViewById(R.id.tvMovieTitle);
            mTvMinuteM = itemView.findViewById(R.id.tvMovieMinute);
            mTvSecondM = itemView.findViewById(R.id.tvMovieSecond);

            mMainMovieLayout = itemView.findViewById(R.id.mainMovieLayout);
            translate_animation = AnimationUtils.loadAnimation(context, R.anim.translate_animation);
            mMainMovieLayout.setAnimation(translate_animation);
        }
    }
}
