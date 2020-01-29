package clienteLogica;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaChat extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textChat;
	private DefaultCaret caret;


	/**
	 * Create the frame.
	 */
	public VentanaChat() {
		this.setTitle("Chat de la partida");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(1200, 180, 315, 678);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 11, 299, 628);
		contentPane.add(scrollPane);
		
		textChat = new JTextArea();
		textChat.setEditable(false);
		scrollPane.setViewportView(textChat);
		caret = (DefaultCaret)textChat.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	}

	public JTextArea getTextChat() {
		return textChat;
	}

	public void setTextChat(JTextArea textChat) {
		this.textChat = textChat;
	}
	@Override
	public void dispose() {
		this.setVisible(false);
		
	}
	
	
}
