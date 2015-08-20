package com.emdsys.android.gmailopenpage;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by EMD029 on 8/11/2015.
 */
public class ListviewAdapter extends BaseAdapter implements View.OnClickListener{
    ArrayList<ListData> arrayList=new ArrayList<ListData>();
    ViewHolder myViewHolder;
    Context context;
    LayoutInflater inflater;
    Dialog settingsDialog;
    public ListviewAdapter(Context context,ArrayList<ListData> arrayList){
        this.arrayList=arrayList;
        this.context=context;
        inflater=LayoutInflater.from(this.context);

    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //ViewHolder myViewHolder;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.list_item,parent,false);
            myViewHolder=new ViewHolder(convertView);
            convertView.setTag(myViewHolder);
        }else {
            myViewHolder= (ViewHolder) convertView.getTag();
        }

        ListData currentListData = (ListData) getItem(position);

        myViewHolder.profile_name.setText(currentListData.getName());
        myViewHolder.profile_status.setText(currentListData.getStatus());
        myViewHolder.profile_image.setImageResource(currentListData.getImgId());

        myViewHolder.profile_image.setTag(new Integer(position));
        myViewHolder.profile_image.setOnClickListener(this);
        /*new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                settingsDialog = new Dialog(context);
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                settingsDialog.setContentView(inflater.inflate(R.layout.image_layout
                        , null));
                ListData currentListData = (ListData) getItem(Integer.parseInt(v.getTag().toString()));
                ImageView image_dialog= (ImageView) settingsDialog.findViewById(R.id.image_dialog);
                image_dialog.setImageResource(currentListData.getImgId());
                settingsDialog.show();
            }
        });*/

        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile_image:
                //use this v.gettad() method to get the position. it shows in toast.
                //Toast.makeText(context, "ImageView clicked for the row = " + v.getTag().toString(), Toast.LENGTH_SHORT).show();
                settingsDialog = new Dialog(context);
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                settingsDialog.setContentView(inflater.inflate(R.layout.image_layout
                        , null));
                ListData currentListData = (ListData) getItem(Integer.parseInt(v.getTag().toString()));
                ImageView image_dialog= (ImageView) settingsDialog.findViewById(R.id.image_dialog);
                image_dialog.setImageResource(currentListData.getImgId());
                settingsDialog.show();
                break;
        }
    }

    private class ViewHolder {
        TextView profile_name,profile_status;
        ImageView profile_image;

        private ViewHolder(View item){
            profile_name= (TextView) item.findViewById(R.id.profile_name);
            profile_status= (TextView) item.findViewById(R.id.profile_status);
            profile_image= (ImageView) item.findViewById(R.id.profile_image);
        }
    }

}
