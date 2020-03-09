package com.gutotech.loteria.ui.historico

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe

import com.gutotech.loteria.R
import com.gutotech.loteria.databinding.FragmentHistoricoBinding
import com.gutotech.loteria.ui.adapter.HistoricoAdapter

class HistoricoFragment : Fragment() {

    private lateinit var historicoViewModel: HistoricoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        historicoViewModel = ViewModelProvider(this).get(HistoricoViewModel::class.java)

        val binding: FragmentHistoricoBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_historico, container, false
        )

        binding.viewModel = historicoViewModel

        val adapter = HistoricoAdapter(context)

        binding.historicoRecyclerView.setHasFixedSize(true)
        binding.historicoRecyclerView.adapter = adapter

        historicoViewModel.historico.observe(viewLifecycleOwner, adapter::setResults)

        historicoViewModel.concursos.observe(viewLifecycleOwner, {
            val arrayAdapter =
                ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, it.toList())
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.startSpinner.adapter = arrayAdapter
            binding.endSpinner.adapter = arrayAdapter
        })

        return binding.root
    }
}
