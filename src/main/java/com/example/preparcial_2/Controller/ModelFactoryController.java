package com.example.preparcial_2.Controller;

import com.example.preparcial_2.model.Usuario;
import com.example.preparcial_2.persistencia.Persistencia;
import com.example.preparcial_2.viewControllers.VentanaPrincipalViewController;

import java.io.IOException;

public class ModelFactoryController {

    Usuario usuario;

    private VentanaPrincipalViewController ventanaPrincipalViewController;

    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aquí al ser protected
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();

    }

    // Método para obtener la instancia de nuestra clase

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController(){
        inicializarDatos();
        cargarUsuarios();
        Persistencia.guardaRegistroLog( "Aplicación iniciada", 1 , "Aplicación abierta y usada" );

    }

    private void inicializarDatos() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean crearUsuario(String nombre , String codigo , String nota1 , String nota2 , String nota3){
        Usuario newUsuario = new Usuario(nombre,codigo,nota1,nota2,nota3);
        boolean flag = usuario.crearUsuario(newUsuario);
        if ( flag ){
            guardarUsuarios( newUsuario );
            Persistencia.guardaRegistroLog( "Usuario creado", 1, "Se ha creado un usuario" );
        }
        return flag;
    }

    private void guardarUsuarios(Usuario newUsuario) {
        try {
            Persistencia.guardarUsuarios(newUsuario);
            System.out.println("Usuario Guardado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarUsuarios() {
        try {
            usuario.setUsuarios( Persistencia.cargarUsuarios() );
            System.out.println("Usuarios cargados");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}
