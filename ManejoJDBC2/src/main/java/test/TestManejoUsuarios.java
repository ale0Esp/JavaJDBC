package test;

import datos.Conexion;
import datos.UsuarioDAO;
import datos.UsuarioDaoJDBC;
import domain.UsuarioDTO;
import java.sql.*;
import java.util.List;

public class TestManejoUsuarios {
    public static void main(String[] args) {
        
        
        Connection conexion=null;
        UsuarioDAO user;
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
                user = new UsuarioDaoJDBC(conexion);
                List<UsuarioDTO> usuarios = user.select();
                for(UsuarioDTO usuario: usuarios){
                    System.out.println("usuario = " + usuario);
                }
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
