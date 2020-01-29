package interfazDelJuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import hilosDelJuego.HiloBuffos;
import logicaJuego.Bloque;
import logicaJuego.Bomberman;
import logicaJuego.Entidad;
import logicaJuego.Mapa;

public class JPanelGraficoLocal extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon ladrillo;
	private ImageIcon bomba;
	private ImageIcon pared;	
	private ImageIcon roca;
	private ImageIcon fuego;
	private ImageIcon buffBombas;
	private ImageIcon buffRango;
	private ImageIcon buffVelocidad;
	private Mapa test;
	private ArrayList <Bomberman> players;
	private ImageIcon muertes;
	private String temporizador;
	
	
	public JPanelGraficoLocal(Mapa mapa) {
		players = new ArrayList<Bomberman>(); 
		ladrillo = new ImageIcon("./src/imagenes/pared.png");
		bomba = new ImageIcon("./src/imagenes/bomba.png");
		pared = new ImageIcon("./src/imagenes/pared.png");		
		roca = new ImageIcon("./src/imagenes/roca.png");
		bomba = new ImageIcon("./src/imagenes/bomba.png");
		fuego = new ImageIcon("./src/imagenes/fuego.gif");
		buffBombas = new ImageIcon("./src/imagenes/al.png");
		buffVelocidad = new ImageIcon("./src/imagenes/veloc.png");
		buffRango = new ImageIcon("./src/imagenes/bm.png");
		
		muertes = new ImageIcon("./src/imagenes/muertes.jpg");
		test = mapa;

		for(int i=0; i<test.getJugadoresLocal().size();i++) {
			players.add(mapa.getJugadoresLocal().get(i));
		}
		
		
		
	}

	public void paintComponent(Graphics g) {//dibujado de la ventana grafica bomberman
		super.paintComponent(g);
		int tam = 40;
		String tipo;
		Entidad tester;
		Entidad tester2;
		setBackground(new java.awt.Color(0, 129, 0));
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Entidad matrizMapa[][] = (Entidad[][])test.getMatrizMapa();
		
		g2d.drawImage(muertes.getImage(), 0, 600, null);

		
		for (int i = 0; i < matrizMapa.length; i++)
			g2d.drawImage(pared.getImage(), tam * (i), 0, tam, tam, null);
		for (int j = 0; j < matrizMapa[0].length; j++)
			g2d.drawImage(pared.getImage(), 0, tam * (j), tam, tam, null);
		for (int i = 0; i < matrizMapa.length; i++)
			g2d.drawImage(pared.getImage(), tam * (i), tam * (14), tam, tam, null);
		for (int j = 0; j < matrizMapa[0].length; j++)
			g2d.drawImage(pared.getImage(), 10 * tam, tam * (j), tam, tam, null);

		for (int i = 1; i < matrizMapa.length; i++)
			for (int j = 1; j < matrizMapa[0].length; j++) {
				tester = test.getPosicionMapa(i, j);
				tester2 = test.getPosicionMatrizCosas(i, j);
				if (tester!=null && tester.esBloque()) {
					tipo = ((Bloque) tester).queTipo();
					if (tipo.equals("obstaculo"))
						g2d.drawImage(ladrillo.getImage(), tam * (i), tam * (j), tam, tam, null);
					if (tipo.equals("piedra"))
						g2d.drawImage(roca.getImage(), tam * (i), tam * (j), tam, tam, null);
					if (tipo.equals("fuego") || tipo.equals("fuegoPiedra"))
						g2d.drawImage(fuego.getImage(), tam * (i), tam * (j), tam, tam, null);
					if(tipo.equals("masBombas"))
						g2d.drawImage(buffBombas.getImage(), tam * (i), tam * (j), tam, tam, null);	
					if(tipo.equals("velocidad"))
						g2d.drawImage(buffVelocidad.getImage(), tam * (i), tam * (j), tam, tam, null);	
					if(tipo.equals("rango"))
						g2d.drawImage(buffRango.getImage(), tam * (i), tam * (j), tam, tam, null);	
						
				}
				if (tester2.esBomba())
					g2d.drawImage(bomba.getImage(), tam * (i), tam * (j), tam, tam, null);
			}
		for (int i = 0; i < test.getJugadoresLocal().size(); i++) {
			
			g2d.drawImage(test.getJugadoresLocal().get(i).getBomberman().getImage(), (int) test.getJugadoresLocal().get(i).getPosGrafX(), (int) test.getJugadoresLocal().get(i).getPosGrafY(),tam, tam, null);
		}

		// muertes de los bomberman
		for (int i = 0; i < test.getJugadoresLocal().size(); i++) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Tahoma", Font.BOLD, 15));
			String muertes = (test.getJugadoresLocal().get(i).getNombre() + ": "
					+ (Integer) (test.getJugadoresLocal().get(i)).getCantMuertes()).toString();
			if(test.getJugadoresLocal().size()<=2)
				g2d.drawString(muertes, (i * 4 * 58) + 5 , (14 * 45) + 15);
			else {
				if(i<2)
					g2d.drawString(muertes, (i * 4 * 58) + 5 , (14 * 45) + 15);
				else
					g2d.drawString(muertes, ((i-2) * 4 * 58) + 5 , (14 * 45) + 50);
			}
			
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Tahoma", Font.BOLD, 15));
			String nombre= test.getJugadoresLocal().get(i).getNombre();
			if(nombre.length()>=7)
				nombre=nombre.substring(0, 7); 
			int var=nombre.length()/2;
			g2d.drawString(nombre, (test.getJugadoresLocal().get(i).getPosGrafX()+20) - (10*var),
				test.getJugadoresLocal().get(i).getPosGrafY() - 15);
		}
//		puntaje de los bomberman
		for(int i = 0 ; i < test.getJugadoresLocal().size() ; i++) {
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Tahoma", Font.BOLD, 15));
			String puntajes = (test.getJugadoresLocal().get(i).getNombre() + ": "
					+ (Integer) (test.getJugadoresLocal().get(i).getPuntaje())).toString();		
			if(test.getJugadoresLocal().size()<=2)
				g2d.drawString(puntajes, (i * 4 * 58) + 5, (14 * 45) + 95);
			else {
				if(i<2)
					g2d.drawString(puntajes, (i * 4 * 58) + 5, (14 * 45) + 95);
				else
					g2d.drawString(puntajes, ((i-2) * 4 * 58) + 5 , (14 * 45) + 130);
			}
			
		}
		if(temporizador!=null) {
			g.setColor(Color.RED);
			g2d.drawString("Tiempo: "+temporizador, 338 , 620);
		}
		
		
	}	
	public Mapa getTest() {
		return test;
	}
	public void setTest(Mapa test) {
		this.test = test;
	}	
	public ArrayList<Bomberman> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Bomberman> players) {
		
		this.players = players;
	}
	

	public String getTemporizador() {
		return temporizador;
	}

	public void setTemporizador(String temporizador) {
		
		this.temporizador = temporizador;
	}

	public void actualizarJugadores() {
		players = new ArrayList<Bomberman>(); 
		for(int i=0; i<test.getJugadoresLocal().size();i++) {
			players.add(test.getJugadoresLocal().get(i));
		}
	}
	
}