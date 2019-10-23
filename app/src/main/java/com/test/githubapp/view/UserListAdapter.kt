package com.test.githubapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.test.githubapp.R
import com.test.githubapp.model.UserEntity

class UserListAdapter(context : Context, userList:List<UserEntity>, userListListener: UserListListener) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    val context = context
    var userList = userList
    val mListener = userListListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.row_user_list,parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder:UserViewHolder, position: Int) {
        val user = userList[position]
        holder.userName.text = "Name - "+user.login
        holder.userType.text = "Type - "+user.type

        if(user.avatar_url != null){
            Glide.with(context).load(user.avatar_url).apply(
                RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ic_default_placeholder)
                    .error(R.drawable.ic_default_placeholder)
            ).into(holder.userThumbnail)
        }


        holder.userThumbnail.setOnClickListener {

            if(mListener != null){

                mListener.onUserThumbnailClick(user,position)
            }

        }


    }


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val userName : TextView = itemView.findViewById(R.id.txtUserName)
        val userType : TextView = itemView.findViewById(R.id.txtUserType)
        val userThumbnail : ImageView = itemView.findViewById(R.id.imgUserThumbnail)
    }


    interface UserListListener {

        fun onUserThumbnailClick(user : UserEntity, index : Int)

    }

}