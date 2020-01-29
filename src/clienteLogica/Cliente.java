package clienteLogica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {	
	private int puerto;
	private Socket socketCliente;
	private String nombre;	
	private DataOutputStream salida;

	public Cliente(String ipServer, int puerto, String nombre) {
		this.puerto = puerto;
		this.nombre = nombre;
		try {
			socketCliente = new Socket(ipServer, puerto);
			salida = new DataOutputStream(socketCliente.getOutputStream());
			salida.writeUTF(nombre);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void enviar(String texto) {
		try {
			salida = new DataOutputStream(socketCliente.getOutputStream());
			salida.writeUTF(texto);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public void escuchar(VentanaLobby ventanaLobby) {
        DataInputStream entrada;
        try {
            entrada = new DataInputStream(socketCliente.getInputStream());
            Escuchar hilo = new Escuchar(entrada, ventanaLobby);
            hilo.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	public void cerrar () {
		try {
			socketCliente.close();
			salida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getNombre() {
		return nombre;
	}


	public int getPuerto() {
		return puerto;
	}
	public Socket getSocketCliente() {
		return socketCliente;
	}
	
}