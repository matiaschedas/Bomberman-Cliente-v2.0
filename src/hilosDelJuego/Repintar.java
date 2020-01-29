package hilosDelJuego;


import interfazDelJuego.JPanelGrafico;

import interfazDelJuego.JVentanaGrafica;
import logicaJuego.Bomberman;

public class Repintar extends Thread {
	private int fps;
	private JVentanaGrafica pane;
	private JPanelGrafico ventana;
	private Bomberman bomber1;
	private Bomberman bomber2;
	
	private boolean flag;

	public Repintar(JVentanaGrafica panel, JPanelGrafico contentPane) {
		this.pane = panel;
		ventana = contentPane;
		fps=0;
		flag=true;
	}
	public Repintar(JVentanaGrafica panel) {
		this.pane = panel;
		fps=0;
		flag=true;
		bomber1 = pane.getContentPaneLocal().getTest().getJugadoresLocal().get(0);
		bomber2= pane.getContentPaneLocal().getTest().getJugadoresLocal().get(1);
	}

	public void run() {
		long timer=System.currentTimeMillis();
       
        while (flag==true) {

                fps++;
                if(System.currentTimeMillis()-timer >=1000) {
                	if(ventana!=null) {
                		pane.setTitle("Bomberman || fps: "+fps + " || Players: " + ventana.getPlayers().size());
                		timer=System.currentTimeMillis();
                		fps=0;
                	}
                }
                
                this.pane.repaint();
                
                
                if(ventana==null){
                	bomber1.actualizacionGrafica(pane.getContentPaneLocal().getTest());			
    				bomber2.actualizacionGrafica(pane.getContentPaneLocal().getTest());
                }
                
                try {
                    Thread.sleep(14);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

	public void finalizarHilo() {
		flag=false;
	}

	
}
