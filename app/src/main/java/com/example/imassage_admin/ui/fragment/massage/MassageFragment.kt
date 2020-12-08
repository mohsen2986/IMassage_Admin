package com.example.imassage_admin.ui.fragment.massage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.BuildConfig
import com.example.imassage_admin.R
import com.example.imassage_admin.data.model.Massage
import com.example.imassage_admin.databinding.FragmentMassageBinding
import com.example.imassage_admin.ui.adapter.ryclerView.RecyclerAdapter
import com.example.imassage_admin.ui.base.ScopedFragment
import com.example.imassage_admin.ui.utils.OnCLickHandler
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.android.synthetic.main.fragment_massage.*
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.io.File

class MassageFragment : ScopedFragment() , KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: MassageViewModelFactory by instance()

    private lateinit var viewModel: MassageViewModel
    private lateinit var binding: FragmentMassageBinding
    private lateinit var navController: NavController

    // -- FOR DATA
    private var fileUri: Uri? = null
    private var mediaPath: String? = null
    private var postPath: String? = null
    private val REQUEST_PICK_PHOTO = 2

    private lateinit var adapter: RecyclerAdapter<Massage>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentMassageBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(MassageViewModel::class.java)
        initAdapter()
        bindAdapter()
        uiActions()
        bindUI()
    }

    private fun bindUI() = launch {
        when(val callback = viewModel.massages()){
            is NetworkResponse.Success ->
                adapter.datas = callback.body.datas
        }
    }
    private fun upload() = launch {
        postPath?.let {
            if (it.isNotEmpty()){
                val imageFile = File(postPath!!)

                val requestBody = RequestBody.create(
                    requireActivity().contentResolver.getType(fileUri!!)?.toMediaTypeOrNull() ,
                    imageFile
                )
                val body = MultipartBody.Part.createFormData("image", imageFile.name , requestBody)


                viewModel.uploadMassage(body , "Massage From Phone" , "120000" , "2" , "this is a test")
            }else{
                Toast.makeText(requireActivity(), "please select an image ", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_PICK_PHOTO) {
                if (data != null) {
                    // Get the Image from data
                    val selectedImage = data.data
                    fileUri = selectedImage

                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

                    val cursor = requireActivity().contentResolver.query(selectedImage!!, filePathColumn, null, null, null)

                    if (BuildConfig.DEBUG && cursor == null) {
                        error("Assertion failed")
                    }
                    cursor!!.moveToFirst()

                    val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                    mediaPath = cursor.getString(columnIndex)
                    // Set the Image in ImageView for Previewing the Media
//                    imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath))
                    cursor.close()
                    postPath = mediaPath
                }
            }

        } else if (resultCode != Activity.RESULT_CANCELED) {
            Toast.makeText(requireContext(), "Sorry, there was an error!", Toast.LENGTH_LONG).show()
        }

    }
    private fun uiActions(){
        binding.onClick = object: OnCLickHandler<Nothing>{
            override fun onClickItem(element: Nothing) {}
            override fun onClickView(view: View, element: Nothing) {}
            override fun onClick(view: View) {
                when(view){
                    fra_massage_back ->
                        requireActivity().onBackPressed()
                    fra_massage_add_massage ->
                        navController.navigate(R.id.action_massageFragment_to_addMassageFragment)
                }
            }

        }
    }
    private fun initAdapter(){
        adapter = RecyclerAdapter()
        adapter.onClickHandler = object: OnCLickHandler<Massage>{
            override fun onClickItem(element: Massage) {
                deleteMassage(element.id)
            }
            override fun onClick(view: View) {}
            override fun onClickView(view: View, element: Massage) {}

        }
    }
    private fun bindAdapter(){
        fra_massage_recycler.adapter = adapter
    }
    private fun deleteMassage(id: String) = launch{
        when(val callback = viewModel.deleteMassage(id)){
            is NetworkResponse.Success -> {
                bindUI()
                Toast.makeText(context, "ماساژ حدف شد.", Toast.LENGTH_LONG).show()
            }
        }
    }
}