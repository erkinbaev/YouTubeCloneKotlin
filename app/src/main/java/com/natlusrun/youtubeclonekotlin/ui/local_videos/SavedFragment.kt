package com.natlusrun.youtubeclonekotlin.ui.local_videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.natlusrun.youtubeclonekotlin.R
import com.natlusrun.youtubeclonekotlin.adapters.PlaylistAdapter
import com.natlusrun.youtubeclonekotlin.adapters.OnPlaylistClickListener
import com.natlusrun.youtubeclonekotlin.data.model.youtube.Item
import kotlinx.android.synthetic.main.saved_fragment.*


class SavedFragment : Fragment(), OnPlaylistClickListener {


    private lateinit var viewModel: SavedViewModel
    private lateinit var adapter: PlaylistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.saved_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SavedViewModel::class.java)
        viewModel.initRepository(requireContext())
        initRecyclerAdapter()
        fetchData()


    }


    private fun fetchData() {
        viewModel.getDetailsFromDB()
        viewModel.localData.observe(viewLifecycleOwner, Observer {
            adapter.add(it)
        })
    }

    private fun initRecyclerAdapter() {
        adapter = PlaylistAdapter(this)
        savedRecycler.adapter = adapter
    }

    override fun onItemClick(item: Item) {

    }


}