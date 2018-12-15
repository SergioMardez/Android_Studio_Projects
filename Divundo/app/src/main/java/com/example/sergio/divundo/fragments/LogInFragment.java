package com.example.sergio.divundo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sergio.divundo.R;
import com.example.sergio.divundo.activities.MainActivity;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends Fragment {


    EditText user, password;

    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);
        user = (EditText)view.findViewById(R.id.editTextUser);
        password = (EditText)view.findViewById(R.id.editTextPassword);

        Button sendLogin = (Button)view.findViewById(R.id.buttonLogin);

        sendLogin.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                loginData();
            }
        });

        return view;
    }

    public  void  loginData() {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(getContext());

        String url = "https://www.axacoair.se/api/companion/signin";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Fragment fragment = new EntrySuccessFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment).commit();
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                Toast.makeText(getContext(), R.string.loginError,Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("user", user.getText().toString()); //Add the data you'd like to send to the server.
                MyData.put("password", password.getText().toString());
                return MyData;
            }
        };


        MyRequestQueue.add(MyStringRequest);
    }
}
