package com.example.signo.halan;

import android.view.View;
import android.widget.TextView;

/**
 * Created by signo on 05/05/2016.
 */
public class listner implements View.OnClickListener{

        TextView t;
        public listner(TextView t){

            this.t = t;

        }
        public void onClick(View view) {
            this.t.setText("F");
        }

}

