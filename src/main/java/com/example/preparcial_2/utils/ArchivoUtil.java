package com.example.preparcial_2.utils;

import java.io.*;
import java.util.ArrayList;

public  class ArchivoUtil {

    static String fechaSistema = "";

    /**
     * Este metodo recibe una cadena con el contenido que se quiere guardar en el archivo
     *
     * @param ruta es la ruta o path donde esta ubicado el archivo
     */
    public static void guardarArchivo(String ruta , String contenido , Boolean flagAnexarContenido) throws IOException {
        FileWriter fw = new FileWriter( ruta , flagAnexarContenido );
        BufferedWriter bfw = new BufferedWriter( fw );
        bfw.write( contenido );
        bfw.close();
        fw.close();
    }

    /**
     * ESte metodo retorna el contendio del archivo ubicado en una ruta,con la lista de cadenas.
     *
     * @param ruta
     * @return
     * @throws IOException
     */
    public static ArrayList<String> leerArchivo(String ruta) throws IOException {

        ArrayList<String> contenido = new ArrayList<String>();
        FileReader fr = new FileReader( ruta );
        BufferedReader bfr = new BufferedReader( fr );
        String linea = "";
        while ((linea = bfr.readLine()) != null) {
            contenido.add( linea );
        }
        bfr.close();
        fr.close();
        return contenido;
    }
}

