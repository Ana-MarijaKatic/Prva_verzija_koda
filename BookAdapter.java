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

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context context;
    private ArrayList book_id;
    private ArrayList book_title;
    private ArrayList book_author;
    private ArrayList book_page;
    private Animation translate_animation;

    BookAdapter( Context context, ArrayList book_id, ArrayList book_title, ArrayList book_author, ArrayList book_page){
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_page = book_page;
    }
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.mTvTitleB.setText(String.valueOf(book_title.get(position)));
        holder.mTvAuthorB.setText(String.valueOf(book_author.get(position)));
        holder.mTvPageB.setText("Page:" + String.valueOf(book_page.get(position)));

        holder.mMainBookLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,BookEdit.class);
                intent.putExtra("id",String.valueOf(book_id.get(position)));
                intent.putExtra("title",String.valueOf(book_title.get(position)));
                intent.putExtra("author",String.valueOf(book_author.get(position)));
                intent.putExtra("page",String.valueOf(book_page.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }


    public class BookViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvTitleB;
        private TextView mTvAuthorB;
        private TextView mTvPageB;
        private RelativeLayout mMainBookLayout;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitleB = itemView.findViewById(R.id.tvBookTitle);
            mTvAuthorB = itemView.findViewById(R.id.tvBookAuthor);
            mTvPageB = itemView.findViewById(R.id.tvBookPage);

            mMainBookLayout = itemView.findViewById(R.id.mainBookLayout);
            translate_animation = AnimationUtils.loadAnimation(context, R.anim.translate_animation);
            mMainBookLayout.setAnimation(translate_animation);
        }
    }
}
