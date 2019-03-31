package com.gagan2005.busesiitbh;

import com.gagan2005.busesiitbh.Ticket;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Ticket2 extends Ticket
{
    public bustrip b;
    public Ticket2(String id, ArrayList<Passengerinfo> list,bustrip b)
    {
        super(id, list);
        this.b=b;
    }
    public Ticket2()
    {

    }
}
