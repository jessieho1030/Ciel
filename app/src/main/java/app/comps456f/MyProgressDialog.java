package app.comps456f;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by hoyin on 15/2/2018.
 */

public class MyProgressDialog {
    private Context _context;
    private String _title;
    private String _message;
    private ProgressDialog progressDialog;

    public MyProgressDialog(Context context, String title, String message){
        this._context = context;
        this._title = title;
        this._message = message;
    }

    // display
    public void show(){
        progressDialog = ProgressDialog.show(_context, _title, _message,
                true, false);

        new Thread() {
            public void run() {

                try{
                    sleep(1000);
                } catch (Exception e) {

                }
                progressDialog.dismiss();
            }
        }.start();
    }

    // dismiss
    public void dimiss(){
        progressDialog.dismiss();
    }

}
