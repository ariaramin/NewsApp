package com.example.newsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class News implements Parcelable {

    @SerializedName("source")
    Source source;

    @SerializedName("author")
    String author;

    @SerializedName("title")
    String title;

    @SerializedName("description")
    String description;

    @SerializedName("url")
    String url;

    @SerializedName("urlToImage")
    String image;

    @SerializedName("publishedAt")
    String publishedDate;

    @SerializedName("content")
    String content;

    public Source getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getContent() {
        return content;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.source, flags);
        dest.writeString(this.author);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.url);
        dest.writeString(this.image);
        dest.writeString(this.publishedDate);
        dest.writeString(this.content);
    }

    public void readFromParcel(Parcel source) {
        this.source = source.readParcelable(Source.class.getClassLoader());
        this.author = source.readString();
        this.title = source.readString();
        this.description = source.readString();
        this.url = source.readString();
        this.image = source.readString();
        this.publishedDate = source.readString();
        this.content = source.readString();
    }

    public News() {
    }

    protected News(Parcel in) {
        this.source = in.readParcelable(Source.class.getClassLoader());
        this.author = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.url = in.readString();
        this.image = in.readString();
        this.publishedDate = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
