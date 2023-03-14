package test;

import datos.Conexion;
import datos.usuarioDAO;
import domain.Usuario;
import java.sql.*;

public class TestManejoUsuarios {
    public static void main(String[] args) {
        
        
        Connection conexion=null;
        usuarioDAO user;
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
                user = new usuarioDAO(conexion);
//                Usuario cambioUser = new Usuario();
//                cambioUser.setIdUsuario(10);
//                cambioUser.setNombreUsuario("Karla Ivonne");
//                cambioUser.setPass("Gomez");
//                user.update(cambioUser);
                //Usuario newUser = new  Usuario();
                newUser.setIdUsuario(15);
                newUser.setNombreUsuario("carlos");
                newUser.setPass("My_pass");
                user.insertar(newUser);
                conexion.commit();
            }
        } catch (SQLException ex) {
            try {
                ex.printStackTrace(System.out);
                System.out.println("Rollback");
                conexion.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace(System.out);
            }
        }
    }
}
