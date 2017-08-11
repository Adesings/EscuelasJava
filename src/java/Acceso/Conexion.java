/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acceso;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ivan Peters
 */
public class Conexion {

//variables miembro
    private String url; 
    private Connection conn = null;
    private Statement instancia;
    private String query; 
    
    private String usuario;
    private String clave;
    private String driverClassName;
    

//CONSTRUCTORES
    //Constructor que crea la conexion sin parametros con unos definidos en la clase
    //(meter los datos correspondientes)
    public Conexion() {  
        this.url = "jdbc:mysql://localhost/ejemplo_testing?useUnicode=true&characterEncoding=utf-8";
        this.driverClassName = "com.mysql.jdbc.Driver";
        this.usuario = "root";
        this.clave   = "1212";
    }
  
    
    public Connection getConn() {
       return  this.conn;
    }

    public String getSqlQuery() {
        return this.query;
    }
   
    public void setSqlQuery(String sql) {
        this.query = sql;
    }
 

    //la conexion propiamente dicha
    public void conectar() throws SQLException, Exception {
        try {
        
            Class.forName(this.driverClassName).newInstance();
            this.conn = (Connection) DriverManager.getConnection(this.url, this.usuario, this.clave);
            this.conn.setCharacterEncoding("UTF-8");
            this.conn.setUseUnicode(true);
        } catch (SQLException sqlE) {
             this.EscribeSalida("Error en la Conexion ExeUpdate : " + sqlE.getMessage()+"\n***********************************");
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException err) {
            System.out.println("Error al Conectar Inesperado" + err.getMessage());
        }
    }
    //Cerrar la conexion

    public void desconectar() throws SQLException {
        this.conn.close();
    }

    //METODOS PARA TRABAJAR CON LA BASE DE DATOS
    public ResultSet consulta() throws SQLException, Exception {
        this.conectar();
        ResultSet rSet = null;
        try{    
            rSet = (ResultSet) this.conn.createStatement().executeQuery(this.query);
        }catch(SQLException sqlE){
             this.EscribeSalida("Error en la Consulta :\n" + this.query + "\n");
             this.EscribeSalida("Error en Consulta ExeUpdate : " + sqlE.getMessage()+"\n***********************************");
        }catch(Exception ex){
             this.EscribeSalida("Error en Exception : " + ex.getMessage()+"\n***********************************");
        }
        return rSet;
    }

    public void accion() throws SQLException, Exception {
        this.conectar();
        try{
            Statement smt =(Statement) this.conn.createStatement();
            smt.executeUpdate(this.query);
        }
        catch(SQLException sqlE){
             this.EscribeSalida("Error en la Consulta :\n" + this.query + "\n");
             this.EscribeSalida("Error en Consulta ExeUpdate : " + sqlE.getMessage()+"\n***********************************");
        }
        catch(Exception e){
             this.EscribeSalida("Error Inesperado : " + e.getMessage());
        }
       this.desconectar();
    }
     
   private Object objectObtenerResultado() throws SQLException, Exception {
        ResultSet res=(ResultSet) this.resultsetObtenerResultado();
        Object valor=null;
        while (res.next()) {
            valor =res.getObject(1);
        }
        this.desconectar();;
        return valor;
    }
   
   public int intObtieneResultado() throws Exception{
       return Integer.parseInt(this.objectObtenerResultado().toString());
   }
   public String stringObtieneResultado() throws Exception{
       return this.objectObtenerResultado().toString();
   }
   
   public double  doubleObtieneResultado() throws Exception{
       return Double.parseDouble( this.objectObtenerResultado().toString());
   }
    public ResultSet resultsetObtenerResultado() throws SQLException, Exception {
        return (ResultSet) this.consulta(); 
     }
     
        
    public int CuentaRegistros() throws Exception {
        int cantidad =   this.resultsetObtenerResultado().getRow(); 
        this.desconectar();
        return cantidad;
    }
    
    public void EscribeSalida(String Salida){
        System.out.println("MySQL :: :: \""+Salida);
    }
 
}
