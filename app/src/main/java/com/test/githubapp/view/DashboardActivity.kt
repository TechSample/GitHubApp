package com.test.githubapp.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.githubapp.R
import com.test.githubapp.model.UserEntity
import com.test.githubapp.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.activity_dashboard.*


class DashboardActivity : AppCompatActivity() , UserListAdapter.UserListListener {

    var mViewModel : DashboardViewModel ?= null
    var mAdapter : UserListAdapter ?= null
    var mUserList : ArrayList<UserEntity> ?= null
    var mClickedItemPosition : Int ?= null
    val REQ_CODE_VIEW_USER_PIC = 101


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        mViewModel = ViewModelProviders.of(this,DashboardViewModel.Factory(this)).get(DashboardViewModel::class.java)

        if(isNetworkConnected(this)){
            rvUsersList.visibility = View.GONE
            shimmer_view_container.visibility = View.VISIBLE
            shimmer_view_container.startShimmerAnimation()

            mViewModel!!.getUsersFromAPI()
        }

        mViewModel!!.getAllUsersList().observe(this, Observer<List<UserEntity>> { usersList ->

            mUserList = usersList as ArrayList<UserEntity>
            if(mUserList!!.size > 0){

                if(mAdapter == null){

                    mUserList = usersList as ArrayList<UserEntity>
                    mAdapter = UserListAdapter(this, mUserList!!,this)
                    rvUsersList.adapter = mAdapter
                    rvUsersList.layoutManager = LinearLayoutManager(this)
                    shimmer_view_container.visibility = View.GONE
                    shimmer_view_container.stopShimmerAnimation()
                    rvUsersList.visibility = View.VISIBLE

                }else{

                    mAdapter!!.userList = mUserList!!
                    mAdapter!!.notifyDataSetChanged()
                }
            }


        })

        imgAddUser.setOnClickListener {

            var newUserEntity = mUserList!!.get(mUserList!!.lastIndex)
            newUserEntity.id = System.currentTimeMillis().toInt()
            mUserList!!.add(newUserEntity)
            mAdapter!!.userList = mUserList!!
            mAdapter!!.notifyDataSetChanged()
            rvUsersList.scrollToPosition(mUserList!!.size-1)
            Toast.makeText(this,getString(R.string.user_added),Toast.LENGTH_SHORT).show()


        }

    }

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    override fun onUserThumbnailClick(user: UserEntity, index : Int) {

        mClickedItemPosition = index
        val intent = Intent(this, UserPicFullscreenActivity::class.java)
        intent.putExtra("user_avatar_url",user.avatar_url)
        startActivityForResult(intent,REQ_CODE_VIEW_USER_PIC)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQ_CODE_VIEW_USER_PIC){
            if(resultCode == Activity.RESULT_OK){

                if(mClickedItemPosition != null){

                    mUserList!!.removeAt(mClickedItemPosition!!)
                    mAdapter!!.userList = mUserList!!
                    mAdapter!!.notifyDataSetChanged()
                }

            }
        }
    }




}