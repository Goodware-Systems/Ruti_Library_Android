package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView booksRecView;
    private BooksRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        booksRecView = findViewById(R.id.booksRecView);
        adapter = new BooksRecViewAdapter(this, "allBooks");

        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));
        //booksRecView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter.setBooks(Utils.getInstance(this).getAllBooks());

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
    }
}