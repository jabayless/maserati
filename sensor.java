import com.pi4j.io.gpio.*;

// variables will change: 
boolean sensorState, lastState;       // variables for reading the pushbutton status

public static void main(String args[]) throws InterruptedException, IOException {
  // initialize the sensor pin as an input:
  GpioController gpio = GpioFactory.getInstance();
  GpioPinDigitalInput sensorPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_12, "sensor", PinPullResistance.PULL_DOWN);
  boolean inputVal = inputPin.isHigh();
  
  final Console console = new Console();
  console.promtForExit();

  final Serial serial = SerialFactory.createInstance();
  serial.addListener(new SerialDataEventListener() {
	  @Override
	  public void dataReceived(SerialDataEvent event) {
		try {
			console.println("[HEX DATA] " + event.getHexByteString());
			console.println("[ASCII DATA]" + event.getAsciiString());
		}  
		catch (IOException e) {
			e.printStackTrace();
		}
	  }
	};
	try {
		SerialConfig config = new SerialConfig();
	}
	catch (IOException e) {
		e.printStackTrace();
	}
	
	config.device(SerialPort.getDefaultPort())
		.baud(Baud._38400)
		.dataBits(DataBits._8)
		.parity(Parity.NONE)
		.stopBits(StopBits._1)
		.flowControl(FlowControl.NONE);
		
	if (args.length > 0) {
		config = CommandArgumentParser.getSerialConfig(config, args);
	}
	
	serial.open(config);
	
	while(console.isRunning()) {
		try {
			SensorState = sensorPin.isHigh();
			if (sensorState && !lastState) {
				System.out.println("Working")
			}
			if (!sensorState && lastState) {
				System.out.println("Broken");
			}
			lastState = sensorState;
		}
		catch(IOException ex) {
			console.println("SERIAL SETUP FAILED: " + ex.getMessage());
			return;
		}
	}
}