package com.eigendaksh.thenewsreader.ui.views


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.eigendaksh.thenewsreader.R
import com.eigendaksh.thenewsreader.base.BaseNewsFragment
import com.eigendaksh.thenewsreader.data.news.NewsItem
import com.eigendaksh.thenewsreader.ui.adapter.NewsItemAdapter
import com.eigendaksh.thenewsreader.ui.viewmodel.StoriesViewModel
import com.eigendaksh.thenewsreader.utils.AppConstants
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class TopStoriesFragment : BaseNewsFragment() {

    private val TAG by lazy { TopStoriesFragment::class.java.simpleName }


    private val viewModel by lazy { ViewModelProviders.of(this).get(StoriesViewModel::class.java) }
    private var newsItemList: List<NewsItem>? = emptyList()

    override fun getNewsItemList(): List<NewsItem>? {
        return newsItemList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchTopStories()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.observableTopStories?.observe(viewLifecycleOwner, Observer { itemList ->
            if (itemList != null) {
                hideProgress()
                updateItems(itemList)
            } else {
                hideProgress()
                showToast(getString(R.string.fetch_failed))
            }
        })
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

    private fun fetchTopStories() {
        viewModel.fetchTopStories()
    }


}
