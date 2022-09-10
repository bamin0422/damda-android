package com.android.damda.ui.main.fragment.memory

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.damda.R
import com.android.damda.data.ImgItem
import com.android.damda.databinding.FragmentMemoryBinding
import com.android.damda.ui.main.fragment.photo.PhotoViewModel

class MemoryFragment : Fragment() {
    private lateinit var binding : FragmentMemoryBinding
    private lateinit var memoryGrid : RecyclerView
    private var memoryData = mutableListOf<ImgItem>()
    private lateinit var memoryViewModel : MemoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_memory, container, false)
        initItems()
        initViews(inflater, container)
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun initViewModel() {
        memoryViewModel = ViewModelProvider(this).get(MemoryViewModel::class.java)
        memoryViewModel
        memoryViewModel.memoryData.observe(this, Observer {
            memoryData = it
        })
        memoryData = memoryViewModel.getMemoryData()!!
        Log.d(ContentValues.TAG, "PhotoData : ${memoryData.size}")
    }

    private fun initViews(inflater: LayoutInflater, container: ViewGroup?) {
        memoryGrid = binding.memoryGrid
        memoryGrid.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        memoryGrid.adapter = MemoryGridAdapter(requireContext(), memoryData)

    }

    private fun initItems(){
        memoryData.add(0, ImgItem("0", "", "https://longing-band-5ba.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fa0ac3cbc-5ed1-42b1-91e9-8aab27e52877%2FUntitled.png?table=block&id=febbe3a3-bd9c-454d-84d8-0160846e0151&spaceId=27bc80dd-ac48-4033-8374-716eb322c62d&width=1710&userId=&cache=v2"))
        memoryData.add(1, ImgItem("1", "", "https://longing-band-5ba.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F532bcda4-d94f-4b69-9bd0-3f8cc76f0d01%2F12.jpeg?table=block&id=70d34fcc-c270-4884-9f87-af4576a25b51&spaceId=27bc80dd-ac48-4033-8374-716eb322c62d&width=2000&userId=&cache=v2"))
        memoryData.add(2, ImgItem("2", "", "https://longing-band-5ba.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fdb5cc500-fa9d-4e8d-bd94-4ca5d8ddd568%2F11.jpeg?table=block&id=dc5af09f-8ac4-406e-8596-c7045ef2d140&spaceId=27bc80dd-ac48-4033-8374-716eb322c62d&width=2000&userId=&cache=v2"))
        memoryData.add(3, ImgItem("3", "", "https://longing-band-5ba.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F38c414bb-6f46-4f3f-a1c8-67e8f7b56fbd%2F10.jpeg?table=block&id=492c3c6f-b651-4834-a6d0-e4c7e1231e72&spaceId=27bc80dd-ac48-4033-8374-716eb322c62d&width=2000&userId=&cache=v2"))
        memoryData.add(4, ImgItem("4", "", "https://longing-band-5ba.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F38c414bb-6f46-4f3f-a1c8-67e8f7b56fbd%2F10.jpeg?table=block&id=492c3c6f-b651-4834-a6d0-e4c7e1231e72&spaceId=27bc80dd-ac48-4033-8374-716eb322c62d&width=2000&userId=&cache=v2"))
        memoryData.add(5, ImgItem("5", "", "https://longing-band-5ba.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F0390c46f-caf1-47da-8ab6-2b90f80a6b37%2F9.jpeg?table=block&id=4396fe60-2017-4b9a-80d5-b5fe66fafffb&spaceId=27bc80dd-ac48-4033-8374-716eb322c62d&width=2000&userId=&cache=v2"))
        memoryData.add(6, ImgItem("6", "", "https://longing-band-5ba.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F3542dc8a-085b-4e9f-948e-f7745853e351%2F8.jpeg?table=block&id=406681c3-4020-4a63-8945-8b5fe508b178&spaceId=27bc80dd-ac48-4033-8374-716eb322c62d&width=2000&userId=&cache=v2"))
        memoryData.add(7, ImgItem("7", "", "https://longing-band-5ba.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Ffa457521-7ebd-4d2c-a0db-bd38eefbf83c%2F7.jpeg?table=block&id=c54e1e2d-6fc6-4997-93f8-7e824ea599ab&spaceId=27bc80dd-ac48-4033-8374-716eb322c62d&width=2000&userId=&cache=v2"))
        memoryData.add(8, ImgItem("8", "", "https://longing-band-5ba.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fdd4bf7f7-ab69-40ed-a327-0c2ab575b0bb%2F6.jpeg?table=block&id=62e90d9f-1d44-46db-b052-5a19df2f6e9f&spaceId=27bc80dd-ac48-4033-8374-716eb322c62d&width=2000&userId=&cache=v2"))
        memoryData.add(9, ImgItem("9", "", "https://longing-band-5ba.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F0b25dd27-757c-42d6-be59-aa0559605e86%2F5.jpeg?table=block&id=766bd74e-50a7-40b3-8148-e49a19b1c2f7&spaceId=27bc80dd-ac48-4033-8374-716eb322c62d&width=2000&userId=&cache=v2"))
        memoryData.add(10, ImgItem("10", "", "https://longing-band-5ba.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fb37dce70-7f09-427f-99bd-03ea41d67485%2F4.jpeg?table=block&id=9b2c707b-7659-48c0-b655-bdfea7c25347&spaceId=27bc80dd-ac48-4033-8374-716eb322c62d&width=2000&userId=&cache=v2"))
        memoryData.add(11, ImgItem("11", "", "https://longing-band-5ba.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F8acc5531-b39c-4736-af1b-4ee5dd6b278d%2F2.jpeg?table=block&id=967515a8-dd92-48b4-b524-89c7377a00ce&spaceId=27bc80dd-ac48-4033-8374-716eb322c62d&width=2000&userId=&cache=v2"))
        memoryData.add(12, ImgItem("12", "", "https://longing-band-5ba.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fee295025-ab2f-492c-9849-b8dd0ba26183%2F1.jpeg?table=block&id=fe006285-5090-4d44-900d-240fd085a50b&spaceId=27bc80dd-ac48-4033-8374-716eb322c62d&width=2000&userId=&cache=v2"))
    }
}