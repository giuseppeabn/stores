package com.example.stores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.stores.adapter.StoreAdapter
import com.example.stores.databinding.ActivityMainBinding
import com.example.stores.db.StoreApplication
import com.example.stores.interfaces.OnClickListener
import com.example.stores.model.StoreEntity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: StoreAdapter
    private lateinit var mGridLayout: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fab.setOnClickListener { launchEditFragment() }
        initRecyclerView()
    }

    private fun launchEditFragment() {
        // instancia del fragmento
        val fragment = EditStoreFragment()
        // gestor para controlar los fragmentos
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        // como se va a ejecutar
        fragmentTransaction.add(R.id.containerMain, fragment)
        fragmentTransaction.commit()
        binding.fab.hide()
    }

    private fun initRecyclerView(){
        mAdapter = StoreAdapter(mutableListOf(), this)
        mGridLayout = GridLayoutManager(this, 2)
        getStores()
        binding.recyclerView.apply {
            adapter = mAdapter
            mGridLayout = mGridLayout

            setHasFixedSize(true)// Indicamos que no cambia de tamanio
        }
    }

    private fun getStores(){
        // Esta tarea se ejecuta en segundo plano
        // por eso se requiere de uiThread
        doAsync {
            val stores = StoreApplication.dataBase.storeDao().getAllStores()
            // hilo principal de la app
            // si la tarea realiza cambios en la ui, debe ejecutarse
            // en este hilo
            uiThread {
                mAdapter.setSores(stores)
            }
        }
    }

    override fun onClick(storeEntity: StoreEntity) {
        TODO("Not yet implemented")
    }

    override fun onFavoriteStore(storeEntity: StoreEntity) {
        storeEntity.isFavorite = !storeEntity.isFavorite
        doAsync {
            StoreApplication.dataBase.storeDao().updateStore(storeEntity)
            uiThread {
                mAdapter.update(storeEntity)
            }
        }
    }

    override fun onDeleteStore(storeEntity: StoreEntity) {
        doAsync {
            StoreApplication.dataBase.storeDao().deleteStore(storeEntity)
            uiThread {
                mAdapter.delete(storeEntity)
            }
        }
    }
}