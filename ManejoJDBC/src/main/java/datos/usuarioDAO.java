package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import domain.Usuario;
import java.sql.*;
import java.util.*;

public class usuarioDAO {
    private static final String SQL_SELECT = "SELECT * FROM usuario";   
    private static final String SQL_INSERT = "INSERT INTO persona (id_usuario, usuario, pass) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_PASS = "UPDATE usuario  SET pass = ? WHERE id_usuario = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM usuario WHERE (id_usuario = ? );";
    public List<Usuario> seleccionar()  {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idPersona= rs.getInt("id_usuario");
                String nombre= rs.getString("usuario");
                String pass =rs.getString("pass");
                usuario = new Usuario(idPersona,nombre,pass);
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
        }finally{
            try {
                close(conn);
                close(stmt);
                close(rs);
            } catch (SQLException ex) {
            }
        }
        return usuarios;
    }
    
    public int insertar(Usuario usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, usuario.getIdUsuario());
            stmt.setString(2, usuario.getNombreUsuario());
            stmt.setString(3, usuario.getPass());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
        ex.printStackTrace(System.out);
        }finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
            }
        }
        return registros;
    }
    
    
    public void recoverPass(int id, String newPass) {
        int rows = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_PASS);
            stmt.setString(1, newPass);
            stmt.setInt(2, id);

            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
            }
        }
    }
    
     public void deleteUsuario(int id) {
        int rows = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_USER);
            stmt.setInt(1, id);

            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
            }
        }
    }
    
    
}
