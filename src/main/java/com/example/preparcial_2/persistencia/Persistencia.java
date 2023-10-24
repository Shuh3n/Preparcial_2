package com.example.preparcial_2.persistencia;

import com.example.preparcial_2.model.Usuario;
import com.example.preparcial_2.utils.ArchivoUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    public static final String RUTA_ARCHIVO_USUARIOS = "src/main/resources/com/example/preparcial_2/persistencia/archives/archiveUsuarios.txt";
    private static final String RUTA_ARCHIVO_LOG = "src/main/resources/com/example/preparcial_2/persistencia/logs/log.txt";

    public static void guardarUsuarios(Usuario usuario) throws IOException {
        String contenido = "";
            contenido += usuario.getNombre() + "@@"
                    + usuario.getCodigo() + "@@"
                    + usuario.getNota1() + "@@"
                    + usuario.getNota2() + "@@"
                    + usuario.getNota3() + "\n";

        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, true);
    }

    public static ArrayList<Usuario> cargarUsuarios() throws FileNotFoundException, IOException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);//
            Usuario usuario = new Usuario();
            usuario.setNombre(linea.split("@@")[0]);
            usuario.setCodigo(linea.split("@@")[1]);
            usuario.setNota1(linea.split("@@")[2]);
            usuario.setNota2(linea.split("@@")[3]);
            usuario.setNota3(linea.split("@@")[4]);
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion) {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }

}
