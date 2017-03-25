package com.example.android.booklisting;

/**
 * An {@link } object contains information related to a single earthquake.
 */
public class Book {

    /** Magnitude of the earthquake */
    private String mTitle;

    /** Location of the earthquake */
    private String mAuthor;

    private String mUrl;


    public Book(String title, String author, String url) {
        mTitle = title;
        mAuthor = author;
        mUrl = url;
    }


    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getUrl(){
        return mUrl;
    }
}
