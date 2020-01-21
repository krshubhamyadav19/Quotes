package com.app.final_kumar_shubham.authentication


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.assignment_5_kumarshubham.utills.showToast
import com.app.final_kumar_shubham.R
import com.app.final_kumar_shubham.authentication.createacount.AuthCreateAcount
import com.app.final_kumar_shubham.authentication.signin.AuthSingIn
import com.app.final_kumar_shubham.data.model.MessageHandler
import com.app.final_kumar_shubham.databinding.FragmentAuthWelcomeBinding

/**
 * A simple [Fragment] subclass.
 */
class AuthWelcome : Fragment() {

    var mAutWelcomeViewModel: AutWelcomeViewModel? = null
    var mBinding: FragmentAuthWelcomeBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_auth_welcome, container, false)
        return mBinding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //get viewModel
        mAutWelcomeViewModel =
            ViewModelProviders.of(this).get(AutWelcomeViewModel::class.java)
        // set data binding
        mBinding!!.authviewModel = mAutWelcomeViewModel


        mAutWelcomeViewModel!!.errorHandler.observe(this,
            Observer<MessageHandler> { t ->
                when (t!!.type) {
                    0 -> {
                        showToast(t.message)
                        val mFragmentTransaction: FragmentTransaction =
                            activity?.supportFragmentManager!!.beginTransaction()
                                .remove(this)
                        mFragmentTransaction.add(R.id.activity_dashboard, AuthSingIn())
                        mFragmentTransaction.commit()
                    }
                    1 -> {
                        showToast(t.message)
                        val mFragmentTransaction: FragmentTransaction =
                            activity?.supportFragmentManager!!.beginTransaction()
                                .remove(this)
                        mFragmentTransaction.add(R.id.activity_dashboard, AuthCreateAcount())
                        mFragmentTransaction.commit()
                    }
                }
            })
    }


}
