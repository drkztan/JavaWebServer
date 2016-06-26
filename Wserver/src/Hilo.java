import java.io.*;
import java.net.*;
import java.util.regex.*;
import java.util.zip.*;

public class Hilo extends Thread {

	ServerSocket serverSocket;
	Socket clientSocket;
	InputStream is;
	OutputStream os, os2;
	ZipEntry zip;
	
	String bufferRequest = null, filename;

	static int BUFFER = 2048;

	int caracter;

	public BufferedReader bufferLectura;
	


	public Hilo(Socket client) throws IOException {
		this.clientSocket = client;
	}

	public void run() {


		System.out.println("Estamos ejecutando desde un thread!");

		try {

			os = clientSocket.getOutputStream();
			os2 = clientSocket.getOutputStream();
			is = clientSocket.getInputStream();
			bufferLectura = new BufferedReader(new InputStreamReader(is));
			bufferRequest = bufferLectura.readLine();

			
			System.out.println("\nRequest recibida: " + bufferRequest);

			String filename = bufferRequest.split("/")[1].split(" HTTP")[0].split("\\?")[0];
			
			is = new FileInputStream(filename);

			String opciones = "";
			try {
				opciones = bufferRequest.split("\\?")[1].split(" HTTP")[0];
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
			}

			System.out.println("\n\n heeeeeeeeeey \n\n");

			Boolean ASC = false;
			Boolean ZIP = false;
			Boolean GZIP = false;
			Boolean PNG = false;
			Boolean HTML = false;

			// parsear opciones
			// parsear opciones
			// parsear opciones
			// parsear opciones

			try {

				Pattern patronASC = Pattern.compile("asc=([^&]+)");
				Pattern patronZIP = Pattern.compile("zip=([^&]+)");
				Pattern patronGZIP = Pattern.compile("gzip=([^&]+)");
				Pattern patronPNG = Pattern.compile(".png");
				Pattern patronHTML = Pattern.compile(".html");
				
				Matcher matcherASC = patronASC.matcher(opciones);
				Matcher matcherZIP = patronZIP.matcher(opciones);
				Matcher matcherGZIP = patronGZIP.matcher(opciones);
				Matcher matcherPNG = patronPNG.matcher(filename);
				Matcher matcherHTML = patronHTML.matcher(filename);

				if (matcherASC.find()) {
					ASC = Boolean.valueOf(matcherASC.group(1));
					System.out.println("\nOpci�n ASC habilitada\n");

				}
				if (matcherZIP.find()) {
					ZIP = Boolean.valueOf(matcherZIP.group(1));
					System.out.println("\nOpci�n ZIP habilitada\n");

				}
				if (matcherGZIP.find()) {
					GZIP = Boolean.valueOf(matcherGZIP.group(1));
					System.out.println("\nOpci�n GZIP habilitada\n");

				}
				if (matcherPNG.find()) {
					PNG = Boolean.valueOf(matcherPNG.group(0));
					System.out.println("\nOpci�n PNG habilitada\n");

				}
				if (matcherHTML.find()) {
					HTML = Boolean.valueOf(matcherHTML.group(0));
					System.out.println("\nOpci�n HTML habilitada\n");

				}
				
			} catch (PatternSyntaxException patternex) {
				System.out.println("Error al parsear las opciones del HTTP request!!!\n");
			}
			
			// parsear opciones
			// parsear opciones
			// parsear opciones
			// parsear opciones

			//Mensajes de consola para comprobar estados
			//Mensajes de consola para comprobar estados
			//Mensajes de consola para comprobar estados
			//Mensajes de consola para comprobar estados
			//Mensajes de consola para comprobar estados
			
			String sirviendo = "Sirviendo " + filename;
			String consoleLogASC = sirviendo + " con opci�n ASC";
			String consoleLogZIP = sirviendo + " con opci�n ZIP";
			String consoleLogGZIP = sirviendo + " con opci�n GZIP";
			String consoleLogPNG = sirviendo + " como PNG";
			String consoleLogHTML = sirviendo + " como HTML";

			
			String ok = "servido: ";
			String okASC = ok + "ASC";
			String okZIP = ok + "ZIP";
			String okGZIP = ok + "GZIP";
			String okPNG = ok + "PNG";
			String okHTML = ok + "HTML";

			
			//Mensajes de consola para comprobar estados
			//Mensajes de consola para comprobar estados
			//Mensajes de consola para comprobar estados
			//Mensajes de consola para comprobar estados
			//Mensajes de consola para comprobar estados

			// Cabeceras
			// Cabeceras
			// Cabeceras
			// Cabeceras
			// Cabeceras
			
			String cabeceraOK = "HTTP/1.1 200 OK\n";
			
			String cabeceraPNG = cabeceraOK + "Content-Type: image/png\n" + "Content-Disposition: filename=\""
					+ filename + ".png\"\n\n";

			String cabeceraZIP = cabeceraOK + "Content-Type: application/zip\n" + "Content-Disposition: filename=\""
					+ filename + ".zip\"\n\n";

			String cabeceraGZIP = cabeceraOK + "Content-Type: application/gzip\n" + "Content-Disposition: filename=\""
					+ filename + ".gz\"\n\n";
			
			String cabeceraHTML = cabeceraOK + "Content-Type: text/html\n"  + "Content-Disposition: filename=\""
					+ filename + ".html\"\n\n";
			
			String respuestaError = "HTTP/1.1 404 Not Found\n\n" + "<!DOCTYPE><HTML><HEAD>Recurso: "
					+ filename + " no encontrado</HEAD></HTML>"; 
			
			String cabeceraFinal = "";

			// Cabeceras
			// Cabeceras
			// Cabeceras
			// Cabeceras
			// Cabeceras

			// filename= "index.html";

			System.out.println("Index of get: " + bufferRequest.indexOf("GET"));

			if ((bufferRequest.indexOf("GET") != -1)) {
				System.out.println("Nos han pedido el archivo con filename: " + filename);
				try {
<<<<<<< HEAD
					
					if (HTML){
						cabeceraFinal = cabeceraHTML;
						
						if (ASC){
							is = new AsciiInputStream(is);
						}						
=======

					if (ASC) {

						System.out.println(consoleLogASC);
						is3 = new FileInputStream(filename);
						is = new AsciiInputStream(is3);
						os.write(cabeceraHTML.getBytes());
						while ((caracter = is.read()) != -1) {
							os.write(caracter);
						}

						os.write("\n\n".getBytes(), 0, "\n\n".getBytes().length);
						// os.write(System.getProperty("line.separator").getBytes());

						System.out.println(okASC);
						// os.flush();
>>>>>>> parent of 438a6c3... pre-rework
					}
					
					if (PNG){
						cabeceraFinal = cabeceraPNG;
					}
					
					if (ZIP){
						
<<<<<<< HEAD
						cabeceraFinal = cabeceraZIP;
						os = new ZipOutputStream(os);
						zip = new ZipEntry(filename);
						((ZipOutputStream) os ).putNextEntry(zip);
=======
						zos.putNextEntry(new ZipEntry(filename));
						byte buffer[] = new byte[BUFFER];
						int length;
						while ((length = is2.read(buffer)) > 0) {
							zos.write(buffer, 0, length);
						}
						os2.write("\n\n".getBytes());
//						zos.flush();
						zos.closeEntry();

//						 zos.finish();
						 os2.flush();
						// zos.close();

						System.out.println(okZIP);
>>>>>>> parent of 438a6c3... pre-rework
					}
					
					if (GZIP){
						
						cabeceraFinal = cabeceraGZIP;
						os = new GZIPOutputStream(os);
						
					}
					
					os.write(cabeceraFinal.getBytes());
					os.flush();
					
					int carac;
					while ((carac= is.read()) !=-1)
			    	{
			    		os.write(carac);
			    	}
					
					/*
					while ((carac = is.read())!=-1){
						if (carac!=-2){
							os.write(carac);
						}
					}
					*/
						
						
				} catch (FileNotFoundException excep) {
					os.write(respuestaError.getBytes());
					excep.printStackTrace();
				}
			}
<<<<<<< HEAD
			
			
			/*
			os.close();
=======

>>>>>>> parent of 438a6c3... pre-rework
			is.close();
//			zos.closeEntry();
//			zos.close();
			// clientSocket.close();
			 */
			
			is.close();
			os.close();
			clientSocket.close();
			
		} catch (IOException excep) {
			excep.printStackTrace();
		}
		

		
		System.out.println("\nDONE\n");
	}
}