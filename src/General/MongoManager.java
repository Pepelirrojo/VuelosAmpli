package General;

import java.util.ArrayList;
import java.util.HashMap;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import Clases.Vuelo;

public class MongoManager {
	private MongoClient mongo;
	private MongoDatabase db;

	public MongoManager() {
		try {
			mongo = new MongoClient("localhost", 27017);
			db = mongo.getDatabase("adat_vuelos");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<Integer, Vuelo> verTodo() {
		HashMap<Integer, Vuelo> misVuelos = new HashMap<>();
		MongoCollection coleccionVuelos = db.getCollection("vuelos_compra");
		FindIterable fi = coleccionVuelos.find();
		MongoCursor cur = fi.cursor();
		while (cur.hasNext()) {
			Document doc = (Document) cur.next();
			int plazasTotales = doc.getInteger("plazas_totales");
			int plazasDisponibles = doc.getInteger("plazas_disponibles");
			Vuelo miVuelo = new Vuelo(doc.getInteger("id"), doc.getString("codigo"), doc.getString("origen"),
					doc.getString("destino"), doc.getString("fecha"), doc.getString("hora"), plazasTotales,
					plazasDisponibles);
			misVuelos.put(miVuelo.getId(), miVuelo);
		}
		return misVuelos;
	}

	public void comprar(String[] datosComprador, String[] datosAsiento) {
		MongoCollection coleccionVuelos = db.getCollection("vuelos_compra");
		int numAsiento = Integer.parseInt(datosAsiento[3]);
		Document vueloAMod = new Document("codigo", datosComprador[0]);
		Document cambios = new Document("vendidos",
				new Document().append("asiento", numAsiento).append("dni", datosAsiento[0])
						.append("apellido", datosAsiento[1]).append("nombre", datosAsiento[2])
						.append("dniPagador", datosComprador[1]).append("tarjeta", datosComprador[2])
						.append("codigoVenta", datosComprador[3]));
		Document auxSet = new Document("$push", cambios);
		coleccionVuelos.updateOne(vueloAMod, auxSet);
	}

	public int getPlaza(String codigoVuelo) {
		MongoCollection coleccionVuelos = db.getCollection("vuelos_compra");
		Document vueloAMod = new Document("codigo", codigoVuelo);
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("codigo", codigoVuelo);
		FindIterable fi = coleccionVuelos.find(whereQuery);
		MongoCursor cursor = fi.cursor();
		Document doc = (Document) cursor.next();
		ArrayList<Document> vendidos = new ArrayList<Document>();
		vendidos.addAll((ArrayList<Document>) doc.get("vendidos"));
		return vendidos.size();
	}
}
