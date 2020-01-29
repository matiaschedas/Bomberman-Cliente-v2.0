package clienteLogica;



import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField textUsuario;
	private JTextField txtContras;
	private JPasswordField textPass;
	private boolean hayTexto1;
	private boolean hayTexto2;
	private  Cliente cliente;
	private String ipText;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param ip 
	 */
	public VentanaLogin(String ip) {
		setResizable(false);
		this.ipText=ip;
		this.setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 382, 167);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setForeground(Color.GRAY);
		txtUsuario.setEditable(false);
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setText("Usuario:");
		txtUsuario.setBounds(10, 30, 80, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		textUsuario = new JTextField();
		textUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(textUsuario.getText().length()>9) {
					arg0.consume();
					JOptionPane.showMessageDialog(rootPane, "No mas de 10 caracteres");
				}
			}
		});
		textUsuario.setBounds(95, 30, 99, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		
		txtContras = new JTextField();
		txtContras.setText("Contrase\u00F1a:");
		txtContras.setHorizontalAlignment(SwingConstants.CENTER);
		txtContras.setForeground(Color.GRAY);
		txtContras.setEditable(false);
		txtContras.setColumns(10);
		txtContras.setBounds(10, 79, 80, 20);
		contentPane.add(txtContras);
		
		textPass = new JPasswordField();
		
		textPass.getDocument().addDocumentListener(new DocumentListener(){
			 public void insertUpdate(DocumentEvent evt){
			      actualizaEstadoBoton(evt);
			   }
			   private void actualizaEstadoBoton(DocumentEvent evt) {
				   Document doc = evt.getDocument();
				   hayTexto1 = doc.getLength() > 0;
				   boolean dos=false;
				   if(hayTexto2 && hayTexto1)
					   dos=true;
				   btnIniciarSesion.setEnabled(dos);
			   }
			   public void removeUpdate(DocumentEvent evt){
			      actualizaEstadoBoton(evt);
			   }
			   public void changedUpdate(DocumentEvent evt) {}
		});
		textUsuario.getDocument().addDocumentListener(new DocumentListener(){
			 public void insertUpdate(DocumentEvent evt){
			      actualizaEstadoBoton(evt);
			   }
			   private void actualizaEstadoBoton(DocumentEvent evt) {
				   Document doc = evt.getDocument();
				   hayTexto2 = doc.getLength() > 0;
				   boolean dos=false;
				   if(hayTexto2 && hayTexto1)
					   dos=true;
				   btnIniciarSesion.setEnabled(dos);
			   }
			   public void removeUpdate(DocumentEvent evt){
			      actualizaEstadoBoton(evt);
			   }
			   public void changedUpdate(DocumentEvent evt) {}
		});
		
		textPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		textPass.setColumns(10);
		textPass.setBounds(95, 79, 99, 20);
		contentPane.add(textPass);
		
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cliente = new Cliente(ipText,10000,textUsuario.getText());
				
				if(cliente.getSocketCliente()!=null) {
					new VentanaLobby(textUsuario.getText(),cliente).setVisible(true);;
					setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "Error al iniciar sesion: contraseña incorrecta o usuario inexistente");
				}
			}
		});
		btnIniciarSesion.setEnabled(false);
		btnIniciarSesion.setBounds(215, 48, 128, 32);
		contentPane.add(btnIniciarSesion); 
	}
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
