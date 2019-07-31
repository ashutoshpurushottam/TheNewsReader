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
import com.eigendaksh.thenewsreader.ui.viewmodel.StoriesViewModel


class SportsFragment : Fragment() {

    private val TAG by lazy { SportsFragment::class.java.simpleName }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(StoriesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchSportsStories()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sports, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.observableSportsStories?.observe(viewLifecycleOwner, Observer { itemList ->
            if(itemList != null) {
                for(item in itemList) {
                    Log.i(TAG, item.title ?: "empty")
                }
            }
        })
    }


    private fun fetchSportsStories() {
        viewModel.fetchSportsStories()
    }



}
