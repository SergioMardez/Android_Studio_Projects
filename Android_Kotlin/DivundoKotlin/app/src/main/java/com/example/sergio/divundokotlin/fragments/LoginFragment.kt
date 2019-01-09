package com.example.sergio.divundokotlin.fragments


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.sergio.divundokotlin.R
import com.example.sergio.divundokotlin.toast
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 *
 */
class LoginFragment : Fragment() {

    private lateinit var prefs: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)

        //shared preferences
        val fileName = "my-preferences"
        prefs = activity!!.applicationContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)

        view.buttonLogin.setOnClickListener { postRequest() }

        val token = prefs.getString("token", "")
        if (!token.isEmpty()){
            fragmentTransaction(EntrySuccessFragment())
        }

        return view
    }

    private fun postRequest() {
        val jsonPost = JSONObject()

        var url = "https://www.axacoair.se/api/companion/signin"

        var user = editTextUser.text.toString()
        var password = editTextPassword.text.toString()

        jsonPost.put("user", user)
        jsonPost.put("password", password)

        //jsonPost.put("user", "rafael+test@divundo.com")
        //jsonPost.put("password", "Sergio18")

        val postRequest = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(Request.Method.POST,url,jsonPost,
            Response.Listener { response ->
                var token = response.getString("token") //take the token from the response
                prefs.edit().putString("token", token).apply() //Save the token
                fragmentTransaction(EntrySuccessFragment())
            }, Response.ErrorListener {
                activity?.toast(R.string.loginError)
            }
        )
        postRequest.add(request)
    }

    private fun fragmentTransaction(fragment: Fragment) {
        activity?.supportFragmentManager!!.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

}
