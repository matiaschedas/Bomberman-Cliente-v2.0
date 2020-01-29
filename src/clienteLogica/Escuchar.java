package clienteLogica;


import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import com.google.gson.Gson;

import interfazDelJuego.JVentanaGrafica;
import logicaJuego.Bomberman;
import logicaJuego.Entidad;
import logicaJuego.Mapa;
import clienteLogica.Sala;

public class Escuchar extends Thread {
	private DataInputStream entrada;
	private VentanaLobby ventana;
	private JVentanaGrafica ventanaGrafica;
	private Mapa mapa;
	private VentanaChat ventanaChat;
	boolean flag;
	


	public Escuchar(DataInputStream entrada, VentanaLobby ventanaLobby) {
		// TODO Auto-generated constructor stub
		this.entrada = entrada;
		this.ventana = ventanaLobby;
		flag = false;
		
	}

	public void run() {

		while (flag == false) {
			String mensajeRecibido;
			ArrayList<Bomberman> bombers;
			ArrayList<ImageIcon> imagenes;

			try {
				mensajeRecibido = (String) entrada.readUTF();

				Mensaje mensajes = new Mensaje();
				Gson gson = new Gson();
				mensajes = gson.fromJson(mensajeRecibido, Mensaje.class);

				String keys = mensajes.getTipo();

				switch (keys) {
				case "crearSala":
					int id = mensajes.getId();
					ventana.setIdSala(id);
					if (ventana.getVentanaSala() != null)
						ventana.getVentanaSala().dispose();
					ventana.setVentanaSala(new VentanaSala(ventana.getNombre(), ventana.getTextField().getText(),
							ventana.getCliente(), id));
					ventana.getVentanaSala().setVisible(true);

					ventana.setBtnUnirseSala(false);

					break;

				case "listarSalas":
					ventana.getTextSalas().setText("");
					ventana.setTextSalas(mensajes.getMsj());
					break;

				case "unirseSala":
					Sala salita = mensajes.getSala();
					if (ventana.getUnirse() != null)
						ventana.getUnirse().setVisible(false);
					ventana.setUnirse(new VentanaUnirse(salita, ventana.getCliente()));
					ventana.getUnirse().setVisible(true);

					ventana.setBtnUnirseSala(false);
					ventana.getUnirse().setLobby(ventana);
					ventana.desactivarCamposDeTexto();
					
					break;

				case "errorUnirse":
					JOptionPane.showMessageDialog(null,
							"Error, la sala: " + mensajes.getMsj()+ " no existe o la contraseña es incorrecta");
					break;
				case "salaLlena":
					JOptionPane.showMessageDialog(null,
							"Error, la sala: " + mensajes.getMsj() + " esta llena");
					break;
				case "iniciarPartida":

					Entidad[][] matrizmapa;
					Entidad[][] matrizcosas;
					mapa = new Mapa();

					matrizmapa = mensajes.getMapa().descargarMatrizMapa();
					matrizcosas = mensajes.getMapa().descargarMatrizCosas();
					bombers = mensajes.getMapa().descargarBombers();
					imagenes = mensajes.getMapa().descargarImageIcons();
					
					mapa.setMatrizMapa(matrizmapa);
					mapa.setMatrizCosas(matrizcosas);
					mapa.setJugadores(bombers);
					for (int i = 0; i < mapa.getJugadores().size(); i++) {
						ImageIcon icono = new ImageIcon(imagenes.get(i).getDescription());
						mapa.getJugadores().get(i).setBomberman(icono);
					}
					ventanaChat = new VentanaChat();
					if(!mensajes.isEspectador()) {
						ventanaChat.setVisible(true);					
					}else ventanaChat.setVisible(false);

					ventanaGrafica = new JVentanaGrafica(mapa, ventana.getCliente());
					ventanaGrafica.getMov1().setDirecciones(ventana.getVentanaBotones());
					ventanaGrafica.setIdSala(mensajes.getId());
					ventanaGrafica.setDueño(mensajes.getPropietarioMsj().equals(ventana.getCliente().getNombre()));
					ventanaGrafica.setChat(ventanaChat);
					ventanaGrafica.setVisible(true);
					ventanaGrafica.setLobby(ventana);
					if (ventana.getUnirse() != null)
						ventana.getUnirse().setVisible(false);

					break;

				case "eliminarSala":
					if (ventana.getUnirse() != null) {
						ventana.getUnirse().setVisible(false);
						ventana.setVentanaSala(null);
					}
					if (ventanaGrafica != null) {
						ventanaGrafica.getHiloDibujado().finalizarHilo();
						ventanaGrafica.setVisible(false);
						ventanaGrafica.dispose();						
						ventanaChat.dispose();
						
						
					}
					ventana.getBtnUnirseSala().setVisible(true);
					ventana.habilitarCamposTexto();
					
					break;

				case "actualizarSala":
					if (ventana.getUnirse() != null) {
						ventana.getUnirse().setTextUsuarios(mensajes.getMsj());
					}
					if (ventana.getVentanaSala() != null) {
						ventana.getVentanaSala().setTextParticipantes(mensajes.getMsj());
					}
					break;

				case "actualizarMapa":
					mapa = new Mapa();
					matrizmapa = mensajes.getMapa().descargarMatrizMapa();
					matrizcosas = mensajes.getMapa().descargarMatrizCosas();
					bombers = mensajes.getMapa().descargarBombers();
					imagenes = mensajes.getMapa().descargarImageIcons();

					mapa.setMatrizMapa(matrizmapa);
					mapa.setMatrizCosas(matrizcosas);
					mapa.setJugadores(bombers);

					for (int i = 0; i < mapa.getJugadores().size(); i++) {
						ImageIcon icono = new ImageIcon(imagenes.get(i).getDescription());
						mapa.getJugadores().get(i).setBomberman(icono);
					}
					ventanaGrafica.setNuevo(mapa);

					break;

				case "mensajeChat":
					String msj = new String();
					msj = msj.concat(mensajes.getPropietarioMsj());
					msj = msj.concat(": ");
					msj = msj.concat(mensajes.getMsj());
					msj = msj.concat("\n");
					ventanaChat.getTextChat().append(msj);
					break;
				case "modoEspectador":		
					
					if(ventanaGrafica!=null) {
						ventanaGrafica.setVisible(false);
					}
					Entidad[][] matrizMapa;
					Entidad[][] matrizCosas;
					mapa = new Mapa();

					matrizMapa = mensajes.getMapa().descargarMatrizMapa();
					matrizCosas = mensajes.getMapa().descargarMatrizCosas();
					bombers = mensajes.getMapa().descargarBombers();
					imagenes = mensajes.getMapa().descargarImageIcons();

					mapa.setMatrizMapa(matrizMapa);
					mapa.setMatrizCosas(matrizCosas);
					mapa.setJugadores(bombers);
					for (int i = 0; i < mapa.getJugadores().size(); i++) {
						ImageIcon icono = new ImageIcon(imagenes.get(i).getDescription());
						mapa.getJugadores().get(i).setBomberman(icono);
					}
					ventanaChat = new VentanaChat();
					if (!mensajes.isEspectador()) {
						ventanaChat.setVisible(true);
					} else
						ventanaChat.setVisible(false);

					ventanaGrafica = new JVentanaGrafica(mapa, ventana.getCliente());
					ventanaGrafica.getMov1().setDirecciones(ventana.getVentanaBotones());
					ventanaGrafica.setIdSala(mensajes.getId());
					ventanaGrafica.setDueño(mensajes.getPropietarioMsj().equals(ventana.getCliente().getNombre()));
					ventanaGrafica.setChat(ventanaChat);
					ventanaGrafica.setVisible(true);
					ventanaGrafica.setLobby(ventana);
					if (ventana.getUnirse() != null)
						ventana.getUnirse().setVisible(false);
				
						
					break;
				case "finalizarPartida":

					class podio implements Comparable<podio> {
						int puntaje;
						String nombre;

						public podio(int puntaje, String nombre) {
							this.puntaje = puntaje;
							this.nombre = nombre;
						}

						public String devolverPodio() {
							return "Nombre: " + nombre + " Puntaje: " + puntaje + "\n";
						}
						

						@Override
						public int compareTo(podio o) {
							if (puntaje == o.puntaje)
								return 0;
							else if (puntaje > o.puntaje)
								return -1;
							else
								return 1;
						}
					}
					int puntajes[] = mensajes.getSala().getPuntajes();

					ArrayList<String> jugadores = mensajes.getSala().getJugadores();
					ArrayList<podio> podios = new ArrayList<>();

					for (int i = 0; i < mensajes.getSala().getJugadores().size(); i++) {
						podios.add(new podio(puntajes[i], jugadores.get(i)));
					}

					Collections.sort(podios);
					
					String podioDeGanadores = new String();
				
					for (int i = 0; i < podios.size(); i++) {
						podioDeGanadores = podioDeGanadores.concat((i + 1) + "° " + podios.get(i).devolverPodio());
					}

					JOptionPane.showMessageDialog(null, "Podio de los ganadores:" + "\n" + podioDeGanadores);

					break;
				case "temporizador":
					ventanaGrafica.getContentPane().setTemporizador(new String());
					ventanaGrafica.getContentPane().setTemporizador(mensajes.getMsj());
					break;

				}
				

			} catch (IOException e) {
				flag = true;
			}

		}

	}
	



}
