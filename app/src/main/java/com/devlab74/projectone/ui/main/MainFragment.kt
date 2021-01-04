package com.devlab74.projectone.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devlab74.projectone.R
import java.lang.Exception

class MainFragment : Fragment() {

    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = activity?.let {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?: throw Exception("Invalid Activity")

        subscribeObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer {dataState ->
            println("DEBUG: DataState: ${dataState}")
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer {viewState ->
            viewState.blogPostRequest?.let {
                println("DEBUG: Setting blog posts to RecyclerView: ${it}")
            }

            viewState.userRequest?.let {
                println("DEBUG: Setting user data: ${it}")
            }
        })
    }
}