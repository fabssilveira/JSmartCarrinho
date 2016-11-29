package br.com.jsmart.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import br.com.jsmart.dao.ProdutoDao;
import br.com.jsmart.model.Cliente;
import br.com.jsmart.model.Compra;
import br.com.jsmart.model.ItensCompra;
import br.com.jsmart.model.ListaDeProdutos;
import br.com.jsmart.model.Pagamento;
import br.com.jsmart.model.Produto;
import br.com.jsmart.util.JPAUtil;

import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.persistence.EntityManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCarrinho extends JFrame {

	private JPanel contentPane;
	private JButton btnIniciarCompra;
	private JButton btnFinalizarCompra;
	private JButton btnCorrigirItem;
	private JButton btnCancelarCompra;
	private JButton btnSair;
	private JScrollPane scrollPane;
	private JTable tabela;
	private DefaultTableModel modelo;
	private JTextField txtCliente;
	private JTextField txtData;
	private JTextField txtProduto;
	private JTextField txtValorUnitario;
	private JTextField txtTotalCompra;
	private JComboBox comboBox;
	private JButton btnConfirmar;

	private Cliente cliente;
	private Compra compra;
	private Produto produto;
	private List<ListaDeProdutos> listaProdutos;

	private float totalCompra, valorUnitario;
	private int qtde, posicao = 0;

	EntityManager em;

	/**
	 * Create the frame.
	 */
	public TelaCarrinho(Cliente cli) {
		produto = new Produto();
		compra = new Compra();
		listaProdutos = new ArrayList<ListaDeProdutos>();
		cliente = new Cliente();
		cliente = cli;

		setTitle("JSmart Market - O Carrinho de Supermercado Inteligente");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 800, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnIniciarCompra = new JButton("Iniciar Compra");
		btnIniciarCompra.setToolTipText("Iniciar uma Compra");
		btnIniciarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iniciarCompra();
			}
		});
		btnIniciarCompra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIniciarCompra.setBounds(10, 11, 150, 43);
		contentPane.add(btnIniciarCompra);

		btnFinalizarCompra = new JButton("Finalizar");
		btnFinalizarCompra.setToolTipText("Finalizar uma Compra e ir para as op\u00E7\u00F5es de Pagamento");
		btnFinalizarCompra.setEnabled(false);
		btnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finalizarCompra();
			}
		});
		btnFinalizarCompra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFinalizarCompra.setBounds(170, 11, 150, 43);
		contentPane.add(btnFinalizarCompra);

		btnCorrigirItem = new JButton("Corrigir Item");
		btnCorrigirItem.setToolTipText("Alterar algum item da Compra");
		btnCorrigirItem.setEnabled(false);
		btnCorrigirItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				corrigirItem();
			}
		});
		btnCorrigirItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCorrigirItem.setBounds(490, 11, 150, 43);
		contentPane.add(btnCorrigirItem);

		btnCancelarCompra = new JButton("Cancelar");
		btnCancelarCompra.setToolTipText("Cancelar uma Compra em Andamento");
		btnCancelarCompra.setEnabled(false);
		btnCancelarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarCompra();
			}
		});
		btnCancelarCompra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelarCompra.setBounds(330, 11, 150, 43);
		contentPane.add(btnCancelarCompra);

		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSair.setBounds(650, 11, 124, 43);
		contentPane.add(btnSair);

		JPanel painelPrincipal = new JPanel();
		painelPrincipal.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		painelPrincipal.setBounds(10, 65, 764, 324);
		contentPane.add(painelPrincipal);
		painelPrincipal.setLayout(null);

		modelo = new DefaultTableModel();
		tabela = new JTable(modelo);
		tabela.setShowGrid(false);
		tabela.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabela.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabela.setCellSelectionEnabled(true);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		modelo.addColumn("Cód.");
		modelo.addColumn("Produto");
		modelo.addColumn("V. Unit.");
		modelo.addColumn("Qtde");
		modelo.addColumn("V. Total");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(35);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(222);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(45);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(40);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(60);
		scrollPane = new JScrollPane(tabela);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(348, 11, 406, 302);
		painelPrincipal.add(scrollPane);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCliente.setBounds(10, 18, 64, 20);
		painelPrincipal.add(lblCliente);

		txtCliente = new JTextField();
		txtCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCliente.setEditable(false);
		txtCliente.setBounds(84, 14, 254, 30);
		txtCliente.setColumns(10);
		txtCliente.setText(cliente.getNome() + " " + cliente.getSobrenome());
		painelPrincipal.add(txtCliente);

		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblData.setBounds(10, 61, 64, 20);
		painelPrincipal.add(lblData);

		LocalDate hoje = LocalDate.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		txtData = new JTextField();
		txtData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtData.setEditable(false);
		txtData.setBounds(84, 55, 143, 30);
		txtData.setColumns(10);
		txtData.setText(hoje.format(formatador));
		painelPrincipal.add(txtData);

		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProduto.setBounds(10, 102, 75, 20);
		painelPrincipal.add(lblProduto);

		txtProduto = new JTextField();
		txtProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lerCodigoBarras();
			}
		});
		txtProduto.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtProduto.setEditable(false);
		txtProduto.setColumns(10);
		txtProduto.setBounds(10, 127, 328, 40);
		painelPrincipal.add(txtProduto);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantidade.setBounds(10, 181, 96, 20);
		painelPrincipal.add(lblQuantidade);

		JLabel lblValorUnitrio = new JLabel("Valor Unit\u00E1rio:");
		lblValorUnitrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValorUnitrio.setBounds(116, 181, 96, 20);
		painelPrincipal.add(lblValorUnitrio);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" }));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(10, 205, 83, 40);
		painelPrincipal.add(comboBox);

		txtValorUnitario = new JTextField();
		txtValorUnitario.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtValorUnitario.setEditable(false);
		txtValorUnitario.setColumns(10);
		txtValorUnitario.setBounds(116, 205, 127, 40);
		painelPrincipal.add(txtValorUnitario);

		Image imgConfirma = new ImageIcon(this.getClass().getResource("/confirma.png")).getImage();
		btnConfirmar = new JButton("");
		btnConfirmar.setToolTipText("Confirmar entrada do produto e sua quantidade");
		btnConfirmar.setEnabled(false);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravarProduto();
			}
		});
		btnConfirmar.setBounds(269, 205, 54, 40);
		btnConfirmar.setIcon(new ImageIcon(imgConfirma));
		painelPrincipal.add(btnConfirmar);

		JLabel lblTotalDaCompra = new JLabel("Total da Compra:");
		lblTotalDaCompra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalDaCompra.setBounds(10, 272, 127, 20);
		painelPrincipal.add(lblTotalDaCompra);

		txtTotalCompra = new JTextField();
		txtTotalCompra.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtTotalCompra.setEditable(false);
		txtTotalCompra.setColumns(10);
		txtTotalCompra.setBounds(147, 269, 191, 30);
		painelPrincipal.add(txtTotalCompra);

		JPanel painelAvisos = new JPanel();
		painelAvisos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		painelAvisos.setBounds(10, 400, 764, 30);
		contentPane.add(painelAvisos);
		painelAvisos.setLayout(null);
	}

	private void gravarProduto() {
		qtde = Integer.parseInt(comboBox.getSelectedItem().toString());
		totalCompra = totalCompra + valorUnitario * qtde;
		txtTotalCompra.setText("R$ " + totalCompra);
		modelo.addRow(new Object[1]);
		modelo.setValueAt(produto.getCodigoProduto(), posicao, 0);
		modelo.setValueAt(produto.getDescricao(), posicao, 1);
		modelo.setValueAt(produto.getValorUnitario(), posicao, 2);
		modelo.setValueAt(qtde, posicao, 3);
		modelo.setValueAt(qtde * produto.getValorUnitario(), posicao, 4);
		posicao++;
		ListaDeProdutos aux = new ListaDeProdutos();
		aux.setCodigoProduto(produto.getCodigoProduto());
		aux.setQuantidade(qtde);
		listaProdutos.add(aux);
		txtProduto.setText("");
		txtValorUnitario.setText("");
		comboBox.setSelectedIndex(0);
	}

	private void lerCodigoBarras() {
		String codigoBarras = JOptionPane.showInputDialog("Código de Barras");
		em = new JPAUtil().getEntityManager();
		produto = new ProdutoDao(em).consultaCodigoBarras(codigoBarras);
		txtProduto.setText("Cod:" + produto.getCodigoProduto() + " - " + produto.getDescricao());
		txtValorUnitario.setText("R$ " + produto.getValorUnitario());
		valorUnitario = produto.getValorUnitario();
		btnConfirmar.setEnabled(true);
		em.close();
	}

	private void iniciarCompra() {
		btnIniciarCompra.setEnabled(false);
		btnSair.setEnabled(false);
		btnFinalizarCompra.setEnabled(true);
		btnCancelarCompra.setEnabled(true);
		compra = new Compra();
		compra.setCodigoCliente(cliente.getCodigoCliente());
		compra.setDataCompra(txtData.getText());
		totalCompra = 0;
		valorUnitario = 0;
		txtTotalCompra.setText(String.valueOf(totalCompra));
	}

	private void finalizarCompra() {
		compra.setValorCompra(txtTotalCompra.getText());
		TelaFinalizar tela = new TelaFinalizar(cliente, compra, listaProdutos);
		tela.setVisible(true);
		dispose();
	}

	private void cancelarCompra() {
		if (JOptionPane.showConfirmDialog(null, "Deseja Cancelar a Compra?", "Cancelar",
				JOptionPane.YES_NO_OPTION) == 0) {
			TelaCancelar tela = new TelaCancelar();
			tela.setVisible(true);
			dispose();
		}
	}

	private void corrigirItem() {

	}
}
