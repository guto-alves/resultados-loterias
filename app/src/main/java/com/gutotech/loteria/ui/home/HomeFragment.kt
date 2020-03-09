package com.gutotech.loteria.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.gutotech.loteria.R
import com.gutotech.loteria.databinding.FragmentHomeBinding
import com.gutotech.loteria.ui.adapter.LotteryAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.viewModel = homeViewModel

        val adapter = LotteryAdapter(context, homeViewModel)

        binding.lotteryRecyclerView.setHasFixedSize(true)
        binding.lotteryRecyclerView.adapter = adapter

        homeViewModel.resultList.observe(viewLifecycleOwner, adapter::setResults)

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.LEFT, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (direction == ItemTouchHelper.LEFT) {
                    homeViewModel.loadPreviousResult(position)
                } else {
                    homeViewModel.loadNextResult(position)
                }
            }
        }).attachToRecyclerView(binding.lotteryRecyclerView)

        return binding.root
    }
}
