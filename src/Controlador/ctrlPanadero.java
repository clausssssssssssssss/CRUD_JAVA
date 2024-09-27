/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Panadero;
import Vista.frmPanaderos;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Estudiante
 */
public class ctrlPanadero implements MouseListener, KeyListener{
   
    //1- Llamar a las otras capas(modelo, vista)
    private frmPanaderos vista;
    private Panadero modelo;
    
      public ctrlPanadero(Panadero modelo, frmPanaderos vista) {
        this.modelo = modelo;
        this.vista = vista;

        //Siempre poner todos los botones que vamos a detectar
        vista.btnGuardar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
        vista.jtbPanaderos.addMouseListener(this);

        modelo.Mostrar(vista.jtbPanaderos);
    }

    /////////////////////////////////////////Eventos
    @Override
    public void mouseClicked(MouseEvent e) {
        //////////////////////////4- Detección de clicks en la vista
        if (e.getSource() == vista.btnGuardar) {
            if (vista.txtNombreP.getText().isEmpty() || vista.txtEdadP.getText().isEmpty() || vista.txtPesoP.getText().isEmpty() || vista.txtCorreoP.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo
                    modelo.setNombre_Panadero(vista.txtNombreP.getText());
                    modelo.setEdad_Panadero(Integer.parseInt(vista.txtEdadP.getText()));
                    modelo.setPeso_Panadero(vista.txtPesoP.getText());
                    modelo.setCorreo_Panadero(vista.txtCorreoP.getText());

                    //Ejecutar el metodo 
                    modelo.Guardar();
                    modelo.Mostrar(vista.jtbPanaderos);
                    modelo.Limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == vista.btnEliminar) {
            if (vista.txtNombreP.getText().isEmpty() || vista.txtEdadP.getText().isEmpty() || vista.txtPesoP.getText().isEmpty() || vista.txtCorreoP.getText().isEmpty()){
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                modelo.Eliminar(vista.jtbPanaderos);
                modelo.Mostrar(vista.jtbPanaderos);
                modelo.limpiar(vista);
            }
        }

        if (e.getSource() == vista.btnActualizar) {
            if (vista.txtNombreP.getText().isEmpty() || vista.txtEdadP.getText().isEmpty() || vista.txtPesoP.getText().isEmpty() || vista.txtCorreoP.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    modelo.setNombre_Panadero(vista.txtNombreP.getText());
                    modelo.setEdad_Panadero(Integer.parseInt(vista.txtEdadP.getText()));
                    modelo.setPeso_Panadero(vista.txtPesoP.getText());
                    modelo.setCorreo_Panadero(vista.txtCorreoP.getText());

                    //Ejecutar el método    
                    modelo.Actualizar(vista.jtbPanaderos);
                    modelo.Mostrar(vista.jtbPanaderos);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == vista.btnLimpiar) {
            modelo.limpiar(vista);
        }

        if (e.getSource() == vista.tbEstudiantes) {
            modelo.cargarDatosTabla(vista);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
   
