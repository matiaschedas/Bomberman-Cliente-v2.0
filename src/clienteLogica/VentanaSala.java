package clienteLogica;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaSala extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt;
	private JTextField txtNombreSala;
	private JTextField textNombreSala;
	private JTextField txtIdSala;
	private JTextField textIDSala;
	private Cliente cliente;
	private int idSala;
	private JTextArea textParticipantes;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param nombreSala 
	 * @param idSala 
	 */
	public VentanaSala(String nombreLider, String nombreSala, Cliente cliente, int idSala) {
		setResizable(false);
		this.setTitle("Sala");
		this.cliente=cliente;
		this.idSala=idSala;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 413, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textParticipantes = new JTextArea();
		textParticipantes.setEditable(false);
		textParticipantes.setBounds(10, 43, 179, 276);
		contentPane.add(textParticipantes);
		
		txt = new JTextField();
		txt.setEditable(false);
		txt.setHorizontalAlignment(SwingConstants.CENTER);
		txt.setText("Participantes:");
		txt.setBounds(54, 11, 86, 20);
		contentPane.add(txt);
		txt.setColumns(10);
		
		textParticipantes.append(nombreLider+" (Lider)");
		
		
		JButton btnIniciarPartida = new JButton("Iniciar Partida");
		btnIniciarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.enviar("iniciarPartida");
				setVisible(false);
			}
		});
		btnIniciarPartida.setBounds(206, 94, 189, 94);
		contentPane.add(btnIniciarPartida);
		
		txtNombreSala = new JTextField();
		txtNombreSala.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreSala.setEditable(false);
		txtNombreSala.setText("Nombre Sala:");
		txtNombreSala.setBounds(206, 11, 86, 20);
		contentPane.add(txtNombreSala);
		txtNombreSala.setColumns(10);
		
		textNombreSala = new JTextField();
		textNombreSala.setEditable(false);
		textNombreSala.setBounds(302, 11, 86, 20);
		contentPane.add(textNombreSala);
		textNombreSala.setColumns(10);
		
		txtIdSala = new JTextField();
		txtIdSala.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdSala.setEditable(false);
		txtIdSala.setText("ID Sala:");
		txtIdSala.setBounds(206, 45, 86, 20);
		contentPane.add(txtIdSala);
		txtIdSala.setColumns(10);
		
		textIDSala = new JTextField();
		textIDSala.setEditable(false);
		textIDSala.setBounds(302, 45, 86, 20);
		contentPane.add(textIDSala);
		textIDSala.setColumns(10);
		
		textNombreSala.setText(nombreSala);
		textIDSala.setText(((Integer)(idSala)).toString());
	}
	
	public int getIdSala() {
		return idSala;
	}

	@Override
	public void dispose() {
		this.setVisible(false);
		
		cliente.enviar("eliminarSala"+"/"+((Integer)(idSala)).toString());
	}
	public JTextField getTextIDSala() {
		return textIDSala;
	}
	public void setTextIDSala(int id) {
		this.textIDSala.setText(((Integer)id).toString());
	}
	public JTextArea getTextParticipantes() {
		return textParticipantes;
	}
	public void setTextParticipantes(String textParticipantes) {
		this.textParticipantes.setText(textParticipantes);
	
	}
}
