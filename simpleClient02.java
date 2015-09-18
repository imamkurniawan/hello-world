import java.io.*;
import java.net.*;

public class simpleClient02{
	//public final static int REMOTE_PORT = 5000;
	public static void main(String args[]) throws Exception{
		Socket cl = null;
		BufferedReader is = null;
		DataOutputStream os = null;
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String userInput = "";
		String output = null;

		if (args.length <= 2){
			System.out.println("Pemakaian: java simpleClient <IP-ADDR> <PORT> <MESSAGE>");
			System.exit(0);
		}


		// Membuka koneksi ke server pada port REMOTE_PORT
		try{
			int REMOTE_PORT = Integer.parseInt(args[1]); // ubah dari string ke integer
			cl = new Socket(args[0], REMOTE_PORT);
			is = new BufferedReader(new InputStreamReader(cl.getInputStream()));
			os = new DataOutputStream(cl.getOutputStream());
		}
		catch (UnknownHostException e1){
			System.out.println("Unknown Host: " + e1);
			System.exit(0);
		}
		catch (IOException e2){
			System.out.println("Error io: " + e2);
			System.exit(0);
		}

		// Menulis ke server
		try{
			//
			//userInput = stdin.readLine();
			for(int i = 2; i < args.length; i++){
				userInput = userInput + " "+ args[i];
			}
			//userInput = args[2]; // mengambil nilai dari string ke-3 yang dimasukkan
			//System.out.print(userInput);
			os.writeBytes(userInput + "\n");
			System.out.print("\nKirim: "+ userInput +"\n");
		}
		catch (IOException ex){
			System.out.println("Error writing to server..." + ex);
		}

		// Menerima tanggapan dari server
		try{
			output = is.readLine();
			System.out.println("\nDari server: " + output +"\n");
		}
		catch (IOException e){
			e.printStackTrace();
			//System.out.println("....");
		}
		
		// close input stream, output stream dan koneksi
		try{
			is.close();
			os.close();
			cl.close();
		}
		catch (IOException x){
			System.out.println("Error writing... " + x);
		}
	
	}
}