package com.example.imassage_admin.ui.fragment.aboutUs

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.NetworkOnMainThreadException
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.BuildConfig
import com.example.imassage_admin.R
import com.example.imassage_admin.databinding.FragmentAboutUsBinding
import com.example.imassage_admin.ui.base.ScopedFragment
import com.example.imassage_admin.ui.utils.OnCLickHandler
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.android.synthetic.main.fragment_about_us.*
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.io.File

class AboutUsFragment : ScopedFragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: AboutUsViewModelFactory by instance()

    private lateinit var viewModel: AboutUsViewModel
    private lateinit var navController: NavController
    private lateinit var binding: FragmentAboutUsBinding

    private var fileUri: Uri? = null
    private var mediaPath: String? = null
    private var postPath: String? = null
    private val REQUEST_PICK_PHOTO = 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutUsBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(AboutUsViewModel::class.java)
        bindUI()
        uiActions()

    }
    fun bindUI() = launch {
        when(val callback = viewModel.aboutUs()){
            is NetworkResponse.Success ->
                binding.aboutUs = callback.body
        }
    }
    fun update() = launch{
        postPath?.let {
            if (it.isNotEmpty()){
                val imageFile = File(postPath!!)

                val requestBody = RequestBody.create(
                        activity!!.contentResolver.getType(fileUri!!)?.toMediaTypeOrNull() ,
                        imageFile
                )
                val body = MultipartBody.Part.createFormData("image", imageFile.name , requestBody)

//                val description_ = "we are fcosiety"
//                val description: RequestBody = RequestBody.create(
//                        MultipartBody.FORM, description_)
                val description = fra_aboutUs_text.text.toString()

                viewModel.aboutUsUpdate(body , description)
            }else{
                Toast.makeText(activity!!, "لطفان عکس را انتخاب کنید.", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun getImageFromGallery(){
        val galleryIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO)
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

                    val cursor = activity!!.contentResolver.query(selectedImage!!, filePathColumn, null, null, null)

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
            Toast.makeText(context!!, "Sorry, there was an error!", Toast.LENGTH_LONG).show()
        }

    }
    private fun uiActions(){
        binding.onClickHandler = object: OnCLickHandler<Nothing> {
            override fun onClickItem(element: Nothing) {}
            override fun onClickView(view: View, element: Nothing) {}

            override fun onClick(view: View) {
                when(view) {
                    fra_aboutUs_insertImage ->
                        getImageFromGallery()
                    fra_aboutUs_save ->
                        if(fileUri != null) update() else uploadText()
                }
            }
        }
    }
    private fun uploadText() = launch {
        if(fra_aboutUs_text.text!!.isNotEmpty()) {
            when (val callback = viewModel.aboutUsUpdateDescription(fra_aboutUs_text.text.toString())) {
                is NetworkResponse.Success -> {
                    Toast.makeText(context, "متن تغییر پیدا کرد." , Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(context, "متن را پر کنید" , Toast.LENGTH_SHORT).show()
        }
    }

}