package com.gagan2005.busesiitbh;

import android.util.Log;

import java.util.Date;

public class bustrip
{
    public Bus bus;
    public String from;
    public String to;
    public String date;

    public int time_in_minutes;




    public String getFrom()
    {
        return from;
    }


    public bustrip(Bus bus, String from, String to, String date, int time_in_minutes)
    {
        this.bus=bus;
        this.from=from;
        this.to=to;
        this.date=date;
        this.time_in_minutes=time_in_minutes;

    }

    public void setDatee(int cday,int cmonth,int cyear)
    {
        String mcday=new String();
        String mcmonth=new String();
        if(cday<=9)mcday="0"+Integer.toString(cday);
        else mcday=Integer.toString(cday);
        if(cmonth<=8)mcmonth="0"+(Integer.toString(cmonth+1));
        else
            mcmonth=Integer.toString(cmonth+1);
        this.date=mcday+"/"+mcmonth+"/"+Integer.toString(cyear);
        Log.d("date",this.date);
    }

    public bustrip()
    {

    }

    public void decreaseseats(int n)
    {
        bus.availseat=bus.availseat-n;
    }

    public String getTimeString()
    {   int chour=time_in_minutes/60;
        int cminute=time_in_minutes%60;
        String hh=new String();
        String mm=new String();
        if(chour<=9)hh="0"+Integer.toString(chour);
        else hh=Integer.toString(chour);
        hh=hh+" :";
        if(cminute<=9)mm="0"+Integer.toString(cminute);
        else mm=Integer.toString(cminute);
        return hh+mm;
    }

    public String getFromString()
    {
        String code=from;
        if(code.equals("iitbh"))return "IIT BHILAI";
        else if(code.equals("rstat"))return "Raipur Railway";
        else if(code.equals("rair"))return "Airport";
        else if(code.equals("rcit"))return "Raipur City";
        else return code;
    }


    public String getToString()
    {
        String code=to;
        if(code.equals("iitbh"))return "IIT BHILAI";
        else if(code.equals("rstat"))return "Raipur Railway";
        else if(code.equals("rair"))return "Airport";
        else if(code.equals("rcit"))return "Raipur City";
        else return code;
    }



}
