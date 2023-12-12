package org.example;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;

public class Listener implements IMqttMessageListener {

    final ArrayList<MqttMessage> messages;

    public Listener() {
        messages = new ArrayList<MqttMessage>();
    }

    public MqttMessage getNextMessage() {
        synchronized (messages) {
            if (messages.size() == 0) {
                try {
                    messages.wait(1000);
                }
                catch (InterruptedException e) {
                    // empty
                }
            }

            if (messages.size() == 0) {
                return null;
            }
            return messages.remove(0);
        }
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {

        System.out.println("message arrived: '" + new String(message.getPayload()) + "' "+this.hashCode()+
                " " + (message.isDuplicate() ? "duplicate" : ""));

        if (!message.isDuplicate()) {
            synchronized (messages) {
                messages.add(message);
                messages.notifyAll();
            }
        }
    }
}
