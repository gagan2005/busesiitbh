package com.gagan2005.busesiitbh;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class bookedhist extends RecyclerView.Adapter<histholder>
{
    public ArrayList<Ticket2> list1;
    public int n;
    private Context context;


            public void setData(ArrayList<Ticket2> list1)
            {
                this.list1=list1;

                n=this.list1.size();

            }
    public bookedhist(Context context)
    {   this.context=context;
        ArrayList<Ticket2> list1=new ArrayList<Ticket2>();
        n=0;

    }

    @NonNull
    @Override
    public histholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.one_booking,viewGroup,false);
        histholder V=new histholder(v);

        return V;
    }

    @Override
    public void onBindViewHolder(@NonNull histholder histholder, int i)
    {
        Log.d("histholder bind","hb");
        histholder.busnno.setText(Integer.toString(list1.get(i).b.bus.busno));
       // histholder.dob.setText(list1.get(i).getDate().toString());
        histholder.doj.setText(list1.get(i).b.date);
        histholder.toj.setText(list1.get(i).b.getTimeString());
        histholder.viewE.setOnClickListener(new viewTicket(context,list1.get(i)));


    }



    @Override
    public int getItemCount()
    {
        if(list1!=null)
        return list1.size();
        return 0;
    }
}

class histholder extends RecyclerView.ViewHolder
{
    public TextView busnno;
    public TextView dob;
    public TextView toj;
    public TextView doj;
    public Button viewE;

    public histholder(View view)
    {
        super(view);
        busnno=(TextView) view.findViewById(R.id.busno);
        //dob=(TextView) view.findViewById(R.id.dob);
        toj=(TextView) view.findViewById(R.id.toj);
        doj=(TextView) view.findViewById(R.id.doj);
        viewE=(Button) view.findViewById(R.id.viewE);
    }

}

class viewTicket implements View.OnClickListener
{
    private Context context;
    private Ticket2 ticket2;
    public viewTicket(Context context,Ticket2 ticket2)
    {
        this.context=context;
        this.ticket2=ticket2;
    }
    @Override
    public void onClick(View v)
    {
        Intent it=new Intent(context,eticket.class);
        Gson gson=new Gson();
        String jsonn=gson.toJson(ticket2);
        it.putExtra("jsonn",jsonn);
        context.startActivity(it);
    }
}
