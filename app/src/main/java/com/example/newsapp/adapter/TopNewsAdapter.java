package com.example.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newsapp.model.News;
import com.example.newsapp.activities.detail.DetailActivity;
import com.example.newsapp.R;
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
        viewHolder.bindData(context, sliderItems.get(position));
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

        private void bindData(Context context, News news) {
            title.setText(news.getTitle());
            Glide.with(context)
                    .load(news.getImage())
                    .placeholder(R.drawable.resource_default)
                    .error(R.drawable.resource_default)
                    .fitCenter()
                    .into(image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("news", news);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
