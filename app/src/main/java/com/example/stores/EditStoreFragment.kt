package com.example.stores

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.stores.databinding.FragmentEditStoreBinding
import com.example.stores.db.StoreApplication
import com.example.stores.model.StoreEntity
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EditStoreFragment : Fragment() {
    private lateinit var mBinding: FragmentEditStoreBinding
    private var mActivity: MainActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentEditStoreBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // obtener la actividad donde esta insertado el fragment
        mActivity = activity as? MainActivity
        // mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // mActivity?.supportActionBar?.title = getString(R.string.edit_store_title_add)
        handleSupportActionBar(true)

        setHasOptionsMenu(true)// acceso al menu
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                mActivity?.onBackPressed()
                true
            }
            R.id.action_save -> {
                handleSaveStore()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun handleSaveStore(){
        val store = StoreEntity(name = mBinding.etName.text.toString().trim(),
            phone = mBinding.etPhone.toString().trim(),
            website = mBinding.etWebsite.toString().trim()
        )
        doAsync {
            store.id = StoreApplication.dataBase.storeDao().addStore(store)
            uiThread {
                mActivity?.addStore(store)
                hideKeyboard()
                Snackbar.make(
                    mBinding.root,
                    getString(R.string.edit_store_message_save_success),
                    Snackbar.LENGTH_SHORT
                ).show()
                mActivity?.onBackPressed()
            }
        }
    }

    private fun handleSupportActionBar(isVisible:Boolean){
        var title = if(isVisible) getString(R.string.edit_store_title_add) else getString(R.string.app_name)
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(isVisible)
        mActivity?.supportActionBar?.title = title
        mActivity?.hideFab(!isVisible)

    }

    private fun hideKeyboard(){
        val inputMethodManager = mActivity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if(view != null){
            inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
        }
    }

    override fun onDestroyView() {
        hideKeyboard()
        super.onDestroyView()
    }
    // ciclo de vide del fragment
    override fun onDestroy() {
        // mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        // mActivity?.supportActionBar?.title = getString(R.string.app_name)
        handleSupportActionBar(false)
        super.onDestroy()
    }
}