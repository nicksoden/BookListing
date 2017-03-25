package com.example.android.booklisting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.booklisting_list_item, parent, false);
        }
        Book currentBook = getItem(position);

        TextView titleOfBook = (TextView) listItemView.findViewById(R.id.title);
        titleOfBook.setText(currentBook.getTitle());

        TextView authorOfBook = (TextView) listItemView.findViewById(R.id.author);
        authorOfBook.setText(currentBook.getAuthor());

        return listItemView;
    }
}