package com.eigendaksh.thenewsreader.ui.views


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.eigendaksh.thenewsreader.R
import com.eigendaksh.thenewsreader.base.BasePopularNewsFragment
import com.eigendaksh.thenewsreader.data.popular.PopularNewsItem
import com.eigendaksh.thenewsreader.ui.adapter.PopularNewsItemAdapter
import com.eigendaksh.thenewsreader.ui.viewmodel.StoriesViewModel
import com.eigendaksh.thenewsreader.utils.AppConstants


/**
 * A simple [Fragment] subclass.
 *
 */
class MostPopularFragment : BasePopularNewsFragment() {
    private val TAG by lazy { MostPopularFragment::class.java.simpleName }

    private val viewModel by lazy { ViewModelProviders.of(this).get(StoriesViewModel::class.java) }
    private var newsItemList: List<PopularNewsItem>? = emptyList()

    override fun getPopularNewsItemList(): List<PopularNewsItem>? {
        return newsItemList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchPouplarStories()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.observablePopularStories?.observe(viewLifecycleOwner, Observer { itemList ->
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
            bundle.putBoolean(AppConstants.IS_POPULAR_KEY, true)
            bundle.putBoolean(AppConstants.IS_READ_KEY, item.isRead)
            NavHostFragment.findNavController(this).navigate(R.id.action_global_newsDetailFragment, bundle)
        }
    }

    private fun fetchPouplarStories() {
        viewModel.fetchPopularStories()
    }


}
