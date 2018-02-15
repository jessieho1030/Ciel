package app.comps456f;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hoyin on 9/2/2018.
 */

public class Teacher_Recycler_adapter extends RecyclerView.Adapter<Teacher_Recycler_adapter.ViewHolder> implements View.OnClickListener {
    String [] student_name,student_rank;

    public Teacher_Recycler_adapter(String[] student_name, String[] student_rank) {
        this.student_name =student_name;
        this.student_rank=student_rank;

    }

    @Override
    public Teacher_Recycler_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.teacher_cardview, parent, false);
       Teacher_Recycler_adapter.ViewHolder vh = new Teacher_Recycler_adapter.ViewHolder(v);
        v.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.student_name.setText(student_name[position]);
        holder.student_rank.setText(student_rank[position]);
    }


    @Override
    public void onClick(View view) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView student_name,student_rank;
        public CircleImageView studimg;

        public ViewHolder(View itemView) {
            super(itemView);
            student_name = (TextView)itemView.findViewById(R.id.studname);
            student_rank = (TextView)itemView.findViewById(R.id.studrank);
            studimg = (CircleImageView)itemView.findViewById(R.id.studimg);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public int getItemCount() {
        return student_name.length;
    }
}
