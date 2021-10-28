package com.example.newsapptest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class TopNewsAdapter extends SliderViewAdapter<TopNewsAdapter.TopNewsViewHolder> {


    private final Context context;
    private final List<News> sliderItems;

    public TopNewsAdapter(Context context, List<News> sliderItems) {
        this.context = context;
        this.sliderItems = sliderItems;
    }

    @Override
    public TopNewsViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_news_item, parent, false);
        return new TopNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopNewsViewHolder viewHolder, int position) {
        viewHolder.title.setText(sliderItems.get(position).getTitle());
        Glide.with(context)
                .load(sliderItems.get(position).getImage())
                .placeholder(R.drawable.resource_default)
                .error(R.drawable.resource_default)
                .fitCenter()
                .into(viewHolder.image);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("source", sliderItems.get(position).source.getName());
                intent.putExtra("author", sliderItems.get(position).getAuthor());
                intent.putExtra("title", sliderItems.get(position).getTitle());
                intent.putExtra("description", sliderItems.get(position).getDescription());
//                intent.putExtra("url", sliderItems.get(position).getUrl());
                intent.putExtra("imageUrl", sliderItems.get(position).getImage());
                intent.putExtra("publishedDate", sliderItems.get(position).getPublishedDate());
                intent.putExtra("content", sliderItems.get(position).getContent());

                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getCount() {
        return sliderItems.size();
    }

    static class TopNewsViewHolder extends SliderViewAdapter.ViewHolder {

        ImageView image;
        TextView title;

        public TopNewsViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.topNewsImageView);
            title = itemView.findViewById(R.id.newsTitleTextView);
        }
    }
}
