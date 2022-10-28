package com.zh.ktapp.mqtt;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttReceivedMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.HashMap;

/**
 * @ClassName: MqttManager
 * @Description:
 * @Author: ZHW
 * @Date: 2022/10/20 下午6:21
 */
public class MqttManager {

    private static volatile MqttManager mInstance = null;
    private MqttCallback mCallback;
    public MqttClient client;
    private MqttConnectOptions conOpt;
    private Context context;
    public String[] topic;

    private MqttManager(Context context) {
        mCallback = new MqttCallbackBus(context);
        this.context = context;
    }

    public static MqttManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (MqttManager.class) {
                if (mInstance == null) {
                    mInstance = new MqttManager(context);
                }
            }
        }
        return mInstance;
    }


    /**
     * 释放单例, 及其所引用的资源
     */
    public static void release() {
        try {
            if (mInstance != null) {
                mInstance.disConnect();
                mInstance = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建Mqtt 连接
     *
     * @param brokerUrl Mqtt服务器地址(tcp://xxxx:1863)
     * @param userName  用户名
     * @param password  密码
     * @return
     */
    public boolean createConnect(String brokerUrl, String userName, String password) {
        topic = new String[]{Constant.PUBLISH_TOPIC};
        String deviceId = Tools.INSTANCE.getDeviceSn();
        if (client != null && client.isConnected()) {
            return true;
        }
        boolean flag = false;
        try {
            conOpt = new MqttConnectOptions();
            conOpt.setCleanSession(true); //不接收离线期间的消息  每次都是重新登陆
            conOpt.setAutomaticReconnect(true); //自动重连
            conOpt.setConnectionTimeout(10);//设置超时时间(单位：秒)
            conOpt.setKeepAliveInterval(10);//   // 心跳包发送间隔，单位：秒
            if (!TextUtils.isEmpty(password)) {
                conOpt.setPassword(password.toCharArray());
            }
            if (!TextUtils.isEmpty(userName)) {
                conOpt.setUserName(userName);
            }

            client = new MqttClient(brokerUrl, deviceId, new MemoryPersistence());
            client.setCallback(mCallback);
            flag = doConnect();
            client.subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
            //重连
            String msg = "exception_reconnect";
            //  EventBus.getDefault().post(msg);
            reconnect();
        }
        return flag;
    }

    /**
     * 建立连接
     *
     * @return
     */
    private boolean doConnect() {
        boolean flag = false;
        if (client != null) {
            try {
                client.connect(conOpt);
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean publish(String topicName, int qos, byte[] payload) {
        boolean flag = false;
        try {
            if (client == null) {
                createConnect(Constant.HOST, Constant.USERNAME, Constant.PASSWORD);
            }
            if (!client.isConnected()) {
                this.reconnect();
            }
            MqttMessage message = new MqttMessage(payload);
            message.setQos(qos);
            client.publish(topicName, message);
            flag = true;
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return flag;
    }


    private boolean subscribe(String topicName, int qos) {
        boolean flag = false;
        if (client != null && client.isConnected()) {
            try {
                client.subscribe(topicName, qos);
                flag = true;
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    private boolean subscribe(String[] topicName, int qos[]) {
        boolean flag = false;
        if (client != null && client.isConnected()) {
            try {
                client.subscribe(topicName, qos);
                flag = true;
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }


    /**
     * 取消连接
     *
     * @throws MqttException
     */
    public void disConnect() throws MqttException {
        if (client != null && client.isConnected()) {
            client.disconnect();
        }
    }

    /**
     * 关闭连接
     */
    public void close() {
        if (client != null && client.isConnected()) {
            try {
                client.disconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 重新连接
     */
    private void reconnect() {
        if (client != null && !client.isConnected()) {
            try {
                client.setCallback(mCallback);
                client.connect(conOpt);
                client.subscribe(topic);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    private class MqttCallbackBus implements MqttCallbackExtended {

        private Context mContext;

        public MqttCallbackBus(Context context) {
            this.mContext = context;
        }

        @Override
        public void connectComplete(boolean reconnect, String serverURI) {
            Log.e("MqttCallbackBus", "MQTT_connectComplete:");
            //断开连接必须重新订阅才能收到之前订阅的session连接的消息
            if (reconnect) {
                Log.e("MqttCallbackBus_重连订阅主题", "MQTT_connectComplete:");
                //这里是发送消息去重新订阅
                String msg = "reconnect";
                subscribe(topic, new int[1]);
                // EventBus.getDefault().postSticky(msg);
            }
        }

        @Override
        public void connectionLost(Throwable cause) { //掉线
            Log.e("MqttCallbackBus>>>", "MQTT_connectionLost 掉线原因:" + cause.getMessage());
            cause.printStackTrace();
        }

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            MqttReceivedMessage msg = (MqttReceivedMessage) message;
            String s = msg.toString();
            Log.e("MQTT_messageArrived_msg", "MQTT_msg" + topic);
            HashMap hs = new Gson().fromJson(s, HashMap.class);
            //  EventBus.getDefault().post(hs); //抛出收到的消息 eventbus响应做自己的业务处理
        }


        @Override
        public void deliveryComplete(IMqttDeliveryToken token) { //（发布）publish后会执行到这里,发送状态 token.isComplete()
            Log.e("MqttCallbackBus>>>", "MQTT_deliveryComplete:");
        }
    }

}
