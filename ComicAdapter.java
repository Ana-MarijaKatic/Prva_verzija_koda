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

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ComicViewHolder> {

    private Context context;
    private ArrayList comic_id;
    private ArrayList comic_title;
    private ArrayList comic_author;
    private ArrayList comic_volume;
    private ArrayList comic_issue;
    private ArrayList comic_page;
    private Animation translate_animation;

    ComicAdapter( Context context, ArrayList comic_id, ArrayList comic_title, ArrayList comic_author, ArrayList comic_volume, ArrayList comic_issue, ArrayList comic_page){
        this.context = context;
        this.comic_id = comic_id;
        this.comic_title = comic_title;
        this.comic_author = comic_author;
        this.comic_volume = comic_volume;
        this.comic_issue = comic_issue;
        this.comic_page = comic_page;
    }
    @NonNull
    @Override
    public ComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_item, parent,false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicViewHolder holder, int position) {
        holder.mTvTitleC.setText(String.valueOf(comic_title.get(position)));
        holder.mTvAuthorC.setText(String.valueOf(comic_author.get(position)));
        holder.mTvVolumeC.setText("Vol : " + String.valueOf(comic_volume.get(position)));
        holder.mTvIssueC.setText("Iss : " + String.valueOf(comic_issue.get(position)));
        holder.mTvPageC.setText("Page:" + String.valueOf(comic_page.get(position)));

        holder.mMainComicLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ComicEdit.class);
                intent.putExtra("id",String.valueOf(comic_id.get(position)));
                intent.putExtra("title",String.valueOf(comic_title.get(position)));
                intent.putExtra("author",String.valueOf(comic_author.get(position)));
                intent.putExtra("volume",String.valueOf(comic_volume.get(position)));
                intent.putExtra("issue",String.valueOf(comic_issue.get(position)));
                intent.putExtra("page",String.valueOf(comic_page.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comic_id.size();
    }

    public class ComicViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvTitleC;
        private TextView mTvAuthorC;
        private TextView mTvVolumeC;
        private TextView mTvIssueC;
        private TextView mTvPageC;
        private RelativeLayout mMainComicLayout;

        public ComicViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitleC = itemView.findViewById(R.id.tvComicTitle);
            mTvAuthorC = itemView.findViewById(R.id.tvComicAuthor);
            mTvVolumeC = itemView.findViewById(R.id.tvComicVolume);
            mTvIssueC = itemView.findViewById(R.id.tvComicIssue);
            mTvPageC = itemView.findViewById(R.id.tvComicPage);

            mMainComicLayout = itemView.findViewById(R.id.mainComicLayout);
            translate_animation = AnimationUtils.loadAnimation(context, R.anim.translate_animation);
            mMainComicLayout.setAnimation(translate_animation);
        }
    }
}
