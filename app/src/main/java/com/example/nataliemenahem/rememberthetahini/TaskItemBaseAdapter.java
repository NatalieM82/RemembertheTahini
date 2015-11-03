package com.example.nataliemenahem.rememberthetahini;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by NatalieMenahem on 02/11/15.
 */
public class TaskItemBaseAdapter extends BaseAdapter {

    private List<TaskItem> tasks;
    private Context context;

    public TaskItemBaseAdapter(Context context, List<TaskItem> task) {
        this.context = context;
        tasks = task;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        if (this.tasks != null && tasks.size() > position)
            return this.tasks.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.task_item, null);
            holder = new ViewHolder();
            holder.description = (TextView) convertView
                    .findViewById(R.id.task_description);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.description.setText(tasks.get(position).getDescription());
        return convertView;
    }

    private class ViewHolder {
        public long taskId;
        public TextView description;
    }
}
