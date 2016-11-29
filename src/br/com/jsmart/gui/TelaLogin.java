package br.com.jsmart.gui;

import java.awt.Image;

import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import br.com.jsmart.dao.ClienteDao;
import br.com.jsmart.model.Cliente;
import br.com.jsmart.util.JPAUtil;
import br.com.jsmart.util.VerificaSenha;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JLabel lblBoasVindas;
	private JLabel logo;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	private JButton btnConfirma;
	private JButton btnCancela;
	private JLabel lblSenha;
	private JLabel lblUsurio;
	private EntityManager em ;

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setTitle("JSmart Market - O Carrinho de Supermercado Inteligente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 557, 339);
		contentPane.add(panel);
		panel.setLayout(null);
		
		logo = new JLabel("");
		logo.setBounds(10, 11, 120, 120);
		Image imglogo = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		logo.setIcon(new ImageIcon(imglogo));
		panel.add(logo);
		
		lblBoasVindas = new JLabel("Bem Vindo ao JSmart Market");
		lblBoasVindas.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblBoasVindas.setBounds(176, 23, 360, 48);
		panel.add(lblBoasVindas);
		
		JLabel lblFaaOLogin = new JLabel("Fa\u00E7a o Login para come\u00E7ar:");
		lblFaaOLogin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFaaOLogin.setBounds(225, 82, 262, 30);
		panel.add(lblFaaOLogin);
		
		lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUsurio.setBounds(27, 164, 103, 30);
		panel.add(lblUsurio);
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSenha.setBounds(27, 225, 103, 30);
		panel.add(lblSenha);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUsuario.setBounds(140, 164, 262, 30);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSenha.setBounds(140, 225, 262, 30);
		panel.add(txtSenha);
		
		btnConfirma = new JButton("Confirma");
		btnConfirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				em = new JPAUtil().getEntityManager();
				
				Cliente cliente = new Cliente();
				cliente = new ClienteDao(em).buscaLogin(txtUsuario.getText());
				
				String campoSenha = new String(txtSenha.getPassword());
				String senha = new VerificaSenha().calculaSenha(campoSenha);
				
				if(cliente == null){
					txtUsuario.setText("");
					txtSenha.setText(null);
					JOptionPane.showMessageDialog(null, "Usuário não Encontrado!");
				}else{
					if(senha.equals(cliente.getSenha())){
						txtUsuario.setText("");
						txtSenha.setText(null);
						TelaCarrinho tela = new TelaCarrinho(cliente);
						tela.setVisible(true);
					}else{
						txtSenha.setText("");
						txtSenha.requestFocus();
						JOptionPane.showMessageDialog(null, "Senha Incorreta!");
					}
				}
				em.close();
			}
		});
		btnConfirma.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConfirma.setBounds(245, 290, 142, 38);
		panel.add(btnConfirma);
		
		btnCancela = new JButton("Cancela");
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsuario.setText("");
				txtSenha.setText(null);
			}
		});
		btnCancela.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancela.setBounds(405, 290, 142, 38);
		panel.add(btnCancela);
	}
}
