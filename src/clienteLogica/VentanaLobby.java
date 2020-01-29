package clienteLogica;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class VentanaLobby extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtSalas;
	private JTextField txtUsuario;
	private JTextField textUser;
	private Cliente cliente;
	private JTextField txtSalas_1;
	private JTextField txtNombreDeTu;
	private JTextField textCrearSala;
	private JTextArea textSalas;
	private int idSala;
	private VentanaSala ventanaSala;
	private VentanaUnirse unirse;
	private String nombre;
	private JTextField textIDUnirse;
	private JTextField txtIdSala;
	private JButton btnUnirseSala;
	private JTextField textPass;
	private JTextField txtContraseaopcional;
	private JTextField txtPass;
	private JTextField textPassUnirse;
	VentanaConfigBotones ventanaBotones;
	JButton btnCrearSala;
	
	/**
	 * Launch the application.
	 */

	

	/**
	 * Create the frame.
	 */
	public VentanaLobby(String nombre, Cliente cliente) {
		setResizable(false);
		this.cliente=cliente;
		this.nombre=nombre;
		this.setTitle("Lobby");
		ventanaBotones = new VentanaConfigBotones();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 705, 554);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 424, 465);
		getContentPane().add(scrollPane);
		
		textSalas = new JTextArea();
		scrollPane.setViewportView(textSalas);
		textSalas.setEditable(false);
		
			
			
		txtUsuario = new JTextField();
		txtUsuario.setForeground(Color.GRAY);
		txtUsuario.setEditable(false);
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setText("Usuario:");
		txtUsuario.setBounds(478, 8, 86, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		textUser = new JTextField();
		textUser.setHorizontalAlignment(SwingConstants.CENTER);
		textUser.setEditable(false);
		textUser.setBounds(574, 8, 86, 20);
		getContentPane().add(textUser);
		textUser.setColumns(10);
		textUser.setText(nombre);
		
		JButton btnListarSalas = new JButton("Listar Salas / Refresh");
		btnListarSalas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cliente.enviar("listarSalas");
			}
		});
		btnListarSalas.setBounds(497, 68, 182, 30);
		getContentPane().add(btnListarSalas);
		
		cliente.escuchar(this);
		
		btnUnirseSala = new JButton("Unirse Sala");
		
		btnCrearSala= new JButton("Crear Sala");
		btnCrearSala.setEnabled(false);
		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.enviar("crearSala"+"/"+textCrearSala.getText()+"/"+textPass.getText());
				btnUnirseSala.setEnabled(false);				
				textIDUnirse.setEditable(false);
				textPassUnirse.setEditable(false);
				textCrearSala.setEditable(false);
				textPass.setEditable(false);
				btnCrearSala.setEnabled(true);
			}
		});
		
		btnCrearSala.setBounds(497, 419, 182, 30);
		getContentPane().add(btnCrearSala);
		
		btnUnirseSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag=true;
					try {
						Integer.parseInt(textIDUnirse.getText());	
					} catch (Exception e2) {
						flag=false;
						JOptionPane.showMessageDialog(null, "Sala incorrecta (Solo numeros)");
					}
			if(flag==true)
				cliente.enviar("unirseSala"+"/"+textIDUnirse.getText()+"/"+textPassUnirse.getText());
			btnCrearSala.setEnabled(false); 
			}
			
		});
		btnUnirseSala.setEnabled(false);
		btnUnirseSala.setBounds(497, 192, 182, 30);
		getContentPane().add(btnUnirseSala);
		
		txtSalas_1 = new JTextField();
		txtSalas_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtSalas_1.setEditable(false);
		txtSalas_1.setText("Salas:");
		txtSalas_1.setBounds(117, 8, 86, 20);
		getContentPane().add(txtSalas_1);
		txtSalas_1.setColumns(10);
		
		txtNombreDeTu = new JTextField();
		txtNombreDeTu.setEditable(false);
		txtNombreDeTu.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreDeTu.setText("Nombre de tu Sala:");
		txtNombreDeTu.setBounds(497, 294, 182, 20);
		getContentPane().add(txtNombreDeTu);
		txtNombreDeTu.setColumns(10);
		
		textCrearSala = new JTextField();
		textCrearSala.setBounds(497, 325, 182, 20);
		getContentPane().add(textCrearSala);
		textCrearSala.setColumns(10);
		
		textIDUnirse = new JTextField();
		textIDUnirse.setBounds(593, 136, 86, 20);
		getContentPane().add(textIDUnirse);
		textIDUnirse.setColumns(10);
		
		txtIdSala = new JTextField();
		txtIdSala.setEditable(false);
		txtIdSala.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdSala.setText("ID Sala:");
		txtIdSala.setBounds(497, 136, 86, 20);
		getContentPane().add(txtIdSala);
		txtIdSala.setColumns(10);
		
		textPass = new JTextField();
		textPass.setColumns(10);
		textPass.setBounds(497, 387, 182, 20);
		getContentPane().add(textPass);
		
		txtContraseaopcional = new JTextField();
		txtContraseaopcional.setText("Pass (opcional):");
		txtContraseaopcional.setHorizontalAlignment(SwingConstants.CENTER);
		txtContraseaopcional.setEditable(false);
		txtContraseaopcional.setColumns(10);
		txtContraseaopcional.setBounds(497, 356, 182, 20);
		getContentPane().add(txtContraseaopcional);
		
		txtPass = new JTextField();
		txtPass.setText("Pass:");
		txtPass.setHorizontalAlignment(SwingConstants.CENTER);
		txtPass.setEditable(false);
		txtPass.setColumns(10);
		txtPass.setBounds(497, 162, 86, 20);
		getContentPane().add(txtPass);
		
		textPassUnirse = new JTextField();
		textPassUnirse.setColumns(10);
		textPassUnirse.setBounds(593, 162, 86, 20);
		getContentPane().add(textPassUnirse);
		
		Button Configuraciones = new Button("Configuraciones");
		Configuraciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				ventanaBotones.setVisible(true);
			}
		});
		Configuraciones.setBounds(564, 483, 115, 22);
		getContentPane().add(Configuraciones);
		
		textIDUnirse.getDocument().addDocumentListener(new DocumentListener(){
			 public void insertUpdate(DocumentEvent evt){
			      actualizaEstadoBoton(evt);
			   }
			   private void actualizaEstadoBoton(DocumentEvent evt) {
				   Document doc = evt.getDocument();
				   boolean hayTexto = doc.getLength() > 0;
				   if(ventanaSala!=null && !ventanaSala.isShowing())
                       btnUnirseSala.setEnabled(hayTexto);
                   if(unirse!=null && !unirse.isShowing())
                       btnUnirseSala.setEnabled(hayTexto); 
                   if(ventanaSala==null && unirse==null)
                      btnUnirseSala.setEnabled(hayTexto);                   
			   }
			   public void removeUpdate(DocumentEvent evt){
			      actualizaEstadoBoton(evt);
			   }
			   public void changedUpdate(DocumentEvent evt) {}
		});
		
		textCrearSala.getDocument().addDocumentListener(new DocumentListener(){
			 public void insertUpdate(DocumentEvent evt){
			      actualizaEstadoBoton(evt);
			   }
			   private void actualizaEstadoBoton(DocumentEvent evt) {
				   Document doc = evt.getDocument();
				   boolean hayTexto = doc.getLength() > 0;
				   btnCrearSala.setEnabled(hayTexto);
			   }
			   public void removeUpdate(DocumentEvent evt){
			      actualizaEstadoBoton(evt);
			   }
			   public void changedUpdate(DocumentEvent evt) {}
		});
		
		textCrearSala.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(textCrearSala.getText().length()>14) {
					arg0.consume();
					JOptionPane.showMessageDialog(rootPane, "No mas de 15 caracteres");
				}
			}
		});
	}
	
	@Override
	public void dispose() {
		if(unirse!=null)
			unirse.dispose();
		if(ventanaSala!=null)
			ventanaSala.dispose();
		this.setVisible(false);
	}
	
	public VentanaConfigBotones getVentanaBotones() {
		return ventanaBotones;
	}
	public void setVentanaBotones(VentanaConfigBotones ventanaBotones) {
		this.ventanaBotones = ventanaBotones;
	}
	public JTextField getTxtSalas() {
		return txtSalas;
	}

	public void setTxtSalas(JTextField txtSalas) {
		this.txtSalas = txtSalas;
	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public JTextField getTextUser() {
		return textUser;
	}

	public void setTextUser(JTextField textUser) {
		this.textUser = textUser;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public JTextField getTxtSalas_1() {
		return txtSalas_1;
	}

	public void setTxtSalas_1(JTextField txtSalas_1) {
		this.txtSalas_1 = txtSalas_1;
	}

	public JTextField getTxtNombreDeTu() {
		return txtNombreDeTu;
	}

	public void setTxtNombreDeTu(JTextField txtNombreDeTu) {
		this.txtNombreDeTu = txtNombreDeTu;
	}

	public JTextField getTextField() {
		return textCrearSala;
	}

	public void setTextField(JTextField textField) {
		this.textCrearSala = textField;
	}
	public JTextArea getTextSalas() {
		return textSalas;
	}

	public void setTextSalas(String textSalas) {
		this.textSalas.append(textSalas);
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

	public VentanaSala getVentanaSala() {
		return ventanaSala;
	}

	public void setVentanaSala(VentanaSala ventanaSala) {
		this.ventanaSala = ventanaSala;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public VentanaUnirse getUnirse() {
		return unirse;
	}
	public void setUnirse(VentanaUnirse unirse2) {
		this.unirse = unirse2;		
	}
	public JButton getBtnUnirseSala() {
		return btnUnirseSala;
	}
	public void setBtnUnirseSala(boolean flag) {
		this.btnUnirseSala.setEnabled(flag);  
	}
	public JTextField getTextCrearSala() {
		return textCrearSala;
	}
	public void setTextCrearSala(JTextField textCrearSala) {
		this.textCrearSala = textCrearSala;
	}
	public JTextField getTextIDUnirse() {
		return textIDUnirse;
	}
	public void setTextIDUnirse(JTextField textIDUnirse) {
		this.textIDUnirse = textIDUnirse;
	}
	public JTextField getTextPass() {
		return textPass;
	}
	public void setTextPass(JTextField textPass) {
		this.textPass = textPass;
	}
	public JTextField getTextPassUnirse() {
		return textPassUnirse;
	}
	public void setTextPassUnirse(JTextField textPassUnirse) {
		this.textPassUnirse = textPassUnirse;
	}
	public void habilitarCamposTexto() {
		textIDUnirse.setEditable(true);
		textPassUnirse.setEditable(true);
		textCrearSala.setEditable(true);
		textPass.setEditable(true);
		btnCrearSala.setEnabled(true);
	}
	public void desactivarCamposDeTexto() {
		textIDUnirse.setEditable(false);
		textPassUnirse.setEditable(false);
		textCrearSala.setEditable(false);
		textPass.setEditable(false);
	}
	
}

