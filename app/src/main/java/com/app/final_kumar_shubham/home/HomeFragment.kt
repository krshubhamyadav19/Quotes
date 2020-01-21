package com.app.final_kumar_shubham.home


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.app.assignment_5_kumarshubham.utills.showToast
import com.app.final_kumar_shubham.R
import com.app.final_kumar_shubham.authentication.signin.AuthSingIn
import com.app.final_kumar_shubham.base.WorkerHandler.WorkerHandler
import com.app.final_kumar_shubham.data.SharedPref.SharedPrefrenceHandle
import com.app.final_kumar_shubham.data.model.MessageHandler
import com.app.final_kumar_shubham.data.model.UserModel
import com.app.final_kumar_shubham.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.row_layout.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    var mHomeViewModel: HomeViewModel? = null
    var mBinding: FragmentHomeBinding? = null
    var mHomeCustomAdapter: HomeCustomAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //get viewModel
        mHomeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        mHomeCustomAdapter = HomeCustomAdapter(mHomeViewModel!!)
        mBinding!!.lifecycleOwner = this
        // set data binding

        mBinding!!.homeviewmodel = mHomeViewModel
        var userId = FirebaseAuth.getInstance().currentUser!!.email
        mHomeViewModel!!.userEmail.value = userId.toString()

        mHomeViewModel!!.getdata()
        mHomeViewModel!!.fetchUsers()
        observelivedata()

        mBinding!!.recyclerView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        mBinding!!.recyclerView.adapter = mHomeCustomAdapter


        mHomeViewModel!!.errorHandler.observe(this,
            Observer<MessageHandler> { t ->
                when (t!!.type) {
                    0 -> {
                        showToast(t.message)

                    }
                    1 -> {
                        showToast(t.message)
                        openDialog()

                    }
                    2 -> {
                        showToast(t.message)

                        SharedPrefrenceHandle.clear()
                        val mFragmentTransaction: FragmentTransaction =
                            activity?.supportFragmentManager!!.beginTransaction()
                                .remove(this)
                        mFragmentTransaction.add(R.id.activity_dashboard, AuthSingIn())
                        mFragmentTransaction.commit()
                        SharedPrefrenceHandle.clear()

                    }
                }
            })
    }

    fun openDialog() {
        val dialog = Dialog(activity!!, R.style.AppBaseTheme)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.row_layout)
        dialog.show()

        dialog.btn_cancel_rowl_ayout.setOnClickListener {


            dialog.cancel()

        }
        dialog.btn_submit_home_frag.setOnClickListener {

            val requestBuilder = OneTimeWorkRequest.Builder(WorkerHandler::class.java).build()
            WorkManager.getInstance().enqueue(requestBuilder)
            val userEmail = FirebaseAuth.getInstance().currentUser!!.email
            val ref = FirebaseDatabase.getInstance().getReference("users").child("data").push()
            val mQuotes: String = dialog.et_quotes_home_frag.text.toString()

            if (mQuotes.isEmpty()) {

                showToast("Field are empty")
            } else {
                val user = UserModel(mQuotes, userEmail!!)

                ref.setValue(user)
                    .addOnSuccessListener {
                        showToast("data added  sucessfully")
                    }
                dialog.dismiss()


            }

        }
    }

    //Recyecler view observer
    fun observelivedata() {
        mHomeViewModel!!.dataget.observe(viewLifecycleOwner, Observer {
            mHomeCustomAdapter!!.updateList(it)
        })


    }

}





