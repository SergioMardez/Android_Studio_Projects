package com.example.sergio.divundo.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sergio.divundo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends Fragment {

    private SharedPreferences prefs;
    EditText user, password;

    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);
        //Bind UI
        user = (EditText)view.findViewById(R.id.editTextUser);
        password = (EditText)view.findViewById(R.id.editTextPassword);

        //Use shared preferences to save the token. No need to login unless logout
        prefs = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        Button sendLogin = (Button)view.findViewById(R.id.buttonLogin);

        sendLogin.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                postRequestData();
            }
        });

        //If token is saved, go to entrySuccess
        if(!TextUtils.isEmpty(getTokenPreferences()))
            goEntrySuccess();

        return view;
    }

    private void  postRequestData() {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(getContext());

        String url = "https://www.axacoair.se/api/companion/signin";


        Map<String, String> params = new HashMap<String, String>();
        params.put("user", user.getText().toString());
        params.put("password", password.getText().toString());
        //params.put("user", "rafael+test@divundo.com");
        //params.put("password", "Sergio18");
        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String token = null;
                try {
                    //Take the token from the server answer
                    token = response.getString("token");

                    //Save the token
                    savePreferences(token);

                    goEntrySuccess();
                } catch (JSONException e) {
                    Toast.makeText(getContext(), R.string.JsonError,Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                Toast.makeText(getContext(), R.string.loginError,Toast.LENGTH_LONG).show();
            }
        });


        MyRequestQueue.add(jsonRequest);
    }

    private void savePreferences(String token) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("token", token);
        editor.commit();
        editor.apply();
    }

    private String getTokenPreferences() {
        return prefs.getString("token", "");
    }

    private void goEntrySuccess(){
        Fragment fragment = new EntrySuccessFragment();
        getActivity().getSupportFragmentManager().popBackStack();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }
}
