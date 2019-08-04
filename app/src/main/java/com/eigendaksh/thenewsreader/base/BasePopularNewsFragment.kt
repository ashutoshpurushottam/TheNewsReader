package com.eigendaksh.thenewsreader.base

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eigendaksh.thenewsreader.R
import com.eigendaksh.thenewsreader.data.popular.PopularNewsItem
import com.eigendaksh.thenewsreader.ui.adapter.NewsItemListener
import com.eigendaksh.thenewsreader.ui.adapter.PopularNewsItemAdapter

abstract class BasePopularNewsFragment : Fragment(), NewsItemListener {

    private lateinit var recyclerView: RecyclerView
    private var progressBar: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.itemAnimator = DefaultItemAnimator()

    }

    fun setAdapter(adapter: PopularNewsItemAdapter) {
        recyclerView.adapter = adapter
    }

    override fun onItemClicked(position: Int) {
        val newsItem = getPopularNewsItemList()?.get(position)
        showToast(newsItem?.title)
    }

    fun showToast(msg: String?) {
        val toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun hideProgress() {
        progressBar?.visibility = View.GONE
    }

    fun getLayoutId() = R.layout.fragment_stories

    abstract fun getPopularNewsItemList(): List<PopularNewsItem>?


}