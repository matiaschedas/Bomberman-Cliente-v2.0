package hilosDelJuego;

import logicaJuego.Bloque;
import logicaJuego.Bomba;
import logicaJuego.Bomberman;
import logicaJuego.Mapa;

public class AlejarseBomba extends Thread{
	
	private Bomba bomba;
	private IA hiloIA;
	private Bomberman bomber;
	private String dir;
	private Mapa mapa;
	
	public AlejarseBomba(Bomberman bomber2, Bomba bomba2, String dir2, IA hilo, Mapa mapa2) {
		hiloIA=hilo;
		bomba=bomba2;
		bomber=bomber2;
		dir=dir2;
		mapa=mapa2;
	}
	public void run() {
		
		hiloIA.setAndando(false);
		
		int xVieja = bomber.getPosX();
		int yVieja = bomber.getPosY();
		int distancia=0;
		boolean flagIzq=false;
		boolean flagDer=false;
		boolean flagArriba=false;
		boolean flagAbajo=false;
		
		
		while(distancia<bomba.getRango()+2 && flagIzq==false && flagDer==false && 
				flagArriba == false && flagAbajo==false){
		
			
			if(xVieja!=bomber.getPosX()) 
				distancia=Math.abs(bomba.getX()-bomber.getPosX());
			
			if(yVieja!=bomber.getPosY()) 
				distancia=Math.abs(bomba.getY()-bomber.getPosY());
			
			
			switch (dir) {
				case "arriba":
					if(mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY()-1).esBloque())
						if(((Bloque)mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY()-1)).queTipo().equals("transitable")) {
							
							bomber.setVelY(-bomber.getVelocidad());
						}
						else
							flagArriba=true;
					break;
				case "abajo":
					if(mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY()+1).esBloque())
						if(((Bloque)mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY()+1)).queTipo().equals("transitable")) {
							
							bomber.setVelY(+bomber.getVelocidad());
						}
						else
							flagAbajo=true;
					break;
				case "izq":
					if(mapa.getPosicionMapa(bomber.getPosX()-1, bomber.getPosY()).esBloque())
						if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()-1, bomber.getPosY())).queTipo().equals("transitable")) {
							bomber.setVelX(-bomber.getVelocidad());
						}
						else
							flagIzq=true;
					break;
				case "der":	
					if(mapa.getPosicionMapa(bomber.getPosX()+1, bomber.getPosY()).esBloque())
						if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()+1, bomber.getPosY())).queTipo().equals("transitable")) {
							bomber.setVelX(+bomber.getVelocidad());
						}
						else
							flagDer=true;
					break;
				
			};
			if(flagArriba==true) {
				int posY= -10;
				while(posY!=bomber.getPosY()) {
					posY=bomber.getPosY();
					bomber.setVelY(-bomber.getVelocidad());
				}
			}
			if(flagDer==true) {
				int posX= -10;
				while(posX!=bomber.getPosX()) {
					posX=bomber.getPosX();
					bomber.setVelX(+bomber.getVelocidad());
				}
			}
			if(flagAbajo==true) {
				int posY= -10;
				while(posY!=bomber.getPosY()) {
					posY=bomber.getPosY();
					bomber.setVelY(+bomber.getVelocidad());
				}
			}
			if(flagIzq==true) {
				int posX= -10;
				while(posX!=bomber.getPosX()) {
					posX=bomber.getPosX();
					bomber.setVelX(-bomber.getVelocidad());
				}
			}
			
			
		}
		bomber.setVelX(0);
		bomber.setVelY(0);
		try {
			Thread.sleep(3200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hiloIA.setAndando(true);
		hiloIA.run();
		
	}
}
