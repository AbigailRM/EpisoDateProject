package com.moviledev.tvseries.appModules

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.moviledev.tvseries.R
import com.moviledev.tvseries.dataObjects.EpisodeAdapter
import com.moviledev.tvseries.dataObjects.EpisodesDetailAdapter
import com.moviledev.tvseries.dataObjects.TvShowEpisodes
import com.moviledev.tvseries.databinding.FragmentDetailsBinding
import com.moviledev.tvseries.databinding.FragmentTvShowDetailBinding

class TvShowDetailFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    lateinit var viewModel: com.moviledev.tvseries.viewModel.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailsBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(requireActivity()).get(com.moviledev.tvseries.viewModel.ViewModel::class.java)

        viewModel.tvShowEpisodes.observe(viewLifecycleOwner,{
//            setAdapter(it)
        })

        return binding.root
    }

//    private fun setAdapter(items: List<TvShowEpisodes>?) {
//        binding.episodesRv.layoutManager = LinearLayoutManager(requireContext())
//        binding.episodesRv.adapter = EpisodesDetailAdapter(requireContext()){
//            viewModel.select(items)
//        }
//    }

}