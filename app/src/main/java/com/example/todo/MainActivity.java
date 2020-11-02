package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText t_title,t_desc,t_dates,deleting_title;
Button btn,viewList,del;
ListView listView;
ImageView iconImage;
ArrayList<String >title=new ArrayList<>();//={"Task"};
ArrayList<String>dates=new ArrayList<>();//={"07/03/2020"};
ArrayList<String> desc=new ArrayList<>();//={"I need to complete this : Greedy, D.P., Android project : To do list"};
int imageId[]={R.drawable.list1,R.drawable.list2,R.drawable.l1,R.drawable.l2,R.drawable.l3,R.drawable.l4,R.drawable.l5,R.drawable.l6,R.drawable.l7,R.drawable.l8,R.drawable.l9};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.l_submit);
        viewList=(Button)findViewById(R.id.l_viewList);
        iconImage=(ImageView)findViewById(R.id.l_iconimg);
        iconImage.setImageResource(R.drawable.icon1);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t_title = (EditText) findViewById(R.id.l_title);
                t_desc = (EditText) findViewById(R.id.l_desc);
                t_dates = (EditText) findViewById(R.id.l_dates);
               // listView = (ListView) findViewById(R.id.l_list);
                String Title = t_title.getText().toString();
                String Desc = t_desc.getText().toString();
                String Date = t_dates.getText().toString();
                //title.add(Title);
                //desc.add(Desc);
                //dates.add(Date);
                //Toast.makeText(MainActivity.this, "" + t_title.getText().toString(), Toast.LENGTH_SHORT).show();
                if(Title.length()==0||Desc.length()==0||Date.length()==0)
                {
                    Toast.makeText(MainActivity.this, "Enter Valid Title,Description or Date", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SqliteHelperClass sqliteHelperClass = new SqliteHelperClass(getApplicationContext());
                    long l = sqliteHelperClass.insert_data(Title, Date, Desc);
                    if (l > 0)
                        Toast.makeText(MainActivity.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();

                }


            }
        });

         //data fetching from data base
        SqliteHelperClass sqliteHelperClass = new SqliteHelperClass(getApplicationContext());
        Cursor cursor = sqliteHelperClass.fetch_data();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                title.add(cursor.getString(1));
                desc.add(cursor.getString(2));
                dates.add(cursor.getString(3));
            }
        } else {
            Toast.makeText(MainActivity.this, "No data Found", Toast.LENGTH_SHORT).show();
        }

        //viewList Listener
       viewList.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {



               Intent intent=new Intent(MainActivity.this,ViewList.class);
               intent.putExtra("title",title);
               intent.putExtra("desc",desc);
               intent.putExtra("imageId",imageId);
               intent.putExtra("dates",dates);
               startActivity(intent);
               //MyCustomListView myCustomListView = new MyCustomListView(getApplicationContext(), title, desc, imageId, dates);
              // listView.setAdapter(myCustomListView);
           }
       });

        //delete facilities
       /*del=(Button)findViewById(R.id.l_delete);
       del.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               deleting_title=(EditText)findViewById(R.id.l_tileToDel);
               String del_title=deleting_title.getText().toString();
               SqliteHelperClass sqliteHelperClass=new SqliteHelperClass(getApplicationContext());
               long l=sqliteHelperClass.delete_by_title(del_title);
               if(l>0)
                   Toast.makeText(MainActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(MainActivity.this, "There is no title corresponding to this ", Toast.LENGTH_SHORT).show();
           }
       });*/
    }
}
