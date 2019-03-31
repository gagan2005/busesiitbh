package com.gagan2005.busesiitbh;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class datagenerator
{
    public final  int totalbuses = 6;
    public final  String dest[] = {"iitbh", "rstat", "rair", "rcit"};
    public  int seats_in_each_bus = 40;
    public  int bus_starting_time=8*60+30;
    //8:30 am

    private FirebaseFirestore database;

    public  void generate()
    {
        database = FirebaseFirestore.getInstance();

        for (int i = 0; i < 60; i++) {


            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, i);
            if(cal.get(Calendar.DAY_OF_WEEK)!=1 && cal.get(Calendar.DAY_OF_WEEK)!=7 )continue;
            SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
            String dd = d.format(cal.getTime());
            for (int k = 0; k < 3; k++) {
                for (int m = 1; m <= 3; m++) {
                    bustrip go = new bustrip(new Bus(2 * k + 1), dest[0], dest[k + 1], dd, (m - 1) * 60 * 4 + bus_starting_time);
                    bustrip ret = new bustrip(new Bus(2 * k + 1), dest[k+1],dest[0], dd, (m-1)* 60*4 + bus_starting_time +  120);

                    addToDatabase(go);
                    addToDatabase(ret);

                     go = new bustrip(new Bus(2 * k + 2), dest[0], dest[k + 1], dd, (m - 1) * 60 * 4 + bus_starting_time+60);
                    ret = new bustrip(new Bus(2 * k + 2), dest[0], dest[k + 1], dd, (m-1)* 60*4 + bus_starting_time +  180);

                    addToDatabase(go);
                    addToDatabase(ret);
                }

            }





            }


        }


    private  void addToDatabase(bustrip b)
    {
        database.collection("bustrips").add(b).addOnSuccessListener(new OnSuccessListener<DocumentReference>()
        {
            @Override
            public void onSuccess(DocumentReference documentReference)
            {
                Log.d("add","succesful");
            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                Log.d("add","unsuccesful");
            }
        });

    }
}

