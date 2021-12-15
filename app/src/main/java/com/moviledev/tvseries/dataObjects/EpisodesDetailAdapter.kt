package com.moviledev.tvseries.dataObjects

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.moviledev.tvseries.R
import com.moviledev.tvseries.appModules.TvShowDetailFragment
import com.moviledev.tvseries.databinding.RvEpisodesItemsBinding
import com.moviledev.tvseries.databinding.RvItemsBinding
import com.moviledev.tvseries.models.TvShow
import com.squareup.picasso.Picasso

class EpisodesDetailAdapter(

    private val context: Context,
    private val tvShows: List<TvShowEpisodes>
): RecyclerView.Adapter<EpisodesDetailAdapter.ViewHolder>() {
    class ViewHolder(private val binding: RvEpisodesItemsBinding)
        : RecyclerView.ViewHolder(binding.root){

        val episodeSeason = binding.etSeason
        val episode = binding.etEpisode
        val name = binding.etName
        val airDate = binding.etAirDate
        val cardView = binding.cardView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return EpisodesDetailAdapter.ViewHolder(
            RvEpisodesItemsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = tvShows.get(position)
        holder.episodeSeason.setText(tvShow.season)
        holder.episode.setText(tvShow.episode)
        holder.name.setText(tvShow.name)
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }
}