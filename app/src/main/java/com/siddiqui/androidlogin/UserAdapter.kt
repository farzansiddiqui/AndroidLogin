package com.siddiqui.androidlogin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private var userList:List<UserData>): RecyclerView.Adapter<UserAdapter.UserDataViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDataViewModel {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_user_data,parent,false)
        return UserDataViewModel(view)
    }

    override fun getItemCount(): Int {
       return userList.size
    }

    override fun onBindViewHolder(holder: UserDataViewModel, position: Int) {
        val setUserData = userList[position]
        holder.bind(setUserData)
    }
    inner class UserDataViewModel(itemView: View): RecyclerView.ViewHolder(itemView){
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val ageTextView: TextView = itemView.findViewById(R.id.ageTextView)
        private val cityTextView: TextView = itemView.findViewById(R.id.cityTextView)

        fun bind(userData:UserData){
            nameTextView.text = userData.Name
            ageTextView.text = userData.Age.toString()
            cityTextView.text = userData.City
        }
    }

    fun sortByName() {
        userList = userList.sortedBy { it.Name }
        notifyDataSetChanged()
    }
    fun sortByAge() {
        userList = userList.sortedBy { it.Age }
        notifyDataSetChanged()
    }

    fun sortByCity() {
        userList = userList.sortedBy { it.City }
        notifyDataSetChanged()
    }
}