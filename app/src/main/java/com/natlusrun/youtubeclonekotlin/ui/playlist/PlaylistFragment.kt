package com.natlusrun.youtubeclonekotlin.ui.playlist

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
import com.natlusrun.youtubeclonekotlin.adapters.PlaylistAdapter
import com.natlusrun.youtubeclonekotlin.adapters.OnPlaylistClickListener
import com.natlusrun.youtubeclonekotlin.data.model.youtube.Item
import com.natlusrun.youtubeclonekotlin.data.model.youtube.PlaylistResponse
import com.natlusrun.youtubeclonekotlin.ui.playlists.PlaylistsFragment
import com.natlusrun.youtubeclonekotlin.utils.Resource
import com.natlusrun.youtubeclonekotlin.utils.Status
import com.natlusrun.youtubeclonekotlin.utils.showToast
import kotlinx.android.synthetic.main.playlist_fragment.*

class PlaylistFragment : Fragment(), OnPlaylistClickListener {


        private lateinit var viewModel: PlaylistViewModel
        private lateinit var adapter: PlaylistAdapter
        private lateinit var list: Item
        private var nextPageList: String? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            arguments?.let {
                list = it.getSerializable(PlaylistsFragment.PLAYLIST_KEY) as Item
            }
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.playlist_fragment, container, false)
        }


        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
            viewModel.initRepository(requireContext())
            initRecyclerAdapter()
            fetchData()
            pagination()

        }

        private fun pagination() {
            nested_scroll.setOnScrollChangeListener { nested: NestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY == nested.getChildAt(0).measuredHeight - nested.measuredHeight) {
                    nextPageList?.let {
                        fetchNextList(nextPageList!!)
                    }
                }
            }
        }

        private fun fetchNextList(nextPageList: String) {
            viewModel.fetchNextDetailsList(list.id, nextPageList).observe(viewLifecycleOwner, Observer {
                if (it?.data?.nextPageToken == null) {
                    this.nextPageList = null
                }
                statusCheck(it)
            })
        }

        private fun fetchData() {
            viewModel.fetchDetailsListFromServer(list.id).observe(viewLifecycleOwner, Observer {
                statusCheck(it)
            })
            playlist_title.text = list.snippet.title
            playlist_description.text = list.snippet.channelTitle
        }

        private fun statusCheck(it: Resource<PlaylistResponse>?) {
            when (it?.status) {
                Status.SUCCESS -> setData(it)
                Status.ERROR -> context?.showToast(it.message ?: "ERROR")
            }
        }

        private fun setData(it: Resource<PlaylistResponse>) {

            it.data?.items?.let { it1 ->
                adapter.add(it1)
                viewModel.addDetailsToDB(it1 as MutableList<Item>)
            }
            nextPageList = it.data?.nextPageToken
        }

        private fun initRecyclerAdapter() {
            adapter = PlaylistAdapter(this)
            detailsRecycler.adapter = adapter
        }


        override fun onItemClick(item: Item) {
            findNavController().navigate(R.id.videoFragment)
        }


    }