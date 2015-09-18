import java.io.*;
import java.net.*;

public class simpleServer02{
//	public final static int TESTPORT = 10000;

	public static void main(String args[]){
		ServerSocket checkServer = null;
		String line;
		BufferedReader is = null;
		DataOutputStream os = null;
		Socket clientSocket = null;

		if (args.length == 0){
			System.out.println("Pemakaian: java simpleServer02 <PORT>");
			System.exit(0);
		}

		try{
			int TESTPORT = Integer.parseInt(args[0]);			
			checkServer = new ServerSocket(TESTPORT);
			System.out.println("Aplikasi Server hidup...");
			System.out.println("PORT active: " + TESTPORT);
		}
		catch (IOException e){
			System.out.println(e);
		}

		try{
			clientSocket = checkServer.accept();
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			os = new DataOutputStream(clientSocket.getOutputStream());
		}
		catch (Exception ei){
			ei.printStackTrace();
		}

		try{
			line = is.readLine();
			System.out.println("Terima: " + line);
			if(line.compareTo(" salam") == 0){
				String balas = "Salam juga";
				os.writeBytes(balas);
				System.out.println("Balas: " + balas);
			}
			else{
				os.writeBytes("Maaf, saya tidak mengerti");
				System.out.println("Balas: Gak ngerti euy!");
			}
		}
		catch (IOException e){
			System.out.println(e);
		}
		try{
			os.close();
			is.close();
			clientSocket.close();
		}
		catch (IOException ic){
			ic.printStackTrace();
		}
		
	}
}