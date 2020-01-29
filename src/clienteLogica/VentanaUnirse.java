package clienteLogica;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import clienteLogica.Sala;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaUnirse extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombreSala;
	private JTextField textNombreSala;
	private JTextField txtIdSala;
	private JTextField textIDSala;
	private JTextField txtEsperandoQueEl;
	private Sala sala;
	private Cliente cliente;
	private JTextArea textUsuarios;
	private VentanaLobby lobby;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 * @param sala 
	 */
	public VentanaUnirse(Sala sala, Cliente cliente) {
		setResizable(false);
		this.setTitle("Sala");
		this.sala=sala;
		this.cliente=cliente;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 575, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 34, 213, 386);
		contentPane.add(scrollPane);
		
		textUsuarios = new JTextArea();
		textUsuarios.setEditable(false);
		scrollPane.setViewportView(textUsuarios);
		
		txtNombreSala = new JTextField();
		txtNombreSala.setEditable(false);
		txtNombreSala.setText("Nombre Sala:");
		txtNombreSala.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreSala.setBounds(277, 37, 86, 20);
		contentPane.add(txtNombreSala);
		txtNombreSala.setColumns(10);
		
		textNombreSala = new JTextField();
		textNombreSala.setEditable(false);
		textNombreSala.setBounds(373, 37, 86, 20);
		contentPane.add(textNombreSala);
		textNombreSala.setColumns(10);
		
		txtIdSala = new JTextField();
		txtIdSala.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdSala.setEditable(false);
		txtIdSala.setText("ID Sala:");
		txtIdSala.setBounds(277, 68, 86, 20);
		contentPane.add(txtIdSala);
		txtIdSala.setColumns(10);
		
		textIDSala = new JTextField();
		textIDSala.setEditable(false);
		textIDSala.setBounds(373, 68, 86, 20);
		contentPane.add(textIDSala);
		textIDSala.setColumns(10);
		
		txtEsperandoQueEl = new JTextField();
		txtEsperandoQueEl.setHorizontalAlignment(SwingConstants.CENTER);
		txtEsperandoQueEl.setEditable(false);
		txtEsperandoQueEl.setText("Esperando que el lider comience la partida...");
		txtEsperandoQueEl.setBounds(163, 6, 244, 20);
		contentPane.add(txtEsperandoQueEl);
		txtEsperandoQueEl.setColumns(10);
		
		textIDSala.setText(((Integer)this.sala.getIdSala()).toString());
		textNombreSala.setText(this.sala.getNombre());
		textUsuarios.append(this.sala.getNombreLider()+" (Lider)\n");
		for(int i=1; i<this.sala.getJugadores().size();i++) {
			textUsuarios.append(this.sala.getJugadores().get(i));
			textUsuarios.append("\n");
		}
	
	}
	@Override
	public void dispose() {
		this.setVisible(false);
		cliente.enviar("salirSala"+"/"+textIDSala.getText());
		lobby.habilitarCamposTexto();
	}
	public JTextArea getTextUsuarios() {
		return textUsuarios;
	}
	public void setTextUsuarios(String textUsuarios) {
		this.textUsuarios.setText(textUsuarios);
	}
	public VentanaLobby getLobby() {
		return lobby;
	}
	public void setLobby(VentanaLobby lobby) {
		this.lobby = lobby;
	}
	
	
}
