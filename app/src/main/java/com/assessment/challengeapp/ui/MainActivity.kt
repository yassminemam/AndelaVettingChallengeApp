package com.assessment.challengeapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.assessment.challengeapp.AppUtilities
import com.assessment.challengeapp.R
import com.assessment.challengeapp.pojo.Photo
import com.assessment.challengeapp.pojo.PhotosSearchResponse
import com.assessment.challengeapp.repository.ApiClient
import com.assessment.challengeapp.repository.ApiInterface
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_search.setOnClickListener {
            val searchText = et_search.text.toString()
            if (searchText.isNotEmpty())
            {
                getAPIResults(searchText)
            }
            else
            {
                Toast.makeText(this, getString(R.string.enter_txt), Toast.LENGTH_SHORT).show();
            }
        }

    }

    fun getAPIResults(searchText:String){
        val service = ApiClient.client!!.create(ApiInterface::class.java)
        val call = service.getPhotosSearchAnswers(AppUtilities.methodName,
                                                    AppUtilities.apiKey,
                                                    searchText,
                                                    AppUtilities.format,
                                                    AppUtilities.noJsonCallback,
                                                    AppUtilities.max_per_page);

        call.enqueue(object : Callback<PhotosSearchResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<PhotosSearchResponse>,
                response: Response<PhotosSearchResponse>
            ) {
                Log.e("response.code()", response.code().toString())
                if (response.code() == 200) {
                    val photosSearchResponse = response.body();
                    rv_photos.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        val photosAdapter = PhotosRecyclerViewAdapter(this@MainActivity,
                            photosSearchResponse!!.photos.photo as ArrayList<Photo>)
                        adapter = photosAdapter
                        tv_place_holder.visibility = View.GONE
                        rv_photos.visibility = View.VISIBLE
                    }
                }
                else
                {
                    tv_place_holder.visibility = View.VISIBLE
                    rv_photos.visibility = View.GONE
                    tv_place_holder.text = getString(R.string.error) + response.code();
                }
            }

            override fun onFailure(calll: Call<PhotosSearchResponse>, t: Throwable) {
                Log.e("response", t.message!!)
            }

        })
    }
}
