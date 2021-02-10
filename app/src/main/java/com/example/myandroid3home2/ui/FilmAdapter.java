package com.example.myandroid3home2.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroid3home2.R;
import com.example.myandroid3home2.model.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {
    Onclick onclick;
    public List<Film> films = new ArrayList<>();
    private Context context;

    public FilmAdapter (List<Film> films, Context context) {
        this.films = films;
        this.context = context;
    }

    @NonNull
    @Override
    public FilmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmAdapter.ViewHolder holder, int position) {
       holder.bind(films.get(position));
    }

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }
    @Override
    public int getItemCount() {
        return films.size();
    }
    public void setElement(Film body) {
        films.add(0, body);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tv_film_title;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_film_title = itemView.findViewById(R.id.tv_film_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclick.onClick(getAdapterPosition());
                }
            });
        }

        public void bind(Film film) {
            tv_film_title.setText(film.getTitle());
        }
    }
    public interface Onclick {
        void onClick(int position);
    }
}




