package com.gagan2005.busesiitbh;

import com.google.firebase.firestore.ServerTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Ticket
{
    public String id;
   public ArrayList<Passengerinfo> list=new ArrayList<Passengerinfo>();
   @ServerTimestamp
   private Date date;

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Date getDate()
    {
        return date;
    }


    public Ticket(String id, ArrayList<Passengerinfo> list)
    {
            this.id=id;
            this.list=list;

    }
public Ticket()
{

}
    public void print()
    {

    }
}

