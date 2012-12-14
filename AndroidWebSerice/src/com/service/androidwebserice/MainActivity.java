package com.service.androidwebserice;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	String str;
	Button btnCallWebService;
	EditText txtEdit;
	TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnCallWebService = (Button)findViewById(R.id.button1);
        txtEdit = (EditText)findViewById(R.id.editText1);
        textView = (TextView)findViewById(R.id.textView1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
private class CallWebServiceTask extends AsyncTask<Void, Void, String> {
    	
        @Override
        protected String doInBackground(Void... Params) {
          
        	str = "Inside try";
	      try {
	    	  String SendingData;
	    	  str = "Inside try";
	         
	          String u;
	          BufferedReader in = null;
	          //Here Localhost is represented by "10.0.2.2". SO use this.
	          Socket ClientSocket = new Socket("170.224.168.64",8087);
	          DataOutputStream ClientData = new DataOutputStream(ClientSocket.getOutputStream());
	          SendingData =  txtEdit.getText().toString();
	          ClientData.writeBytes(SendingData + '\n');
	          str= "Incoming Data from Server:";
	          in = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
	          try{
	        	
	        	  str= "Incoming Data from Server:";
		          while ((u=in.readLine())!=null){
		            
		        	  str = u;
		          }
	          }
	          finally{
	      
	          }
	          ClientSocket.close();

			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
	      
	    	  return str;
        }

        @Override
        protected void onPostExecute(final String result) {
        	runOnUiThread (new Runnable(){
             	 public void run() {
             		textView.setText(result);
             	 }
             });
        }
      }

      public void readWebpage(View view) {
    	  CallWebServiceTask task = new CallWebServiceTask();
    	  task.execute();

      }
}
