package app.comps456f;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


//public class Discuss_recycler_adapter extends RecyclerView.Adapter<Discuss_recycler_adapter.ViewHolder> implements View.OnClickListener {
public class Discuss_recycler_adapter extends RecyclerView.Adapter<Discuss_recycler_adapter.ViewHolder> {

    ArrayList<String> subject = new ArrayList<String>();
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> time = new ArrayList<String>();
    ArrayList<String> pid = new ArrayList<String>();
    String category;

    public Discuss_recycler_adapter(ArrayList<String> subject, ArrayList<String> name , ArrayList<String> time, ArrayList<String> pid, String category) {
        this.subject = subject;
        this.name = name;
        this.time = time;
        this.pid = pid;
        this.category = category;
    }

    @Override
    public Discuss_recycler_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_cardview, parent, false);
        Discuss_recycler_adapter.ViewHolder vh = new Discuss_recycler_adapter.ViewHolder(v);
        //v.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(Discuss_recycler_adapter.ViewHolder holder, int position) {
        holder.subject.setText(subject.get(position));
        holder.name.setText(name.get(position));
        holder.time.setText(time.get(position));

    }

    @Override
    public int getItemCount() {
        return pid.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView subject, name, time;
        //private View view;


        public ViewHolder(final View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
            subject = (TextView)itemView.findViewById(R.id.subject);
            name = (TextView)itemView.findViewById(R.id.name);
            time = (TextView)itemView.findViewById(R.id.time);
            //view = itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(view.getContext(), ReadPost.class);
                    intent.putExtra("pid",pid.get(position));
                    intent.putExtra("category", category);
                    view.getContext().startActivity(intent);
                }
            });
        }

    }

}
