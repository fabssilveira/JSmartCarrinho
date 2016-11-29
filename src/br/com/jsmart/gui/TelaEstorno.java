package br.com.jsmart.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TelaEstorno extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtRs;
	private JTextField txtRs_1;

	/**
	 * Create the dialog.
	 */
	public TelaEstorno() {
		setTitle("JSmartMarket - O Carrinho de Supermercado Inteligente");
		setBounds(100, 100, 472, 316);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblDesejaEstornarO = new JLabel("Selecione o Item:");
		lblDesejaEstornarO.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDesejaEstornarO.setBounds(24, 11, 201, 30);
		contentPanel.add(lblDesejaEstornarO);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQuantidade.setBounds(24, 113, 148, 30);
		contentPanel.add(lblQuantidade);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		comboBox.setBounds(24, 145, 82, 45);
		contentPanel.add(comboBox);
		
		JLabel lblValorUnitrio = new JLabel("Valor Unit\u00E1rio:");
		lblValorUnitrio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblValorUnitrio.setBounds(148, 113, 126, 30);
		contentPanel.add(lblValorUnitrio);
		
		txtRs = new JTextField();
		txtRs.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtRs.setEditable(false);
		txtRs.setBounds(148, 145, 126, 45);
		contentPanel.add(txtRs);
		txtRs.setColumns(10);
		
		JLabel lblValorTotal = new JLabel("Valor Total:");
		lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblValorTotal.setBounds(307, 113, 109, 30);
		contentPanel.add(lblValorTotal);
		
		txtRs_1 = new JTextField();
		txtRs_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtRs_1.setEditable(false);
		txtRs_1.setBounds(307, 145, 126, 45);
		contentPanel.add(txtRs_1);
		txtRs_1.setColumns(10);
		
		JButton btnSim = new JButton("Sim");
		btnSim.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSim.setBounds(148, 220, 126, 42);
		contentPanel.add(btnSim);
		
		JButton btnNao = new JButton("N\u00E3o");
		btnNao.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNao.setBounds(307, 220, 126, 42);
		contentPanel.add(btnNao);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBox_1.setBounds(24, 49, 409, 52);
		contentPanel.add(comboBox_1);
	}
}
