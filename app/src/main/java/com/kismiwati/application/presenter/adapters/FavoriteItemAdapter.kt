package com.kismiwati.application.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kismiwati.application.databinding.FavoriteListItemBinding
import com.kismiwati.application.domain.model.Movie

class FavoriteItemAdapter(
    var onClickListener: (movie: Movie) -> Unit
) :
    ListAdapter<Movie, FavoriteItemAdapter.FavoriteMovieItemViewHolder>(DIFF_CALLBACK) {

    //berfungsi untuk membuat objek ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieItemViewHolder {
        val binding =
            FavoriteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteMovieItemViewHolder(binding, onClickListener)
    }

    //berfungsi untuk menghubungkan data yang ada dengan objek ViewHolder.
    override fun onBindViewHolder(holder: FavoriteMovieItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FavoriteMovieItemViewHolder(
        private val binding: FavoriteListItemBinding,
        private val onClickListener: (movie: Movie) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movieName.text = movie.title
            binding.movieData.text = movie.release_date

            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/original" + movie.poster_path)
                .centerCrop()
                .into(binding.moviePoster)

            binding.root.setOnClickListener {
                onClickListener.invoke(movie)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            // Properti ID mengidentifikasi kapan itemnya sama.
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            // Jika user menggunakan operator "==", pastikan objek tersebut diimplementasikan
            // sama dengan(). Alternatifnya, tulis logika perbandingan data khusus di sini.
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}