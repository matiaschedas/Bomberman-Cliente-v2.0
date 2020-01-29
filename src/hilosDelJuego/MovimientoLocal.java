package hilosDelJuego;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import interfazDelJuego.JVentanaGrafica;
import logicaJuego.Bomberman;



public class MovimientoLocal extends Thread {
	
	private JVentanaGrafica mov;
	
	
	Bomberman bomber1;
	Bomberman bomber2;
	
	

	public MovimientoLocal(JVentanaGrafica mov){
		this.mov=mov;
		bomber1=mov.getContentPaneLocal().getTest().getJugadoresLocal().get(0);
		if(mov.getNuevo().getCantJug()==2)
			bomber2=mov.getContentPaneLocal().getTest().getJugadoresLocal().get(1);
	}

	private class TAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent arg0) {
			
			int key = arg0.getKeyCode();		
				
			if (key == KeyEvent.VK_UP) {
				bomber1.setVelY(-bomber1.getVelocidad());
			}
			
			if (key == KeyEvent.VK_DOWN) {
				bomber1.setVelY(bomber1.getVelocidad());
			}
			if(key == KeyEvent.VK_LEFT) {
				bomber1.setVelX(-bomber1.getVelocidad());
				
			}		
			if (key== KeyEvent.VK_RIGHT) {				
				bomber1.setVelX(bomber1.getVelocidad());
			}
			
			if(key== KeyEvent.VK_ENTER) {
				Bombita hiloBomba = new Bombita(bomber1,mov.getContentPaneLocal().getTest());
				hiloBomba.start();
			}
			if (mov.getNuevo().getCantJug() == 2) {

				if (key == KeyEvent.VK_W) {
					bomber2.setVelY(-bomber2.getVelocidad());
				}

				if (key == KeyEvent.VK_S) {
					bomber2.setVelY(bomber2.getVelocidad());
				}
				if (key == KeyEvent.VK_A) {
					bomber2.setVelX(-bomber2.getVelocidad());

				}
				if (key == KeyEvent.VK_D) {
					bomber2.setVelX(bomber2.getVelocidad());
				}

				if (key == KeyEvent.VK_SPACE) {
					Bombita hiloBomba = new Bombita(bomber2, mov.getContentPaneLocal().getTest());
					hiloBomba.start();

				}
			
			}
		}
		public void keyReleased(KeyEvent arg0) {
			int key = arg0.getKeyCode();
			
			if (key == KeyEvent.VK_UP) {
				bomber1.setVelY(0);
			}
				
			if (key == KeyEvent.VK_DOWN) {
				bomber1.setVelY(0);
			}
			if(key == KeyEvent.VK_LEFT) {
				bomber1.setVelX(0);
				
			}		
			if (key== KeyEvent.VK_RIGHT) {				
				bomber1.setVelX(0);
			}
			if (mov.getNuevo().getCantJug() == 2) {
				if (key == KeyEvent.VK_W) {
					bomber2.setVelY(0);
				}

				if (key == KeyEvent.VK_S) {
					bomber2.setVelY(0);
				}
				if (key == KeyEvent.VK_A) {
					bomber2.setVelX(0);

				}
				if (key == KeyEvent.VK_D) {
					bomber2.setVelX(0);
				}
			}
			
		}
	}	

	public void run() {			
		mov.addKeyListener(new TAdapter());	
	}

}
