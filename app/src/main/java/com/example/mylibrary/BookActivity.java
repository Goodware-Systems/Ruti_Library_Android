package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";
    private TextView txtBookNameSX, txtBookNameDX, txtAuthorSX, txtAuthorDX, txtPagesSX, txtPagesDX, txtDescriptionSX, txtLongDescriptionSX;
    private Button addToReadingList, addToWantReadList, addToAlreadyReadList, addToFavorites;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        Intent intent = getIntent();
        if (intent != null) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                //if (incomingBook != null) {
                    setData(incomingBook);
                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                //}
            }
        }

    }

    private void handleFavoriteBooks(final Book book) {

        ArrayList<Book> favoritesBooks = Utils.getInstance(this).getFavoritesBooks();

        boolean existsInFavoriteBooks = false;

        for(Book b : favoritesBooks){
            if(b.getId() == book.getId()){
                existsInFavoriteBooks = true;
            }
        }

        if (existsInFavoriteBooks){
            addToFavorites.setEnabled(false);
        }else{
            addToFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToFavorite(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, FavoritesActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void handleCurrentlyReadingBooks(final Book book) {

        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean existsInCurrentlyReadingBooks = false;

        for(Book b : currentlyReadingBooks ){
            if(b.getId() == book.getId()){
                existsInCurrentlyReadingBooks = true;
            }
        }

        if (existsInCurrentlyReadingBooks){
            addToReadingList.setEnabled(false);
        }else{
            addToReadingList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToCurrentlyReading(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void handleWantToReadBooks(final Book book) {

        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();

        boolean existsInWantToReadBooks = false;

        for(Book b : wantToReadBooks){
            if(b.getId() == book.getId()){
                existsInWantToReadBooks = true;
            }
        }

        if (existsInWantToReadBooks){
            addToWantReadList.setEnabled(false);
        }else{
            addToWantReadList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToWantToRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    /**
     * Enable and Disable button.
     * Add the book to the Already Read Book ArrayList
     * @param book
     */
    private void handleAlreadyRead(final Book book) {

        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();

        boolean existsInAlreadyReadBooks = false;

        for(Book b : alreadyReadBooks){
            if(b.getId() == book.getId()){
                existsInAlreadyReadBooks = true;
            }
        }

        if (existsInAlreadyReadBooks){
            addToAlreadyReadList.setEnabled(false);
        }else{
            addToAlreadyReadList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void setData(Book book)  {

        txtBookNameDX.setText(book.getName());
        txtAuthorDX.setText(book.getAuthor());
        txtPagesDX.setText(String.valueOf(book.getPages()));
        txtDescriptionSX.setText(book.getShortDesc());
        txtLongDescriptionSX.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(imgView);
    }

    private void initViews(){

        txtBookNameDX = findViewById(R.id.txtBookNameDX);
        //txtBookNameDX = findViewById(R.id.txtBookNameDX);
        txtAuthorDX = findViewById(R.id.txtAuthorDX);
        //txtAuthorDX = findViewById(R.id.txtAuthorDX);
        txtPagesDX = findViewById(R.id.txtPagesDX);
        //txtPagesDX = findViewById(R.id.txtPagesDX);
        txtDescriptionSX = findViewById(R.id.txtDescriptionSX);
        txtLongDescriptionSX = findViewById(R.id.txtLongDescriptionSX);

        imgView = findViewById(R.id.imageBook);

        addToReadingList = findViewById(R.id.btnAddToCurrentReadList);
        addToWantReadList = findViewById(R.id.btnAddToWantReadList);
        addToAlreadyReadList = findViewById(R.id.btnAddToAlreadyReadList);
        addToFavorites = findViewById(R.id.btnAddToFavorites);
    }

}