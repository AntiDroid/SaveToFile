package com.example.talip.savetofile;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int counter;
    String fileName = "file";

    private void writeIntAsStringToFile(String filename, int i){
        FileOutputStream o;
        try{
            o = openFileOutput(filename, Context.MODE_PRIVATE);
            o.write(Integer.toString(i).getBytes());
            o.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private int readStringFromFileAsInt(String filename){
        FileInputStream o;
        byte[] b = new byte[16];

        try{
            o = openFileInput(filename);
            int n = o.read(b);
            o.close();

            // Wir wollen nicht alle 16 Bytes, sondern nur n-viele.
            byte[] number = Arrays.copyOfRange(b, 0, n);
            String s = new String(number);

            return Integer.parseInt(s);

        } catch(IOException e){
            e.printStackTrace();
        }

        return -99;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = readStringFromFileAsInt(fileName);

        if(counter == -99)
            counter = 0;

        TextView tv = findViewById(R.id.counterText);
        tv.setText(Integer.toString(counter));
    }

    @Override
    protected void onStop() {

        writeIntAsStringToFile(fileName, counter);

        super.onStop();
    }

    public void clickMe(View v){
        counter++;

        TextView tv = findViewById(R.id.counterText);
        tv.setText(Integer.toString(counter));
    }
}
