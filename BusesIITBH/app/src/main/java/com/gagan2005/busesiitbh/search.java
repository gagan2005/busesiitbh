package com.gagan2005.busesiitbh;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class search extends AppCompatActivity
{   private RecyclerView r;

private Searchresult adaptor;
    private Calendar calendar;
    private TextView day;
    private TextView date;
    private FirebaseFirestore databse;
    //private ArrayList<bustrip> list;
    private ArrayList<map> list;



    private Spinner from;
    private Spinner to;
    private CardView timein;
    private TextView hourin,minutein;
    public Button search_button;
    public ProgressBar pb;
    private TextView nores;

    private int chour,cminute;
    private int cyear,cmonth,cday;


    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return super.onOptionsItemSelected(item) || t.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        r=(RecyclerView)findViewById(R.id.s);
        adaptor=new Searchresult(getApplicationContext());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        r.setLayoutManager(layoutManager);
        r.setAdapter(adaptor);




        dl = (DrawerLayout)findViewById(R.id.drawer);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        t.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(t);
        t.syncState();

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        nv = (NavigationView)findViewById(R.id.nav);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(

        )
        {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int id = item.getItemId();
                if(id==R.id.home)dl.openDrawer(Gravity.END);
                if (id == R.id.s) {
                    Intent it = new Intent(getApplicationContext(), search.class);
                    startActivity(it);
                } else if (id == R.id.bh) {
                    Intent it = new Intent(getApplicationContext(), bookedtickets.class);
                    startActivity(it);
                } else if (id == R.id.about) {
                    Toast.makeText(search.this, "Developed by \n Gagan deep Singh \n www.github.com/gagan2005/busesiitbh", Toast.LENGTH_SHORT).show();
                }
                return true;

            }
        });


//(new datagenerator()).generate();
        search_button=(Button)findViewById(R.id.button4);
       pb=(ProgressBar)findViewById(R.id.pb);
        from=(Spinner)findViewById(R.id.from);
        to=(Spinner)findViewById(R.id.to);
        day=(TextView)findViewById(R.id.day);
        date=(TextView)findViewById(R.id.date);
        timein=(CardView) findViewById(R.id.timein) ;
        hourin=(TextView)findViewById(R.id.hour) ;
        minutein=(TextView)findViewById(R.id.minute) ;
        Calendar cal=Calendar.getInstance();
        nores=(TextView)findViewById(R.id.nores);
        chour=8;
        cminute=30;
        setday();







        List<String> items=new ArrayList<String>();

        items.add("IIT BHILAI");
        items.add("Raipur Railway Station");
        items.add("Airport");
        items.add("Raipur City");

        ArrayAdapter<String> data=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,items);
        from.setAdapter(data);
        to.setAdapter(data);


        findViewById(R.id.timein2).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DatePickerDialog dp=new DatePickerDialog(search.this, new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        cyear=year;
                        cmonth=month;
                        cday=dayOfMonth;
                        view.setMinDate(System.currentTimeMillis()-1000);
                        Calendar cal=Calendar.getInstance();
                        cal.set(cyear,cmonth,cday);
                        setday(cal);



                       // day.setText(Integer.toString(cday));



                    }
                },cyear,cmonth,cday);
                dp.getDatePicker().setMinDate(new Date().getTime());
                dp.show();


            }
        });

        timein.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TimePickerDialog tp;
                tp = new TimePickerDialog(search.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                    {
                        cminute=minute;
                        chour=hourOfDay;

                        String hh=new String();
                        String mm=new String();
                        if(chour<=9)hh="0"+Integer.toString(chour);
                        else hh=Integer.toString(chour);
                        hh=hh+":";
                        if(cminute<=9)mm="0"+Integer.toString(cminute);
                        else mm=Integer.toString(cminute);
                        minutein.setText(mm);
                        hourin.setText(hh);

                    }
                }, 8, 30, true);
                tp.show();


            }
        });

      // (new datagenerator()).generate();



    }

    public void Search(View v)
    {
        search_button.setVisibility(View.INVISIBLE);
        pb.setVisibility(View.VISIBLE);
        list=new ArrayList<map>();
        bustrip b=new bustrip();
        String fromm=from.getSelectedItem().toString();
        if(fromm=="IIT BHILAI")b.from="iitbh";
        else if(fromm=="Raipur Railway Station")b.from="rstat";
        else if(fromm=="Airport")b.from="rair";
        else if(fromm=="Raipur City")b.from="rcit";
        String tom=to.getSelectedItem().toString();
        if(tom=="IIT BHILAI")b.to="iitbh";
        else if(tom=="Raipur Railway Station")b.to="rstat";
        else if(tom=="Airport")b.to="rair";
        else if(tom=="Raipur City")b.to="rcit";
        b.time_in_minutes=(chour*60+cminute);
        b.setDatee(cday,cmonth,cyear);

        databse=FirebaseFirestore.getInstance();
        databse.collection("bustrips")
                .whereEqualTo("date", b.date).whereEqualTo("from",b.from)
                .whereEqualTo("to",b.to).
                whereGreaterThanOrEqualTo("time_in_minutes",b.time_in_minutes-120).
                whereLessThanOrEqualTo("time_in_minutes",b.time_in_minutes+120)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                bustrip b1=document.toObject(bustrip.class);

                                Log.d("search",b1.date);
                                list.add(new map(document.getReference().getId(),b1));
                               // Log.d("time",(b1.time_in_minutes));



                            }


                        } else
                            {

                        }
                        search_button.setVisibility(View.VISIBLE);
                        pb.setVisibility(View.INVISIBLE);
                        Log.d("size",Integer.toString(list.size()));
                        adaptor.newdata(list);
                        adaptor.notifyDataSetChanged();
                        if(list.size()==0)nores.setVisibility(View.VISIBLE);
                        else nores.setVisibility(View.INVISIBLE);


                    }

                });
    }

    public void setday()
    {
        Calendar cal=Calendar.getInstance();
        setday(cal);

    }

    public void setday(Calendar cal)
    {
        cyear=cal.get(Calendar.YEAR);
        cday=cal.get(Calendar.DATE);
        cmonth=cal.get(Calendar.MONTH);
        SimpleDateFormat dd=new SimpleDateFormat("dd");
        day.setText(Integer.toString(cday));
        int weekday=cal.get(Calendar.DAY_OF_WEEK);

        String week=new String();
        if(weekday==2)week="Mon";
        if(weekday==3)week="Tue";
        if(weekday==4)week="Wed";
        if(weekday==5)week="Thu";
        if(weekday==6)week="Fri";
        if(weekday==7)week="Sat";
        if(weekday==1)week="Sun";

        int monthh=cal.get(Calendar.MONTH);
        String smonth=new String();
        if(monthh==0)smonth="January";
        if(monthh==1)smonth="February";if(monthh==2)smonth="March";
        if(monthh==3)smonth="April";
        if(monthh==4)smonth="May";
        if(monthh==5)smonth="June";if(monthh==6)smonth="July";
        if(monthh==7)smonth="August";
        if(monthh==8)smonth="September";
        if(monthh==9)smonth="October";
        if(monthh==10)smonth="November";
        if(monthh==11)smonth="December";

        date.setText(week+" \n"+smonth+" "+cal.get(Calendar.DATE) );





    }



}

class  map
{
    public String id;
    public bustrip b1;

    public map(String id,bustrip b1)
    {
        this.b1=b1;
        this.id=id;
    }
}