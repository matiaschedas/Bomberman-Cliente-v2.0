package clienteLogica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaConfigBotones extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtConfiguracionDeLos;
	private JTextField arribaText;
	private JTextField abajoText;
	private JTextField derechaText;
	private JTextField izquierdaText;
	JTextArea textArea;
	private JTextField bombaText;
	private int arriba;
	private int abajo;
	private int derecha;
	private int izquierda;
	private int bombaKey;
	

	/**
	 * Create the frame.
	 */
	public VentanaConfigBotones() {
		arriba = KeyEvent.VK_UP;
		abajo = KeyEvent.VK_DOWN;
		izquierda = KeyEvent.VK_LEFT;
		derecha = KeyEvent.VK_RIGHT;
		bombaKey = KeyEvent.VK_SPACE;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtConfiguracionDeLos = new JTextField();
		txtConfiguracionDeLos.setBounds(5, 5, 424, 48);
		txtConfiguracionDeLos.setHorizontalAlignment(SwingConstants.CENTER);
		txtConfiguracionDeLos.setEditable(false);
		txtConfiguracionDeLos.setText("Configuracion de los botones");
		contentPane.add(txtConfiguracionDeLos);
		txtConfiguracionDeLos.setColumns(10);
		
		arribaText = new JTextField();
		arribaText.setHorizontalAlignment(SwingConstants.CENTER);
		arribaText.setEditable(false);
		arribaText.setBounds(153, 110, 122, 31);
		arribaText.setColumns(10);
		contentPane.add(arribaText);
		
		abajoText = new JTextField();
		abajoText.setHorizontalAlignment(SwingConstants.CENTER);
		abajoText.setEditable(false);
		abajoText.setColumns(10);
		abajoText.setBounds(153, 199, 122, 31);
		contentPane.add(abajoText);
		
		derechaText = new JTextField();
		derechaText.setHorizontalAlignment(SwingConstants.CENTER);
		derechaText.setEditable(false);
		derechaText.setColumns(10);
		derechaText.setBounds(329, 154, 100, 31);
		contentPane.add(derechaText);
		
		izquierdaText = new JTextField();
		izquierdaText.setHorizontalAlignment(SwingConstants.CENTER);
		izquierdaText.setEditable(false);
		izquierdaText.setColumns(10);
		izquierdaText.setBounds(5, 154, 98, 31);
		contentPane.add(izquierdaText);	
		
		arribaText.setText("UP");
		abajoText.setText("DOWN");
		derechaText.setText("RIGHT");
		izquierdaText.setText("LEFT");
		JButton btnArriba = new JButton("Arriba");
		btnArriba.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				arriba = e.getKeyCode();
				arribaText.setText(Character.toString(e.getKeyChar()));
			}
		});			

		btnArriba.setBounds(153, 65, 122, 34);
		contentPane.add(btnArriba);
		
		
		
		JButton btnAbajo = new JButton("Abajo");
		btnAbajo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				abajo = e.getKeyCode();
				abajoText.setText(Character.toString(e.getKeyChar()));
			}
		});		
		btnAbajo.setBounds(153, 152, 122, 34);
		contentPane.add(btnAbajo);
		
		
		
		JButton btnDerecha = new JButton("Derecha");
		btnDerecha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				derecha = e.getKeyCode();
				derechaText.setText(Character.toString(e.getKeyChar()));				
			}
		});
		
		
		btnDerecha.setBounds(329, 108, 100, 34);
		contentPane.add(btnDerecha);		
		
		
		JButton btnIzquierda = new JButton("Izquierda");
		btnIzquierda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				izquierda = e.getKeyCode();
				izquierdaText.setText(Character.toString(e.getKeyChar()));
			}
		});

		btnIzquierda.setBounds(5, 108, 100, 34);
		contentPane.add(btnIzquierda);		
		
		JButton bomba = new JButton("PonerBomba");
		bomba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		bomba.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				bombaKey = e.getKeyCode();
				bombaText.setText(Character.toString(e.getKeyChar()));
			}
		});
	
		bomba.setBounds(153, 249, 122, 34);
		contentPane.add(bomba);
		
		bombaText = new JTextField();
		bombaText.setText("SPACE");
		bombaText.setHorizontalAlignment(SwingConstants.CENTER);
		bombaText.setEditable(false);
		bombaText.setColumns(10);
		bombaText.setBounds(153, 294, 122, 31);
		contentPane.add(bombaText);
			

	}	
	
	public int getArriba() {
		return arriba;
	}
	public int getAbajo() {
		return abajo;
	}
	public int getDerecha() {
		return derecha;
	}
	public int getIzquierda() {
		return izquierda;
	}
	public int getBombaKey() {
		return bombaKey;
	}

	@Override
	public void dispose() {
		this.setVisible(false);
	}
}
