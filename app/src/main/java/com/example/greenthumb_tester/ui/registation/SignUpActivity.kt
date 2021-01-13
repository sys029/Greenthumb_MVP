package com.example.greenthumb_tester.ui.registation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.greenthumb_tester.R
import com.example.greenthumb_tester.model.registration.SignupResponse
import com.example.greenthumb_tester.model.login.Error
import com.example.greenthumb_tester.model.registration.*
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Response

class SignUpActivity : AppCompatActivity(),
    ISignupView,AdapterView.OnItemSelectedListener{

    val builder = GsonBuilder()
    val gson = builder.serializeNulls().create()

    private val signupPresenter=
        SignupPresenter(this, this)
    var user_type: Int = 1

    private lateinit var countryList : Array<CountryData>
    private lateinit var stateList : Array<StateData>
    private lateinit var cityList : Array<CityData>
    private  var countryId :Int = 0
    private  var stateId:Int = 0
    private  var cityId:Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        toolbarSignUp.title=""
        setSupportActionBar(toolbarSignUp)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        countrySpinner.onItemSelectedListener = this
        stateSpinner.onItemSelectedListener = this
        citySpinner.onItemSelectedListener = this

        signupPresenter.countryApi()

        radioSignUp.setOnCheckedChangeListener { group, checkedId ->

            if (checkedId == R.id.rUser)
                user_type = 1
            if (checkedId == R.id.rProvider)
                user_type = 2
            if (checkedId == R.id.rConsultant)
                user_type = 3
        }



        signUpButton.setOnClickListener {

            val firstN = firstName.text.toString().trim()
            val lastN = lastName.text.toString().trim()
            val phone = phoneNumber.text.toString().trim()
            val emailId = emailID.text.toString().trim()
            val password = passwordText.text.toString().trim()
            val cpassword = confirmPass.text.toString().trim()

            if(firstN.isEmpty()){
                firstName.error = "First Name required"
                firstName.requestFocus()
                return@setOnClickListener
            }

            if(lastN.isEmpty()){
                lastName.error = "Last Name required"
                lastName.requestFocus()
                return@setOnClickListener
            }

            if(phone.isEmpty()){
                phoneNumber.error = "Phone required"
                phoneNumber.requestFocus()
                return@setOnClickListener
            }

            if(emailId.isEmpty()){
                emailID.error = "Email required"
                emailID.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                passwordText.error = "Password required"
                passwordText.requestFocus()
                return@setOnClickListener
            }

            if(cpassword.isEmpty()){
                confirmPass.error = "Confirm Password required"
                confirmPass.requestFocus()
                return@setOnClickListener
            }

            if(cpassword != password){
                confirmPass.error = "Dont match"
                confirmPass.requestFocus()
                return@setOnClickListener
            }
            if(stateSpinner.isEnabled == false ){
                Toast.makeText(this,"Select Location",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }



            signupPresenter.callSignUpAPI(firstN,lastN,phone,password,user_type,emailId,countryId,cityId,stateId)
        }



    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onSignupSuccess(loginBase: SignupResponse) {
        if (loginBase.status == resources.getString(R.string.success)) {

            Toast.makeText(applicationContext, loginBase.message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onSignupError(error: Error) {
        Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show()
    }

    override fun countrySpinner(response: Response<JsonObject>) {
        val res = gson.fromJson(response.body().toString(), CountryResponse::class.java)
        countryList = res.data.toTypedArray()
        var country = arrayOfNulls<String>(countryList.size+1)
        country[0] = "Select Country"

        for (i in countryList.indices) {
            country[i+1] = countryList[i].country_name
        }


        val adapter =  ArrayAdapter(this@SignUpActivity, android.R.layout.simple_spinner_item,country)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countrySpinner.adapter = adapter
    }


    override fun stateSpinner(response: Response<JsonObject>) {
        val res = gson.fromJson(response.body().toString(), StateResponse::class.java)
        stateList = res.data.toTypedArray()
        var state = arrayOfNulls<String>(stateList.size+1)
        state[0]="Select State"
        for (i in stateList.indices) {

            state[i+1] = stateList[i].state_name
        }

        val adapter =  ArrayAdapter(this@SignUpActivity, android.R.layout.simple_spinner_item,state)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        stateSpinner.adapter = adapter
    }

    override fun citySpinner(response: Response<JsonObject>) {
        val res = gson.fromJson(response.body().toString(), CityResponse::class.java)
        cityList = res.data.toTypedArray()
        var city = arrayOfNulls<String>(cityList.size+1)
        city[0]="Select city"
        for (i in cityList.indices) {

            city[i+1] = cityList[i].city_name

        }



        val adapter =  ArrayAdapter(this@SignUpActivity, android.R.layout.simple_spinner_item,city)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        citySpinner.adapter = adapter
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id) {
            R.id.countrySpinner -> {
                countryId = countryList.get(position).country_id.toInt()
                signupPresenter.stateApi(countryId)

                if (countryId == 1) {
                    countryId = 1
                    stateSpinner.isEnabled = false
                    signupPresenter.stateApi(countryId)
                    countrySpinner.setSelection(0)
                }
                else if(countryId>1) {
                    stateSpinner.isEnabled = true
                    countryId -= 1
                    signupPresenter.stateApi(countryId)
                }



            }

            R.id.stateSpinner -> {

                stateId = stateList.get(position).state_id.toInt()



                if (position>=1){
                    citySpinner.isEnabled=true
                }
                else if (position<1){
                    citySpinner.isEnabled=false
                }


                if (stateId == 1) {
                    stateId = 1
                    signupPresenter.cityApi(stateId)
                    stateSpinner.setSelection(0)
                } else if(stateId>1) {
                    stateId -= 1

                    signupPresenter.cityApi(stateId)
                }


            }

            R.id.citySpinner -> {
                cityId = cityList.get(position).city_id.toInt()

            }

        }
    }


}
