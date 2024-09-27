/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Vista.frmArtesanias;
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Estudiante
 */
public class classCarpintero {
    
    /********************** Párametros **************************/
    
    private String nombre;
    private int edad;
    private double peso;
    private String correo;

   
    public String getNombre() {
        return nombre;
    }

    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

  
    public int getEdad() {
        return edad;
    }

   
    public void setEdad(int edad) {
        this.edad = edad;
    }

    
    public double getPeso() {
        return peso;
    }

    
    public void setPeso(double peso) {
        this.peso = peso;
    }

    
    public String getCorreo() {
        return correo;
    }

    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /******************** Métodos *************************/
    
    public void Guardar() {
        
        Connection conexion = ClaseConexion.getConexion();
        try {
            
            String sql = "Insert into tbCarpintero(UUID_Carpintero, Nombre_Carpintero, Edad_Carpintero, Peso_Carpintero,Correo_Carpintero) VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement addCarpt = conexion.prepareStatement(sql);
            
            addCarpt.setString(1, UUID.randomUUID().toString());
            addCarpt.setString(2,getNombre());
            addCarpt.setInt(3,getEdad());
            addCarpt.setDouble(4, getPeso());
            addCarpt.setString(5, getCorreo());
           
            addCarpt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Este es el error en el modelo: metodo guardar " + ex);
        }
    }
    
    
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloCarpintero = new DefaultTableModel();
        modeloCarpintero.setColumnIdentifiers(new Object[]{"UUID_Carpintero", "Nombre_Carpintero", "Edad_Carpintero", "Peso_Carpintero", "Correo_Carpintero"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbCarpintero");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloCarpintero.addRow(new Object[]{rs.getString("UUID_Carpintero"), 
                    rs.getString("Nombre_Carpintero"), 
                    rs.getInt("Edad_Carpintero"), 
                    rs.getString("Peso_Carpintero"),
                    rs.getString("Correo_Carpintero")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloCarpintero);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
    
    
    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada

        String CarptId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            String sql = "delete from tbCarpintero where UUID_Carpintero = ?";
            PreparedStatement deleteCarpt = conexion.prepareStatement(sql);
            deleteCarpt.setString(1, CarptId);
            deleteCarpt.executeUpdate();
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
                String sql = "update tbCarpintero set Nombre_Carpintero= ?, Edad_Carpintero = ?, Peso_Carpintero = ?, Correo_Carpintero = ?  where UUID_Carpintero = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getNombre());
                updateUser.setInt(2, getEdad());
                updateUser.setDouble(3, getPeso());
                updateUser.setString(4, getCorreo());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("Este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
   
   public void cargarDatosTabla(frmArtesanias vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbCarpinteros.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbCarpinteros.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbCarpinteros.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbCarpinteros.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTb = vista.jtbCarpinteros.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTb = vista.jtbCarpinteros.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtPeso.setText(PesoDeTb);
            vista.txtCorreo.setText(CorreoDeTb);
        }
    }
   
   public void limpiar(frmArtesanias vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }

}
