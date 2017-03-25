package com.example.android.booklisting;

/**
 * An {@link } object contains information related to a single earthquake.
 */
public class Book {

    /** Magnitude of the earthquake */
    private String mTitle;

    /** Location of the earthquake */
    private String mAuthor;




    public Book(String title, String author) {
        mTitle = title;
        mAuthor = author;
    }




    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }
}
