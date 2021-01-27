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

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder> {

    private Context context;
    private ArrayList series_id;
    private ArrayList series_title;
    private ArrayList series_season;
    private ArrayList series_episode;
    private ArrayList series_minute;
    private ArrayList series_second;
    private Animation translate_animation;

    SeriesAdapter( Context context, ArrayList series_id, ArrayList series_title, ArrayList series_season,
                  ArrayList series_episode, ArrayList series_minute, ArrayList series_second){
        this.context = context;
        this.series_id = series_id;
        this.series_title = series_title;
        this.series_season = series_season;
        this.series_episode = series_episode;
        this.series_minute = series_minute;
        this.series_second = series_second;
    }

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_item, parent,false);
        return new SeriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        holder.mTvTitleS.setText(String.valueOf(series_title.get(position)));
        holder.mTvSeasonS.setText("Se   :  " + String.valueOf(series_season.get(position)));
        holder.mTvEpisodeS.setText("Ep   :  " + String.valueOf(series_episode.get(position)));
        holder.mTvMinuteS.setText("Min :  " + String.valueOf(series_minute.get(position)));
        holder.mTvSecondS.setText("Sec :  " + String.valueOf(series_second.get(position)));

        holder.mMainSeriesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SeriesEdit.class);
                intent.putExtra("id",String.valueOf(series_id.get(position)));
                intent.putExtra("title",String.valueOf(series_title.get(position)));
                intent.putExtra("season",String.valueOf(series_season.get(position)));
                intent.putExtra("episode",String.valueOf(series_episode.get(position)));
                intent.putExtra("minute",String.valueOf(series_minute.get(position)));
                intent.putExtra("second",String.valueOf(series_second.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return series_id.size();
    }

    public class SeriesViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvTitleS;
        private TextView mTvSeasonS;
        private TextView mTvEpisodeS;
        private TextView mTvMinuteS;
        private TextView mTvSecondS;
        private RelativeLayout mMainSeriesLayout;

        public SeriesViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitleS = itemView.findViewById(R.id.tvSeriesTitle);
            mTvSeasonS = itemView.findViewById(R.id.tvSeriesSeason);
            mTvEpisodeS = itemView.findViewById(R.id.tvSeriesEpisode);
            mTvMinuteS = itemView.findViewById(R.id.tvSeriesMinute);
            mTvSecondS = itemView.findViewById(R.id.tvSeriesSecond);
            mMainSeriesLayout = itemView.findViewById(R.id.mainSeriesLayout);
            translate_animation = AnimationUtils.loadAnimation(context, R.anim.translate_animation);
            mMainSeriesLayout.setAnimation(translate_animation);
        }
    }
}
