package test;

import java.sql.*;


public class TestMysqlJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        
        try {
          //  Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url,"root","admin");
            Statement instruccion = conexion.createStatement();
            String sql= "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
            ResultSet rs = instruccion.executeQuery(sql);
            while(rs.next()){
                System.out.println("id: "+rs.getInt("id_persona"));
                System.out.print(" nombre: "+rs.getString("nombre"));
                System.out.print(" apellido: "+rs.getString("apellido"));
                System.out.println("");
            }
            rs.close();
            instruccion.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
}
