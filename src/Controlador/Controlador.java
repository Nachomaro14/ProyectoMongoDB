package Controlador;

import Modelo.Modelo;
import Vista.Interfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Controlador implements ActionListener, MouseListener {

    Interfaz vista;
    Modelo modelo = new Modelo();
    
    public Controlador(Interfaz i) {
        vista = i;
    }
    
    public enum AccionMVC {
        btnNuevoAlumno,
        btnBusqueda,
        
        btnModificar,
        btnEliminar,
        
        btnVolver,
        
        btnCancelar,
        btnAceptar
    }
    
    public void iniciar() {
        try{
            SCifras(vista.txtTelefono1);
            SCifras(vista.txtTelefono2);
            SCifras(vista.txtTelefono3);
            SCifras(vista.txtNuevoTelefono1);
            SCifras(vista.txtNuevoTelefono2);
            SCifras(vista.txtNuevoTelefono3);
            
            SLetras(vista.txtNombre);
            SLetras(vista.txtApellidos);
            SLetras(vista.txtNuevoNombre);
            SLetras(vista.txtNuevoApellidos);
            
            vista.setTitle("Aplicación con MongoDB");
            vista.nuevoAlumno.setTitle("Nuevo alumno");
            vista.busqueda.setTitle("Búsqueda");
            vista.pack();
            vista.setLocationRelativeTo(null);
            vista.setVisible(true);
            
            vista.tablaAlumnos.setModel(modelo.tablaAlumnosVacia());
            vista.tablaAlumnos.addMouseListener(this);
            vista.tablaAlumnos.getTableHeader().setReorderingAllowed(false);
            vista.tablaAlumnos.getTableHeader().setResizingAllowed(false);
            
            vista.tablaBusqueda.setModel(modelo.tablaAlumnosVacia());
            vista.tablaBusqueda.getTableHeader().setReorderingAllowed(false);
            vista.tablaBusqueda.getTableHeader().setResizingAllowed(false);
            
            vista.tablaAlumnos.setModel(modelo.tablaAlumnosPorCiclo("DAM"));
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error en el método iniciar()");
            ex.printStackTrace();
        }
        
        vista.btnNuevoAlumno.setActionCommand("btnNuevoAlumno");
        vista.btnNuevoAlumno.addActionListener(this);
        vista.btnBusqueda.setActionCommand("btnBusqueda");
        vista.btnBusqueda.addActionListener(this);
        
        vista.btnModificar.setActionCommand("btnModificar");
        vista.btnModificar.addActionListener(this);
        vista.btnEliminar.setActionCommand("btnEliminar");
        vista.btnEliminar.addActionListener(this);
        
        vista.btnVolver.setActionCommand("btnVolver");
        vista.btnVolver.addActionListener(this);
        
        vista.btnCancelar.setActionCommand("btnCancelar");
        vista.btnCancelar.addActionListener(this);
        vista.btnAceptar.setActionCommand("btnAceptar");
        vista.btnAceptar.addActionListener(this);
        
        vista.comboCiclo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String ciclo = vista.comboCiclo.getSelectedItem().toString();
                vista.tablaAlumnos.setModel(modelo.tablaAlumnosPorCiclo(ciclo));
            }
        });
        
        vista.txtBusqueda.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
                
            }

            public void keyPressed(KeyEvent e) {
                
            }

            public void keyReleased(KeyEvent e) {
                String campo = vista.comboCampo.getSelectedItem().toString();
                String busqueda = vista.txtBusqueda.getText();
                if(campo.equals("DNI")){
                    vista.tablaBusqueda.setModel(modelo.tablaAlumnosPorDNI(busqueda));
                }else if(campo.equals("Nombre")){
                    vista.tablaBusqueda.setModel(modelo.tablaAlumnosPorNombre(busqueda));
                }else if(campo.equals("Apellidos")){
                    vista.tablaBusqueda.setModel(modelo.tablaAlumnosPorApellidos(busqueda));
                }else if(campo.equals("Domicilio")){
                    vista.tablaBusqueda.setModel(modelo.tablaAlumnosPorDomicilio(busqueda));
                }else{
                    vista.tablaBusqueda.setModel(modelo.tablaAlumnosPorCorreo(busqueda));
                }
            }
        });
        
        vista.comboCampo.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                String campo = vista.comboCampo.getSelectedItem().toString();
                String busqueda = vista.txtBusqueda.getText();
                if(campo.equals("DNI")){
                    vista.tablaBusqueda.setModel(modelo.tablaAlumnosPorDNI(busqueda));
                }else if(campo.equals("Nombre")){
                    vista.tablaBusqueda.setModel(modelo.tablaAlumnosPorNombre(busqueda));
                }else if(campo.equals("Apellidos")){
                    vista.tablaBusqueda.setModel(modelo.tablaAlumnosPorApellidos(busqueda));
                }else if(campo.equals("Domicilio")){
                    vista.tablaBusqueda.setModel(modelo.tablaAlumnosPorDomicilio(busqueda));
                }else{
                    vista.tablaBusqueda.setModel(modelo.tablaAlumnosPorCorreo(busqueda));
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        switch(AccionMVC.valueOf(e.getActionCommand())){
            case btnNuevoAlumno:
                vista.nuevoAlumno.pack();
                vista.nuevoAlumno.setLocationRelativeTo(null);
                vista.nuevoAlumno.setVisible(true);
                break;
            case btnBusqueda:
                vista.busqueda.pack();
                vista.busqueda.setLocationRelativeTo(null);
                vista.busqueda.setVisible(true);
                break;
                
            case btnModificar:
                String dniM = vista.txtDni.getText();
                if(!dniM.equals("")){
                    String nombreM = vista.txtNombre.getText();
                    String apellidosM = vista.txtApellidos.getText();
                    String domicilioM = vista.txtDomicilio.getText();
                    long telefono1M = Long.parseLong(vista.txtTelefono1.getText());
                    long telefono2M = Long.parseLong(vista.txtTelefono2.getText());
                    long telefono3M = Long.parseLong(vista.txtTelefono3.getText());
                    String ciclo = vista.comboCiclo.getSelectedItem().toString();
                    String correoM = vista.txtCorreo.getText();
                    String nacimientoM = vista.txtFechaNac.getText();
                    modelo.modificarAlumno(dniM, nombreM, apellidosM, domicilioM, telefono1M, telefono2M, telefono3M, correoM, nacimientoM, ciclo);
                    vista.tablaAlumnos.setModel(modelo.tablaAlumnosPorCiclo(ciclo));
                }else{
                    JOptionPane.showMessageDialog(null, "Seleccione un alumno primero");
                }
                break;
            case btnEliminar:
                String dni = vista.txtDni.getText();
                modelo.eliminarAlumno(dni);
                String ciclo = vista.comboCiclo.getSelectedItem().toString();
                vista.tablaAlumnos.setModel(modelo.tablaAlumnosPorCiclo(ciclo));
                vista.txtDni.setText("");
                vista.txtNombre.setText("");
                vista.txtApellidos.setText("");
                vista.txtDomicilio.setText("");
                vista.txtTelefono1.setText("");
                vista.txtTelefono2.setText("");
                vista.txtTelefono3.setText("");
                vista.txtCorreo.setText("");
                vista.txtFechaNac.setText("");
                break;
                
            case btnVolver:
                vista.busqueda.setVisible(false);
                vista.txtBusqueda.setText("");
                vista.tablaBusqueda.setModel(modelo.tablaAlumnosVacia());
                break;
                
            case btnCancelar:
                vista.nuevoAlumno.setVisible(false);
                vista.txtNuevaFechaNac.setText("");
                vista.txtNuevoApellidos.setText("");
                vista.txtNuevoCorreo.setText("");
                vista.txtNuevoDNI.setText("");
                vista.txtNuevoNombre.setText("");
                vista.txtNuevoTelefono1.setText("");
                vista.txtNuevoTelefono2.setText("");
                vista.txtNuevoTelefono3.setText("");
                vista.comboNuevoCiclo.setSelectedIndex(0);
                break;
            case btnAceptar:
                String nuevoDni = vista.txtNuevoDNI.getText();
                if(!nuevoDni.equals("")){
                    String nuevoNombre = vista.txtNuevoNombre.getText();
                    String nuevoApellidos = vista.txtNuevoApellidos.getText();
                    String nuevoDomicilio = vista.txtNuevoDomicilio.getText();
                    long nuevoTelefono1 = Long.parseLong(vista.txtNuevoTelefono1.getText());
                    long nuevoTelefono2 = Long.parseLong(vista.txtNuevoTelefono2.getText());
                    long nuevoTelefono3 = Long.parseLong(vista.txtNuevoTelefono3.getText());
                    String nuevoCorreo = vista.txtNuevoCorreo.getText();
                    String nuevoNacimiento = vista.txtNuevaFechaNac.getText();
                    String nuevoCiclo = vista.comboNuevoCiclo.getSelectedItem().toString();
                    modelo.insertarAlumno(nuevoDni, nuevoNombre, nuevoApellidos, nuevoDomicilio, nuevoTelefono1, nuevoTelefono2, nuevoTelefono3, nuevoCorreo, nuevoNacimiento, nuevoCiclo);
                    String cicloo = vista.comboCiclo.getSelectedItem().toString();
                    vista.tablaAlumnos.setModel(modelo.tablaAlumnosPorCiclo(cicloo));
                    vista.nuevoAlumno.setVisible(false);
                    vista.txtNuevaFechaNac.setText("");
                    vista.txtNuevoApellidos.setText("");
                    vista.txtNuevoCorreo.setText("");
                    vista.txtNuevoDNI.setText("");
                    vista.txtNuevoNombre.setText("");
                    vista.txtNuevoTelefono1.setText("");
                    vista.txtNuevoTelefono2.setText("");
                    vista.txtNuevoTelefono3.setText("");
                    vista.comboNuevoCiclo.setSelectedIndex(0);
                }else{
                    JOptionPane.showMessageDialog(null, "Seleccione un alumno primero");
                }
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {
        int alumno = vista.tablaAlumnos.rowAtPoint(e.getPoint());
        if (alumno > -1) {
            try {
                String dni = String.valueOf(vista.tablaAlumnos.getValueAt(alumno, 0));
                String nombre = String.valueOf(vista.tablaAlumnos.getValueAt(alumno, 1));
                String apellidos = String.valueOf(vista.tablaAlumnos.getValueAt(alumno, 2));
                String domicilio = String.valueOf(vista.tablaAlumnos.getValueAt(alumno, 3));
                String telefono1 = String.valueOf(vista.tablaAlumnos.getValueAt(alumno, 4));
                String telefono2 = String.valueOf(vista.tablaAlumnos.getValueAt(alumno, 5));
                String telefono3 = String.valueOf(vista.tablaAlumnos.getValueAt(alumno, 6));
                String correo = String.valueOf(vista.tablaAlumnos.getValueAt(alumno, 7));
                String nacimiento = String.valueOf(vista.tablaAlumnos.getValueAt(alumno, 8));
                
                vista.txtDni.setText(dni);
                vista.txtNombre.setText(nombre);
                vista.txtApellidos.setText(apellidos);
                vista.txtDomicilio.setText(domicilio);
                vista.txtTelefono1.setText(telefono1);
                vista.txtTelefono2.setText(telefono2);
                vista.txtTelefono3.setText(telefono3);
                vista.txtCorreo.setText(correo);
                vista.txtFechaNac.setText(nacimiento);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al obtener los datos de la tupla de la tabla.\n\n" + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        
    }

    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }
    
    public static void SCifras(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    e.consume();
                }
            }
        });
    }

    public static void SLetras(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    }
}