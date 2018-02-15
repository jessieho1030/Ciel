package app.comps456f;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Result extends Fragment {
    private View view;

    private TextView txtProgress;
    private ProgressBar progressBar;
    private int pStatus = 0;
    private Handler handler = new Handler();
    public Result() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_result, container, false);
        init();
        return view;
    }

    public void init(){
        txtProgress = (TextView) view.findViewById(R.id.txtProgress);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus <= 65) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(pStatus);
                            txtProgress.setText(pStatus + " %");
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
            }
        }).start();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
