package com.zh.ktapp.mqtt;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.zh.ktapp.base.LogUtils;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @ClassName: MqttManagerp
 * @Description: TODO
 * @Author: ZHW
 * @Date: 2022/10/24 下午2:31
 */
public class MqttManagerp {
    private static boolean initFirst = true;//是否第一次初始化mqtt标识符
    private static String host = "tcp://47.106.172.221:8081";
    private static String userName; //mqtt用户名
    private static String passWord; //mqtt登陆密码
    private static MqttManagerp manager;
    private static MqttClient mqttClient;
    private static MqttConnectOptions options;
    private static String topic;//订阅的主题
    private static String clientId; //客户端id

    private static Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                LogUtils.INSTANCE.d(msg.obj.toString());
               // EventBus.getDefault().post(new MessageEventBean(AppConstants.MQTT_EVENT_TYPE, (String) msg.obj));
            } else if (msg.what == 2) {
                LogUtils.INSTANCE.d("连接成功");
                try {
                    LogUtils.INSTANCE.d("订阅的主题:" + topic);
                    mqttClient.subscribe(topic, 0);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (msg.what == 3) {
                LogUtils.INSTANCE.d("连接失败，系统正在重连");
            }
        }
    };

    private MqttManagerp() {

    }
    private static MqttCallback myMqttCallback = new MqttCallback(){

        @Override
        public void messageArrived(String topic, MqttMessage message){
            //subscribe后得到的消息会执行到这里面
            LogUtils.INSTANCE.d("messageArrived topic:"+topic);
            Message msg = new Message();
            msg.what = 1;
            msg.obj = message.toString();
            handler.sendMessage(msg);
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {
            //publish后会执行到这里
            LogUtils.INSTANCE.d("deliveryComplete");
        }

        @Override
        public void connectionLost(Throwable cause) {
            LogUtils.INSTANCE.d("connectionLost cause = "+cause);
            //连接丢失后，一般在这里面进行重连
            try{
                LogUtils.INSTANCE.d("mqtt重连");
                manager.startReconnect();
            }catch (Exception e){
                LogUtils.INSTANCE.d("Exception = "+ e);
                e.printStackTrace();
            }
        }

    };

    public static MqttManagerp getInstance() {
        if (manager == null) {
            manager = new MqttManagerp();
        }
        return manager;
    }

    public void initConnection() {
        if (initFirst){
            LogUtils.INSTANCE.d("第一次调用initConnection");
            try {
                clientId = Tools.INSTANCE.getDeviceSn() + System.currentTimeMillis();//客户端标识符(本机mac地址+当前时间ms)
                userName = Constant.USERNAME;//用户名
                passWord = Constant.PASSWORD;//密码
                topic = userName;
                //host为主机名；clientid即连接MQTT的客户端ID，是客户端的唯一标识符；MemoryPersistence设置clientid的保存形式，默认为以内存保存
                mqttClient = new MqttClient(host, clientId, new MemoryPersistence());
                //MQTT的连接设置
                options = new MqttConnectOptions();
                //设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
                options.setCleanSession(true);
                //断开后，是否自动连接
                options.setAutomaticReconnect(true);
                //设置连接的用户名
                options.setUserName(userName);
                //设置连接的密码
                options.setPassword(passWord.toCharArray());
                // 设置超时时间 单位为秒
                options.setConnectionTimeout(10);
                // 设置会话心跳时间 单位为秒 服务器会每隔1.5*(20)秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
                options.setKeepAliveInterval(20);
                //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
                //            options.setWill(topic,"close".getBytes(),2,true);
                //设置回调
                mqttClient.setCallback(myMqttCallback);
                LogUtils.INSTANCE.d("clientId: "+clientId +", userName: "+userName+", passWord: "+passWord+", topic: "+topic);
                //设置标识符状态
                initFirst = false;
                //mqtt第一次连接
                manager.startReconnect();
            } catch (Exception e) {
                LogUtils.INSTANCE.d("initConnection Exception: " + e);
                e.printStackTrace();
            }
        }else {
            LogUtils.INSTANCE.d("网络重连后调用initConnection");
            manager.startReconnect();
        }
    }

    public  void startReconnect() {

        if (NetworkUtils.isConnected()){

            if (!mqttClient.isConnected()) {
                //重新连接
                connect();
                LogUtils.INSTANCE.d("mqtt连接结束");
            }else {
                LogUtils.INSTANCE.d("mqttClient.isConnected");
            }
        }else {
            LogUtils.INSTANCE.d("网络不可用");
        }

    }

    public void sendMsg(String msg) {
        LogUtils.INSTANCE.d("sendMsg");
        if (mqttClient != null && mqttClient.isConnected()) {
            try {
                LogUtils.INSTANCE.d("发送的主题:" + Constant.PUBLISH_TOPIC);
                String topic = Constant.PUBLISH_TOPIC;
                LogUtils.INSTANCE.d(topic);
                byte[] msgBytes = msg.getBytes();
                LogUtils.INSTANCE.d("0000");
                mqttClient.publish(topic, msgBytes, 0, false);
                LogUtils.INSTANCE.d("11111111111111111");
            } catch (MqttException e) {
                LogUtils.INSTANCE.d(e.toString());
            }
        }
    }

    //发布的主题设为pubTopic = "owh" + Preferences.getUserAccount();
    //发布主题（发布主题和订阅主题应设为不同值）
    public void publish(String topicName, String payload) {
        if (mqttClient != null && mqttClient.isConnected()) {
            // 创建和配置一个消息
            MqttMessage message = new MqttMessage(payload.getBytes());
            message.setPayload(payload.getBytes());
            message.setQos(0);
            try {
                LogUtils.INSTANCE.d("1111");
                mqttClient.publish(topicName, message);
                LogUtils.INSTANCE.d("2222");
            } catch (MqttException e) {
                LogUtils.INSTANCE.d("publish : " + e.toString());
            }
        }
    }

    private void connect() {
        ThreadUtils.executeBySingle(new ThreadUtils.SimpleTask<Object>() {
            @Override
            public Object doInBackground() throws Throwable {
                try {
                    mqttClient.connect(options);
                    Message msg = Message.obtain();
                    msg.what = 2;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = Message.obtain();
                    msg.what = 3;
                    handler.sendMessage(msg);
                }
                return null;
            }

            @Override
            public void onSuccess(Object result) {

            }
        });
    }

    //断开连接
    public static void mqttDisconnect(){
        if(mqttClient !=null && mqttClient.isConnected()){
            try{
                mqttClient.disconnect();
            }catch (MqttException e){
                LogUtils.INSTANCE.d("mqtt disconnect error");
                e.printStackTrace();
            }
        }
    }
    /**
     * 释放资源
     */
    public void release(){
        if (manager!=null) {
            manager=null;
        }
    }
}
