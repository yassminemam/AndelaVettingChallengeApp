package com.assessment.challengeapp.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assessment.challengeapp.R
import com.assessment.challengeapp.pojo.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image.view.*

class PhotosRecyclerViewAdapter(val context:Context, photosList : ArrayList<Photo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val ctxt = context;
    private var items: ArrayList<Photo> = photosList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PhotoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is PhotoViewHolder -> {
                holder.bind(ctxt, items.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class PhotoViewHolder
    constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        val image = itemView.iv_photo
        val title = itemView.tv_photo_title
        fun bind(ctxt:Context, photo: Photo){
            title.text = photo.title

            val imageUri = "https://farm"+ photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id + "_" + photo.secret +".jpg"

            Picasso.with(ctxt).load(imageUri).fit().centerCrop()
                .into(image)

            image.setOnClickListener {
                val intent = Intent(ctxt, FullPhotoActivity::class.java)
                intent.putExtra("URL", imageUri)
                ctxt.startActivity(intent)
            }
            title.setOnClickListener {
                val intent = Intent(ctxt, FullPhotoActivity::class.java)
                intent.putExtra("URL", imageUri)
                ctxt.startActivity(intent)
            }
        }
    }

}