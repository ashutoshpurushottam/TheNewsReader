package com.eigendaksh.thenewsreader.ui.views


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.eigendaksh.thenewsreader.R
import com.eigendaksh.thenewsreader.base.BaseNewsFragment
import com.eigendaksh.thenewsreader.data.news.NewsItem
import com.eigendaksh.thenewsreader.ui.adapter.NewsItemAdapter
import com.eigendaksh.thenewsreader.ui.viewmodel.StoriesViewModel
import com.eigendaksh.thenewsreader.utils.AppConstants


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
                updateItems(itemList)
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
        val item = newsItemList?.get(position)
        item?.let {
            val bundle = Bundle()
            bundle.putString(AppConstants.URL_KEY, it.url)
            bundle.putString(AppConstants.TITLE_KEY, it.title)
            bundle.putBoolean(AppConstants.IS_POPULAR_KEY, false)
            bundle.putBoolean(AppConstants.IS_READ_KEY, item.isRead)
            NavHostFragment.findNavController(this).navigate(R.id.action_global_newsDetailFragment, bundle)
        }
    }


}
