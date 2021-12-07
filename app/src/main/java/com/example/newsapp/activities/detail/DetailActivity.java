package com.example.newsapp.activities.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.example.newsapp.model.News;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {

    ImageView image;
    TextView title, description, content, source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DetailPresenter presenter = new DetailPresenter(this);
    }

    @Override
    public void setUpView() {
        image = findViewById(R.id.detailImageView);
        title = findViewById(R.id.detailTitleTextView);
        description = findViewById(R.id.detailDescriptionTextView);
        content = findViewById(R.id.detailContentTextView);
        source = findViewById(R.id.detailSourceTextView);
    }

    @Override
    public Intent getViewIntent() {
        return getIntent();
    }

    @Override
    public void setData(News news) {
        if (news != null) {
            Glide.with(this)
                    .load(news.getImage())
                    .placeholder(R.drawable.resource_default)
                    .error(R.drawable.resource_default)
                    .fitCenter()
                    .into(image);
            source.setText(news.getSource().getName());
            title.setText(news.getTitle());
            description.setText(news.getDescription());
            content.setText(news.getContent());
        }
    }
}