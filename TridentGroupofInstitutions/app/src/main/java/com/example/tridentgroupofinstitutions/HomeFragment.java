package com.example.tridentgroupofinstitutions;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.card.MaterialCardView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {

    TextView username;
    TextView regd_no;
    TextView phonenumberr;
    Button loginbtn;
    MaterialCardView viewProfile;
    MaterialCardView accntDetails;
    MaterialCardView Checkattendance;
    MaterialCardView semesterresult;
    MaterialCardView librarydetails;
    MaterialCardView carerdetails;
    Context context;

    public HomeFragment() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = view.getContext();

        username = view.findViewById(R.id.name);
        accntDetails=view.findViewById(R.id.accntdetails);
        viewProfile=view.findViewById(R.id.viewprofile);
        Checkattendance=view.findViewById(R.id.attddetails);
        semesterresult=view.findViewById(R.id.semres);
        carerdetails=view.findViewById(R.id.careerdetails);
        librarydetails=view.findViewById(R.id.libdetails);
        /*EnterPhoneActivity activity = (EnterPhoneActivity)getActivity();
        String phonenumber = activity.getPhoneNumber();
        String url = "https://raw.githubusercontent.com/imvivekpandey/Json/main/db2.json";
        String key = "phno=";

        String finalurl = url+key+phonenumber;*/
        /*JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
        url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d("res", response.toString());

                //parse
                try {
                    JSONObject objresult = response.getJSONObject(0);
                    String namee = objresult.getString("regdno").toString();

                    username.setText(namee);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });*/

        getJsonData();
        accntDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AccountDetailsActivity.class);
                startActivity(intent);
            }
        });
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ViewProfileActivity.class);
                startActivity(intent);
            }
        });
        semesterresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SemesterResultsActivity.class);
                startActivity(intent);
            }
        });
        Checkattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AttendanceDetailsActivity.class);
                startActivity(intent);
            }
        });
        librarydetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,LibraryDetailsActivity.class);
                startActivity(intent);
            }
        });
        carerdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,CareerDetailsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void getJsonData() {
      /*  EnterPhoneActivity activity = (EnterPhoneActivity)getActivity();
        String phonenumber = activity.getPhoneNumber();*/
        String url = "https://adityadas.000webhostapp.com/validate.php?phno=";
      /*  Log.d(TAG, "phonenumber" +phonenumber);;
        String finalurl = url+phonenumber;
        System.out.println("heelo"+phonenumber);*/
        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject objresult = response.getJSONObject(0);

                    String namee = objresult.getString("name");
                    String regdno = objresult.getString("regdno");
                    String phone = objresult.getString("phone_number");


                    username.setText("Name"+namee);
                    regd_no.setText("Regd no"+regdno);
                    phonenumberr.setText("Phone Number"+phone);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getMessage());
            }
        });
        requestQueue.add(arrayRequest);
    }
}

