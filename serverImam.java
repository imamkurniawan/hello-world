import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class serverImam{
	public static void main(String args[]) throws IOException {
		BufferedReader bufferedReader = null;
		StringBuffer content = new StringBuffer();
		OutputStream out = null;
		if (args.length == 0){
			System.out.println("Pemakaian: java serverImam <PORT>");
			System.exit(0);
		}
		try {
			int i = 0;
			int TESTPORT = Integer.parseInt(args[0]);
			System.out.println("Running in PORT "+ TESTPORT +"...");
			ServerSocket listener = new ServerSocket( TESTPORT );
			while ( i<20 ) {
				Socket client = listener.accept();  // wait for connection
				InputStream in = client.getInputStream();
				out = client.getOutputStream();			
				// read a byte
				byte someByte = (byte)in.read();
				// read a newline or carriage-return-delimited string
				BufferedReader bin = new BufferedReader( new InputStreamReader( in ) );
				String someString = bin.readLine();
				System.out.println("\nRequest From Client: " +someString + "\n");
				// write a byte
				out.write(43);
				PrintWriter pout = null; //new PrintWriter( out, true );
				pout = new PrintWriter( out, true ); //
				// if client message = "1" then will read a.txt
				if(someString.compareTo(" 1") == 0){
					
					try{						
						String text = null;
						// open file a.txt
						bufferedReader = new BufferedReader(new FileReader("a.txt"));
						while((text = bufferedReader.readLine()) != null){
							content.append(text).append("\n");
						}
					}
					catch(FileNotFoundException e){
						System.out.println("File Tak ditemukan!");
					}
					finally{
						try{
							if(bufferedReader != null)
								bufferedReader.close();
						}
						catch(IOException ioe){
							System.out.println("ioe");
						}
					}
					System.out.print(content.toString());					
					pout.println(content.toString());
				}
				// if client message = "2" the will read b.txt
				if(someString.compareTo(" 2") == 0){
					//pout.println("Baca file 2");
					try{

						String text = null;
						// open file b.txt
						bufferedReader = new BufferedReader(new FileReader("b.txt"));
						while((text = bufferedReader.readLine()) != null){
							content.append(text).append("\n");
						}
					}
					catch(FileNotFoundException e){
						System.out.println("File Tak ditemukan!");
					}
					finally{
						try{
							if(bufferedReader != null)
								bufferedReader.close();
						}
						catch(IOException ioe){
							System.out.println("ioe");
						}
					}
					System.out.print(content.toString());
					pout.println(content.toString());	
				}
				else{
					pout.println("Server says: [1] untuk buka a.txt dan [2] untuk b.txt \n");
					pout.println("# untuk membuka file a.txt \nCommand: java clientImam <IP> <PORT> 1");
					pout.println("# untuk membuka file b.txt \nCommand: java clientImam <IP> <PORT> 2");
				}
				pout.flush();
				//pout.println("Goodbye!");
				// read a serialized Java object
				ObjectInputStream oin = new ObjectInputStream( in );
				//Date date = (Date)oin.readObject();
				//client.close();
				i++;
			} // END while 
			//listener.close();
		}
		catch (IOException e ) { 
			System.out.println("...");
		}
		//catch (ClassNotFoundException e2 ) { 
		//	System.out.println("...");
		//}
	}
}
