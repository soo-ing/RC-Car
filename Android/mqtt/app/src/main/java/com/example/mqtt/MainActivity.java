package com.example.mqtt;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.example.mqtt.ChatAdapter;
import com.example.mqtt.ChatItem;
import com.example.mqtt.R;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    static final String TAG = MainActivity.class.getSimpleName();
    static final String TOPIC = "hello/world";
    static final String TOPIC1 = "hello/world";

    private ChatAdapter chatAdapter;
    private MqttClient mqttClient;

    @OnClick(R.id.detection)
    public void detection(){
        try{
            mqttClient.publish(TOPIC,new MqttMessage("0".getBytes()));
        }catch (Exception e){}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final Button btn1=(Button)findViewById(R.id.btn01);
        final Button btn2=(Button)findViewById(R.id.btn02);
        final Button btn3=(Button)findViewById(R.id.btn03);
        final Button btn4=(Button)findViewById(R.id.btn04);
        final Button btn5=(Button)findViewById(R.id.buzzer);

        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int status = event.getAction();
                switch (status){
                    case MotionEvent.ACTION_DOWN:
                        try{
                            mqttClient.publish(TOPIC,new MqttMessage("1".getBytes()));
                            break;
                        }catch (Exception e){}
                    case MotionEvent.ACTION_UP:
                        try{
                            mqttClient.publish(TOPIC,new MqttMessage("stop".getBytes()));
                            break;
                        }catch (Exception e){}
                    case MotionEvent.ACTION_MOVE:
                        try{
                            mqttClient.publish(TOPIC,new MqttMessage("1".getBytes()));
                            break;
                        }catch (Exception e){}
                }

                return false;
            }
        });


        btn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int status = event.getAction();
                switch (status){
                    case MotionEvent.ACTION_DOWN:
                        try{
                            mqttClient.publish(TOPIC,new MqttMessage("2".getBytes()));
                            break;
                        }catch (Exception e){}
                    case MotionEvent.ACTION_UP:
                        try{
                            mqttClient.publish(TOPIC,new MqttMessage("stop".getBytes()));
                            break;
                        }catch (Exception e){}
                    case MotionEvent.ACTION_MOVE:
                        try{
                            mqttClient.publish(TOPIC,new MqttMessage("2".getBytes()));
                            break;
                        }catch (Exception e){}
                }

                return false;
            }
        });
        btn3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int status = event.getAction();
                switch (status){
                    case MotionEvent.ACTION_DOWN:
                        try{
                            mqttClient.publish(TOPIC,new MqttMessage("3".getBytes()));
                            break;
                        }catch (Exception e){}
                    case MotionEvent.ACTION_UP:
                        try{
                            mqttClient.publish(TOPIC,new MqttMessage("stop".getBytes()));
                            break;
                        }catch (Exception e){}
                    case MotionEvent.ACTION_MOVE:
                        try{
                            mqttClient.publish(TOPIC,new MqttMessage("3".getBytes()));
                            break;
                        }catch (Exception e){}
                }

                return false;
            }
        });
        btn4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int status = event.getAction();
                switch (status){
                    case MotionEvent.ACTION_DOWN:
                        try{
                            mqttClient.publish(TOPIC,new MqttMessage("4".getBytes()));
                            break;
                        }catch (Exception e){}
                    case MotionEvent.ACTION_UP:
                        try{
                            mqttClient.publish(TOPIC,new MqttMessage("stop".getBytes()));
                            break;
                        }catch (Exception e){}
                    case MotionEvent.ACTION_MOVE:
                        try{
                            mqttClient.publish(TOPIC,new MqttMessage("4".getBytes()));
                            break;
                        }catch (Exception e){}
                }

                return false;
            }
        });
        btn5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int status = event.getAction();
                switch (status){
                    case MotionEvent.ACTION_DOWN:
                        try{
                            mqttClient.publish(TOPIC,new MqttMessage("5".getBytes()));
                            break;
                        }catch (Exception e){}
                    case MotionEvent.ACTION_UP:
                        try{
                            mqttClient.publish(TOPIC,new MqttMessage("stop".getBytes()));
                            break;
                        }catch (Exception e){}
                    case MotionEvent.ACTION_MOVE:
                        try{
                            mqttClient.publish(TOPIC,new MqttMessage("5".getBytes()));
                            break;
                        }catch (Exception e){}
                }

                return false;
            }
        });

        chatAdapter = new ChatAdapter();
        // chatListView.setAdapter(chatAdapter);
        try{
            connectMqtt();
        }catch(Exception e){
            Log.d(TAG,"MqttConnect Error");
        }
    }

    private void connectMqtt() throws MqttException {
        mqttClient = new MqttClient("tcp://192.168.143.47:1883", MqttClient.generateClientId(), new MemoryPersistence());

        MqttConnectOptions mqttConnectOptions;
        mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);
        mqttConnectOptions.setConnectionTimeout(60);
        mqttConnectOptions.setKeepAliveInterval(200);

        mqttClient.connect(mqttConnectOptions);
        mqttClient.subscribe(TOPIC);
        //mqttClient.subscribe(TOPIC1);
        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                Log.d(TAG,"Mqtt ReConnect");
                try{connectMqtt();}catch(Exception e){Log.d(TAG,"MqttReConnect Error");}
            }
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                JSONObject json = new JSONObject(new String(message.getPayload(), "UTF-8"));
                chatAdapter.add(new ChatItem(json.getString("id"), json.getString("content")));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        chatAdapter.notifyDataSetChanged();
                    }
                });
            }
            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }
}