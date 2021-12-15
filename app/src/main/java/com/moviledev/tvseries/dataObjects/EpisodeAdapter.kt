package com.moviledev.tvseries.dataObjects

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.moviledev.tvseries.R
import com.moviledev.tvseries.appModules.TvShowDetailFragment
import com.moviledev.tvseries.appModules.TvShowListFragment
import com.moviledev.tvseries.databinding.FragmentTvShowDetailBinding
import com.moviledev.tvseries.databinding.RvItemsBinding
import com.moviledev.tvseries.models.TvShow
import com.squareup.picasso.Picasso

class EpisodeAdapter (
    private val context: Context,
    private val tvShows: List<TvShow>,
    //private val onClickTvShowItem : TvShowListFragment,
    private val onItemSelected: (tvShow: TvShow) -> Unit
) : RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {


    class ViewHolder(private val binding: RvItemsBinding)
        : RecyclerView.ViewHolder(binding.root) {

        val tvShowTitle = binding.tvTitleItem
        val TvShowImage = binding.ivShowItem
        val tvShowDate = binding.tvEpDate
        val tvShowSource = binding.tvSource
        val cardView = binding.cardView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvItemsBinding.inflate(
            LayoutInflater.from(parent.context) , parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = tvShows.get(position)
        holder.tvShowTitle.text = tvShow.name
        holder.tvShowDate.text = tvShow.start_date
        holder.tvShowSource.text = tvShow.network
        Picasso.get() //.with(context)
            .load(tvShow.image_path)
            .placeholder(R.drawable.movie_placeholder)
            .resize(350, 350)
            .centerCrop()
            .into(holder.TvShowImage)

        holder.cardView.setOnClickListener {
            val activity = it.context as AppCompatActivity
            var fragment = TvShowDetailFragment()
            activity.supportFragmentManager.beginTransaction().replace(R.id.fragNavHost,fragment).addToBackStack(null).commit()
            onItemSelected(tvShow)
            //onClickTvShowItem.onClickTvShow(tvShow)
        }
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }



}