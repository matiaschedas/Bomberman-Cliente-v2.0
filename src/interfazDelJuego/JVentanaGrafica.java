package interfazDelJuego;

import java.awt.Color;

import javax.swing.JFrame;

import clienteLogica.Cliente;
import clienteLogica.VentanaChat;
import clienteLogica.VentanaLobby;
import hilosDelJuego.HiloBuffos;
import hilosDelJuego.IA;
import hilosDelJuego.Movimiento;
import hilosDelJuego.MovimientoLocal;
import hilosDelJuego.Repintar;
import logicaJuego.Mapa;

public class JVentanaGrafica extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanelGrafico contentPane;
	private JPanelGraficoLocal contentPaneLocal;
	

	private Repintar hiloDibujado;

	private Movimiento mov1;
	private MovimientoLocal mov2;
	private Mapa nuevo;
	private Cliente cliente;
	private int idSala;
	private boolean dueño;
	private VentanaChat chat;
	private VentanaLobby lobby;

	private HiloBuffos debuff1;
	private HiloBuffos debuff2;
	
	private IA HiloIA;
	
	
	public JVentanaGrafica(Mapa mapa, Cliente cliente) {
		super("Bomberman");
		setResizable(false);
		this.cliente = cliente;
		this.nuevo = mapa;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 443, 820);

		contentPane = new JPanelGrafico(nuevo);

		setBackground(Color.GREEN);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		hiloDibujado = new Repintar(this, contentPane);
		hiloDibujado.start();

		mov1 = new Movimiento(this, cliente);
		mov1.start();
	}
	
	public JVentanaGrafica(Mapa mapa) {
		super("Bomberman");
		setResizable(false);
		this.nuevo = mapa;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 443, 820);

		contentPaneLocal = new JPanelGraficoLocal(nuevo);

		setBackground(Color.GREEN);
		setContentPane(contentPaneLocal);
		setLocationRelativeTo(null);
		hiloDibujado = new Repintar(this);
		hiloDibujado.start();
		
		mov2 = new MovimientoLocal(this);
		mov2.start();
		
		
		if(nuevo.getCantJug()==1) {
			HiloIA = new IA(this);
			HiloIA.start();
		}
		
		debuff1=new HiloBuffos(contentPaneLocal.getTest().getJugadoresLocal().get(0));
		debuff1.start();

		debuff2=new HiloBuffos(contentPaneLocal.getTest().getJugadoresLocal().get(1));
		debuff2.start();
	}
	
	

	public JPanelGrafico getContentPane() {
		return contentPane;
	}

	public Mapa getNuevo() {
		return nuevo;
	}

	public void setNuevo(Mapa nuevo) {
		this.nuevo = nuevo;
		contentPane.setTest(nuevo);
		contentPane.actualizarJugadores();
	}

	public Repintar getHiloDibujado() {
		return hiloDibujado;
	}

	public void setHiloDibujado(Repintar hiloDibujado) {
		this.hiloDibujado = hiloDibujado;
	}

	public Movimiento getMov1() {
		return mov1;
	}

	public void setMov1(Movimiento mov1) {
		this.mov1 = mov1;
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

	public void setDueño(boolean dueño) {
		this.dueño = dueño;
	} 
	

	public VentanaChat getChat() {
		return chat;
	} 
	

	public void setChat(VentanaChat chat) {
		this.chat = chat;
	}
	

	public VentanaLobby getLobby() {
		return lobby;
	}

	public void setLobby(VentanaLobby lobby) {
		this.lobby = lobby;
	}
	
	public JPanelGraficoLocal getContentPaneLocal() {
		return contentPaneLocal;
	}

	public void setContentPaneLocal(JPanelGraficoLocal contentPaneLocal) {
		this.contentPaneLocal = contentPaneLocal;
	}


	@Override
	public void dispose() {
		this.setVisible(false);
		
		if(cliente!=null) {
			if (dueño)
				cliente.enviar("eliminarSala" + "/" + ((Integer) (idSala)).toString());
			else
				cliente.enviar("salirSala" + "/" + ((Integer) (idSala)).toString());
			chat.dispose();
		
			lobby.habilitarCamposTexto();
		}
	}



 
}
