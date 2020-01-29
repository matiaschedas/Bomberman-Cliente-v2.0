package clienteLogica;

import java.io.Serializable;
import java.util.ArrayList;

import logicaJuego.Bomberman;
import logicaJuego.Mapa;

public class Sala implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String nombreLider;
	private ArrayList<String> jugadores;
	private int puntajes[];
	private int idSala;
	private int cantJugadores;
	private Mapa mapa;
	private boolean partidaComenzada;
	private String pass;
	private ArrayList<String>espectadores;


	public Sala(String n, int id,String nombreLider, String pass) {
		nombre=n;
		idSala=id;
		cantJugadores=1;
		this.nombreLider=nombreLider;
		jugadores=new ArrayList<>();
		mapa=new Mapa();
		jugadores.add(nombreLider);
		partidaComenzada=false;
		this.pass=pass;		
		this.puntajes = new int[4];
		this.espectadores=new ArrayList<String>();
	}
	
	public void eliminarJugador(String nombre) {
		jugadores.remove(nombre);
		cantJugadores--;
	}
	
	@Override
	public String toString() {
		if(pass.equals(""))
			return String.format("|%-25s|", nombre)+String.format("id: |%-5d|", idSala)+String.format("jugadores: |%-5d|", cantJugadores);
		else
			return String.format("|%-25s|", nombre)+String.format("id: |%-5d|", idSala)+String.format("jugadores: |%-5d|", cantJugadores)+" Privada";
	}
	
	public String obtenerJugadores() {
		String jugadores = new String();
		
		if(this.jugadores.size()>0) {			
			jugadores =  jugadores.concat(this.jugadores.get(0)+" (Lider)"+"\n");
		}
		
		for(int i = 1; i < this.jugadores.size();i++) {
			jugadores = jugadores.concat(this.jugadores.get(i)+"\n");
		}
		return jugadores;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getIdSala() {
		return idSala;
	}
	
	public int getCantJugadores() {
		return cantJugadores;
	}
	
	public void agregarJugador(String jugador) {
		if(!partidaComenzada) {
			this.jugadores.add(jugador);
			cantJugadores++;
		}
		else 
			this.espectadores.add(jugador);
	}

	public ArrayList<String> getJugadores() {
		return jugadores;
	}
	public String getNombreLider() {
		return nombreLider;
	}

	public void setNombreLider(String nombreLider) {
		this.nombreLider = nombreLider;
	}

	public Mapa getMapa() {
		return mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	public void iniciarPartida() {
		
		
	}
	
	public boolean isPartidaComenzada() {
		return partidaComenzada;
	}

	public void setPartidaComenzada(boolean partidaComenzada) {
		this.partidaComenzada = partidaComenzada;
	}

	public Bomberman obtenerBomber(String nombre) {
		int pos = -1;
		for (int i = 0; i < jugadores.size(); i++) {
			if (nombre.equals(jugadores.get(i)))
				pos = i;
		}
		if (pos != -1)
			return mapa.getJugadores().get(pos);
		return null;
	}

	public void finalizarRonda() {
		
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int[] getPuntajes() {
		return puntajes;
	}

	public void setPuntajes(int[] puntajes) {
		this.puntajes = puntajes;
	}
 
}