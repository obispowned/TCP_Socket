package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private final String HOST = "localhost";
	private final int PUERTO = 9876;
	private Socket socket;

	
	public Client() throws IOException{
		socket = new Socket(HOST, PUERTO);
	}
	
	public void iniciarCliente() throws  IOException{
		DataInputStream entrada = new DataInputStream(socket.getInputStream());
		DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
		Scanner sc = new Scanner(System.in);
		String mensajeServidor;
		int tareas = 0;
		int i =0;
		
		/*recibimos del Servidor cual es nuestro nombre*/
		mensajeServidor = entrada.readUTF();
		System.out.println(mensajeServidor);
		/*Enviamos nuestro nombrer*/
        String name = sc.nextLine();
        salida.writeUTF(name);
        System.out.println(entrada.readUTF());
        /*Nos pide numero de tareas*/
        do {								//Este do while obliga al cliente a poner un numero en formato string
        	System.out.println("Digite un numero de tareas");
        	name = sc.nextLine();
        }
        while (!(name.matches("[+-]?\\d*(\\.\\d+)?")));
        salida.writeUTF(name); 				//enviamos numero de tareas al servidor
        tareas = Integer.parseInt(name);	//parseamos el numero de tareas para poder hacer la condicion del bucle
        while (i < tareas)					//En este bucle recibimos y enviamos la informacion de las tareas
        {
        	mensajeServidor = entrada.readUTF();
    		System.out.println(mensajeServidor);
    		name = sc.nextLine();
            salida.writeUTF(name);
            mensajeServidor = entrada.readUTF();
    		System.out.println(mensajeServidor);
    		name = sc.nextLine();
            salida.writeUTF(name);
            i++;
        }
        i = 0;
        while (i <= tareas)					//En este bucle recibiremos toda la informacion del arraylist que tiene el servidor y lo imprimiremos por pantalla
        {
        	mensajeServidor = entrada.readUTF();
    		System.out.println(mensajeServidor);
    		i++;
        }
        
        System.out.println("Cliente finalizado");
		salida.close();
		entrada.close();
		socket.close();
	}
}
