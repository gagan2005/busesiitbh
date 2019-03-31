package com.gagan2005.busesiitbh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PassengerAdaptor extends RecyclerView.Adapter<Viewholder>
{
    public ArrayList<Integer> list;
    private int k;



    public PassengerAdaptor()
    {
        super();

      list=new ArrayList<Integer>();
      k=0;


    }

    public void addpassenger()
    {

        list.add(k++);
        Log.d("SIZE",Integer.toString(list.size()));
    }
    public int remove(int i)
    {
       int ind= list.indexOf(new Integer(i));
        list.remove(new Integer(i));
        return ind;

    }


    @Override
    public int getItemCount()
    {
        return list.size();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.one_passenger,viewGroup,false);
        Viewholder V=new Viewholder(v,i);

        return V;

    }


    public void onBindViewHolder(Viewholder viewHolder, int i)
    {



            viewHolder.add.setVisibility(View.VISIBLE);
            viewHolder.rem.setVisibility(View.VISIBLE);
            viewHolder.rem.setOnClickListener(new OnRemoveClick(list.get(i).intValue(),this));


        }
    }

class Passengerinfo
{
    public String name;
    public int rno;
    public Passengerinfo(String name,int rno)
    {
        this.name=name;
        this.rno=rno;
    }

    public Passengerinfo()
    {
        name=new String();

    }
}
class Viewholder extends RecyclerView.ViewHolder
{
    public EditText name;
    public EditText rno;
    public TextView pn;
    public Button add;
    public Button rem;
    public boolean islast;
    public int i;




    public Viewholder(View v,int i)
    {
        super(v);
        this.i=i;
        islast=false;
        name=(EditText)v.findViewById(R.id.name);
        pn=(TextView)v.findViewById(R.id.number);
        rno=(EditText)v.findViewById(R.id.id);

        add=(Button)v.findViewById(R.id.button3);
        rem=(Button)v.findViewById(R.id.button2);



    }
}

class OnRemoveClick implements View.OnClickListener
{
    private int i;
    private PassengerAdaptor pa;
    public OnRemoveClick(int i,PassengerAdaptor context){this.i=i;this.pa=context;}
    @Override
    public void onClick(View v)
    {
        if(pa.getItemCount()!=1)
        {
            int k=pa.remove(i);
            pa.notifyItemRemoved(k);
            return;
        }

            View s=(View)(v.getParent());
        s.findViewById(R.id.layout);
            Snackbar.make(s, "Atleast one passenger should be there", Snackbar.LENGTH_SHORT).show();
        //pa.notifyDataSetChanged();

    }
}
