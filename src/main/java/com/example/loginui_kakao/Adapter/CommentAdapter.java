package com.example.loginui_kakao.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginui_kakao.R;
import com.example.loginui_kakao.data.CommentItem;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter <CommentAdapter.MyViewHolder>{
    //private Categories categoriesList;
    private List<CommentItem> comments;
    private Context context;
    private OnCommentListener mOnCommentListener;

    public CommentAdapter(List<CommentItem> comments, Context context, OnCommentListener onCommentListener) {
        this.comments = comments;
        this.context = context;
        this.mOnCommentListener = onCommentListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment, parent, false);
        return new MyViewHolder(view, mOnCommentListener);
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

    public interface OnCommentListener {
        void onCommentClick(int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView Content, Id;
        OnCommentListener onCommentListener;

        public MyViewHolder(@NonNull View itemView, OnCommentListener onCommentListener) {
            super(itemView);
            Content = (TextView) itemView.findViewById(R.id.comment_content);
            Id = (TextView) itemView.findViewById(R.id.comment_id);
            this.onCommentListener = onCommentListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCommentListener.onCommentClick(getAdapterPosition());
        }
    }
}
