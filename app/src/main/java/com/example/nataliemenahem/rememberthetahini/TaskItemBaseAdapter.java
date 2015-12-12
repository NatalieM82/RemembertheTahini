package com.example.nataliemenahem.rememberthetahini;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
public class TaskItemBaseAdapter extends RecyclerView.Adapter<TaskItemBaseAdapter.ViewHolder> {

    private List<TaskItem> tasks;

    public TaskItemBaseAdapter(List<TaskItem> tasks) {
        this.tasks = tasks;
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TaskItem item = tasks.get(position);
        holder.mTvDescription.setText(item.getDescription());
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Each item is a view in the card.
        private TextView mTvDescription;
        public ViewHolder(View parentView) {
            super(parentView);
            mTvDescription = (TextView) parentView.findViewById(R.id.task_description);
        }
    }

//    @Override
//    public Object getItem(int position) {
//        if (this.tasks != null && tasks.size() > position)
//            return this.tasks.get(position);
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder;
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(
//                    R.layout.task_item, null);
//            holder = new ViewHolder();
//            holder.description = (TextView) convertView
//                    .findViewById(R.id.task_description);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//        holder.description.setText(tasks.get(position).getDescription());
//        return convertView;
//    }
//
//    private class ViewHolder {
//        public long taskId;
//        public TextView description;
//    }

    public void UpdateDataSource(List<TaskItem> items)
    {
        if(items ==null) return; //TODO Decide how to deal with it (Maybe an exception??)
        this.tasks= items;
    }


}
