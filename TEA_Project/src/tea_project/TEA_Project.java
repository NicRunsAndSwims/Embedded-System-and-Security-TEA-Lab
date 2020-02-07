package tea_project;
import tea_project.TEA.*;
import gnu.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class TEA_Project {

    public static void main(String[] args) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException, IOException {
        
        int[] teakey = new int[4];
        int[] teadata = new int[2];
        String output_line;
        
        /* Set TEA Key */
        teakey[0] = 0x9D834B8D;
	teakey[1] = 0x93B59B9B;
	teakey[2] = 0x4B9B9BB5;
	teakey[3] = 0x9B9B9B9B;  
        /* Set TEA Data */
        teadata[0] = 0x4B9B9A9A;
	teadata[1] = 0x9B4B9B9A;
        
        
        
        System.out.println("\n\nTEA Encryption Algorithm Demo\n\n");
        
        System.out.println("Original Data (Word 0): " + Integer.toHexString(teadata[0]));
        System.out.println("Original Data (Word 1): " + Integer.toHexString(teadata[1]) + "\n");
        
        TEA test = new TEA(teadata, teakey);
        test.encrypt(teadata, teakey);
        
        System.out.println("Encrypted Data (Word 0): " + Integer.toHexString(test.getV0()));
        System.out.println("Encrypted Data (Word 1): " + Integer.toHexString(test.getV1()) + "\n");
        
        test.decrypt(teadata, teakey);
        
        System.out.println("Decrypted Data (Word 0): " + Integer.toHexString(test.getV0()));
        System.out.println("Decrypted Data (Word 1): " + Integer.toHexString(test.getV1()) + "\n");
        
        /* Acquire and Initialize Serial Port */
        
        CommPortIdentifier com = CommPortIdentifier.getPortIdentifier("COM4");
        SerialPort port = (SerialPort)com.open("PortOpener", 4);
        port.setSerialPortParams(19200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
        SerialPort.PARITY_NONE);
        
        /* Acquire Serial Port I/O Objects */
        
        OutputStream portOut = port.getOutputStream();
        InputStream portIn = port.getInputStream();
        Scanner in = new Scanner(portIn);
        PrintWriter out = new PrintWriter(portOut);
        
        /* Send Data to BIG8051 */
        
        output_line = "(" + teadata[0] + "," + teadata[1]+ ")";
        out.print(output_line);
        out.flush();
        
       
        
        
        
     
    }
    
}
