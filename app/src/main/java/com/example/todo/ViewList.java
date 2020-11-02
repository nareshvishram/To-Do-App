package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewList extends AppCompatActivity {
Bundle bundle;
public ListView listView;
int position;
String title_of_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        listView=(ListView)findViewById(R.id.l_list) ;

        bundle=getIntent().getExtras();
        ArrayList<String> title=bundle.getStringArrayList("title");
        ArrayList<String>desc=bundle.getStringArrayList("desc");
        int imageId[]=bundle.getIntArray("imageId");
        ArrayList<String> dates=bundle.getStringArrayList("dates");

        MyCustomListView myCustomListView = new MyCustomListView(getApplicationContext(), title, desc, imageId, dates);
         listView.setAdapter(myCustomListView);
          registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                title_of_listView=(String)parent.getItemAtPosition(position);
            }
        });
         /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                title_of_listView= (String) parent.getItemAtPosition(position);

                 Toast.makeText(ViewList.this, ""+title_of_listView, Toast.LENGTH_SHORT).show();
             }
         });*/
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater=getMenuInflater();
        //add menu resource
        menuInflater.inflate(R.menu.context_menu,menu);
        //giving an header to the context menu
       // menu.setHeaderIcon(R.drawable.icon);
        menu.setHeaderTitle("Select an operation");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.c_del:


                SqliteHelperClass sqliteHelperClass=new SqliteHelperClass(getApplicationContext());
                long l=sqliteHelperClass.delete_by_title(title_of_listView);
                if(l>0)
                Toast.makeText(this, "deleted successfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "Failed to delete : First click on the List view icon then select", Toast.LENGTH_LONG).show();
                break;

            case R.id.c_fullView:
                //getting data behalf of title (selected title of list View).
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        title_of_listView=(String) parent.getItemAtPosition(position);
                        //parent.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.lavender));
                    }
                });
                /*if(title_of_listView.equals(null))
                {
                    Toast.makeText(this, "Failed to delete : First click on the List view icon then select", Toast.LENGTH_LONG).show();
                }*/


                    SqliteHelperClass sqliteHelperClass1 = new SqliteHelperClass(getApplicationContext());
                    //Log.i("title of list view","title"+title_of_listView);
                    Cursor cursor = sqliteHelperClass1.fetch_data_withTitle(title_of_listView);
                    StringBuffer sb = new StringBuffer("");
                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            String title = cursor.getString(1);
                            String date = cursor.getString(2);
                            String description = cursor.getString(3);
                            sb.append("Title : " + title + "\n\n" + "Description : " + description + "\n\n" + "Date : " + date);
                        }
                    } else {
                        Toast.makeText(this, "No data found correspond to this title ", Toast.LENGTH_LONG).show();
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(ViewList.this, R.style.Theme_AppCompat_DayNight_Dialog);

                    builder.setIcon(R.drawable.icon).setTitle(title_of_listView).setMessage(sb.toString()).setCancelable(false).setPositiveButton("Stay here", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ViewList.this, "Best of luck :)", Toast.LENGTH_LONG).show();
                        }
                    });

                    builder.setNegativeButton("Go to form activity", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.create().show();
                    //Toast.makeText(this, "Showing items", Toast.LENGTH_LONG).show();

                break;

            case R.id.c_res:
                Toast.makeText(this, "Showing response", Toast.LENGTH_LONG).show();
                break;

                default:
                    Toast.makeText(this, "Select a item", Toast.LENGTH_LONG).show();
        }
        return super.onContextItemSelected(item);
    }
}
