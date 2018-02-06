package app.comps456f;

/**
 * Created by hoyin on 4/2/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Logout extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getActivity(), LoginPage.class);
        startActivity(intent);
    }

}