package proyecto15.proyecto_15;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {
    //Registro

    @FXML private TextField nombreTextField;
    @FXML private TextField edadTextField;
    @FXML private ComboBox<String> categoriaCarrera;
    @FXML private ComboBox<String> categoriaSemestre;
    @FXML private ListView<String> materias;
    @FXML private Label labelRegistro;
    @FXML Button botonRegistrar = new Button("REGISTRAR");
    @FXML Button botonLimpiar = new Button("LIMPIAR");
    //Calculadora
    @FXML private TextField numero1TextField;
    @FXML private TextField numero2TextField;
    @FXML private ComboBox<String> operacionComboBox;
    @FXML private TextField resultadoTextField;
    @FXML private Label ultimoCalculoLabel;
    @FXML private ListView<String> historialListView;

    @FXML
    private void registrar() {
        String nombre = nombreTextField.getText();
        String edad = edadTextField.getText();
        String carrera = categoriaCarrera.getValue();
        String semestre = categoriaSemestre.getValue();
        var materiasSeleccionadas = materias.getSelectionModel().getSelectedItems();
        if (nombre.isEmpty() || edad.isEmpty() || carrera == null || semestre == null || materiasSeleccionadas.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Campos incompletos");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, llena todos los campos antes de registrar.");
            alerta.showAndWait();
            return;
        }
        String materiasTexto = String.join(", ", materiasSeleccionadas);
        String registro = String.format("Registro: "+nombre+" | Edad: "+edad+" | Carrera: "+carrera+" | Semestre: "+semestre+" | Materias: "+materiasTexto);
        labelRegistro.setText(registro);
    }

    @FXML
    private void limpiar() {
        nombreTextField.clear();
        edadTextField.clear();

        categoriaSemestre.getSelectionModel().clearSelection();
        categoriaCarrera.getSelectionModel().clearSelection();

        materias.getSelectionModel().clearSelection();
        labelRegistro.setText("Registro: ");
    }
    @FXML
    private void calcular(){
        try {
            double numero1 = Double.parseDouble(numero1TextField.getText());
            double numero2 = Double.parseDouble(numero2TextField.getText());
            String operacion = operacionComboBox.getValue();

            if (operacion == null){
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Campos incompletos");
                alerta.setHeaderText(null);
                alerta.setContentText("Por favor, llena todos los campos.");
                alerta.showAndWait();
                return;
            }
            double resultado =0;
            String expresion="";

            switch (operacion) {
                case "Suma":
                    resultado = numero1 + numero2;
                    expresion = numero1 + " + " + numero2 + "=" + resultado;
                    break;

                case "Resta":
                    resultado = numero1 - numero2;
                    expresion = numero1 + " - " + numero2 + "=" + resultado;
                    break;
                case "Multiplicacion":
                    resultado = numero1 * numero2;
                    expresion = numero1 + " * " + numero2 + "=" + resultado;
                    break;
                case "Division":
                    if (numero2 == 0) {
                        Alert alerta = new Alert(Alert.AlertType.WARNING);
                        alerta.setTitle("Division Erronea");
                        alerta.setContentText("No se puede dividir por cero");
                        alerta.showAndWait();
                        return;
                    }
                    resultado = numero1 / numero2;
                    expresion = numero1 + " / " + numero2 + "=" + resultado;
                    break;
                case "Potencia":
                    resultado = Math.pow(numero1, numero2);
                    expresion = numero1 + " ^ " + numero2 + "=" + resultado;
                    break;
                case "Raiz Cuadrada":
                    resultado = Math.sqrt(numero1);
                    expresion = " √ "+numero1  + "=" + resultado;
                    break;

            }
            resultadoTextField.setText(expresion);
            ultimoCalculoLabel.setText("Ultimo Calculo: "+expresion);

        }catch (NumberFormatException e){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Campos incorrectos");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, ingresa numeros validos.");
        }

    }
    @FXML
    private void agregarHistorial() {
        String calculo = resultadoTextField.getText();
        if (!calculo.isEmpty()){
            historialListView.getItems().add(calculo);
        }
    }
    @FXML
    private void limpiarCalc() {
        numero1TextField.clear();
        numero2TextField.clear();
        operacionComboBox.getSelectionModel().clearSelection();
        resultadoTextField.clear();
        ultimoCalculoLabel.setText("Ultimo Calculo: ");
    }
    @FXML
    private void borrarHistorial() {
        historialListView.getItems().clear();
    }
    @FXML
    public void initialize(){
        categoriaCarrera.getItems().addAll("Desarrollo de Software", "Redes y Telecomunicaciones", "Mecatronica","Saneamiento de Aguas");
        categoriaSemestre.getItems().addAll("1er Semestre", "2do Semestre", "3er Semestre", "4to. Semestre", "5to Semestre");
        materias.getItems().addAll("Matematicas","Programacion","Base de Datos","Estadistica","Redes","Fisica");
        materias.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        operacionComboBox.getItems().addAll("Suma", "Resta","Multiplicacion", "Division","Potencia","Raiz Cuadrada");

    }

}
