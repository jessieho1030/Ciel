package app.comps456f;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class Tutorial extends Fragment implements View.OnClickListener {

    private View view;
    private RelativeLayout chapter1_rl,chapter2_rl,chapter3_rl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tutorial, container, false);

        init();
        return view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void init(){
        chapter1_rl = (RelativeLayout) view.findViewById(R.id.chapter1Clicked);
        chapter2_rl = (RelativeLayout) view.findViewById(R.id.chapter2Clicked);
        chapter3_rl = (RelativeLayout) view.findViewById(R.id.chapter3Clicked);
        chapter1_rl.setOnClickListener(this);
        chapter2_rl.setOnClickListener(this);
        chapter3_rl.setOnClickListener(this);
    }
    // TODO: Onclick Relative Layout
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.chapter1Clicked:{
                Intent chapter1Intent = new Intent(getActivity(), Chapter1.class);
                startActivity(chapter1Intent);
                break;
                }

            case R.id.chapter2Clicked:{
                Intent chapter2Intent = new Intent(getActivity(), Chapter2.class);
                startActivity(chapter2Intent);
                break;
            }

            case R.id.chapter3Clicked:{
                Intent chapter3Intent = new Intent(getActivity(),Chapter3.class);
                startActivity(chapter3Intent);
                break;
                }

            default:
                break;
        }
        /*if (view == view.findViewById(R.id.chapter1Clicked)) {
            Intent chapter1Intent = new Intent(getActivity(), Chapter1.class);
            startActivity(chapter1Intent);
        }
        else if(view == view.findViewById(R.id.chapter2Clicked)){
            Intent chapter2Intent = new Intent(getActivity(), Chapter2.class);
            startActivity(chapter2Intent);
        }
        else if(view == view.findViewById(R.id.chapter3Clicked)){
            Intent chapter3Intent = new Intent(getActivity(),Chapter3.class);
            startActivity(chapter3Intent);
        }*/
    }




}
