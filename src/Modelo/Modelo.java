package Modelo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Modelo extends Database{
    
    MongoClient mongo = crearConexion();
    DB db = mongo.getDB("IE642");
    
    public class ModeloTablaNoEditable extends DefaultTableModel {

        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
    
    public void iniciarOCrearBD(){
        if (mongo != null) {
            crearColeccion();
	}else {
            JOptionPane.showMessageDialog(null, "Error: Conexión no establecida");
	}
    }
    
    private void crearColeccion(){
        DBCollection table = db.getCollection("alumno");
        BasicDBObject document = new BasicDBObject();
        
        document.put("id", "53570058Z");
        document.put("nombre", "Ignacio");
        document.put("apellidos", "Mazuecos Roldán");
        document.put("domicilio", "Comino, 7");
        document.put("telefono1", 647359446);
        document.put("telefono2", 955609220);
        document.put("telefono3", 647927844);
        document.put("correo", "mazuecos18@gmail.com");
        document.put("fechaNac", "28/11/1994");
        document.put("ciclo", "DAM");
        
        table.insert(document);
    }
    
    private DefaultTableModel tablaAlumnos(String tabla) {
	DBCollection table = db.getCollection("alumno");
	DBCursor cur = table.find();
        
        DefaultTableModel tablemodel = new ModeloTablaNoEditable();
        tablemodel.addColumn("DNI");
        tablemodel.addColumn("Nombre");
        tablemodel.addColumn("Apellidos");
        tablemodel.addColumn("Domicilio");
        tablemodel.addColumn("Teléfono 1");
        tablemodel.addColumn("Teléfono 2");
        tablemodel.addColumn("Teléfono 3");
        tablemodel.addColumn("Correo");
        tablemodel.addColumn("Nacimiento");
        tablemodel.addColumn("Ciclo");

        Object[] data = new Object[10];

        while(cur.hasNext()){
            cur.next();
            data[0] = cur.curr().get("dni");
            data[1] = cur.curr().get("nombre");
            data[2] = cur.curr().get("apellidos");
            data[3] = cur.curr().get("domicilio");
            data[4] = cur.curr().get("telefono1");
            data[5] = cur.curr().get("telefono2");
            data[6] = cur.curr().get("telefono3");
            data[7] = cur.curr().get("correo");
            data[8] = cur.curr().get("fechaNac");
            data[9] = cur.curr().get("ciclo");
            tablemodel.addRow(data);
        }
        return tablemodel;
    }
    
    private void insertarAlumno(String dni, String nombre, String apellidos, String domicilio, long telefono1, long telefono2, long telefono3, String correo, String fechaNac, String ciclo) {
        DBCollection table = db.getCollection("alumno");

        BasicDBObject document = new BasicDBObject();
        document.put("dni", dni);
        document.put("nombre", nombre);
        document.put("apellidos", apellidos);
        document.put("domicilio", domicilio);
        document.put("telefono1", telefono1);
        document.put("telefono2", telefono2);
        document.put("telefono3", telefono3);
        document.put("correo", correo);
        document.put("fechaNac", fechaNac);
        document.put("ciclo", ciclo);
        
        table.insert(document);
    }
    
    private void updateAlumno(String dni, String nombre, String apellidos, String domicilio, long telefono1, long telefono2, long telefono3, String correo, String fechaNac) {
        DBCollection table = db.getCollection("alumno");
        BasicDBObject updateAlumno = new BasicDBObject();
        updateAlumno.append("$set", new BasicDBObject().append("nombre", nombre));
        updateAlumno.append("$set", new BasicDBObject().append("apellidos", apellidos));
        updateAlumno.append("$set", new BasicDBObject().append("domicilio", domicilio));
        updateAlumno.append("$set", new BasicDBObject().append("telefono1", telefono1));
        updateAlumno.append("$set", new BasicDBObject().append("telefono2", telefono2));
        updateAlumno.append("$set", new BasicDBObject().append("telefono3", telefono3));
        updateAlumno.append("$set", new BasicDBObject().append("correo", correo));
        updateAlumno.append("$set", new BasicDBObject().append("fechaNac", fechaNac));
        BasicDBObject searchById = new BasicDBObject();
        searchById.append("dni", dni);
        table.updateMulti(searchById, updateAlumno);
    }
    
    private void tablaAlumnoPorCiclo(String ciclo) {
	DBCollection table = db.getCollection("alumno");
	BasicDBObject query = new BasicDBObject();
	query.put("ciclo", ciclo);
	DBCursor cur = table.find(query);
        
        DefaultTableModel tablemodel = new ModeloTablaNoEditable();
        tablemodel.addColumn("DNI");
        tablemodel.addColumn("Nombre");
        tablemodel.addColumn("Apellidos");
        tablemodel.addColumn("Domicilio");
        tablemodel.addColumn("Teléfono 1");
        tablemodel.addColumn("Teléfono 2");
        tablemodel.addColumn("Teléfono 3");
        tablemodel.addColumn("Correo");
        tablemodel.addColumn("Nacimiento");
        tablemodel.addColumn("Ciclo");
        
        Object[] data = new Object[10];
        
	while (cur.hasNext()) {
            cur.next();
            data[0] = cur.curr().get("dni");
            data[1] = cur.curr().get("nombre");
            data[2] = cur.curr().get("apellidos");
            data[3] = cur.curr().get("domicilio");
            data[4] = cur.curr().get("telefono1");
            data[5] = cur.curr().get("telefono2");
            data[6] = cur.curr().get("telefono3");
            data[7] = cur.curr().get("correo");
            data[8] = cur.curr().get("fechaNac");
            data[9] = cur.curr().get("ciclo");
            tablemodel.addRow(data);
	}
    }
    
    private void eliminarAlumno(String dni) {
	DBCollection table = db.getCollection("alumno");
	table.remove(new BasicDBObject().append("dni", dni));
    }
}