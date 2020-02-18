package com.kotlin.demo_week2.mvp.presenter

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.kotlin.demo_week2.R
import com.kotlin.demo_week2.data.DataBaseWeeks
import com.kotlin.demo_week2.data.entity.User
import com.kotlin.demo_week2.mvp.model.IsUser
import com.kotlin.demo_week2.mvp.view.ILoginView
import com.kotlin.demo_week2.unit.UnitApp
import com.kotlin.demo_week2.unit.unitAp

class LoginPresenter(var mContext: Context, val iLoginView: ILoginView, var data: DataBaseWeeks?) :
    ILoginPresenter {
    private val TAG: String = "PresenterLogin"

    override fun isCheckValidLogin(email: String, passWord: String): Boolean {
        val isUser = IsUser()
        val resultCode = isUser.isInvalidLogin(email, passWord)
        when (resultCode) {
            1 -> iLoginView.onLoginFail(R.string.err_emailnull)
            2 -> iLoginView.onLoginFail(R.string.err_email_invalid)
            3 -> iLoginView.onLoginFail(R.string.err_password_invalid)
            else -> return true
        }
        return false
    }

    override fun isCheckValidRegister(user: User): Boolean {
        val isUser = IsUser()
        val resultCode = isUser.isInvalidRegister(
            user.userName,
            user.pass,
            user.email,
            user.address,
            user.gender
        )
        when (resultCode) {
            1 -> iLoginView.onLoginFail(R.string.user_null)
            2 -> iLoginView.onLoginFail(R.string.err_emailnull)
            3 -> iLoginView.onLoginFail(R.string.err_email_invalid)
            4 -> iLoginView.onLoginFail(R.string.err_password_invalid)
            else -> return true
        }
        return false
    }

    override fun loginApp(email: String, pass: String) {
        if (data?.userDao()?.checkExistsUser(email, pass)!!) {
            iLoginView.onLoginSuccess()
            for (user in data?.userDao()?.getAllData()!!) {
                if (email.equals(user.email)) {
                    unitAp.currentUser = user
                    UnitApp.currentUser = user
                    Log.d(
                        TAG,
                        "Current User: ${unitAp.currentUser!!.email} - ${UnitApp.currentUser?.userName}"
                    )
                    break
                }
            }
        } else {
            Toast.makeText(mContext, "This account not exists", Toast.LENGTH_SHORT).show()
        }
    }

    override fun registerAccount(user: User) {
        if (!data?.userDao()?.getDataUserByID(user.email)!!) {
            data?.userDao()!!.insertAccount(
                User(
                    null, user.userName,
                    user.pass, user.email, user.address, user.gender
                )
            )
            Toast.makeText(mContext, "Register is success!", Toast.LENGTH_SHORT).show()
        } else {
            Log.d(TAG, "This account already exists")
        }
    }


}