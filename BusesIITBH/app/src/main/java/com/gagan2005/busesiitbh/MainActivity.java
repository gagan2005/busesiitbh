package com.gagan2005.busesiitbh;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity
{
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
       mAuth=FirebaseAuth.getInstance();


        //setContentView(R.layout.activity_main);

    }
    @Override
    public void onStart()
    {

        super.onStart();
        FirebaseUser cur=mAuth.getCurrentUser();

        if(cur!=null) {


            Intent it = new Intent(this, search.class);
            startActivity(it);
            finish();
        }
        else {
            Intent it = new Intent(this, signup.class);
            startActivity(it);
            finish();
        }



    }
}
