package com.example.imassage_admin.ui.fragment.slider

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.BuildConfig
import com.example.imassage_admin.R
import com.example.imassage_admin.data.model.Boarder
import com.example.imassage_admin.databinding.FragmentSliderBinding
import com.example.imassage_admin.ui.adapter.ryclerView.RecyclerAdapter
import com.example.imassage_admin.ui.base.ScopedFragment
import com.example.imassage_admin.ui.utils.OnCLickHandler
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.android.synthetic.main.fragment_add_slider.*
import kotlinx.android.synthetic.main.fragment_slider.*
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.io.File

class SliderFragment : ScopedFragment() , KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: SliderViewModelFactory by instance()

    private lateinit var viewModel: SliderViewModel
    private lateinit var binding: FragmentSliderBinding
    private lateinit var navController: NavController

    // -- FOR DATA
    private var fileUri: Uri? = null
    private var mediaPath: String? = null
    private var postPath: String? = null
    private val REQUEST_PICK_PHOTO = 2

    private lateinit var adapter :RecyclerAdapter<Boarder>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = FragmentSliderBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(SliderViewModel::class.java)
        initAdapters()
        bindAdapters()
        bindUI()
        uiAcitons()

    }
    private fun bindUI() = launch {
        when(val callbeck = viewModel.sliders()){
            is NetworkResponse.Success ->
                adapter.datas = callbeck.body.boarders
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

                val description = fra_add_slide_text.text.toString()

                viewModel.uploadSlider(body , "IMassage" , description)
            }else{
                Toast.makeText(requireActivity(), "لطفان عکس را انتخاب کنید.", Toast.LENGTH_LONG).show()
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

    private fun initAdapters(){
        adapter = RecyclerAdapter()
        adapter.onClickHandler = object: OnCLickHandler<Boarder>{
            override fun onClickItem(element: Boarder) {
                deleteSlider(element.id)
            }

            override fun onClick(view: View) {}
            override fun onClickView(view: View, element: Boarder) {}
        }
    }
    private fun bindAdapters(){
        fra_slider_recycler.adapter = adapter
    }
    private fun deleteSlider(id: String)= launch {
        when(val callback = viewModel.deleteSlider(id)){
            is NetworkResponse.Success -> {
                Toast.makeText(context, "حذف با موفقیت انجام شد.", Toast.LENGTH_SHORT).show()
                bindUI()
            }
        }
    }
    private fun uiAcitons(){
        binding.onClick = object : OnCLickHandler<Nothing> {
            override fun onClickItem(element: Nothing) {}
            override fun onClickView(view: View, element: Nothing) {}
            override fun onClick(view: View) {
                when(view) {
                    fra_slider_add_slide ->
                        navController.navigate(R.id.action_sliderFragment_to_addSliderFragment
                        )
                }

            }

        }
    }

}