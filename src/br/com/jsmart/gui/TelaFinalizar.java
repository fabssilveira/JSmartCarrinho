package br.com.jsmart.gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.border.EtchedBorder;

import br.com.jsmart.dao.CompraDao;
import br.com.jsmart.dao.ItensCompraDao;
import br.com.jsmart.model.Cliente;
import br.com.jsmart.model.Compra;
import br.com.jsmart.model.ItensCompra;
import br.com.jsmart.model.ListaDeProdutos;
import br.com.jsmart.util.JPAUtil;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.persistence.EntityManager;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaFinalizar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCliente;
	private JTextField txtData;
	private JTextField txtTotalCompra;
	private JComboBox comboBox;
	private JButton btnFinalizarCompra;
	private JButton btnContinuar;
	
	private Cliente cliente;
	private Compra compra;
	
	EntityManager em;
	
	/**
	 * Create the dialog.
	 */
	public TelaFinalizar(Cliente cli, Compra comp, List<ListaDeProdutos> listaProdutos) {
		cliente = new Cliente();
		compra = new Compra();
		cliente = cli;
		compra = comp;
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setTitle("JSmartMarket - O Carrinho de Supermercado Inteligente");
		setBounds(100, 100, 628, 379);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTotalDaCompra = new JLabel("Informa\u00E7\u00F5es da Compra:");
			lblTotalDaCompra.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblTotalDaCompra.setBounds(10, 11, 221, 25);
			contentPanel.add(lblTotalDaCompra);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 47, 378, 109);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCliente.setBounds(10, 16, 64, 22);
		panel.add(lblCliente);
		
		txtCliente = new JTextField();
		txtCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCliente.setEditable(false);
		txtCliente.setBounds(84, 13, 271, 30);
		txtCliente.setText(cliente.getNome()+" "+cliente.getSobrenome());
		txtCliente.setColumns(10);
		panel.add(txtCliente);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblData.setBounds(10, 69, 48, 17);
		panel.add(lblData);
		
		txtData = new JTextField();
		txtData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtData.setEditable(false);
		txtData.setBounds(84, 62, 104, 30);
		txtData.setColumns(10);
		LocalDate hoje = LocalDate.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		txtData.setText(hoje.format(formatador));
		panel.add(txtData);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 167, 600, 164);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		btnFinalizarCompra = new JButton("Finalizar");
		btnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gravarCompra();
				gravaItensCompra(listaProdutos);
				JOptionPane.showMessageDialog(null, "Compra Finalizada com Sucesso");
				dispose();
			}
		});
		btnFinalizarCompra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFinalizarCompra.setBounds(313, 118, 129, 35);
		panel_1.add(btnFinalizarCompra);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnContinuar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnContinuar.setBounds(461, 118, 129, 35);
		panel_1.add(btnContinuar);
		
		JLabel lblFormaDePagamento = new JLabel("Forma de Pagamento:");
		lblFormaDePagamento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFormaDePagamento.setBounds(10, 11, 161, 25);
		panel_1.add(lblFormaDePagamento);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Cart\u00E3o de Cr\u00E9dito", "Cart\u00E3o de D\u00E9bito"}));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
		comboBox.setBounds(10, 36, 258, 35);
		panel_1.add(comboBox);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotal.setBounds(313, 13, 89, 21);
		panel_1.add(lblTotal);
		
		txtTotalCompra = new JTextField();
		txtTotalCompra.setFont(new Font("Tahoma", Font.BOLD, 22));
		txtTotalCompra.setEditable(false);
		txtTotalCompra.setBounds(313, 36, 277, 56);
		txtTotalCompra.setColumns(10);
		txtTotalCompra.setText(compra.getValorCompra());
		panel_1.add(txtTotalCompra);
		
		JLabel lblObrigadoPelaPreferncia = new JLabel("Obrigado pela Prefer\u00EAncia");
		lblObrigadoPelaPreferncia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblObrigadoPelaPreferncia.setBounds(10, 117, 242, 36);
		panel_1.add(lblObrigadoPelaPreferncia);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(415, 11, 195, 145);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		Image imglogo = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		lblLogo.setIcon(new ImageIcon(imglogo));
		lblLogo.setBounds(37, 11, 120, 123);
		panel_2.add(lblLogo);
	}
	
	private void gravarCompra(){
		if(comboBox.getSelectedItem().equals("Cartão de Crédito")){
			compra.setCodigoPagamento(2);
		}
		if(comboBox.getSelectedItem().equals("Cartão de Débito")){
			compra.setCodigoPagamento(3);
		}
		em = new JPAUtil().getEntityManager();
		CompraDao compraDao = new CompraDao(em);
		compraDao.salvar(compra);
	}
	
	private void gravaItensCompra(List<ListaDeProdutos> lista){
		ItensCompraDao itensDao = new ItensCompraDao(em);
		for(ListaDeProdutos l: lista){
			ItensCompra item = new ItensCompra();
			item.setCodigoCompra(compra.getCodigoCompra());
			item.setCodigoProduto(l.getCodigoProduto());
			item.setQuantidade(l.getQuantidade());
			itensDao.salvar(item);
		}
		em.close();
	}
}
