package org.example;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;
import java.util.ArrayList;

public class MqttPublishSample {

    public static void main(String[] args) {

        String topic        = "MQTT Examples";
        int qos             = 2;
        String broker       = "tcp://127.0.0.1:1883";
        String clientId     = "JavaSample Sub";

        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            client.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Connected to "+broker+" with client ID "+ clientId);

            // Subscribe to the requested topic
            // The QoS specified is the maximum level that messages will be sent to the client at.
            // For instance if QoS 1 is specified, any messages originally published at QoS 2 will
            // be downgraded to 1 when delivering to the client but messages published at 1 and 0
            // will be received at the same level they were published at.
            System.out.println("Subscribing to topic \""+topic+"\" qos "+qos);
            var listener = new Listener();
            client.subscribe(topic, qos, listener);

            // Continue waiting for messages until the Enter is pressed
            System.out.println("Press <Enter> to exit");
            System.out.println("Checking msg");
            MqttMessage msg = listener.getNextMessage();
            System.out.println(msg);
            try {
                System.in.read();
            } catch (IOException e) {
                System.out.println("Something failed :(");
            }
            client.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
}