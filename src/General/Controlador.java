package General;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.JFrame;

import Clases.Vuelo;
import Vistas.*;

public class Controlador {
	private Modelo miModelo;
	private HashMap<String, JFrame> misPantallas;
	private Scanner sc = new Scanner(System.in);

	public Controlador(Modelo miModelo) {
		this.miModelo = miModelo;
		this.misPantallas = new HashMap<String, JFrame>();
	}

	public void setMenu(Menu menu) {
		misPantallas.put("menu", menu);
	}

	public void changeWindow(String origen, String destino) {
		misPantallas.get(origen).setVisible(false);
		misPantallas.get(destino).setVisible(true);
	}

	public void comprarVuelo() {
		for (Entry<Integer, Vuelo> vuelo : miModelo.verTodo().entrySet()) {
			System.out.println(vuelo.getValue());
		}
		String datosComprador[] = new String[4];
		System.out.println("Inserte el codigo de vuelo que desea comprar: ");
		datosComprador[0] = sc.next();
		System.out.println("Intoduce el numero de asientos que desea: ");
		String numAux = sc.next();
		int num = Integer.parseInt(numAux);
		System.out.println("Introduce los siguientes datos del pagador: \nDNI: ");
		datosComprador[1] = sc.next();
		System.out.println("Tarjeta: ");
		sc.nextLine();
		datosComprador[2] = sc.nextLine();
		datosComprador[3] = miModelo.generarCodigoVenta();
		for (int i = 0; i < num; i++) {
			String datosAsiento[] = new String[4];
			System.out.println("Introduce los datos del pasajero:  \nDNI: ");
			datosAsiento[0] = sc.next();
			System.out.println("Apellido: ");
			datosAsiento[1] = sc.next();
			System.out.println("Nombre: ");
			datosAsiento[2] = sc.next();
			datosAsiento[3] = miModelo.getPlaza(datosComprador[0]);
			miModelo.comprar(datosComprador, datosAsiento);
		}
	}
}