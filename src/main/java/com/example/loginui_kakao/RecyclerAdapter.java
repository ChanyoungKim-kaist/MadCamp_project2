package com.example.loginui_kakao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginui_kakao.data.PostItem;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.MyViewHolder>{
    //private Categories categoriesList;
    private List<PostItem> posts;
    private Context context;
    private OnNoteListener mOnNoteListener;

    public RecyclerAdapter(List<PostItem> posts, Context context, OnNoteListener onNoteListener){
        //this.categoriesList = categoriesList;
        this.posts = posts;
        this.context = context;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_layout, parent, false);
        return new MyViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.Title.setText(posts.get(position).getTitle());
        holder.subtitle.setText(posts.get(position).getSubtitle());
        holder.AuthorId.setText(String.valueOf(posts.get(position).getAuthorId()));
        holder.Likes.setText(String.valueOf(posts.get(position).getLikes()));
    }

    @Override
    public int getItemCount() {
        return posts.size();

    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView Title, subtitle, AuthorId, Likes;
        OnNoteListener onNoteListener;

        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            Title = (TextView) itemView.findViewById(R.id.txt_title);
            subtitle = (TextView) itemView.findViewById(R.id.txt_sub);
            AuthorId = (TextView) itemView.findViewById(R.id.txt_author);
            Likes = (TextView) itemView.findViewById(R.id.likes);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
}
