package com.eigendaksh.thenewsreader.ui.views


import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.eigendaksh.thenewsreader.R
import com.eigendaksh.thenewsreader.base.BaseNewsFragment
import com.eigendaksh.thenewsreader.data.news.NewsItem
import com.eigendaksh.thenewsreader.ui.adapter.NewsItemAdapter
import com.eigendaksh.thenewsreader.ui.viewmodel.StoriesViewModel


class SportsFragment : BaseNewsFragment() {

    private val TAG by lazy { TopStoriesFragment::class.java.simpleName }

    private val viewModel by lazy { ViewModelProviders.of(this).get(StoriesViewModel::class.java) }
    private var newsItemList: List<NewsItem>? = emptyList()


    override fun getNewsItemList(): List<NewsItem>? {
        return newsItemList
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchSportsStories()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.observableSportsStories?.observe(viewLifecycleOwner, Observer { itemList ->
            if (itemList != null) {
                hideProgress()
                newsItemList = itemList
                val adapter = NewsItemAdapter(itemList, this)
                setAdapter(adapter)
            } else {
                hideProgress()
                showToast(getString(R.string.fetch_failed))
            }
        })
    }


    private fun fetchSportsStories() {
        viewModel.fetchSportsStories()
    }

    override fun onItemClicked(position: Int) {
    }


}
