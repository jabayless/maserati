import java.io.*;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Date;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.mail.smtp.*;

public static void main(String[] args) {
	ProcessBuilder pb = new ProcessBuilder();
	pb.command("bash", "-c", "ls");		//runs shell command
	//pb.command("path/to/hello.sh");	//runs shell script
	
	try {
		Process process = processBuilder.start();	
		StringBuilder output = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			output.append(line + "\n");
		}
		int exit = process.waitFor();
		if (exit == 0) {
			System.out.println("Successfully executed shell command");
			System.out.println(output);
			System.exit(0);
		}
		else {
			System.out.println("Unsuccessfully executed shell command");
			System.exit(1);
		}
	}
	catch (IOException e) {
		e.printStackTrace();
	}
	catch (InterruptedException e) {
		e.printStackTrace();
	}
}
