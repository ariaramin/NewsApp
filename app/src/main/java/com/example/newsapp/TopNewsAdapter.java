package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

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
        News current = sliderItems.get(position);

        viewHolder.title.setText(current.getTitle());
        Glide.with(context)
                .load(current.getImage())
                .placeholder(R.drawable.resource_default)
                .error(R.drawable.resource_default)
                .fitCenter()
                .into(viewHolder.image);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
