package com.example.aruduino13_3;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    TextView mTvBluetoothStatus;
    TextView mTvReceiveData;
    TextView mTvSendData;
    Button mBtnBluetoothConnect;
    Button mBtnBluetoothDisconnect;
    Button mBtnSendData;
    Button mBtnSendData2;
    Button btn_on;
    Button btn_off;
    Button btn_br1;
    Button btn_br2;
    Button btn_br3;
    Button btn_red;
    Button btn_green;
    Button btn_blue;
    Switch modeswitch;
    Button btn_close;
    Button btn_custom;
    SeekBar SeekBarR;
    SeekBar SeekBarG;
    SeekBar SeekBarB;
    int iR=0;
    int iG=0;
    int iB=0;
    int iColor=1000;
    String sColor="1000";
    Button btn_customset;
    TextView textR;
    TextView textG;
    TextView textB;
    Button btntimer;
    Button btn_close2;

    Spinner spnhour;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    String stHour;

    Spinner spnminute;
    ArrayList<String> arrayList2;
    ArrayAdapter<String> arrayAdapter2;
    String stMinute;


    BluetoothAdapter mBluetoothAdapter;
    Set<BluetoothDevice> mPairedDevices;
    List<String> mListPairedDevices;
    Handler mBluetoothHandler;
    ConnectedBluetoothThread mThreadConnectedBluetooth;
    BluetoothDevice mBluetoothDevice;
    BluetoothSocket mBluetoothSocket;

    final static int BT_REQUEST_ENABLE = 1;
    final static int BT_MESSAGE_READ = 2;
    final static int BT_CONNECTING_STATUS = 3;
    final static UUID BT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        mTvBluetoothStatus = findViewById(R.id.tvBluetoothStatus);
        mTvReceiveData = findViewById(R.id.tvReceiveData);
        mTvSendData =  (EditText) findViewById(R.id.tvSendData);
        mBtnBluetoothConnect = findViewById(R.id.btnBluetoothConnect);
        mBtnBluetoothDisconnect = findViewById(R.id.btnBluetoothDisconnect);
        mBtnSendData = findViewById(R.id.btnSendData);
        mBtnSendData2 = findViewById(R.id.btnSendData2);

        btn_on = findViewById(R.id.btn_on);
        btn_off = findViewById(R.id.btn_off);
        btn_br1 = findViewById(R.id.btn_br1);
        btn_br2 = findViewById(R.id.btn_br2);
        btn_br3 = findViewById(R.id.btn_br3);
        btn_red = findViewById(R.id.btn_red);
        btn_green =findViewById(R.id.btn_grn);
        btn_blue = findViewById(R.id.btn_blu);
        modeswitch = findViewById(R.id.modeswitch);
        btn_close = findViewById(R.id.btn_close);
        btn_custom = findViewById(R.id.btn_custom);
        btn_customset = findViewById(R.id.btn_customset);

        SeekBarR = findViewById(R.id.seekBarR);
        SeekBarG = findViewById(R.id.seekBarG);
        SeekBarB = findViewById(R.id.seekBarB);
        textR = findViewById(R.id.textR);
        textG = findViewById(R.id.textG);
        textB = findViewById(R.id.textB);

        btntimer = findViewById(R.id.btn_timer);
        btn_close2 = findViewById(R.id.btnclose2);


        spnhour = findViewById(R.id.spnhour);
        arrayList = new ArrayList<>();
        arrayList.add("00");
        arrayList.add("01");
        arrayList.add("02");
        arrayList.add("03");
        arrayList.add("04");
        arrayList.add("05");
        arrayList.add("06");
        arrayList.add("07");
        arrayList.add("08");
        arrayList.add("09");
        arrayList.add("10");
        arrayList.add("11");
        arrayList.add("12");
        arrayList.add("13");
        arrayList.add("14");
        arrayList.add("15");
        arrayList.add("16");
        arrayList.add("17");
        arrayList.add("18");
        arrayList.add("19");
        arrayList.add("20");
        arrayList.add("21");
        arrayList.add("22");
        arrayList.add("23");

        spnminute = findViewById(R.id.spnminute);
        arrayList2 = new ArrayList<>();
        arrayList2.add("00");
        arrayList2.add("01");
        arrayList2.add("02");
        arrayList2.add("03");
        arrayList2.add("04");
        arrayList2.add("05");
        arrayList2.add("06");
        arrayList2.add("07");
        arrayList2.add("08");
        arrayList2.add("09");
        arrayList2.add("10");
        arrayList2.add("11");
        arrayList2.add("12");
        arrayList2.add("13");
        arrayList2.add("14");
        arrayList2.add("15");
        arrayList2.add("16");
        arrayList2.add("17");
        arrayList2.add("18");
        arrayList2.add("19");
        arrayList2.add("20");
        arrayList2.add("21");
        arrayList2.add("22");
        arrayList2.add("23");
        arrayList2.add("24");
        arrayList2.add("25");
        arrayList2.add("26");
        arrayList2.add("27");
        arrayList2.add("28");
        arrayList2.add("29");
        arrayList2.add("30");
        arrayList2.add("31");
        arrayList2.add("32");
        arrayList2.add("33");
        arrayList2.add("34");
        arrayList2.add("35");
        arrayList2.add("36");
        arrayList2.add("37");
        arrayList2.add("38");
        arrayList2.add("39");
        arrayList2.add("40");
        arrayList2.add("41");
        arrayList2.add("42");
        arrayList2.add("43");
        arrayList2.add("44");
        arrayList2.add("45");
        arrayList2.add("46");
        arrayList2.add("47");
        arrayList2.add("48");
        arrayList2.add("49");
        arrayList2.add("50");
        arrayList2.add("51");
        arrayList2.add("52");
        arrayList2.add("53");
        arrayList2.add("54");
        arrayList2.add("55");
        arrayList2.add("56");
        arrayList2.add("57");
        arrayList2.add("58");
        arrayList2.add("59");



        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);

        spnhour.setAdapter(arrayAdapter);
        spnhour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stHour = arrayList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        arrayAdapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList2);

        spnminute.setAdapter(arrayAdapter2);
        spnminute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stMinute = arrayList2.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Checkstate();

        modeswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checkstate();
            }
        });



        mBtnBluetoothConnect.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothOn();
            }
        });
        mBtnBluetoothDisconnect.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothOff();
            }
        });

        btn_on.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                if(mThreadConnectedBluetooth != null){
                    mThreadConnectedBluetooth.write("1");
                }
            }
        });

        btn_off.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                if(mThreadConnectedBluetooth != null){
                    mThreadConnectedBluetooth.write("2");
                }
            }
        });

        btn_br1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                if(mThreadConnectedBluetooth != null){
                    mThreadConnectedBluetooth.write("3");
                }
            }
        });

        btn_br2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                if(mThreadConnectedBluetooth != null){
                    mThreadConnectedBluetooth.write("4");
                }
            }
        });

        btn_br3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                if(mThreadConnectedBluetooth != null){
                    mThreadConnectedBluetooth.write("5");
                }
            }
        });

        btn_red.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                if(mThreadConnectedBluetooth != null){
                    mThreadConnectedBluetooth.write("6");
                }
            }
        });

        btn_green.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                if(mThreadConnectedBluetooth != null){
                    mThreadConnectedBluetooth.write("7");
                }
            }
        });

        btn_blue.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                if(mThreadConnectedBluetooth != null){
                    mThreadConnectedBluetooth.write("8");
                }
            }
        });


        //알람on
        mBtnSendData.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mThreadConnectedBluetooth != null) {

                    if(mThreadConnectedBluetooth != null){
                        Toast.makeText(getApplicationContext(), stHour+"시"+stMinute+"분에 조명이 켜집니다.", Toast.LENGTH_LONG).show();
                        mThreadConnectedBluetooth.write(stHour+stMinute+"01");

                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "블루투스 장치와 연결이 되어 있지 않습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });
        mBtnSendData2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mThreadConnectedBluetooth != null) {

                    if(mThreadConnectedBluetooth != null){
                        Toast.makeText(getApplicationContext(), stHour+"시"+stMinute+"분에 조명이 꺼집니다.", Toast.LENGTH_LONG).show();
                        mThreadConnectedBluetooth.write(stHour+stMinute+"02");

                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "블루투스 장치와 연결이 되어 있지 않습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_custom.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                SeekBarR.setVisibility(View.VISIBLE);
                SeekBarG.setVisibility(View.VISIBLE);
                SeekBarB.setVisibility(View.VISIBLE);
                textR.setVisibility(View.VISIBLE);
                textG.setVisibility(View.VISIBLE);
                textB.setVisibility(View.VISIBLE);
                btn_close.setVisibility(View.VISIBLE);
                btn_customset.setVisibility(View.VISIBLE);
                btn_custom.setVisibility(View.GONE);
                btntimer.setVisibility(View.GONE);
            }
        });

        btntimer.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                mBtnSendData.setVisibility(View.VISIBLE);
                mBtnSendData2.setVisibility(View.VISIBLE);
                btn_custom.setVisibility(View.GONE);
                spnhour.setVisibility(View.VISIBLE);
                spnminute.setVisibility(View.VISIBLE);
                btn_close2.setVisibility(View.VISIBLE);
                btntimer.setVisibility(View.GONE);
            }
        });

        btn_close.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                SeekBarR.setVisibility(View.GONE);
                SeekBarG.setVisibility(View.GONE);
                SeekBarB.setVisibility(View.GONE);
                textR.setVisibility(View.GONE);
                textG.setVisibility(View.GONE);
                textB.setVisibility(View.GONE);
                btn_custom.setVisibility(View.VISIBLE);
                btn_close.setVisibility(View.GONE);
                btn_customset.setVisibility(View.GONE);
                btntimer.setVisibility(View.VISIBLE);
            }
        });

        btn_close2.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                mBtnSendData.setVisibility(View.GONE);
                mBtnSendData2.setVisibility(View.GONE);
                btn_custom.setVisibility(View.VISIBLE);
                spnhour.setVisibility(View.GONE);
                spnminute.setVisibility(View.GONE);
                btn_close2.setVisibility(View.GONE);
                btntimer.setVisibility(View.VISIBLE);
            }
        });

        btn_customset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iColor = 1000 + 100*iR + 10*iG + iB;
                //sColor = String.valueOf(iColor);
                sColor = iR+","+iG+","+iB;
                if(mThreadConnectedBluetooth != null){
                    mThreadConnectedBluetooth.write(sColor);
                }
            }
        });

        SeekBarR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                iR = SeekBarR.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                iR = SeekBarR.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                iR = SeekBarR.getProgress();
            }
        });

        SeekBarG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                iG = SeekBarG.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                iG = SeekBarG.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                iG = SeekBarG.getProgress();
            }
        });

        SeekBarB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                iB = SeekBarB.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                iB = SeekBarB.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                iB = SeekBarB.getProgress();
            }
        });

        mBluetoothHandler = new Handler(){
            public void handleMessage(Message msg){
                if(msg.what == BT_MESSAGE_READ){
                    String readMessage = null;
                    try {
                        readMessage = new String((byte[]) msg.obj, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    //mTvReceiveData.setText(readMessage);
                    char[] creadMessage = readMessage.toCharArray();
                    switch (creadMessage[0]){
                        case '1':
                            mTvReceiveData.setText("현재 상태 : ON");
                            break;
                        case '2':
                            mTvReceiveData.setText("현재 상태 : OFF");
                            break;
                        default :
                            mTvReceiveData.setText("현재 상태 : ?");
                    }
                }
            }
        };
    }

    private void Checkstate() {
        if(modeswitch.isChecked()){
            //자동모드
            if(mThreadConnectedBluetooth != null){
                mThreadConnectedBluetooth.write("9");
            }
            btn_on.setEnabled(false);
            btn_off.setEnabled(false);
            btn_br1.setEnabled(false);
            btn_br2.setEnabled(false);
            btn_br3.setEnabled(false);
            btn_red.setEnabled(false);
            btn_blue.setEnabled(false);
            btn_green.setEnabled(false);
            btn_customset.setEnabled(false);
        }
        else{
            //수동모드
            if(mThreadConnectedBluetooth != null){
                mThreadConnectedBluetooth.write("0`");
            }
            btn_on.setEnabled(true);
            btn_off.setEnabled(true);
            btn_br1.setEnabled(true);
            btn_br2.setEnabled(true);
            btn_br3.setEnabled(true);
            btn_red.setEnabled(true);
            btn_blue.setEnabled(true);
            btn_green.setEnabled(true);
            btn_customset.setEnabled(true);
        }
    }

    void bluetoothOn() {
        if(mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "블루투스를 지원하지 않는 기기입니다.", Toast.LENGTH_LONG).show();
        }
        else {
            if(mBluetoothAdapter.isEnabled()) {
                // Toast.makeText(getApplicationContext(), "블루투스가 이미 활성화 되어 있습니다.", Toast.LENGTH_LONG).show();
                mTvBluetoothStatus.setText("활성화");
                listPairedDevices();
            }
            else {
                Toast.makeText(getApplicationContext(), "블루투스가 비활성화 되어 있습니다.", Toast.LENGTH_LONG).show();
                Intent intentBluetoothEnable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intentBluetoothEnable, BT_REQUEST_ENABLE);
            }
        }
    }
    void bluetoothOff() {
        if(mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
            Toast.makeText(getApplicationContext(), "블루투스가 비활성화 되었습니다.", Toast.LENGTH_SHORT).show();
            mTvBluetoothStatus.setText("비활성화");
        }
        else {
            Toast.makeText(getApplicationContext(), "블루투스가 이미 비활성화 되어 있습니다.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case BT_REQUEST_ENABLE:
                if (resultCode == RESULT_OK) { // 블루투스 활성화를 확인을 클릭하였다면
                    Toast.makeText(getApplicationContext(), "블루투스가 활성화 되었습니다.", Toast.LENGTH_LONG).show();
                    mTvBluetoothStatus.setText("활성화");
                    listPairedDevices();
                } else if (resultCode == RESULT_CANCELED) { // 블루투스 활성화를 취소를 클릭하였다면
                    Toast.makeText(getApplicationContext(), "취소되었습니다.", Toast.LENGTH_LONG).show();
                    mTvBluetoothStatus.setText("비활성화");
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    void listPairedDevices() {
        if (mBluetoothAdapter.isEnabled()) {
            mPairedDevices = mBluetoothAdapter.getBondedDevices();

            if (mPairedDevices.size() > 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("장치 선택");

                mListPairedDevices = new ArrayList<String>();
                for (BluetoothDevice device : mPairedDevices) {
                    mListPairedDevices.add(device.getName());
                    //mListPairedDevices.add(device.getName() + "\n" + device.getAddress());
                }
                final CharSequence[] items = mListPairedDevices.toArray(new CharSequence[mListPairedDevices.size()]);
                mListPairedDevices.toArray(new CharSequence[mListPairedDevices.size()]);

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        connectSelectedDevice(items[item].toString());
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                Toast.makeText(getApplicationContext(), "페어링된 장치가 없습니다.", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "블루투스가 비활성화 되어 있습니다.", Toast.LENGTH_SHORT).show();
        }
    }
    void connectSelectedDevice(String selectedDeviceName) {
        for(BluetoothDevice tempDevice : mPairedDevices) {
            if (selectedDeviceName.equals(tempDevice.getName())) {
                mBluetoothDevice = tempDevice;
                break;
            }
        }
        try {
            mBluetoothSocket = mBluetoothDevice.createRfcommSocketToServiceRecord(BT_UUID);
            mBluetoothSocket.connect();
            mThreadConnectedBluetooth = new ConnectedBluetoothThread(mBluetoothSocket);
            mThreadConnectedBluetooth.start();
            mBluetoothHandler.obtainMessage(BT_CONNECTING_STATUS, 1, -1).sendToTarget();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "블루투스 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
        }
    }
    private class ConnectedBluetoothThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedBluetoothThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "소켓 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            }
            mTvBluetoothStatus.setText("장치 연결됨");

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }
        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;

            while (true) {
                try {
                    bytes = mmInStream.available();
                    if (bytes != 0) {
                        SystemClock.sleep(100);
                        bytes = mmInStream.available();
                        bytes = mmInStream.read(buffer, 0, bytes);
                        mBluetoothHandler.obtainMessage(BT_MESSAGE_READ, bytes, -1, buffer).sendToTarget();
                    }
                } catch (IOException e) {
                    break;
                }
            }
        }
        public void write(String str) {
            byte[] bytes = str.getBytes();
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "데이터 전송 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            }
        }
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "소켓 해제 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            }
        }
    }
}

