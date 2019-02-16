package com.jimit105.wifianalyzer;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.annotation.SuppressLint;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayDetails(View view){

        @SuppressLint("WifiManagerLeak")WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        TextView details = (TextView) findViewById(R.id.textView);

        int signal_strength = wifiInfo.getRssi();
        String signal = null;
        if(signal_strength>-50){
            signal = "Excellent";
        }else if(signal_strength<-50 && signal_strength>-60){
            signal = "Good";
        }else if(signal_strength<-60 && signal_strength>-70){
            signal = "Fair";
        }else if(signal_strength<-70 && signal_strength>-100){
            signal = "Weak";
        }

        String info = "SSID: " + wifiInfo.getSSID() + "\nStrength: " + wifiInfo.getRssi() + "dBm" + "\nSignal Level: " + WifiManager.calculateSignalLevel(wifiInfo.getRssi(), 5) + "/5" +"\nSignal Strength: " + signal + "\nSpeed: " + wifiInfo.getLinkSpeed() + "Mbps" + "\nIP Address: " + Formatter.formatIpAddress(wifiInfo.getIpAddress()) + "\nMAC Address: " + wifiInfo.getMacAddress() + "\nFrequency: "+ (float)wifiInfo.getFrequency()/1000 + "GHz" + "\nHidden SSID: "+ wifiInfo.getHiddenSSID();

        details.setText(info);

    }
}
