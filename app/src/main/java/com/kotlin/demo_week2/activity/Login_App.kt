package com.kotlin.demo_week2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.kotlin.demo_week2.R
import com.kotlin.demo_week2.data.DataBaseWeeks
import com.kotlin.demo_week2.data.DbWorkerThread
import com.kotlin.demo_week2.data.entity.User
import com.kotlin.demo_week2.mvp.presenter.LoginPresenter
import com.kotlin.demo_week2.mvp.view.ILoginView
import kotlinx.android.synthetic.main.activity_login__app.*

class Login_App : AppCompatActivity(), ILoginView {
    private var mDataBaseWeeks: DataBaseWeeks? = null
    private lateinit var mDbWorkerThread: DbWorkerThread
    private lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login__app)
        //
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
        mDataBaseWeeks = DataBaseWeeks.getAppDataBase(this)
        //
        loginPresenter = LoginPresenter(this, this, mDataBaseWeeks)

        btn_Login.setOnClickListener(onClick)
        btn_changeView.setOnClickListener(onClick)
        btn_register.setOnClickListener(onClick)
        btn_cancel.setOnClickListener(onClick)
    }

    override fun onDestroy() {
        DataBaseWeeks.destroyInstance()
        mDbWorkerThread.quit()
        super.onDestroy()
        Log.d("Login_App", "onDestroy")
    }

    override fun onLoginFail(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onLoginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    private val onClick = View.OnClickListener { v ->
        when (v.id) {
            R.id.btn_Login -> {
                val task = Runnable {
                    if (loginPresenter.isCheckValidLogin(
                            et_inputEmail.text.toString(),
                            et_inputPassword.text.toString()
                        )
                    ) {
                        loginPresenter.loginApp(
                            et_inputEmail.text.toString(),
                            et_inputPassword.text.toString()
                        )
                    }

                }
                mDbWorkerThread.postTask(task)
            }
            R.id.btn_changeView -> {
                if (consLogin.isShown) {
                    consLogin.visibility = View.GONE
                    ll_register.visibility = View.VISIBLE
                }
            }
            R.id.btn_cancel -> {
                if (ll_register.isShown) {
                    consLogin.visibility = View.VISIBLE
                    ll_register.visibility = View.GONE
                }
            }
            R.id.btn_register -> {
                val task = Runnable {
                    if (loginPresenter.isCheckValidRegister(
                            User(
                                null, et_user_re.text.toString(),
                                et_password_re.text.toString(), et_email_re.text.toString(),
                                et_address_re.text.toString(), et_gender_re.text.toString()
                            )
                        )
                    ) {
                        loginPresenter.registerAccount(
                            User(
                                null, et_user_re.text.toString(),
                                et_password_re.text.toString(), et_email_re.text.toString(),
                                et_address_re.text.toString(), et_gender_re.text.toString()
                            )
                        )
                    }
                }
                mDbWorkerThread.postTask(task)
            }
        }
    }
}
