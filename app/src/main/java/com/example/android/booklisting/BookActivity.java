package com.example.android.booklisting;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {
    EditText eText;
    Button btn;
    TextView focuser;
    TextView deleteText;
    String str;

    private static final String LOG_TAG = BookActivity.class.getName();

    /** URL for earthquake data from the USGS dataset */
    String GOOGLE_BOOKS_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    /** Adapter for the list of earthquakes */
    private BookAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout
        ListView bookListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(mAdapter);

        deleteText = (TextView) findViewById(R.id.blurb);
        focuser= (TextView) findViewById(R.id.textView1);

        eText = (EditText) findViewById(R.id.edittext);
        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GOOGLE_BOOKS_URL = "https://www.googleapis.com/books/v1/volumes?q=";
                str = eText.getText().toString();
                GOOGLE_BOOKS_URL += str;
                Toast msg = Toast.makeText(getBaseContext(),str,Toast.LENGTH_LONG);
                msg.show();
                hideSoftKeyboard(BookActivity.this, v); // MainActivity is the name of the class and v// is the View parameter used in the button listener method onClick.
                focuser.setFocusable(true);
                focuser.setFocusableInTouchMode(true);
                focuser.requestFocus();
                deleteText.setVisibility(View.GONE);
                BookAsyncTask task = new BookAsyncTask();
                task.execute(GOOGLE_BOOKS_URL);
                Log.v("worked", GOOGLE_BOOKS_URL);

            }
        });

        // Start the AsyncTask to fetch the earthquake data
        /*BookAsyncTask task = new BookAsyncTask();*/

        /*if (searched){
            task.execute(GOOGLE_BOOKS_URL);
        }*/

    }

    private class BookAsyncTask extends AsyncTask<String, Void, List<Book>> {
        @Override
        protected List<Book> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<Book> result = QueryUtils.fetchBookData(urls[0]);
            return result;
        }
        @Override
        protected void onPostExecute(List<Book> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();
            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }

    }

    public static void hideSoftKeyboard (Activity activity, View view)
    {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }
}
