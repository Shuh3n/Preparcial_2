package com.example.preparcial_2.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.example.preparcial_2.Controller.ModelFactoryController;
import com.example.preparcial_2.model.Usuario;
import com.example.preparcial_2.persistencia.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VentanaPrincipalViewController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnLimpiarCampos;

    @FXML
    private Button btnBuscarInfo;
    @FXML
    private Button btnLimpiarInfo;

    @FXML
    private TableColumn<Usuario, String> columnCodigo;

    @FXML
    private TableColumn<Usuario, String> columnNombre;

    @FXML
    private TableColumn<Usuario, String> columnNota1;

    @FXML
    private TableColumn<Usuario, String> columnNota2;

    @FXML
    private TableColumn<Usuario, String> columnNota3;

    @FXML
    private TableView<Usuario> tablerViewRegistros;

    @FXML
    private TableColumn<Usuario, String> columnCodigo1;

    @FXML
    private TableColumn<Usuario, String> columnNombre1;

    @FXML
    private TableColumn<Usuario, String> columnNota11;

    @FXML
    private TableColumn<Usuario, String> columnNota21;

    @FXML
    private TableColumn<Usuario, String> columnNota31;

    @FXML
    private TableView<Usuario> tablerViewRegistros1;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNota1;

    @FXML
    private TextField txtNota2;

    @FXML
    private TextField txtNota3;

    @FXML
    private TextField txtBuscarCodigo;
    private Stage stage;

    ObservableList<Usuario> listaUsuarios= FXCollections.observableArrayList();
    private ObservableList<Usuario> listaUsuarios2 = FXCollections.observableArrayList();

    @FXML
    void crearUsuario(ActionEvent event) {
        String nombre = txtNombre.getText();
        String codigo = txtCodigo.getText();
        String nota1 = txtNota1.getText();
        String nota2 = txtNota2.getText();
        String nota3 = txtNota3.getText();

        if(validarDatos(nombre,codigo,nota1,nota2,nota3)){
            crearUsuarioConfirmado(nombre,codigo,nota1,nota2,nota3);
            tablerViewRegistros.setItems( getListaUsuarios() );
        }
    }

    @FXML
    void encontrarUsuario(ActionEvent event) throws IOException {
        String codigo = txtBuscarCodigo.getText();
        if(codigo!=null || !codigo.isEmpty()){
            Usuario usuarioBuscado = encontrarUsuarioLista( Persistencia.cargarUsuarios(), codigo );
            if(usuarioBuscado!=null){
                tablerViewRegistros1.setItems( setValuesTableView2( usuarioBuscado ));
                txtBuscarCodigo.clear();
            }else{
                mostrarMensaje( "Notificación", "Usuario no encontrado", "Asegurese de haber escrito bien el código", Alert.AlertType.INFORMATION );
            }
        }else{
            mostrarMensaje( "Noticación", "Campos vacíos", "Diligencie el codigo que desea buscar", Alert.AlertType.INFORMATION );
        }


    }

    @FXML
    void limpiarInfo(ActionEvent event) throws IOException {
        tablerViewRegistros1.getItems().clear();
    }

    public Usuario encontrarUsuarioLista(ArrayList<Usuario> listaUsuarios, String codigo){
        Usuario usuarioEncontrado = listaUsuarios.stream()
                .filter(usuario -> usuario.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
        return usuarioEncontrado;
    }

    private void crearUsuarioConfirmado(String nombre , String codigo , String nota1 , String nota2 , String nota3) {
        boolean flag = ModelFactoryController.getInstance().crearUsuario(nombre,codigo,nota1,nota2,nota3);
        if(flag){
            mostrarMensaje( "Notificación de Usuario", "Usuario creado con éxito", "", Alert.AlertType.INFORMATION );
            limpiarCampos( new ActionEvent() );
        }
        else{
            mostrarMensaje( "Notificación de Usuario", "Usuario no creado", "Ya existe un usuario registrado con este código",
                    Alert.AlertType.INFORMATION );
            txtCodigo.clear();
        }
    }

    private boolean validarDatos(String nombre , String contraseña, String nota1, String nota2, String nota3) {
        String notificacion = "";

		/*Se valida que el saldo ingresado no sea null ni sea cadena vacía,
		además se valida que sea numérico para su correcta conversión */

        if (nombre == null || nombre.isEmpty() ) {
            notificacion += "Ingrese su nombre\n";
        }

        if (contraseña == null || contraseña.isEmpty() ) {
            notificacion += "Ingrese su contraseña\n";
        }
        if (nota1 == null || nota1.isEmpty() ) {
            notificacion += "Ingrese la nota 1\n";
        }
        else {
            if ( !esNumero( nota1 ) ) {
                notificacion += "La nota 1 ingresada debe ser númerica\n";
            }
        }
        if (nota2 == null || nota2.isEmpty() ) {
            notificacion += "Ingrese la nota 2\n";
        }
        else {
            if ( !esNumero( nota2 ) ) {
                notificacion += "La nota 2 ingresada debe ser númerica\n";
            }
        }
        if (nota3 == null || nota3.isEmpty() ) {
            notificacion += "Ingrese la nota 3\n";
        }else {
            if ( !esNumero( nota3 ) ) {
                notificacion += "La nota 3 ingresada debe ser númerica\n";
            }
        }
        if ( !notificacion.isEmpty() ) {
            mostrarMensaje("Notificación", "Inicio fallido",
                    notificacion
                    , Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private boolean esNumero(String string) {
        try {Float.parseFloat(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        txtNombre.clear();
        txtCodigo.clear();
        txtNota1.clear();
        txtNota2.clear();
        txtNota3.clear();

    }

    @FXML
    void initialize() {
    }

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
        tablerViewRegistros.setItems( getListaUsuarios() );
        this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.columnNota1.setCellValueFactory(new PropertyValueFactory<>("nota1"));
        this.columnNota2.setCellValueFactory(new PropertyValueFactory<>("nota2"));
        this.columnNota3.setCellValueFactory(new PropertyValueFactory<>("nota3"));

        this.columnNombre1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnCodigo1.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.columnNota11.setCellValueFactory(new PropertyValueFactory<>("nota1"));
        this.columnNota21.setCellValueFactory(new PropertyValueFactory<>("nota2"));
        this.columnNota31.setCellValueFactory(new PropertyValueFactory<>("nota3"));

        txtBuscarCodigo.setOnAction(event -> btnBuscarInfo.fire());
    }

    private ObservableList<Usuario> getListaUsuarios() {
        listaUsuarios.clear();
        listaUsuarios.addAll(ModelFactoryController.getInstance().getUsuario().getUsuarios());
        return listaUsuarios;
    }
    private ObservableList<Usuario> setValuesTableView2(Usuario usuario) {
        listaUsuarios2.clear();
        listaUsuarios2.addAll(usuario);
        return listaUsuarios2;
    }

}
