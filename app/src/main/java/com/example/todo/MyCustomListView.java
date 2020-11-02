package com.example.todo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;
public class MyCustomListView extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> title=new ArrayList<>();
    ArrayList<String> desc=new ArrayList<>();
    int imageId[];
    ArrayList<String> dates=new ArrayList<>();
    public MyCustomListView(Context context, ArrayList<String> title,ArrayList<String> desc,int imageId[],ArrayList<String> dates) {
        super(context,R.layout.custom_list_view,title);
        this.context=context;
        this.title=title;
        this.desc=desc;
        this.imageId=imageId;
        this.dates=dates;

    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View root=layoutInflater.inflate(R.layout.custom_list_view,null,true);


        ImageView imges = (ImageView) root.findViewById(R.id.l_img);
        TextView tittle = (TextView) root.findViewById(R.id.l_title);
        TextView dattes = (TextView) root.findViewById(R.id.l_date);
        TextView dessc = (TextView) root.findViewById(R.id.l_desc);

        //
        //
        if(position%2==0) {
             imges = (ImageView) root.findViewById(R.id.l_img);
             tittle = (TextView) root.findViewById(R.id.l_title);
             dattes = (TextView) root.findViewById(R.id.l_date);
             dessc = (TextView) root.findViewById(R.id.l_desc);
            // root.setBackground(Color.parseColor("#FFB6B546"));
            //view.setBackgroundColor(Color.parseColor("#FFB6B546"));

            dattes.setTextColor(Color.LTGRAY);
            tittle.setTextColor(Color.LTGRAY);
            dessc.setTextColor(Color.LTGRAY);
            //dattes.setTextColor(R.color.secon);
        }
        else
        {
            imges = (ImageView) root.findViewById(R.id.l_img);
            tittle = (TextView) root.findViewById(R.id.l_title);
            dattes = (TextView) root.findViewById(R.id.l_date);
            dessc = (TextView) root.findViewById(R.id.l_desc);

        }
        Random random=new Random();
        int i=random.nextInt(imageId.length);

       imges.setImageResource(imageId[i]);
       tittle.setText("Title : "+title.get(position));
       dattes.setText("Description : "+dates.get(position));
       dessc.setText("Date : "+desc.get(position));
        return root;
    }
}
