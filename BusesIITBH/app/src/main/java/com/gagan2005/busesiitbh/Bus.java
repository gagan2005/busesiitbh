package com.gagan2005.busesiitbh;

public class Bus
{
    public int busno;
    public int availseat;

    public Bus(int i)
    {   this.busno=i;
        availseat=40;
    }
    public Bus()
    {

    }

    public void Book(int n)
    {
        availseat=availseat-n;
    }

    public int getAvailseat()
    {
        return availseat;
    }
}
