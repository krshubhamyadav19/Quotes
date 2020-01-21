package com.app.final_kumar_shubham.authentication.createacount


import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.assignment_5_kumarshubham.utills.showToast
import com.app.final_kumar_shubham.R
import com.app.final_kumar_shubham.authentication.signin.AuthSingIn
import com.app.final_kumar_shubham.base.FirebaseHandlerInit
import com.app.final_kumar_shubham.base.ViewModelFactory
import com.app.final_kumar_shubham.data.model.MessageHandler
import com.app.final_kumar_shubham.databinding.FragmentAuthCreateacountBinding
import com.app.final_kumar_shubham.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_auth_createacount.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class AuthCreateAcount : Fragment() {

    var mAuthCreateAcountViewModel: AuthCreateAcountViewModel? = null
    var mBinding: FragmentAuthCreateacountBinding? = null
    var mViewModelFactory: ViewModelFactory? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_auth_createacount, container, false)
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModelFactory =
            ViewModelFactory(
                FirebaseHandlerInit.firebaseHandler
            )

        //get viewModel
        mAuthCreateAcountViewModel =
            ViewModelProviders.of(this, mViewModelFactory)
                .get(AuthCreateAcountViewModel::class.java)
        // set data binding
        mBinding!!.authcreateacountviewmodel = mAuthCreateAcountViewModel


     /*this click return age to tv*/

        et_date_creat_account_frag.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view:View) {
                val c = Calendar.getInstance()
                val mYear = c.get(Calendar.YEAR)
                val mMonth = c.get(Calendar.MONTH)
                val mDay = c.get(Calendar.DAY_OF_MONTH)
                val dateDialog = DatePickerDialog(view.getContext(), datePickerListener, mYear, mMonth, mDay)
                dateDialog.getDatePicker().setMaxDate(Date().getTime())
                dateDialog.show()
            }

            val datePickerListener = object: DatePickerDialog.OnDateSetListener {
                override fun onDateSet(datePicker: DatePicker, year:Int, month:Int, day:Int) {
                    val c = Calendar.getInstance()
                    c.set(Calendar.YEAR, year)
                    c.set(Calendar.MONTH, month)
                    c.set(Calendar.DAY_OF_MONTH, day)
                    SimpleDateFormat("dd MMM YYYY").format(c.getTime())

                    et_date_creat_account_frag.setText(Integer.toString(calculateAge(c.getTimeInMillis())))
                }
            }

            fun calculateAge(date:Long):Int {
                val dob = Calendar.getInstance()
                dob.setTimeInMillis(date)
                val today = Calendar.getInstance()
                var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
                if (today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH))
                {
                    age--
                }
                return age
            }
        })

        mAuthCreateAcountViewModel!!.errorHandler.observe(this,
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
                        mFragmentTransaction.add(R.id.activity_dashboard, AuthSingIn())
                        mFragmentTransaction.commit()
                    }
                    3 -> {

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





}
