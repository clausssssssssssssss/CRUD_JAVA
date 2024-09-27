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
    Double Peso_Panadero;
    String Correo_Panadero;
    
    //2- Getters y Setters

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

    public Double getPeso_Panadero() {
        return Peso_Panadero;
    }

    public void setPeso_Panadero(Double Peso_Panadero) {
        this.Peso_Panadero = Peso_Panadero;
    }
     public String getCorreo_Panadero() {
        return Correo_Panadero;
    }

    public void setCorreo_Panadero(String Correo_Panadero) {
        this.Correo_Panadero = Correo_Panadero;
    }
    
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
    
    
    
    
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloPinulito = new DefaultTableModel();
        modeloPinulito.setColumnIdentifiers(new Object[]{"UUID_Panadero", "Nombre_Panadero", "Edad_Panadero", "Peso_Panadero", "Correo_Panadero"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbPanadero");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloPinulito.addRow(new Object[]{rs.getString("UUID_Panadero"), 
                    rs.getString("Nombre_Panadero"), 
                    rs.getInt("Edad_Panadero"), 
                    rs.getDouble("Peso_Panadero"),
                    rs.getString("Correo_Panadero")
                });
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloPinulito);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }

    public void Eliminar(JTable jtbPanaderos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void limpiar(frmPanaderos vista) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void Actualizar(JTable jtbPanaderos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

