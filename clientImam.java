import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class clientImam{
	public static void main(String args[]) throws IOException{
		String REMOTE_MESSAGE ="";
		StringBuffer content = new StringBuffer();
		if (args.length <= 2){
			System.out.println("Pemakaian: java simpleClient <IP-ADDR> <PORT> <MESSAGE>");
			System.exit(0);
		}
		try{
			String REMOTE_IP = args[0];
			int REMOTE_PORT = Integer.parseInt(args[1]); // ubah dari string ke integer
			// Jika message lebih dari satu kata maka akan ditampung ; String REMOTE_MESSAGE = args[2];
			for(int i = 2; i < args.length; i++){
				REMOTE_MESSAGE = REMOTE_MESSAGE + " "+ args[i];
			}
			Socket server = new Socket(REMOTE_IP, REMOTE_PORT);
			InputStream in = server.getInputStream();
			OutputStream out = server.getOutputStream();
			// write a byte
			out.write(42);
			// write a newline or carriage return delimited string
			PrintWriter pout = new PrintWriter( out, true );
			pout.println(REMOTE_MESSAGE);
			// read a byte
			byte back = (byte)in.read();
			// read a newline or carriage return delimited string
			BufferedReader bin = new BufferedReader( new InputStreamReader( in ) );
			Scanner s = new Scanner(bin);
			//String response = bin.readLine();
			//int jumBar = bin.count();
			//System.out.println(jumBar);
			//String response;
			/*while((response = bin.readLine()) != null){
				System.out.println(response);	
				//content.append(response).append("\n");
			}*/
			while (s.hasNextLine()){
				String text = s.nextLine();
				System.out.println(text);
				//if((s.nextLine()) == null){
				//	break;
				//}	
				if(bin != null)
					//bin.close();
					break;						
			}
			//System.out.print(content.toString());
			//System.out.println(response);
			// send a serialized Java object
			ObjectOutputStream oout = new ObjectOutputStream( out );
			//oout.writeObject( new java.util.Date() );
			oout.flush();
			//server.close();
		}
		catch(IOException e){
			System.out.println("Error");
		}
	}

}