package com.natlusrun.youtubeclonekotlin.ui.playlists

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.natlusrun.youtubeclonekotlin.R
import com.natlusrun.youtubeclonekotlin.adapters.OnPlaylistClickListener
import com.natlusrun.youtubeclonekotlin.adapters.PlayListsAdapter
import com.natlusrun.youtubeclonekotlin.data.model.youtube.Item
import com.natlusrun.youtubeclonekotlin.data.model.youtube.PlaylistResponse
import com.natlusrun.youtubeclonekotlin.utils.Resource
import com.natlusrun.youtubeclonekotlin.utils.Status
import com.natlusrun.youtubeclonekotlin.utils.showToast
import kotlinx.android.synthetic.main.playlist_fragment.*
import kotlinx.android.synthetic.main.playlists_fragment.*

class PlaylistsFragment : Fragment(), OnPlaylistClickListener {
    companion object {
        const val PLAYLIST_KEY = "1"
    }
    private lateinit var viewModel: PlaylistsViewModel
    private lateinit var adapter: PlayListsAdapter
    private var nextPageList: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.playlists_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlaylistsViewModel::class.java)
        initRecyclerAdapter()
        fetchData()
        pagination()

    }

    private fun pagination() {
        playlist_nested_scroll.setOnScrollChangeListener{ nested: NestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == nested.getChildAt(0).measuredHeight - nested.measuredHeight) {
                nextPageList?.let {
                    fetchNextList(nextPageList!!)
                }
            }
        }
    }

    private fun fetchNextList(nextPageList: String) {
        viewModel.fetchNextPlaylist(nextPageList).observe(viewLifecycleOwner, Observer {
            if (it?.data?.nextPageToken == null) {
                this.nextPageList = null
            }
            statusCheck(it)
        })
    }

    private fun initRecyclerAdapter() {
        adapter = PlayListsAdapter(this)
        playListRecycler.adapter = adapter
    }

    private fun fetchData(){
        viewModel.fetchPlaylistFromServer().observe(viewLifecycleOwner, Observer {
            statusCheck(it)
        })
    }

    private fun statusCheck(it: Resource<PlaylistResponse>?) {
        when(it?.status){
            Status.SUCCESS -> setData(it)
            Status.ERROR -> context?.showToast(it.message?: "ERROR")
        }
    }

    private fun setData(it: Resource<PlaylistResponse>) {
        it.data?.items?.let { it1 -> adapter.add(it1)  }
        nextPageList = it.data?.nextPageToken
    }

    override fun onItemClick(item: Item) {
        val bundle = Bundle()
        bundle.putSerializable(PLAYLIST_KEY, item)
        findNavController().navigate(R.id.action_playlistFragment_to_blankFragment, bundle)
    }
}
