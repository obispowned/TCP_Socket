package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	private final int PUERTO = 9876;
	private ServerSocket serverSocket;
	private Socket socket;
	
	public Server()throws  IOException{
		serverSocket = new ServerSocket(PUERTO);
		socket = new Socket();
	}
	
	public void iniciarServer() throws  IOException{
			int i = 0;
			int tareas = 0;
			String descripcion;
			String estado;
			String mensajeCliente;
			ArrayList<Tarea> listaTareas = new ArrayList<Tarea>();

			
			
		while(true) {
			/*A la espera de un cliente*/
			System.out.println("Esperando a algun cliente...");
			socket = serverSocket.accept(); 						
			DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
			DataInputStream entrada = new DataInputStream(socket.getInputStream());
			/*Nos comunicamos con el cliente*/
			salida.writeUTF("Cual es tu nombre?");
			mensajeCliente = entrada.readUTF();
			salida.writeUTF("Bienvenido al Servidor, " + mensajeCliente + ", cuantas tareas vamos a realizar?");
			mensajeCliente = entrada.readUTF();	
			tareas = Integer.parseInt(mensajeCliente); 		//Parseamos el numero de tareas para usarlo como variable int en el bucle
			if (listaTareas.size() != 0) 					//Debemos resetear el arraylist ya que si hubo un cliente anterior, se solaparan las tareas con las del cliente actual
				listaTareas.clear();
			i = 0;
			while (i < tareas)								//Bucle para pedirle informacion de cada tarea
			{
				salida.writeUTF("Tarea " + (i+1) +", indique la DESCRIPCION:");
				descripcion = entrada.readUTF();
				salida.writeUTF("Tarea " + (i+1) +", indique el ESTADO:");
				estado = entrada.readUTF();
				listaTareas.add(new Tarea(descripcion, estado)); //agregamos la informacion de las tareas a nuestro objeto listaTareas
				i++;
			}
			salida.writeUTF("Las tareas han sido registradas:");
			i = 1;
			for (Tarea tarea: listaTareas) { 					//otro bucle para mandar al usuario la informacion de las tareas (guardadas en el arraylist en el bucle anterior)
	            salida.writeUTF("Tarea: "+ i + " descripcion: " + tarea.getDescripcion() + ", estado: " + tarea.getEstado());
	            i++;
	        }
			System.out.println("Comunicacion finalizada");
			System.out.println("Fin de la conexion");
			socket.close();
		}
	}
	
	public void finalizarServer() throws  IOException{
		serverSocket.close();
	}
	
}
