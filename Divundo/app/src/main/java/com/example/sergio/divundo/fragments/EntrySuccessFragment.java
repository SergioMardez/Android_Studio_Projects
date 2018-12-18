package com.example.sergio.divundo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sergio.divundo.R;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class EntrySuccessFragment extends Fragment {


    public EntrySuccessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entry_success, container, false);

        getPostData();

        return view;
    }

    public  void  getPostData() {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(getContext());

        String url = "https://www.axacoair.se/api/companion/Bookings";
        StringRequest MyStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                String resp = response;
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                Toast.makeText(getContext(), R.string.getError,Toast.LENGTH_LONG).show();
            }
        }) {
            /** Passing some request headers **/
            @Override
            public Map getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer (token)");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                //Add the data you'd like to send to the server.
                MyData.put("user","rafael+test@divundo.com");
                MyData.put("customer","");
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }

}
