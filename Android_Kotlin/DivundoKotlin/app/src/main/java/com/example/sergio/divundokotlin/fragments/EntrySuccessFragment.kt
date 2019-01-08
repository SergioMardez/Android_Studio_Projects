package com.example.sergio.divundokotlin.fragments


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.sergio.divundokotlin.R
import com.example.sergio.divundokotlin.adapters.TextPicAdapter
import com.example.sergio.divundokotlin.listeners.RecyclerTextPicListener
import com.example.sergio.divundokotlin.models.TextPic
import com.example.sergio.divundokotlin.toast
import kotlinx.android.synthetic.main.fragment_entry_success.view.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


/**
 * A simple [Fragment] subclass.
 *
 */
class EntrySuccessFragment : Fragment() {

    private lateinit var list: ArrayList<TextPic>

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: TextPicAdapter
    private val layoutManager by lazy { LinearLayoutManager(context) }

    private lateinit var prefs: SharedPreferences
    private var token: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_entry_success, container, false)

        setHasOptionsMenu(true)

        //shared preferences
        val fileName = "mis-preferencias"

        //Take the token back
        prefs = activity!!.applicationContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        token = prefs.getString("token", "")

        recycler = view.recyclerView as RecyclerView

        getRequest()

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.logout, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_logout -> {
                prefs.edit().clear().apply()
                fragmentTransaction(LoginFragment())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fragmentTransaction(fragment: Fragment) {
        activity?.supportFragmentManager!!.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    private fun getRequest() {
        val params = HashMap<String, String>()
        params["user"] = "rafael+test@divundo.com"
        params["customer"] = ""
        val url = "https://www.axacoair.se/api/companion/Bookings"

        val getUrl = createGetWithParams(url, params)

        val postRequest = Volley.newRequestQueue(context)
        val request = object: JsonArrayRequest(Request.Method.GET,getUrl,null,
            Response.Listener<JSONArray> { response ->
                list = ArrayList()
                var textPic: TextPic

                //Take the object from the response
                try {
                    val jsonResponse: JSONObject = response.getJSONObject(0)
                    textPic = TextPic(
                        jsonResponse.getString("subTitle"),
                        "https://images.pexels.com/photos/267885/pexels-photo-267885.jpeg?cs=srgb&dl=academy-accomplishment-celebrate-267885.jpg&fm=jpg"
                    )
                    list.add(textPic)
                } catch (e: JSONException){
                    activity?.toast("Empty JSONArray")
                }

                //Extra
                textPic = TextPic("Happy New Year!", "https://cdn.pixabay.com/photo/2017/01/04/21/00/new-years-eve-1953253_960_720.jpg")
                list.add(textPic)

                setRecyclerView() //Make the cardView
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

    private fun setRecyclerView() {
        recycler.setHasFixedSize(true)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = layoutManager
        adapter = (TextPicAdapter(list, object: RecyclerTextPicListener {
            override fun onClick(textPic: TextPic, position: Int) {
                activity?.toast(textPic.text)
            }
        }))
        recycler.adapter = adapter
    }

}
