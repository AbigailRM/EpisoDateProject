package com.moviledev.tvseries.appModules

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.moviledev.tvseries.R
import com.moviledev.tvseries.dataObjects.EpisodateNetwork
import com.moviledev.tvseries.dataObjects.EpisodateServices
import com.moviledev.tvseries.dataObjects.EpisodeAdapter
import com.moviledev.tvseries.databinding.FragmentTvShowListBinding
import com.moviledev.tvseries.models.APIResponseED
import com.moviledev.tvseries.models.TvShow
import com.moviledev.tvseries.viewModel.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowListFragment : Fragment(), SearchView.OnQueryTextListener  {

    lateinit var binding: FragmentTvShowListBinding
    lateinit var viewModel : ViewModel
    lateinit var q: String
    //private lateinit var args: Bundle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentTvShowListBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(requireActivity()).get(ViewModel::class.java)

        viewModel.tvShowList.observe(viewLifecycleOwner,{
//            binding.showItemsRv.adapter = EpisodeAdapter(requireContext(),it){
//                viewModel.select(it)
//            }
            setAdapter(it)
        })

        viewModel.pagination.observe(viewLifecycleOwner,{
            binding.current.text = it.page.toString()
        })

        viewModel.loading.observe(viewLifecycleOwner,{
            if (it==true){
                binding.loadProgress.visibility = View.VISIBLE
            }
            else{
                binding.loadProgress.visibility = View.GONE
            }
        })

        binding.search.setOnSearchClickListener {
            viewModel.search(binding.search.query.toString())
        }

//        viewModel.selected.observe(viewLifecycleOwner,{
//            viewModel.search(binding.search.query.toString())
//        })

        viewModel.exception.observe(viewLifecycleOwner,{
            Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
        })

        binding.btnNextPage.setOnClickListener {
            val next = viewModel.pagination.value!!.page+1

            if (next <= viewModel.pagination.value!!.totalPages){
                viewModel.loadTvShows(next)
            }else{
                Toast.makeText(requireContext(),"There is no more pages", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnPrevious.setOnClickListener {
            val previous = viewModel.pagination.value!!.page-1

            if (previous >= 1){
                viewModel.loadTvShows(previous)
            }else{
                Toast.makeText(requireContext(),"There is no more pages", Toast.LENGTH_SHORT).show()
            }
        }

        //binding.showItemsRv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.loadTvShows()

        return binding.root

    }

    private fun setAdapter(items: List<TvShow>) {
        binding.showItemsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.showItemsRv.adapter = EpisodeAdapter(requireContext(),items){
            viewModel.select(it)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
                 q = query.toLowerCase()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

//    fun onClickTvShow(tvShow: TvShow) {
//        viewModel.select(tvShow)
//
//        findNavController().navigate(R.id.tvShowDetailFragment)
//
//        val transaction = requireActivity().supportFragmentManager.beginTransaction()
//
//        transaction.replace(R.id.fragNavHost, TvShowDetailFragment()).commit()
//
//        val gson = Gson()
//        args.putString("tvShow", gson.toJson(viewModel.selected.value))
//
//    }

}
