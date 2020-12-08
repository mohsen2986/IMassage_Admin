package com.example.imassage_admin.ui.fragment.massage.addMassage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
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
import com.example.imassage_admin.databinding.FragmentAddMassageBinding
import com.example.imassage_admin.ui.base.ScopedFragment
import com.example.imassage_admin.ui.utils.OnCLickHandler
import kotlinx.android.synthetic.main.fragment_add_massage.*
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.io.File

class AddMassageFragment : ScopedFragment() , KodeinAware{
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: AddMassageViewModelFactory by instance()

    private lateinit var viewModel: AddMassageViewModel
    private lateinit var binding: FragmentAddMassageBinding
    private lateinit var navController: NavController

    // -- FOR DATA
    private var fileUri: Uri? = null
    private var mediaPath: String? = null
    private var postPath: String? = null
    private val REQUEST_PICK_PHOTO = 2

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = FragmentAddMassageBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(AddMassageViewModel::class.java)
        bindUI()
        uiActions()
    }
    private fun bindUI() = launch {

    }
    private fun uiActions(){
        binding.onClick = object: OnCLickHandler<Nothing>{
            override fun onClickView(view: View, element: Nothing) {}
            override fun onClickItem(element: Nothing) {}
            override fun onClick(view: View) {
                when(view){
                    fra_add_massage_back ->
                        requireActivity().onBackPressed()
                    fra_add_massage_insertImage ->
                        getImageFromGallery()
                    fra_add_massage_submit ->{
                        upload()
                    }
                }
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
                    binding.imageUri = fileUri
                    cursor.close()
                    postPath = mediaPath
                }
            }

        } else if (resultCode != Activity.RESULT_CANCELED) {
            Toast.makeText(requireContext(), "Sorry, there was an error!", Toast.LENGTH_LONG).show()
        }

    }
    private fun getImageFromGallery(){
        val galleryIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO)
    }
    private fun upload() = launch {
        if(fra_add_massage_cost.text?.isNotEmpty()!! && fra_add_massage_description.text?.isNotEmpty()!! && fra_add_massage_massageName.text?.isNotEmpty()!! ) {
            postPath?.let {
                if (it.isNotEmpty()) {
                    val imageFile = File(postPath!!)

                    val requestBody = RequestBody.create(
                            requireActivity().contentResolver.getType(fileUri!!)?.toMediaTypeOrNull(),
                            imageFile
                    )
                    val body = MultipartBody.Part.createFormData("image", imageFile.name, requestBody)


                    viewModel.uploadMassage(body, fra_add_massage_massageName.text.toString(), fra_add_massage_cost.text.toString(),
                            "2", fra_add_massage_description.text.toString())
                } else {
                    Toast.makeText(requireActivity(), "please select an image ", Toast.LENGTH_LONG).show()
                }
            }
        }else{
            Toast.makeText(requireContext() , "همه موارد را پر کنید." , Toast.LENGTH_SHORT).show()
        }
    }

}