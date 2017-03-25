package com.example.android.booklisting;

/**
 * Created by Nicholas Soden on 2017-03-24.
 */


    /**
     * An {@link Book} object contains information related to a single earthquake.
     */
    public class Book {

        /** Magnitude of the earthquake */
        private String mTitle;

        /** Location of the earthquake */
        private String mAuthor;

        /** Website URL of the earthquake */
        private String mUrl;

        /**
         * Constructs a new {@link Book object.
         *
         * @param title is the title of the book
         * @param author is the author of the book
         * @param url is the website URL to find more details about the book
         */
        public Book(String title, String author, String url) {
            mTitle = title;
            mAuthor = author;
            mUrl = url;
        }

        /**
         * Returns the title of the book.
         */
        public String getTitle() {
            return mTitle;
        }

        /**
         * Returns the author of the book.
         */
        public String getAuthor() {
            return mAuthor;
        }

        /**
         * Returns the website URL to find more information about the book.
         */
        public String getUrl() {
            return mUrl;
        }
    }






