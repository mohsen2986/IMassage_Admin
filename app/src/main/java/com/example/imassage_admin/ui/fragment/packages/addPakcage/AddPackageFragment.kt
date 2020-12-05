package com.example.imassage_admin.ui.fragment.packages.addPakcage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imassage_admin.BuildConfig
import com.example.imassage_admin.R
import com.example.imassage_admin.data.model.Massage
import com.example.imassage_admin.databinding.FragmentAddPackageBinding
import com.example.imassage_admin.ui.base.ScopedFragment
import com.example.imassage_admin.ui.utils.OnCLickHandler
import com.example.imassage_admin.ui.utils.StaticVariables
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.android.synthetic.main.fragment_add_package.*
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.io.File


class AddPackageFragment : ScopedFragment() , KodeinAware{
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: AddPackageViewModelFactory by instance()

    private lateinit var viewModel: AddPackageViewModel
    private lateinit var navController: NavController
    private lateinit var binding: FragmentAddPackageBinding

    // --FROM DATA
    private var fileUri: Uri? = null
    private var mediaPath: String? = null
    private var postPath: String? = null
    private val REQUEST_PICK_PHOTO = 2

    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var massages: List<Massage>
    private lateinit var selectedMassage: String
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = FragmentAddPackageBinding.inflate(inflater , container ,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , viewModelFactory).get(AddPackageViewModel::class.java)
        initAdapter()
        bindAdapter()
        bindUI()
        uiActions()

        setFragmentResult("requestKey", bundleOf("bundleKey" to StaticVariables.REFRESH))
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
//                 Handle the back button event
                requireActivity().onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

    }
    private fun bindUI() =launch {
        when(val callback = viewModel.massages()){
            is NetworkResponse.Success -> {
                massages  = callback.body.datas
                adapter.addAll(massages.map{it.name})
            }
        }
    }
    private fun uploadData(){

    }
    private fun getImageFromGallery(){
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO)
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

                viewModel.uploadPackages(body , fra_add_package_packageName.text.toString() , fra_add_package_description.text.toString()
                        , fra_add_package_cost.text.toString() , selectedMassage , fra_add_package_length.text.toString() )
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
                    binding.imageUri = fileUri

                    cursor.close()
                    postPath = mediaPath
                }
            }

        } else if (resultCode != Activity.RESULT_CANCELED) {
            Toast.makeText(requireContext(), "Sorry, there was an error!", Toast.LENGTH_LONG).show()
        }

    }
    private fun initAdapter(){
        adapter = ArrayAdapter( requireContext() ,R.layout.dropdown_select_massage)
    }
    private fun bindAdapter(){
        fra_add_package_select_massage.setAdapter(adapter)
    }
    private fun getMassageId(massageName: String) =
        massages.find { it.name == massageName }!!.id
    private fun uiActions(){
        binding.onClick = object: OnCLickHandler<Nothing>{
            override fun onClickItem(element: Nothing) {}
            override fun onClickView(view: View, element: Nothing) {}
            override fun onClick(view: View) {
                when(view){
                    fra_add_package_insertImage ->
                        getImageFromGallery()

                    fra_add_package_submit ->
                        upload()
                }
            }
        }
        fra_add_package_select_massage.onItemClickListener = AdapterView.OnItemClickListener { parent, p1, position, p3 ->
            Toast.makeText(context ,"${parent?.getItemAtPosition(position)}" , Toast.LENGTH_LONG).show()
            selectedMassage = getMassageId(parent?.getItemAtPosition(position).toString())
        }

    }


}