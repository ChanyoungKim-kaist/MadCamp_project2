package com.example.loginui_kakao;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.MyViewHolder>{
    private Categories categoriesList;
    private List<String> titles;
    private List<String> subtitles;
    private List<String> ids;
    private Context context;

    public RecyclerAdapter(Categories categoriesList, Context context){
        this.categoriesList = categoriesList;
        this.titles = categoriesList.getTitle();
        this.subtitles = categoriesList.getSubtitle();
        this.ids = categoriesList.getId();
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //holder.Title.setText(categoriesList.getTitle());
        //holder.subtitle.setText(categoriesList.get(position).getSubtitle());
        holder.Title.setText(titles.get(position));
        holder.subtitle.setText(subtitles.get(position));
    }

    @Override
    public int getItemCount() {
        return categoriesList.getTitle().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Title, subtitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = (TextView) itemView.findViewById(R.id.txt_title);
            subtitle = (TextView) itemView.findViewById(R.id.txt_sub);


        }
    }

}
