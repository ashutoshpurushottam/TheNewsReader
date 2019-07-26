package com.eigendaksh.thenewsreader.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.eigendaksh.thenewsreader.R
import com.eigendaksh.thenewsreader.viewmodel.StoriesViewModel


class TopStoriesFragment : Fragment() {

    private val TAG by lazy { TopStoriesFragment::class.java.simpleName }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(StoriesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchTopStories()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_stories, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.observableTopStories?.observe(viewLifecycleOwner, Observer {
            for (result in it.results) {
                Log.i(TAG, result.title)
            }
        })
    }

    private fun fetchTopStories() {
        viewModel.fetchTopStories()
    }
}
