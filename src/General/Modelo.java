package General;

import java.util.HashMap;
import java.util.Map.Entry;

import Clases.Vuelo;

public class Modelo {
	private MongoManager miManager;

	public Modelo() {
		miManager = new MongoManager();
	}

	public HashMap<Integer, Vuelo> verTodo() {
		return miManager.verTodo();
	}

	public void comprar(String[] datosComprador, String[] datosAsiento) {
		miManager.comprar(datosComprador, datosAsiento);
	}

	public String generarCodigoVenta() {
		String codigoVenta = "";
		String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		char[] caracArray = caracteres.toCharArray();
		for (int i = 0; i < 9; i++) {
			int num = (int) (Math.random() * caracteres.length());
			String aux = caracArray[num] + "";
			codigoVenta += aux;
		}
		return codigoVenta;
	}

	public String getPlaza(String codigoVuelo) {
		int numPlaza = miManager.getPlaza(codigoVuelo);
		String plaza = (numPlaza + 1) + "";
		return plaza;
	}
}
