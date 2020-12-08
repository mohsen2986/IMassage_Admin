package com.example.imassage_admin.data.repository

import android.os.Environment
import android.util.Log
import com.example.imassage_admin.data.remote.api.AuthApiInterface
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import java.io.*

class DataRepository(
    private val apiInterface: AuthApiInterface
){
    // ABOUT US
    suspend fun aboutUs() =
        apiInterface.aboutUs("1")

    suspend fun updateAboutUs(file: MultipartBody.Part? , description: String) =
        apiInterface.updateAboutUs(file = file  , id = "1" , description = description)

    suspend fun updateAboutUsDescription(description: String) =
            apiInterface.updateAboutUsDescription(id = "1" , description = description)

    // sliders
    suspend fun getSliders() =
            apiInterface.sliders()

    suspend fun uploadSlider(file: MultipartBody.Part , title: String , description: String) =
            apiInterface.uploadSlider(file , title ,  description)

    suspend fun deleteSlider(id: String) =
            apiInterface.deleteSlider(id)

    // massages
    suspend fun getMassages() =
        apiInterface.massages()

    suspend fun uploadMassage(image: MultipartBody.Part , name: String , cost: String , length: String , description: String) =
        apiInterface.uploadMassage(image , name , cost , length , description)

    suspend fun deleteMassage(id: String) =
        apiInterface.deleteMassage(id)

    // packages
    suspend fun getPackages() =
        apiInterface.packages()

    suspend fun uploadPackage(image: MultipartBody.Part , name: String ,  description: String , cost: String , massageId: String , length: String) =
        apiInterface.uploadPackage(image , name , description , cost , massageId , length)

    suspend fun deletePackage(id: String) =
        apiInterface.deletePackage(id)

    // questions
    suspend fun question() =
            apiInterface.questions()

    suspend fun addQuestion(question: String) =
            apiInterface.uploadQuestion(question)

    suspend fun deleteQuestion(id: String) =
            apiInterface.deleteQuestion(id)
    // users
    suspend fun users(page: Int?) =
            apiInterface.users(page)

    // configs
    suspend fun configs()=
            apiInterface.configs()
    suspend fun updateConfigs(
        h0: String ,
        h0_gender: String ,
        h1: String ,
        h1_gender: String ,
        h2: String ,
        h2_gender: String ,
        h3: String ,
        h3_gender: String ,
        h4: String ,
        h4_gender: String ,
        h5: String ,
        h5_gender: String ,
        h6: String ,
        h6_gender: String ,
        h7: String ,
        h7_gender: String ,
        h8: String ,
        h8_gender: String ,
        h9: String ,
        h9_gender: String ,
        h10: String ,
        h10_gender: String ,
        h11: String ,
        h11_gender: String ,
        h12: String ,
        h12_gender: String ,
        h13: String ,
        h13_gender: String ,
        h14: String ,
        h14_gender: String ,
        h15: String ,
        h15_gender: String ,
        h16: String ,
        h16_gender: String ,
        h17: String ,
        h17_gender: String ,
        h18: String ,
        h18_gender: String ,
        h19: String ,
        h19_gender: String ,
        h20: String ,
        h20_gender: String ,
        h21: String ,
        h21_gender: String ,
        h22: String ,
        h22_gender: String ,
        h23: String ,
        h23_gender: String ,
        d1: String ,
        d2: String ,
        d3: String ,
        d4: String ,
        d5: String ,
        d6: String ,
        d7: String ,
        closed_days: String ,
        open_days: String
    ) = apiInterface.updateConfigs(
                    h0,
                    h0_gender,
                    h1,
                    h1_gender,
                    h2,
                    h2_gender,
                    h3,
                    h3_gender,
                    h4,
                    h4_gender,
                    h5,
                    h5_gender,
                    h6,
                    h6_gender,
                    h7,
                    h7_gender,
                    h8 ,
                    h8_gender ,
                    h9 ,
                    h9_gender ,
                    h10,
                    h10_gender,
                    h11,
                    h11_gender,
                    h12,
                    h12_gender,
                    h13,
                    h13_gender,
                    h14,
                    h14_gender,
                    h15,
                    h15_gender,
                    h16,
                    h16_gender,
                    h17,
                    h17_gender,
                    h18,
                    h18_gender,
                    h19,
                    h19_gender,
                    h20,
                    h20_gender,
                    h21,
                    h21_gender,
                    h22,
                    h22_gender,
                    h23,
                    h23_gender,
                    d1,
                    d2,
                    d3,
                    d4,
                    d5,
                    d6,
                    d7,
                    closed_days,
                    open_days
    )

    // order
    suspend fun orders(page: Int? , userId: String?) =
            apiInterface.order(page , userId)

    // answers
    suspend fun answers(filledForm: String) =
            apiInterface.answers(filledForm)

    // offers
    suspend fun offers(page: Int) =
            apiInterface.offers(page)

    suspend fun createOffer(number: String , precent: String , massageId: String , startTime: String , expireTime: String ) =
            apiInterface.createOffer(number , precent , massageId , startTime , expireTime)

    suspend fun deleteOffer(id: String) =
            apiInterface.deleteOffer(id)

    suspend fun reservedOrders(page: Int) =
            apiInterface.reservedOrders(page)

    // consulting
    suspend fun getConsulting() =
        apiInterface.getConsulting()

    suspend fun setConsultingUser(userId: String) =
        apiInterface.setConsultingUser(userId)

    // download Users as PDF

    suspend fun downloadUsersPdf():Boolean =  withContext(Dispatchers.IO) {
            val response = apiInterface.downloadUsers()
            val name = "users.pdf"
            when (response) {
                is NetworkResponse.Success -> saveToDisk(response.body, name)
                else -> false
            }

    }


    private suspend fun saveToDisk(body: ResponseBody, filename: String): Boolean {
        val TAG = "SAVE_TO_DISK"
        try {
            val destinationFile = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    filename
            )
            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null
            try {
                inputStream = body.byteStream()
                outputStream = FileOutputStream(destinationFile)
                val data = ByteArray(4096)
                var count: Int
                var progress = 0
                val fileSize = body.contentLength()
                Log.d(TAG, "File Size=$fileSize")
                while (inputStream.read(data).also { count = it } != -1) {
                    outputStream.write(data, 0, count)
                    progress += count
                    Log.d(
                            TAG,
                            "Progress: " + progress + "/" + fileSize + " >>>> " + progress.toFloat() / fileSize
                    )
                }
                outputStream.flush()
                Log.d(TAG, destinationFile.parent)
                return true
            } catch (e: IOException) {
                e.printStackTrace()
                Log.d(TAG, "Failed to save the file!")
                return false
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Log.d(TAG, "Failed to save the file!")
            return false
        }
    }
}