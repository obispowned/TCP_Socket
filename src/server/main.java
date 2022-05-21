package server;

import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException, InterruptedException {

		// TODO Auto-generated method stub
		Server serv = new Server();
		System.out.println("Iniciando servidor . . .");
		
		serv.iniciarServer();
/* Comento esta linea ya que el servidor tiene que estar escuchando continuamente, se podria meter dentro de una condicion para que el servidor deje de escuchar */
		//serv.finalizarServer();   
		
	}

}
