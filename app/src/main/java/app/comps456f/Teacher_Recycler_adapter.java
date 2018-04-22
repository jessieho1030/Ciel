package app.comps456f;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hoyin on 9/2/2018.
 */

public class Teacher_Recycler_adapter extends RecyclerView.Adapter<Teacher_Recycler_adapter.ViewHolder> implements View.OnClickListener {

   ArrayList<String> student_id = new ArrayList<String>();
    ArrayList<String> student_name = new ArrayList<String>();
    ArrayList<String> studscore = new ArrayList<String>();
    ArrayList<String> submit = new ArrayList<String>();
    CheckBox reminder;



    public Teacher_Recycler_adapter( ArrayList<String> student_id, ArrayList<String> student_name , ArrayList<String> studscore, ArrayList<String> submit,CheckBox reminder) {
        this.student_id = student_id;
        this.student_name =student_name;
        this.studscore = studscore;
        this.submit = submit;
        this.reminder =reminder;
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

        holder.student_id.setText(student_id.get(position));
        holder.student_name.setText(student_name.get(position));
        holder.studscore.setText(studscore.get(position));
        holder.submit.setText(submit.get(position));
        holder.reminder.setChecked(false);
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public int getItemCount() {
        return student_name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView student_name,student_id, studscore, submit;
        public CheckBox reminder;

        public ViewHolder(View itemView) {
            super(itemView);
            student_id = (TextView)itemView.findViewById(R.id.studID);
            student_name = (TextView)itemView.findViewById(R.id.studname);
            studscore = (TextView)itemView.findViewById(R.id.studscore);
            submit = (TextView)itemView.findViewById(R.id.quizSub);
            reminder = (CheckBox)itemView.findViewById(R.id.reminderCheckBox);

        }

        @Override
        public void onClick(View view) {

        }
    }

}
