package com.github.iamniklas.liocore.network.mqtt;

import com.github.iamniklas.liocore.config.ProgramConfiguration;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;

public class MQTTPublisher {

    private final MemoryPersistence persistence = new MemoryPersistence();
    public IMqttClient client;
    MqttConnectOptions options = new MqttConnectOptions();

    public MQTTPublisher() {
        options.setAutomaticReconnect(ProgramConfiguration.configuration.mqttAutomaticReconnect);
        options.setCleanSession(ProgramConfiguration.configuration.mqttCleanSession);
        options.setConnectionTimeout(ProgramConfiguration.configuration.mqttConnectionTimeout);
        options.setUserName(ProgramConfiguration.configuration.mqttUser);
        options.setPassword(ProgramConfiguration.configuration.mqttPassword.toCharArray());
    }

    public int connect() {
        try {
            client = new MqttClient(ProgramConfiguration.configuration.mqttBrokerAddress, UUID.randomUUID().toString(), persistence);
            client.connect(options);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    public void disconnect() {
        try {
            client.disconnect();
        } catch (Exception ignored) { }
    }
}
