package com.devlab74.projectone.ui.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.devlab74.projectone.R
import com.devlab74.projectone.model.BlogPost
import com.devlab74.projectone.model.BlogPostRequest
import com.devlab74.projectone.model.UserRequest
import com.devlab74.projectone.ui.DataStateListener
import com.devlab74.projectone.ui.main.state.MainStateEvent
import com.devlab74.projectone.util.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.ClassCastException
import java.lang.Exception
import java.text.DateFormat
import java.util.*

class MainFragment : Fragment(), MainRecyclerAdapter.Interaction {

    override fun onItemSelected(position: Int, item: BlogPost) {
        println("DEBUG: CLICKED ${position}")
        println("DEBUG: CLICKED ${item}")
    }

    lateinit var viewModel: MainViewModel
    lateinit var dataStateHandler: DataStateListener
    lateinit var mainRecyclerAdapter: MainRecyclerAdapter

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
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_get_user_one -> triggerGetUserEvent("5fed2bbb266aa227af6839e9")
            R.id.action_get_user_two -> triggerGetUserEvent("5fed2c2c266aa227af6839ea")
            R.id.action_get_user_three -> triggerGetUserEvent("5fed2c7e266aa227af6839eb")
            R.id.action_get_blogs -> triggerGetBlogsEvent()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun triggerGetUserEvent(userId: String) {
        viewModel.setStateEvent(MainStateEvent.GetUserEvent(userId))
    }

    private fun triggerGetBlogsEvent() {
        viewModel.setStateEvent(MainStateEvent.GetBlogPostsEvent())
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer {dataState ->

            // Handle Loading and Messages
            dataStateHandler.onDataStateChange(dataState)

            // Handle Data<T>
            dataState.data?.let { event ->
                event.getContentIfNotHandled()?.let { mainViewState ->
                    println("DEBUG: DataState: ${mainViewState}")

                    mainViewState.userRequest?.let { userRequest ->
                        // Set User Data
                        viewModel.setUser(userRequest)
                    }

                    mainViewState.blogPostRequest?.let { blogPostRequest ->
                        // Set BlogPosts data
                        viewModel.setBlogPostData(blogPostRequest)
                    }
                }
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer {viewState ->
            viewState.blogPostRequest?.let {
                println("DEBUG: Setting blog posts to RecyclerView: ${it}")

                setBlogPostProperties(it)
            }

            viewState.userRequest?.let {
                println("DEBUG: Setting user data: ${it}")

                setUserProperties(it)
            }
        })
    }

    private fun initRecyclerView() {
        blogs_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainFragment.context)
            val topSpacingItemDecoration = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingItemDecoration)
            mainRecyclerAdapter = MainRecyclerAdapter(this@MainFragment)
            adapter = mainRecyclerAdapter
        }
    }

    private fun setUserProperties(userRequest: UserRequest) {
        full_name.text = userRequest.data?.full_name
        username.text = userRequest.data?.username
        email.text = userRequest.data?.email

        val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        val formattedDOB = dateFormat.format(userRequest.data?.dob)
        val formattedRegistrationDate = dateFormat.format(userRequest.data?.date_created)

        dob.text = formattedDOB
        registration_date.text = formattedRegistrationDate

        view?.let {
            Glide.with(it.context)
                .load(userRequest.data?.profile_image)
                .into(profile_image)
        }
    }

    private fun setBlogPostProperties(blogPostRequest: BlogPostRequest) {
        blogs_count.text = blogPostRequest.blog_posts_total.toString()
        blogPostRequest.data?.let {
            mainRecyclerAdapter.submitList(it)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            dataStateHandler = context as DataStateListener
        } catch (e: ClassCastException) {
            println("DEBUG: $context must implement DataStateListener")
        }
    }
}