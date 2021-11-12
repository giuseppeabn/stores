package com.example.stores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.stores.adapter.StoreAdapter
import com.example.stores.databinding.ActivityMainBinding
import com.example.stores.interfaces.OnClickListener
import com.example.stores.model.Store

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: StoreAdapter
    private lateinit var mGridLayout: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        mAdapter = StoreAdapter(mutableListOf(), this)
        mGridLayout = GridLayoutManager(this, 2)

        binding.recyclerView.apply {
            adapter = mAdapter
            mGridLayout = mGridLayout
            setHasFixedSize(true)// Indicamos que no cambia de tamanio
        }
    }

    override fun onClick(store: Store) {
        TODO("Not yet implemented")
    }
}