/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.frmPanaderos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ClaseConexion;

/**
 *
 * @author Estudiante
 */
public class Panadero {
    //1- Parametros
    String UUID_Panadero;
    String Nombre_Panadero;
    int Edad_Panadero;
    int  Peso_Panadero;

    public String getUUID_Panadero() {
        return UUID_Panadero;
    }

    public void setUUID_Panadero(String UUID_Panadero) {
        this.UUID_Panadero = UUID_Panadero;
    }

    public String getNombre_Panadero() {
        return Nombre_Panadero;
    }

    public void setNombre_Panadero(String Nombre_Panadero) {
        this.Nombre_Panadero = Nombre_Panadero;
    }

    public int getEdad_Panadero() {
        return Edad_Panadero;
    }

    public void setEdad_Panadero(int Edad_Panadero) {
        this.Edad_Panadero = Edad_Panadero;
    }

    public int getPeso_Panadero() {
        return Peso_Panadero;
    }

    public void setPeso_Panadero(int Peso_Panadero) {
        this.Peso_Panadero = Peso_Panadero;
    }

    public String getCorreo_Panadero() {
        return Correo_Panadero;
    }

    public void setCorreo_Panadero(String Correo_Panadero) {
        this.Correo_Panadero = Correo_Panadero;
    }
    String Correo_Panadero;
    
    //2- Getters y Setters

    
    
    //3- Funciones (Guardar, mostrar, eliminar, actualizar)
   
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addPanadero = conexion.prepareStatement("INSERT INTO tbPanadero(UUID_Panadero, Nombre_Panadero, Edad_Panadero, Peso_Panadero, Correo_Panadero) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addPanadero.setString(1, UUID.randomUUID().toString());
            addPanadero.setString(2, getNombre_Panadero());
            addPanadero.setInt(3, getEdad_Panadero());
            addPanadero.setDouble(4, getPeso_Panadero());
             addPanadero.setString(4, getCorreo_Panadero());
 
            //Ejecutar la consulta
            addPanadero.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    
        public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada

        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            String sql = "delete from tbPanadera where UUID = ?";
            PreparedStatement deletePanadero = conexion.prepareStatement(sql);
            deletePanadero.setString(1, miId);
            deletePanadero.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }

    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {
                //Ejecutamos la Query
                String sql = "update tbCRUD set Nombre_Panadero= ?, Edad_Panadero= ?, Peso_Panadero= ?, Correo_Panadero= ? where UUID = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getNombre_Panadero());
                updateUser.setInt(2, getEdad_Panadero());
                updateUser.setDouble(3, getPeso_Panadero());
                updateUser.setString(3, getCorreo_Panadero());
                updateUser.setString(4, miUUId);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelopanaderos = new DefaultTableModel();
        modelopanaderos.setColumnIdentifiers(new Object[]{"UUID_Panadero", "Nombre_Panadero", "Edad_Panadero", "Peso_Panadero", "Correo_Panadero"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbPanadero");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelopanaderos.addRow(new Object[]{rs.getString("UUID_Panadero"), 
                    rs.getString("Nombre_Panadero"), 
                    rs.getInt("Edad_Panadero"), 
                    rs.getDouble("Peso_Panadero"),
                    rs.getString("Correo_Panadero")
                });
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelopanaderos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
    public void limpiar(frmPanaderos vista) {
        vista.txtNombreP.setText("");
        vista.txtEdadP.setText("");
        vista.txtPesoP.setText("");
        vista.txtCorreoP.setText("");
    }
 
    public void cargarDatosTabla(frmPanaderos vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbPanaderos.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbPanaderos.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeP = vista.jtbPanaderos.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeP = vista.jtbPanaderos.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeP= vista.jtbPanaderos.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeP= vista.jtbPanaderos.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombreP.setText(NombreDeP);
            vista.txtEdadP.setText(EdadDeP);
            vista.txtPesoP.setText(PesoDeP);
            vista.txtCorreoP.setText(CorreoDeP);
        }
} 

}

