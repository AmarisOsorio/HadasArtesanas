/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Modelo.classCarpintero;
import Vista.frmArtesanias;
import javax.swing.JOptionPane;
/**
 *
 * @author Estudiante
 */
public class ctrlCarpinteros implements MouseListener, KeyListener {
    
    private classCarpintero modelo;
    private frmArtesanias vista;

    public ctrlCarpinteros(classCarpintero modelo , frmArtesanias vista ) {
        this.modelo = modelo;
        this.vista = vista;
        
        
        vista.btnGuardar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.txtNombre.addKeyListener(this);
        vista.txtEdad.addMouseListener(this);
        vista.txtPeso.addMouseListener(this);
        vista.txtCorreo.addMouseListener(this);
        vista.jtbCarpinteros.addMouseListener(this);
        
        modelo.Mostrar(vista.jtbCarpinteros);
        modelo.cargarDatosTabla(vista);
        
    }
  
    /********************** Evento Guardar, Eliminar, Actualizar, Mostrar **************************/
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //////////////////Guardar////////////////////
        if (e.getSource() == vista.btnGuardar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo
                    modelo.setNombre(vista.txtNombre.getText());
                    modelo.setEdad(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setPeso(Double.parseDouble(vista.txtPeso.getText()));
                    modelo.setCorreo(vista.txtCorreo.getText());
                    modelo.Guardar();
                    
                    
                    modelo.Mostrar(vista.jtbCarpinteros);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
        
        //////////////////Eliminar////////////////////
        if (e.getSource() == vista.btnEliminar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty()|| vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                modelo.Eliminar(vista.jtbCarpinteros);
                modelo.Mostrar(vista.jtbCarpinteros);
                modelo.limpiar(vista);
            }
        }
        
        
        //////////////////Actualizar////////////////////
        if (e.getSource() == vista.btnActualizar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    modelo.setNombre(vista.txtNombre.getText());
                    modelo.setEdad(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setPeso(Double.parseDouble(vista.txtPeso.getText()));
                    modelo.setCorreo(vista.txtCorreo.getText());

                    //Ejecutar el método    
                    modelo.Actualizar(vista.jtbCarpinteros);
                    modelo.Mostrar(vista.jtbCarpinteros);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
        
        
        //////////////////Mostrar////////////////////
        if (e.getSource() == vista.jtbCarpinteros) {
            modelo.cargarDatosTabla(vista);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
