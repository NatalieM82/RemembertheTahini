package com.example.nataliemenahem.rememberthetahini;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by NatalieMenahem on 02/11/15.
 */
public class TaskItemBaseAdapter extends BaseAdapter {

    private static final String TAG = "TaskItemBaseAdapter";


    public TaskItemBaseAdapter(Context context, List<TaskItem> items) {
        this.items = items;
        this.context = context;
    }

    static class ViewHolder {
        TextView task_description;
    }

    private Context context;
    private List<TaskItem> items;

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        if (this.items != null && items.size() > position)
            return this.items.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void UpdateDataSource(List<TaskItem> items)
    {
        if(items ==null) return; //TODO Decide how to deal with it (Maybe an exception??)
        this.items= items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.task_item, null);
            holder = new ViewHolder();
            holder.task_description = (TextView) convertView
                    .findViewById(R.id.task_description);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.task_description.setText(items.get(position).getDescription());

//        Log.i(TAG, items.get(position).getStatus());
        if (items.get(position).getStatus().toString().equals("true"))
        {
            holder.task_description.setTextColor(Color.GRAY);
            holder.task_description.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else {
            holder.task_description.setTextColor(Color.BLACK);
            holder.task_description.setPaintFlags(holder.task_description.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        return convertView;
    }


}
