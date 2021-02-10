package com.example.myandroid3home2.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myandroid3home2.R;
import com.example.myandroid3home2.model.Film;
import com.example.myandroid3home2.data.remote.RetrofitFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private  FilmAdapter adapter;
    private RecyclerView recyclerView;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null) {
            actionBar.hide();
        }
        init();
        getFilms();

    }

    private void getFilms() {
        RetrofitFactory
                .getInstance()
                .getFilm()
                .enqueue(new Callback<List<Film>>() {
                    @Override
                    public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                        if (response.isSuccessful()) {
                            for (Film film : response.body()) {
                                adapter.setElement(film);
                            }
                            adapter.notifyDataSetChanged();
                        }else {
                            Log.d(TAG, response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Film>> call, Throwable t) {
                        Log.d(TAG, t.getMessage());

                    }
                });
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        List<Film> films = new ArrayList<>();
         adapter = new FilmAdapter(films, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnclick(position -> getFilmId());
    }
    private void getFilmId() {
        String id = adapter.films.get(position).getId();
        RetrofitFactory.getInstance().getFilmId(id).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful()) {
                    if (response.body() !=null){
                        Intent intent = new Intent(MainActivity.this, MyBasaDan.class);
                        intent.putExtra("Film_Id",response.body().getId());
                        intent.putExtra("film_title", response.body().getTitle());
                        intent.putExtra("film_desc", response.body().getDescription());
                        startActivity(intent);
                        Log.d("film_Id", response.body().getId());
                    }
                }else {
                    Log.d(TAG, response.message());
                }
                
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
            }
        });
    }
}


