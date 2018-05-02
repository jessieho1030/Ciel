package app.comps456f;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meiyuk on 2/5/2018.
 */

public class ListViewAdapter extends BaseAdapter{
    private LayoutInflater myInflater;
    private List<Comment> comments;
    /*
    ArrayList<String> rcm_time = new ArrayList<String>();
    ArrayList<String> rcm_name = new ArrayList<String>();
    ArrayList<String> rcm_text = new ArrayList<String>();

    public ListViewAdapter(ArrayList<String> rcm_name, ArrayList<String> rcm_text , ArrayList<String> rcm_time){
        this.rcm_name = rcm_name;
        this.rcm_text = rcm_text;
        this.rcm_time = rcm_time;
    }*/
    public ListViewAdapter(Context context, List<Comment> comments){
        myInflater = LayoutInflater.from(context);
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int arg0) {
        return comments.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return comments.indexOf(getItem(position));
    }

    private class ViewHolder {
        TextView txtText;
        TextView txtTime;
        TextView txtName;
        public ViewHolder(TextView txtText, TextView txtTime, TextView txtName){
            this.txtText = txtText;
            this.txtTime = txtTime;
            this.txtName = txtName;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView = myInflater.inflate(R.layout.recomment_list_item, null);
            holder = new ViewHolder(
                    (TextView) convertView.findViewById(R.id.text),
                    (TextView) convertView.findViewById(R.id.time),
                    (TextView) convertView.findViewById(R.id.name)

                    );
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Comment comment = (Comment)getItem(position);

        holder.txtText.setText(comment.getText());
        holder.txtName.setText(comment.getName());
        holder.txtTime.setText(comment.getTime());

        return convertView;
    }
}
