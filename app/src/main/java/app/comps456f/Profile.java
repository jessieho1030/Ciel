package app.comps456f;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andreabaccega.widget.FormEditText;
//TODO: Update Profile Java
/**
 * Created by hoyin on 21/3/2018.
 */
public class Profile extends Fragment{
    private View view;
    private SaveUserInformation svUser;
    private FormEditText s;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
