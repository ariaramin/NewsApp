package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        List<String> colors = new ArrayList<String>();
        colors.add("#fc8c03");
        colors.add("#fcd703");
        colors.add("#fc5e03");
        colors.add("#03fc35");
        colors.add("#036ffc");
        colors.add("#9003fc");
        colors.add("#fc03b1");
        Random randomizer = new Random();
        int color = Color.parseColor(colors.get(randomizer.nextInt(colors.size())));

        News current = data.get(position);

        holder.title.setText(current.getTitle());
        holder.sourceCard.setCardBackgroundColor(color);
        holder.source.setText(current.getSource().name);
        Glide.with(context)
                .load(current.getImage())
                .placeholder(R.drawable.resource_default)
                .error(R.drawable.resource_default)
                .fitCenter()
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("source", current.source.getName());
                intent.putExtra("author", current.getAuthor());
                intent.putExtra("title", current.getTitle());
                intent.putExtra("description", current.getDescription());
                intent.putExtra("imageUrl", current.getImage());
                intent.putExtra("publishedDate", current.getPublishedDate());
                intent.putExtra("content", current.getContent());

                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView source, title;
        CardView sourceCard;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.newsImageView);
            source = itemView.findViewById(R.id.sourceTextView);
            sourceCard = itemView.findViewById(R.id.sourceCardView);
            title = itemView.findViewById(R.id.titleTextView);
        }
    }
}
