package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import domain.Usuario;
import java.sql.*;
import java.util.*;

public class usuarioDAO {

    private Connection connectTransaccion;
    private static final String SQL_SELECT = "SELECT * FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario (id_usuario, usuario, pass) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET usuario=?, pass=? WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario=?";

    public usuarioDAO(Connection connect) {
        this.connectTransaccion = connect;
    }

    public List<Usuario> seleccionar() throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = this.connectTransaccion != null ? this.connectTransaccion : getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idPersona = rs.getInt("id_usuario");
                String nombre = rs.getString("usuario");
                String pass = rs.getString("pass");
                usuario = new Usuario(idPersona, nombre, pass);
                usuarios.add(usuario);
            }
        } finally {
            try {

                close(stmt);
                close(rs);
                if (conn == null) {
                    close(conn);
                }
            } catch (SQLException ex) {
            }
        }
        return usuarios;
    }

    public int insertar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = this.connectTransaccion != null ? this.connectTransaccion : getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, usuario.getIdUsuario());
            stmt.setString(2, usuario.getNombreUsuario());
            stmt.setString(3, usuario.getPass());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                if (conn == null) {
                    close(conn);
                }
            } catch (SQLException ex) {
            }
        }
        return registros;
    }

    public int update(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn =  this.connectTransaccion != null ? this.connectTransaccion : getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getPass());
            stmt.setInt(3, usuario.getIdUsuario());
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);
        } finally {
            close(stmt);
            if (conn == null) {
                    close(conn);
                }
        }

        return rows;
    }

    public int delete(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn =  this.connectTransaccion != null ? this.connectTransaccion : getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getIdUsuario());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } finally {
            close(stmt);
            if (conn == null) {
                    close(conn);
                }
        }
        return rows;
    }

}
