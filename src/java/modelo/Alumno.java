/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Acceso.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList; 

/**
 *
 * @author Ivan Peters
 */
public class Alumno  extends Persona{
    private int run;
    private String nombre;
    private String apellido;
    private String sexo; 
    private int fono;
    private String mail;

     public Alumno(int run, String nombre, String apellido, String sexo,  int fono, String mail){
     this.run       = run;
     this.nombre    = nombre;
     this.apellido  = apellido;
     this.sexo      = sexo; 
     this.fono      = fono;
     this.mail      = mail;
     
     }
     
     public Alumno(String run, String nombre, String apellido, String sexo,  String fono, String mail){
         if ( Persona.validarRut(run)){
            this.run       = Persona.rutNumerico(run);
            this.nombre    = nombre;
            this.apellido  = apellido;
            this.sexo      = sexo; 
            if ( fono.equals("")){
                fono = fono;
            }
            this.fono      = Integer.parseInt(  fono );
            this.mail        = mail;
         } 
     
     }
    public int getRun() {
        return run;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getSexo() {
        return sexo;
    }
 
    public int getFono() {
        return fono;
    }

    public String getMail() {
        return mail;
    }
    
   
   public static LinkedList<Alumno> getAlumnos() throws SQLException
   {
      LinkedList<Alumno> listaAlumno=new LinkedList<>();
      
      Conexion cnx = new Conexion();

      try
      {
          cnx.setSqlQuery("SELECT * FROM Alumno");
          cnx.conectar();
          try (ResultSet puntero = cnx.consulta()) {
              while (puntero.next()){
                  Alumno alumno = new Alumno(
                          puntero.getInt(1),
                          puntero.getString(2),
                          puntero.getString(3),
                          puntero.getString(4), 
                          puntero.getInt(5),
                          puntero.getString(6)
                  );
                  
                  listaAlumno.add(alumno);
              }
          }
      }catch(SQLException eSql){
          System.err.println("Error SQLExeption : "  + eSql.getMessage());
      }
      catch (Exception e)
      {
        System.err.println("Error Exception : "  + e.getMessage());
      }finally{
          cnx.desconectar();
      }
      return listaAlumno;
   }
}
