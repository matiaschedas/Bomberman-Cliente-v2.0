package hilosDelJuego;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import clienteLogica.Cliente;
import clienteLogica.VentanaConfigBotones;
import interfazDelJuego.JVentanaGrafica;



public class Movimiento extends Thread {
	
	private JVentanaGrafica mov;
	private Cliente cliente;
	private boolean derecha;
	private boolean izquierda;
	private boolean abajo;
	private boolean arriba;
	private boolean bombaflag;
	
	private boolean f1;
	private boolean f2;
	private boolean f3;
	private boolean f4;	
	
	VentanaConfigBotones ventanaBotones;
	

	public Movimiento(JVentanaGrafica mov,Cliente cliente){
		this.mov=mov;
		this.cliente=cliente;
		derecha=false;
		izquierda=false;
		arriba=false;
		bombaflag=false;
		
		f1=false;
		f2=false;
		f3=false;
		f4=false;
	}

	private class TAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent arg0) {
		
			int key = arg0.getKeyCode();		
	
			if (key == ventanaBotones.getArriba()) {
				if(arriba==false) {
					cliente.enviar("empiezaMoverseArriba");
					arriba=true;
				}
			}
			if (key == ventanaBotones.getAbajo()) {
				if(abajo==false) {
					cliente.enviar("empiezaMoverseAbajo");
					abajo=true;
				}
			}
			if(key == ventanaBotones.getDerecha()) {
				if(derecha==false) {
					cliente.enviar("empiezaMoverseDerecha");
					derecha=true;
				}
				
			}		
			if (key== ventanaBotones.getIzquierda()) {				
				if(izquierda==false) {
					cliente.enviar("empiezaMoverseIzquierda");
					izquierda=true;
				}	
			}
			if(key== ventanaBotones.getBombaKey()) {
				if(bombaflag==false) {
					cliente.enviar("ponerBomba");
					bombaflag=true;
				}
			}
			
			
			if(key== KeyEvent.VK_F1) {
				if(f1==false) {
					cliente.enviar("msj1");
					f1=true;
				}
			}
			if(key== KeyEvent.VK_F2) {
				if(f2==false) {
					cliente.enviar("msj2");
					f2=true;
				}
			}
			if(key== KeyEvent.VK_F3) {
				if(f3==false) {
					cliente.enviar("msj3");
					f3=true;
				}
			}
			if(key== KeyEvent.VK_F4) {
				if(f4==false) {
					cliente.enviar("msj4");
					f4=true;
				}
			}			

		}
		public void keyReleased(KeyEvent arg0) {
			int key = arg0.getKeyCode();
			
				if (key == ventanaBotones.getAbajo()) {
					cliente.enviar("dejaMoverseAbajo");	
					abajo=false;
				}
				if (key == ventanaBotones.getDerecha()) {
					cliente.enviar("dejaMoverseDerecha");	
					derecha=false;
				}
				if (key == ventanaBotones.getIzquierda()) {
					cliente.enviar("dejaMoverseIzquierda");
					izquierda=false;
				}
				if (key == ventanaBotones.getArriba()) {
					cliente.enviar("dejaMoverseArriba");
					arriba=false;
				}
				if (key == ventanaBotones.getBombaKey()) {
					cliente.enviar("dejaPonerBomba");
					bombaflag=false;
				}
				if(key== KeyEvent.VK_F1) {
					cliente.enviar("dejaDeMandarMensaje");
					f1=false;
				}
				if(key== KeyEvent.VK_F2) {
					cliente.enviar("dejaDeMandarMensaje");
					f2=false;
				}
				if(key== KeyEvent.VK_F3) {
					cliente.enviar("dejaDeMandarMensaje");
					f3=false;
				}
				if(key== KeyEvent.VK_F4) {
					cliente.enviar("dejaDeMandarMensaje");
					f4=false;
				}
			
			
		}
	}	

	public void run() {			
		mov.addKeyListener(new TAdapter());	
	}

	public void setDirecciones(VentanaConfigBotones ventanaBotones) {
		this.ventanaBotones=ventanaBotones;	
	}

}
