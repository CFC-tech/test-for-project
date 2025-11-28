package com.example.myapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp.R
import com.example.myapp.model.Movie

class MovieAdapter (private val movielist: List<Movie>, private var context: Context) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        var poster_image = view.findViewById<ImageView>(R.id.imgMovie)
        var movie_title = view.findViewById<TextView>(R.id.txtMovieTitle)
        var movie_Main = view.findViewById<ConstraintLayout>(R.id.movie_Main)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {
        val view = View.inflate(parent.context, R.layout.movie_items, null)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movielist[position]
        val imageUrl = "https://image.tmdb.org/t/p/w500/${movie.results.get(position).poster_path}"
        Glide.with(context).load(imageUrl).into(holder.poster_image)
        holder.movie_title.text = movie.results.get(position).title
        holder.movie_Main.setOnClickListener {
            Toast.makeText(context, "You clicked on", Toast.LENGTH_SHORT).show()
        }

    }
    override fun getItemCount(): Int {
        return movielist.size
    }

}