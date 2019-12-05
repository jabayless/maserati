import com.pi4j.io.gpio.*;

// variables will change: 
boolean sensorState, lastState;       // variables for reading the pushbutton status

void setup() {
  // initialize the sensor pin as an input:
  GpioController gpio = GpioFactory.getInstance();
  GpioPinDigitalInput sensorPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_12, "sensor", PinPullResistance.PULL_DOWN);
  boolean inputVal = inputPin.isHigh();

  Serial.begin(9600);	//to be changed
}

void loop(){
  // read the state of the pushbutton value:
  sensorState = sensorPin.isHigh();

  if (sensorState && !lastState) {
    System.out.println("Unbroken");
  }
  if (!sensorState && lastState) {
    System.out.println("Broken");
  }
  lastState = sensorState;
}