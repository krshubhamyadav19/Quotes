package com.app.final_kumar_shubham.authentication.signin


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
import com.app.final_kumar_shubham.data.SharedPref.SharedPrefrenceHandle
import com.app.final_kumar_shubham.data.model.MessageHandler
import com.app.final_kumar_shubham.databinding.FragmentAuthSigninBinding
import com.app.final_kumar_shubham.home.HomeFragment

/**
 * A simple [Fragment] subclass.
 */
class AuthSingIn : Fragment() {

    var mAuthSignInViewModel: AuthSignInViewModel? = null
    var mBinding: FragmentAuthSigninBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        SharedPrefrenceHandle.init(activity!!)

        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_auth_signin, container, false)
        return mBinding!!.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get viewModel
        mAuthSignInViewModel =
            ViewModelProviders.of(this).get(AuthSignInViewModel::class.java)
        // set data binding
        mBinding!!.authsigninviewmodel = mAuthSignInViewModel


        sharedPrefObserver()
        val name =
            SharedPrefrenceHandle.read("email", "")
        mAuthSignInViewModel!!.email.value = name


        mAuthSignInViewModel!!.errorHandler.observe(this,
            Observer<MessageHandler> { t ->
                when (t!!.type) {
                    0 -> {
                        showToast(t.message)

                    }
                    1 -> {
                        showToast(t.message)
                        val mFragmentTransaction: FragmentTransaction =
                            activity?.supportFragmentManager!!.beginTransaction()
                                .remove(this)
                        mFragmentTransaction.add(R.id.activity_dashboard, AuthCreateAcount())
                        mFragmentTransaction.commit()
                    }
                    2 -> {
                        showToast(t.message)

                        val email = mAuthSignInViewModel!!.email.value.toString()
                        SharedPrefrenceHandle.write("email", email)
                        val mFragmentTransaction: FragmentTransaction =
                            activity?.supportFragmentManager!!.beginTransaction()
                                .remove(this)
                        mFragmentTransaction.add(
                            R.id.activity_dashboard,
                            HomeFragment()
                        )
                        mFragmentTransaction.commit()

                    }
                }
            })


    }

    private fun sharedPrefObserver() {
        mAuthSignInViewModel!!.email.observe(viewLifecycleOwner, Observer {
            val mData = it
            SharedPrefrenceHandle.write("email", mData)
        })
    }


}
