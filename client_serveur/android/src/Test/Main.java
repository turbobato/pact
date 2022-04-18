package Test;

import Actuator.Lamp;
import Sensors.LightSensor;

public class Main {
    public static void main(String[] args) throws Exception {
        LightSensor lightSensor = new LightSensor();
        lightSensor.sendGet();
    }
}
