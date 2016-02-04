package Controlador;

import Modelo.Modelo;
import Vista.Interfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controlador implements ActionListener, MouseListener {

    Interfaz vista;
    Modelo modelo = new Modelo();
    
    public Controlador(Interfaz i) {
        vista = i;
    }
    
    public enum AccionMVC {
        
    }
    
    public void iniciar() {
        
    }

    public void actionPerformed(ActionEvent e) {
        
    }

    public void mouseClicked(MouseEvent e) {
        
    }

    public void mousePressed(MouseEvent e) {
        
    }

    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }
    
}