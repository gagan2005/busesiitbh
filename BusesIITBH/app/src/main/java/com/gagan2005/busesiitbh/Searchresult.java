package com.gagan2005.busesiitbh;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Searchresult extends RecyclerView.Adapter<Resultholder>
{
    public ArrayList<map> bustrips;
    public int n;
    private Context context;

    public Searchresult(Context context)
    {
         n=0;
         bustrips=new ArrayList<map>();
         this.context=context;
    }

    public void newdata(ArrayList<map> b)
    {
        bustrips=b;
        n=bustrips.size();
    }
    @NonNull
    @Override
    public Resultholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.one_trip,viewGroup,false);
        Resultholder V=new Resultholder(v);

        return V;

    }

    @Override
    public void onBindViewHolder(@NonNull Resultholder resultholder, int i)
    {
        resultholder.Date.setText(bustrips.get(i).b1.date);
        resultholder.time.setText((bustrips.get(i).b1.getTimeString()));
        resultholder.no_of_seats.setText("Available Seats\n"+Integer.toString(bustrips.get(i).b1.bus.getAvailseat()));
        resultholder.busno.setText(Integer.toString(bustrips.get(i).b1.bus.busno));
        resultholder.book.setOnClickListener(new BookNow(bustrips.get(i).b1,context,bustrips.get(i).id));

    }

    @Override
    public int getItemCount()
    {
        return bustrips.size();
    }
}

 class Resultholder extends RecyclerView.ViewHolder
{
    public TextView no_of_seats;
    public TextView busno;
    public TextView time;
    public TextView Date;
    public Button book;


    public Resultholder(View v)
    {

        super(v);
        busno=(TextView)v.findViewById(R.id.busno);
        no_of_seats=(TextView)v.findViewById(R.id.avail);
        time=(TextView)v.findViewById(R.id.toj);
        Date=(TextView)v.findViewById(R.id.doj);
        book=(Button)v.findViewById(R.id.button5);



    }
}

    class BookNow implements View.OnClickListener
    {
        private bustrip b;private Context context;
        private String id;
        public BookNow(bustrip b,Context context,String id)
        {
            this.b=b;
            this.context=context;
            this.id=id;
        }
        @Override
        public void onClick(View v)
        {
            Intent it=new Intent(context,passenger.class);
            Gson gson=new Gson();
            String jsonn=gson.toJson(b);
            it.putExtra("jsonn",jsonn);
            it.putExtra("id",id);
            context.startActivity(it);

        }
    }


