package com.example.tridentgroupofinstitutions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AttendanceDetailsActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener{


    private Spinner spinner;

    //An ArrayList for Spinner Items
    private ArrayList<String> semesternumber;

    //JSON Array
    private JSONArray semester;

    //TextViews to display details
    private TextView textViewslno;
    private TextView textViewsubject;
    private TextView textViewclassheld;
    private TextView textViewclassattended;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_details);
        getSupportActionBar().hide();

        spinner = (Spinner) findViewById(R.id.spinner);

        semesternumber=new ArrayList<String>();

        spinner.setOnItemSelectedListener(this);

        textViewslno = (TextView) findViewById(R.id.textserialno);
        textViewsubject = (TextView) findViewById(R.id.textViewsubject);
        textViewclassheld = (TextView) findViewById(R.id.textViewclassheld);
        textViewclassattended = (TextView) findViewById(R.id.textViewclassattended);

        getData();
    }

    private void getData(){
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Config.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            semester = j.getJSONArray(Config.JSON_ARRAY);

                            //Calling method getStudents to get the students from the JSON Array
                            getSemester(semester);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getSemester(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                semesternumber.add(json.getString(Config.TAG_SEMESTER));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spinner.setAdapter(new ArrayAdapter<String>(AttendanceDetailsActivity.this,
                android.R.layout.simple_spinner_dropdown_item, semesternumber));
    }

    //Method to get student name of a particular position
    private String getSlno(int position){
        String serial="";
        try {
            //Getting object of given index
            JSONObject json = semester.getJSONObject(position);

            //Fetching name from that object
            serial = json.getString(Config.TAG_SERIALNO);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return serial;
    }

    //Doing the same with this method as we did with getName()
    private String getSubject(int position){
        String subject="";
        try {
            JSONObject json = semester.getJSONObject(position);
            subject = json.getString(Config.TAG_SUBJECT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return subject;
    }

    //Doing the same with this method as we did with getName()
    private String getClassHeld(int position){
        String classheld="";
        try {
            JSONObject json = semester.getJSONObject(position);
            classheld = json.getString(Config.TAG_CLASSHELD);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return classheld;
    }

    private String getClassAttended(int position){
        String classattended="";
        try {
            JSONObject json = semester.getJSONObject(position);
            classattended = json.getString(Config.TAG_CLASSATTENDED);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return classattended;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        textViewslno.setText(getSlno(i));
        textViewsubject.setText(getSubject(i));
        textViewclassheld.setText(getClassHeld(i));
        textViewclassattended.setText(getClassAttended(i));

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        textViewslno.setText("");
        textViewsubject.setText("");
        textViewclassattended.setText("");
        textViewclassheld.setText("");

    }
}