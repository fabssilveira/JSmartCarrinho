package br.com.jsmart.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCancelar extends JDialog {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public TelaCancelar() {
		setModal(true);
		setType(Type.POPUP);
		setTitle("JSmartMarket");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 278, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCancelamentoDaCompra = new JLabel("Cancelamento da Compra:");
		lblCancelamentoDaCompra.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCancelamentoDaCompra.setBounds(10, 11, 243, 33);
		contentPane.add(lblCancelamentoDaCompra);
		
		JTextPane txtpnAtenoApsA = new JTextPane();
		txtpnAtenoApsA.setEditable(false);
		txtpnAtenoApsA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnAtenoApsA.setText("Os itens ser\u00E3o eliminados do carrinho \r\ne a compra arquivada em seu hist\u00F3rico\r\ncom o status de cancelada.");
		txtpnAtenoApsA.setBounds(10, 55, 243, 64);
		contentPane.add(txtpnAtenoApsA);
		
		JButton bntConfirmar = new JButton("Confirma");
		bntConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		bntConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bntConfirmar.setBounds(74, 130, 113, 33);
		contentPane.add(bntConfirmar);
	}
}
