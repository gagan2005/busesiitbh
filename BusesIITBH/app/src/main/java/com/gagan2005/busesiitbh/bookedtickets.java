package com.gagan2005.busesiitbh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class bookedtickets extends AppCompatActivity
{
    private RecyclerView r;
    private bookedhist a;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private ArrayList<Ticket> list;
    private ArrayList<Ticket2> list2;
    private bustrip b;
    private Ticket2 t;
    private ProgressBar pbb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
         db = FirebaseFirestore.getInstance();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookedtickets);
        pbb=(ProgressBar)findViewById(R.id.pbb);
        list = new ArrayList<Ticket>();
        list2=new ArrayList<Ticket2>();
        r = (RecyclerView) findViewById(R.id.bhh);
        a = new bookedhist(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        r.setLayoutManager(layoutManager);
        r.setAdapter(a);
        //db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser cuser = mAuth.getCurrentUser();
        db.collection("users").document(cuser.getEmail()).collection("tickets").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Ticket t = document.toObject(Ticket.class);
                        list.add(t);

                    }
                    nextt(0);
                }
            }
        });


    }

    public void nextt(int i)
    {
        if (i == list.size())
        {   pbb.setVisibility(View.INVISIBLE);
            a.setData(list2);
            a.notifyDataSetChanged();
            return;
        }
        final int k = i + 1;
        final Ticket t=list.get(i);
        db.collection("bustrips").document(list.get(i).id).get().addOnSuccessListener(
                new OnSuccessListener<DocumentSnapshot>()
                {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot)
                    {
                        b = documentSnapshot.toObject(bustrip.class);
                        list2.add(new Ticket2(t.id, t.list, b));
                        nextt(k);

                    }

                });

    }


}






