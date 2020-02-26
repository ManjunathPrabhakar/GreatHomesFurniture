package com.example.dailyfoods;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Map;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Map<String, Integer> mapdata;
    int mapdatasize;

    // RecyclerView recyclerView;
    public ListAdapter(Map<String, Integer> mapdata) {
        this.mapdata = mapdata;
        this.mapdatasize = this.mapdata.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.recycler_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        try {
            String key = "";
            Integer val = 0;
            Log.d("BindView", "map size " + mapdata.size());
            for (Map.Entry<String, Integer> entry : mapdata.entrySet()) {
                key = entry.getKey();
                val = entry.getValue();
                break;
            }

            if (key.equals("") || val == 0) {
                return;
            }

            mapdata.remove(key);

            String z[] = key.split("@");


            holder.textView.setText(z[0]);
            holder.imageView.setImageResource(val);
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "click on item: " + z[0], Toast.LENGTH_LONG).show();
                    Intent i = new Intent(view.getContext(), MainActivity.class);
                    i.putExtra("image", z[1]);
                    view.getContext().startActivity(i);
                }
            });
        } catch (Exception e) {
            Log.d("BindView", "Error " + e);
        }
    }


    @Override
    public int getItemCount() {
        Log.d("BindView", "mapdata itemcount " + mapdata.size());
//        return mapdata.size();
        return mapdatasize;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public LinearLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.Head);
            this.textView = (TextView) itemView.findViewById(R.id.sub);
            relativeLayout = (LinearLayout) itemView.findViewById(R.id.linear);
        }
    }
}