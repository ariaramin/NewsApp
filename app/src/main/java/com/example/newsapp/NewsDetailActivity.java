package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsDetailActivity extends AppCompatActivity {

    ImageView image;
    TextView title, description, content, source;

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        image = findViewById(R.id.detailImageView);
        title = findViewById(R.id.detailTitleTextView);
        description = findViewById(R.id.detailDescriptionTextView);
        content = findViewById(R.id.detailContentTextView);
        source = findViewById(R.id.detailSourceTextView);

        setDataOnViews();

    }

    private void setDataOnViews() {
        extras = getIntent().getExtras();
        if (extras != null) {

            Glide.with(this)
                    .load(extras.getString("imageUrl"))
                    .placeholder(R.drawable.resource_default)
                    .error(R.drawable.resource_default)
                    .fitCenter()
                    .into(image);

            source.setText(extras.getString("source"));
            title.setText(extras.getString("title"));
            description.setText(extras.getString("description"));
            content.setText(extras.getString("content"));
        }
    }
}