package com.example.loginui_kakao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginui_kakao.data.CommentItem;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter <CommentAdapter.MyViewHolder>{
    //private Categories categoriesList;
    private List<CommentItem> comments;
    private Context context;

    public CommentAdapter(List<CommentItem> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.Content.setText(comments.get(position).getAuthorId());
        holder.Id.setText(comments.get(position).getContents());
    }

    @Override
    public int getItemCount() {
        return comments.size();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Content, Id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Content = (TextView) itemView.findViewById(R.id.comment_content);
            Id = (TextView) itemView.findViewById(R.id.comment_id);
        }
    }
}
