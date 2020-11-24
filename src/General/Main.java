package General;

import Vistas.*;

public class Main {
	public static void main(String[] args) {
		Modelo miModelo = new Modelo();
		Controlador miControlador = new Controlador(miModelo);
		Menu miMenu = new Menu(miControlador);
		miControlador.setMenu(miMenu);
		miMenu.setVisible(true);

	}
}
