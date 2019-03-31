package com.gagan2005.busesiitbh;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.paytm.pgsdk.PaytmPGService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class passenger extends AppCompatActivity
{
 private RecyclerView r;
 private PassengerAdaptor a;
 private int n;
 private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        n=0;
        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        r=(RecyclerView)findViewById(R.id.rv);
        a=new PassengerAdaptor();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        r.setLayoutManager(layoutManager);
        r.setAdapter(a);
        r.setItemAnimator(new DefaultItemAnimator());
        a.addpassenger();
        a.notifyItemInserted(n++);


    }

    public void addp(View v)
    {
        a.addpassenger();

        a.notifyItemInserted(n++);
    }

    public void book(View v)
    {
        PaytmPGService Service = PaytmPGService.getStagingService();
        
        ArrayList<Passengerinfo> list=new ArrayList<Passengerinfo>();
        for(int i=0;i<a.getItemCount();i++) {
            Viewholder vh = (Viewholder) r.findViewHolderForAdapterPosition(i);

            if (vh.name.getText().toString().length() >= 3) {
                Passengerinfo pi = new Passengerinfo();
                pi.name = vh.name.getText().toString();
                if ((vh.rno.getText().toString()).length() == 8) {
                    pi.rno = Integer.parseInt(vh.rno.getText().toString());
                } else {
                    Snackbar.make((ConstraintLayout) findViewById(R.id.layout_passenger), "Invalid Roll number", Snackbar.LENGTH_LONG).show();
                    return;
                }
                list.add(pi);

            } else {
                Snackbar.make((ConstraintLayout) findViewById(R.id.layout_passenger), "Please enter correct name", Snackbar.LENGTH_LONG).show();
                return;
            }
            Gson gson = new Gson();
            bustrip b = gson.fromJson(getIntent().getStringExtra("jsonn"), bustrip.class);
            String id=getIntent().getStringExtra("id");
            FirebaseUser currentUser = mAuth.getCurrentUser();
            String email=new String();
            email = "h";
            try {


            email = currentUser.getEmail();
        }
            catch (NullPointerException n)
            {

            }
            Timestamp t=new Timestamp(new Date().getTime());
           DocumentReference dr=db.collection("users").document(email).collection("tickets").document();
           Ticket tt=new Ticket(id,list);
           dr.set(tt);

            b.decreaseseats(list.size());
            db.collection("bustrips").document(id).set(b);

            Intent it=new Intent(this,eticket.class);
            gson=new Gson();
            String jsonn=gson.toJson(new Ticket2(dr.getId(),list,b));
            it.putExtra("jsonn",jsonn);
            startActivity(it);
            finish();





        }
    }


}
