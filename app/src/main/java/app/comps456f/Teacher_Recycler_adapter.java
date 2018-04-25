package app.comps456f;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hoyin on 9/2/2018.
 */

public class Teacher_Recycler_adapter extends RecyclerView.Adapter<Teacher_Recycler_adapter.ViewHolder> implements View.OnClickListener {

   ArrayList<String> student_id = new ArrayList<String>();
    ArrayList<String> student_name = new ArrayList<String>();
    ArrayList<String> studscore = new ArrayList<String>();
    ArrayList<String> submit = new ArrayList<String>();
    CheckBox reminder;
    ArrayList<String> remindname = new ArrayList<>();


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
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.student_id.setText(student_id.get(position));
        holder.student_name.setText(student_name.get(position));
        holder.studscore.setText(studscore.get(position));
        holder.submit.setText(submit.get(position));
        holder.reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = ((CheckBox) view).isChecked();
                holder.reminder.setChecked(b);
                if(holder.reminder.isChecked()){
                    remindname.add(student_id.get(position));
                }
                else {
                    remindname.remove(student_id.get(position));
                }
            }
        });

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
