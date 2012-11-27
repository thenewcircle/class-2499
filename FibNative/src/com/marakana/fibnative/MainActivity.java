package com.marakana.fibnative;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText input;
	TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.input);
        output = (TextView) findViewById(R.id.output);
    }

    public void onClick(View v) {
    		long n = Long.parseLong( input.getText().toString() );
    		
    		long resultJ = FibLib.fibJ(n);
    		
    		output.append( String.format("\n fibJ(%d) = %d", n, resultJ) );
    }
}
