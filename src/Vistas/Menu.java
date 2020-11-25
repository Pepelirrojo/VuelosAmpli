package Vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import General.*;
import java.awt.Font;

public class Menu extends JFrame {
	private Controlador miControlador;

	public Menu(Controlador miControlador) {
		this.miControlador = miControlador;

		setTitle("Vuelos");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JButton btnComprar = new JButton("Comprar Vuelo");
		btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnComprar.setBounds(112, 71, 101, 59);
		getContentPane().add(btnComprar);
		btnComprar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				miControlador.comprarVuelo();
			}
		});

		JButton btnComprados = new JButton("Mostrar comprados");
		btnComprados.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnComprados.setBounds(142, 5, 153, 59);
		getContentPane().add(btnComprados);
		btnComprados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				miControlador.vuelosComprador();
			}
		});
		
		
		JButton btnCancelar = new JButton("Cancelar Vuelo");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnCancelar.setBounds(223, 71, 101, 59);
		getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				miControlador.changeWindow("menu", "cancela");
			}
		});

		JButton btnModificar = new JButton("Modificar Vuelo Comprado");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnModificar.setBounds(142, 140, 153, 59);
		getContentPane().add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				miControlador.changeWindow("menu", "modificar");
			}
		});

	}
}
