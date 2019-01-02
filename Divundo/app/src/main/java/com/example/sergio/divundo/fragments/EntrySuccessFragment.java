package com.example.sergio.divundo.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.example.sergio.divundo.adapters.DataEntrySuccessAdapter;
import com.example.sergio.divundo.models.PicText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class EntrySuccessFragment extends Fragment {

    private SharedPreferences prefs;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DataEntrySuccessAdapter adapter;

    private String token;
    private List<PicText> picText;

    public EntrySuccessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entry_success, container, false);
        setHasOptionsMenu(true);

        //Get the token from the shared Preferences
        prefs = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        token = getTokenPreferences();

        getRequestData();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.logout, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
                removeSharedPreferences();
                logOut();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String getTokenPreferences() {
        return prefs.getString("token", "");
    }

    private void removeSharedPreferences () {
        prefs.edit().clear().apply();
    }

    private void logOut() {
        Fragment fragment = new LogInFragment();
        getActivity().getSupportFragmentManager().popBackStack();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }

    private void getRequestData() {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(getContext());

        Map<String, String> params = new HashMap<String, String>();
        //Add the data you'd like to send to the server.
        params.put("user","rafael+test@divundo.com");
        params.put("customer","");
        String url = "https://www.axacoair.se/api/companion/Bookings";

        String getUrl = createGetWithParams(url, params);

        StringRequest MyStringRequest = new StringRequest(Request.Method.GET, getUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Add the data from the server
                picText = new ArrayList<PicText>();
                final PicText serverPicText = new PicText("Happy New Year!", "https://cdn.pixabay.com/photo/2017/01/04/21/00/new-years-eve-1953253_960_720.jpg");
                picText.add(serverPicText);

                makeCardView(picText);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                Toast.makeText(getContext(), R.string.getError, Toast.LENGTH_LONG).show();
            }
        }) {
            /** Passing some request headers **/
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }

    private String createGetWithParams(String url, Map<String, String> params)
    {
        StringBuilder builder = new StringBuilder();
        for (String key : params.keySet())
        {
            Object value = params.get(key);
            if (value != null)
            {
                if (builder.length() > 0)
                    builder.append("&");
                builder.append(key).append("=").append(value);
            }
        }

        return (url += "?" + builder.toString());
    }


    private void makeCardView(List<PicText> picText) {
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new DataEntrySuccessAdapter(picText, R.layout.recyclerview_pic_text, getActivity(), new DataEntrySuccessAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PicText picText, int position) {
                Toast.makeText(getContext(), picText.getText(), Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
