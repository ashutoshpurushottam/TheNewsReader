package com.eigendaksh.thenewsreader.ui.views


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.eigendaksh.thenewsreader.R
import com.eigendaksh.thenewsreader.base.BasePopularNewsFragment
import com.eigendaksh.thenewsreader.data.popular.PopularNewsItem
import com.eigendaksh.thenewsreader.ui.adapter.PopularNewsItemAdapter
import com.eigendaksh.thenewsreader.ui.viewmodel.StoriesViewModel


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

    private fun fetchPouplarStories() {
        viewModel.fetchPopularStories()
    }

    override fun onItemClicked(position: Int) {
        super.onItemClicked(position)
    }

}
