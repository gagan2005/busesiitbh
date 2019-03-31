package com.gagan2005.busesiitbh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

public class eticket extends AppCompatActivity
{

    private TextView tid;
    private TextView fr;
    private TextView to;
    private TextView tojE;
    private TextView dojE;
    private TextView DobE;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eticket);
        tid=(TextView)findViewById(R.id.tid);
        fr=(TextView)findViewById(R.id.fr);
        to=(TextView)findViewById(R.id.to2);
        tojE=(TextView)findViewById(R.id.tojE);
        dojE=(TextView)findViewById(R.id.textView10);

       // DobE=(TextView)findViewById(R.id.DobE);
        Gson gson=new Gson();
        Ticket2 et=gson.fromJson(getIntent().getStringExtra("jsonn"), Ticket2.class);

        tid.setText(et.id);

        fr.setText(et.b.getFromString());
        to.setText(et.b.getToString());
        tojE.setText(et.b.getTimeString());
        dojE.setText(et.b.date);

        LinearLayout ll=(LinearLayout)findViewById(R.id.ll);

        for(int i=0;i<et.list.size();i++)
        {
           TextView v=new TextView(this);
          v.setPadding(4,4,4,4);

           //v.setTextColor(getResources().getColor(R.color.black_alpha_80));
           v.setTextSize(12);
           v.setTextAppearance(this,R.style.Genius_Widget_TextView);


           v.setText("Passenger "+Integer.toString(i+1)+ "\n" +"Name-"+et.list.get(i).name + "  "+"Roll no-"+et.list.get(i).rno);
           ll.addView(v);
        }


    }
}
