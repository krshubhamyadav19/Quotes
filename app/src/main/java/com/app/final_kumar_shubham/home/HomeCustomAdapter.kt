package com.app.final_kumar_shubham.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.app.final_kumar_shubham.BR
import com.app.final_kumar_shubham.R
import com.app.final_kumar_shubham.data.model.UserModel


/**
 * @author Kumar Shubham
 *
 */
class HomeCustomAdapter(var homeviewModel: HomeViewModel) :
    RecyclerView.Adapter<HomeCustomAdapter.ViewHolder>() {

    val userList = ArrayList<UserModel>()

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item, parent, false
        )
        return ViewHolder(binding, homeviewModel)

    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(var binding: ViewDataBinding, var viewModel: HomeViewModel) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItems(user: UserModel) {
            binding.setVariable(BR.homeviewmodel, viewModel)
            binding.setVariable(BR.user, user)
            binding.executePendingBindings()
        }
    }

    fun updateList(list: List<UserModel>) {
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()

    }
}