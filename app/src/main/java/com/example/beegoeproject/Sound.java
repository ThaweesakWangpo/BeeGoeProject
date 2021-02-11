package com.example.beegoeproject;



import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Sound extends Activity {

    TextView textResponse,latitude,longitude;
    EditText editTextAddress;
    Button buttonConnect, buttonClear;
    private String latitudeStr="",longitudeStr="";
    public static double latitudeDOg,longitudeDog;
    public  static boolean click = true;
    //int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        editTextAddress = (EditText)findViewById(R.id.address);

        buttonConnect = (Button)findViewById(R.id.record);
        buttonClear = (Button)findViewById(R.id.stop);
        textResponse = (TextView)findViewById(R.id.textView3);

        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);

        buttonClear.setEnabled(false);

        buttonConnect.setOnClickListener(buttonConnectOnClickListener);

        buttonClear.setOnClickListener(buttonStopOnClickListener);
    }


    View.OnClickListener buttonConnectOnClickListener =

            new View.OnClickListener(){

                @Override

                public void onClick(View arg0) {
                    buttonConnect.setEnabled(false);
                    buttonClear.setEnabled(true);
                    MyClientTask myClientTask = new MyClientTask(
                            editTextAddress.getText().toString(), 8080);
                    myClientTask.execute();
                }};


    View.OnClickListener buttonStopOnClickListener =

            new View.OnClickListener(){

                @Override

                public void onClick(View arg0) {
                    buttonConnect.setEnabled(true);
                    buttonClear.setEnabled(false);
                    MyClientTask myClientTask = new MyClientTask(
                            editTextAddress.getText().toString(), 8080);
                    myClientTask.execute();
                }};




    public class MyClientTask extends AsyncTask<Void, Void, Void> {

        String dstAddress;
        int dstPort;
        String response = "",latitudeDis="",longitudeDis="";


        MyClientTask(String addr, int port){
            dstAddress = addr;
            dstPort = port;
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            Socket socket = null;

            try {
                socket = new Socket(dstAddress, dstPort);

                ByteArrayOutputStream byteArrayOutputStream =
                        new ByteArrayOutputStream(1024);
                byte[] buffer = new byte[1024];

                int bytesRead;
                InputStream inputStream = socket.getInputStream();

                /*
                 * notice:
                 * inputStream.read() will block if no data return
                 */
//            if(count==0)    {
//                while ((bytesRead = inputStream.read(buffer)) != -1){
//                    byteArrayOutputStream.write(buffer, 0, bytesRead);
//                    response += byteArrayOutputStream.toString("UTF-8");
//                    count=1;
//
//                }
//            }

                // if(count==1) {
                while ((bytesRead = inputStream.read(buffer)) != -1){
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                    response += byteArrayOutputStream.toString("UTF-8");
                    //  latitudeDis += byteArrayOutputStream.toString("UTF-8");
                    //  longitudeDis += byteArrayOutputStream.toString("UTF-8");
                    //count=2;
                }


                String[] arr = response.split(",");
                for(String c : arr){

                }
                for(int i = 0; i< arr.length;i++){

                }



                latitudeStr = arr[1];
                longitudeStr = arr[2];

                if(latitudeStr.length()>0&&longitudeStr.length()>0){

                    latitudeDOg=Double.valueOf(latitudeStr);
                    longitudeDog=Double.valueOf(longitudeStr);
                    click=false;

                }





                // }
//                if(count==2) {
//                    while ((bytesRead = inputStream.read(buffer)) != -1){
//                        byteArrayOutputStream.write(buffer, 0, bytesRead);
//
//                        //latitudeDis += byteArrayOutputStream.toString("UTF-8");
//                         longitudeDis += byteArrayOutputStream.toString("UTF-8");
//                        count=0;
//                    }
//                }

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "UnknownHostException: " + e.toString();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "IOException: " + e.toString();
            }finally{
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            textResponse.setText(response);
            // latitude.setText(latitudeDis);


            // longitude.setText(longitudeDis);

            super.onPostExecute(result);
        }

    }
}
