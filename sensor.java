import com.pi4j.io.gpio.*;

// variables will change: 
boolean sensorState, lastState;       // variables for reading the pushbutton status

public static void main(String args[]) throws InterruptedException, IOException {
  // initialize the sensor pin as an input:
  final GpioController gpio = GpioFactory.getInstance();	//create gpio controller to communicate with pins
  GpioPinDigitalInput sensorPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_12, "sensor", PinPullResistance.PULL_DOWN);	//mapping the sensor pin
  boolean inputVal = sensorPin.isHigh();
  
  final Console console = new Console();	//creates console object to communicate with user
  console.promtForExit();	//allows user to exit with CTRL+C

  final Serial serial = SerialFactory.createInstance();
  serial.addListener(new SerialDataEventListener() {
	  @Override
	  public void dataReceived(SerialDataEvent event) {	//tests if data is working
		try {
			console.println("[HEX DATA]: " + event.getHexByteString());
			console.println("[ASCII DATA]: " + event.getAsciiString());
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
	
	config.device(SerialPort.getDefaultPort())	//attributes of the port
		.baud(Baud._38400)
		.dataBits(DataBits._8)
		.parity(Parity.NONE)
		.stopBits(StopBits._1)
		.flowControl(FlowControl.NONE);
		
	if (args.length > 0) {
		config = CommandArgumentParser.getSerialConfig(config, args);
	}
	
	serial.open(config);	//starts serial communication
	
	//loop to detect state
	while(console.isRunning()) {
		try {
			sensorState = sensorPin.isHigh();
			if (sensorState && !lastState) {
				serial.println("Working")
			}
			if (!sensorState && lastState) {
				serial.println("Broken");
			}
			lastState = sensorState;
		}
		catch(IOException ex) {
			console.println("SERIAL SETUP FAILED: " + ex.getMessage());
			return;
		}
	}
}