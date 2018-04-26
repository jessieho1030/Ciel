package app.comps456f;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class Discuss_recycler_adapter extends RecyclerView.Adapter<Discuss_recycler_adapter.ViewHolder> implements View.OnClickListener {

    ArrayList<String> subject = new ArrayList<String>();
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> time = new ArrayList<String>();
    //ArrayList<String> author = new ArrayList<String>();

    public Discuss_recycler_adapter( ArrayList<String> subject, ArrayList<String> name , ArrayList<String> time) {
        this.subject = subject;
        this.name = name;
        this.time = time;
    }

    @Override
    public Discuss_recycler_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_cardview, parent, false);
        Discuss_recycler_adapter.ViewHolder vh = new Discuss_recycler_adapter.ViewHolder(v);
        v.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(Discuss_recycler_adapter.ViewHolder holder, int position) {
        holder.subject.setText(subject.get(position));
        holder.name.setText(name.get(position));
        holder.time.setText(time.get(position));

    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public int getItemCount() {
        return subject.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView subject, name, time;

        public ViewHolder(View itemView) {
            super(itemView);
            subject = (TextView)itemView.findViewById(R.id.subject);
            name = (TextView)itemView.findViewById(R.id.text);
            time = (TextView)itemView.findViewById(R.id.time);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
