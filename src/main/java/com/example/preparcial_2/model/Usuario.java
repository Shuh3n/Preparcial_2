package com.example.preparcial_2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Usuario implements Serializable {

    private String nombre;
    private String codigo;
    private String nota1;
    private String nota2;
    private String nota3;

    private ArrayList<Usuario> usuarios = new ArrayList<>();


    public Usuario() {
    }

    public Usuario(String nombre , String codigo , String nota1 , String nota2 , String nota3) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNota1() {
        return nota1;
    }

    public void setNota1(String nota1) {
        this.nota1 = nota1;
    }

    public String getNota2() {
        return nota2;
    }

    public void setNota2(String nota2) {
        this.nota2 = nota2;
    }

    public String getNota3() {
        return nota3;
    }

    public void setNota3(String nota3) {
        this.nota3 = nota3;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                ", nota1='" + nota1 + '\'' +
                ", nota2='" + nota2 + '\'' +
                ", nota3='" + nota3 + '\'' +
                '}';
    }

    public boolean crearUsuario(Usuario newUsuario) {
        boolean flag = false;
        if(!verificarUsuarioRegistrado(newUsuario.getCodigo())){
            usuarios.add( newUsuario );
            return flag = true;
        }
        return flag;
    }

    private boolean verificarUsuarioRegistrado(String codigo) {
        boolean encontrado = false;
        List<Usuario> usuariosIdenticos = this.usuarios.stream()
                .filter(u-> u.getCodigo().equals(codigo))
                .collect( Collectors.toList());

        if(!usuariosIdenticos.isEmpty()) {
            encontrado = true;
        }
        return encontrado;
    }
}
