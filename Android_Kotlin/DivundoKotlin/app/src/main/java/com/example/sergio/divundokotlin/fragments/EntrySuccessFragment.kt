package com.example.sergio.divundokotlin.fragments


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.sergio.divundokotlin.R
import com.example.sergio.divundokotlin.toast
import org.json.JSONArray


/**
 * A simple [Fragment] subclass.
 *
 */
class EntrySuccessFragment : Fragment() {

    private lateinit var prefs: SharedPreferences
    private var token: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_entry_success, container, false)

        //shared preferences
        val fileName = "mis-preferencias"

        //Take the token back
        prefs = activity!!.applicationContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        token = prefs.getString("token", "")

        getRequest()

        return view
    }

    private fun getRequest() {
        val params = HashMap<String, String>()
        //Add the data you'd like to send to the server.
        params["user"] = "rafael+test@divundo.com"
        params["customer"] = ""
        val url = "https://www.axacoair.se/api/companion/Bookings"

        val getUrl = createGetWithParams(url, params)

        val postRequest = Volley.newRequestQueue(context)
        val request = object: JsonArrayRequest(Request.Method.GET,getUrl,null,
            Response.Listener<JSONArray> { response ->
                activity?.toast(R.string.app_name)
            }, Response.ErrorListener {
                activity?.toast(R.string.JsonError)
            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer $token"
                return headers
            }
        }
        postRequest.add(request)
    }

    private fun createGetWithParams(url: String, params: Map<String, String>): String {
        var url = url
        val builder = StringBuilder()
        for (key in params.keys) {
            val value = params[key]
            if (value != null) {
                if (builder.length > 0)
                    builder.append("&")
                builder.append(key).append("=").append(value)
            }
        }
        url += "?" + builder.toString()
        return url
    }

}
