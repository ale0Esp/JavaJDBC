package domain;

public class Usuario {

    private int idUsuario;
    private String nombreUsuario;
    private String pass;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String pass) {
        this.idUsuario = id;
        this.nombreUsuario = nombre;
        this.pass = pass;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int id) {
        this.idUsuario = id;
    }

    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public void setNombreUsuario(String nombre) {
        this.nombreUsuario = nombre;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
