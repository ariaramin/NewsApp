package com.example.newsapptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private final List<News> data;
    private final Context context;

    public NewsAdapter(Context context, List<News> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.title.setText(this.data.get(position).getTitle());
        holder.source.setText(this.data.get(position).getSource().name);
        Glide.with(context)
                .load(data.get(position).getImage())
                .placeholder(R.drawable.resource_default)
                .error(R.drawable.resource_default)
                .fitCenter()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView source, title;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.newsImageView);
            source = itemView.findViewById(R.id.sourceTextView);
            title = itemView.findViewById(R.id.titleTextView);
        }
    }
}
