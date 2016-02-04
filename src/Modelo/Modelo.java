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
    
    public DefaultTableModel tablaAlumnosVacia() {        
        DefaultTableModel tablemodel = new ModeloTablaNoEditable();
        try{            
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return tablemodel;
    }
    
    public void insertarAlumno(String dni, String nombre, String apellidos, String domicilio, long telefono1, long telefono2, long telefono3, String correo, String fechaNac, String ciclo) {
        try{
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
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void modificarAlumno(String dni, String nombre, String apellidos, String domicilio, long telefono1, long telefono2, long telefono3, String correo, String fechaNac, String ciclo) {
        try{
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
            updateAlumno.append("$set", new BasicDBObject().append("ciclo", ciclo));
            BasicDBObject searchById = new BasicDBObject();
            searchById.append("dni", dni);
            table.updateMulti(searchById, updateAlumno);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public DefaultTableModel tablaAlumnosPorCiclo(String ciclo) {
	DefaultTableModel tablemodel = new ModeloTablaNoEditable();
        try{
            DBCollection table = db.getCollection("alumno");
            BasicDBObject query = new BasicDBObject();
            query.put("ciclo", ciclo);
            DBCursor cur = table.find(query);


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
        }catch(Exception e){
            e.printStackTrace();
        }
        return tablemodel;
    }
    
    public DefaultTableModel tablaAlumnosPorDNI(String dni) {
	DefaultTableModel tablemodel = new ModeloTablaNoEditable();
        try{
            DBCollection table = db.getCollection("alumno");
            BasicDBObject query = new BasicDBObject();
            query.put("dni",dni);
            DBCursor cur = table.find(query);
        
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return tablemodel;
    }
    
    public DefaultTableModel tablaAlumnosPorNombre(String nombre) {
	DefaultTableModel tablemodel = new ModeloTablaNoEditable();
        try{
            DBCollection table = db.getCollection("alumno");
            BasicDBObject query = new BasicDBObject();
            query.put("nombre", nombre);
            DBCursor cur = table.find(query);
        
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return tablemodel;
    }
    
    public DefaultTableModel tablaAlumnosPorApellidos(String apellidos) {
	DefaultTableModel tablemodel = new ModeloTablaNoEditable();
        try{
            DBCollection table = db.getCollection("alumno");
            BasicDBObject query = new BasicDBObject();
            query.put("apellidos", apellidos);
            DBCursor cur = table.find(query);
            
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return tablemodel;
    }
    
    public DefaultTableModel tablaAlumnosPorDomicilio(String domicilio) {
	DefaultTableModel tablemodel = new ModeloTablaNoEditable();
        try{
            DBCollection table = db.getCollection("alumno");
            BasicDBObject query = new BasicDBObject();
            query.put("domicilio", domicilio);
            DBCursor cur = table.find(query);
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return tablemodel;
    }
    
    public DefaultTableModel tablaAlumnosPorCorreo(String correo) {
        DefaultTableModel tablemodel = new ModeloTablaNoEditable();
        try{
            DBCollection table = db.getCollection("alumno");
            BasicDBObject query = new BasicDBObject();
            query.put("correo", correo);
            DBCursor cur = table.find(query);

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
        }catch(Exception e){
            e.printStackTrace();
        }
        return tablemodel;
    }
    
    public void eliminarAlumno(String dni) {
	DBCollection table = db.getCollection("alumno");
	table.remove(new BasicDBObject().append("dni", dni));
    }
}