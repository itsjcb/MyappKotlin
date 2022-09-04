package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView

class PostsAdapter(
    var mContext: Context,
    var resource: Int,
    var values: ArrayList<Post>
) : ArrayAdapter<Post>(mContext, resource, values) {
    //take the view and put a value before showing : create a view with the xml
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //retrieve the value in each position
        //val post = values.get(position)
        val post = values[position]
        //convert xml to view
        val itemView = LayoutInflater.from(mContext).inflate(resource, parent, false)

        //val txtEmail = email.text.toString()
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvDesc = itemView.findViewById<TextView>(R.id.tvDesc)
        val imagePost = itemView.findViewById<ImageView>(R.id.imagePost)
        val imageShPopup = itemView.findViewById<ImageView>(R.id.imageShowPopup)

        tvTitle.text = post.title
        tvDesc.text = post.description
        imagePost.setImageResource(post.image)

        imageShPopup.setOnClickListener{
            //instantiation of a popMenu with his constructor
            val popupMenu = PopupMenu(mContext, imageShPopup)
            //inflate your menu resource into the Menu object returned by PopupMenu.getMenu()
            popupMenu.menuInflater.inflate(R.menu.list_popup_menu, popupMenu.menu)
            popupMenu.show()
            // OnMenuItemClickListener interface and register it with your PopupMenu by calling setOnMenuItemclickListener().
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId){
                    R.id.itemShow ->{
                        // we are in context Adapter : we're using mContext instead of this
                        Intent(mContext, PostDetailsActivity::class.java).also{
                            it.putExtra("title", post.title)
                            mContext.startActivity(it)
                        }
                    }
                    R.id.itemDelete ->{
                        values.removeAt(position)
                        notifyDataSetChanged()
                    }
                }
                true
            }
            popupMenu.show()
        }
        return itemView
    }
}