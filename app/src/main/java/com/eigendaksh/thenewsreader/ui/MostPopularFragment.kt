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


/**
 * A simple [Fragment] subclass.
 *
 */
class MostPopularFragment : Fragment() {

    private val TAG by lazy { MostPopularFragment::class.java.simpleName }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(StoriesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchPouplarStories()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_most_popular, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.observablePopularStories?.observe(viewLifecycleOwner, Observer { itemList ->
            if(itemList != null) {
                for(item in itemList) {
                    Log.i(TAG, item.title ?: "empty")
                }
            }
        })
    }

    private fun fetchPouplarStories() {
        viewModel.fetchPopularStories()
    }

}
