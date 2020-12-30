package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.*;
import java.util.concurrent.atomic.AtomicReference;

public class Controller {


    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<String, Student> tableName;
    @FXML
    private TableColumn<String, Student> tableDate;
    @FXML
    private TableColumn<String, Student> tableGrade;
    @FXML
    private TextField fildName;
    @FXML
    private TextField fildData;
    @FXML
    private TextField fildO;
    @FXML
    private Label vbr;
    @FXML
    private TableView<Student> tableStudent;
    @FXML
    private Button reda;
    int a = 0;
    public String str = "";
    public String name="";
    public String curss;
    public String grade;
    Student studentsList;


    Connection connection;

    public Controller() throws SQLException, ClassNotFoundException {
        connection = DataBaseConnection.getConnection();

    }

    @FXML
    public void exit(ActionEvent event) throws IOException {
        Platform.exit();

    }
    @FXML
    public void java (ActionEvent event) throws IOException {
        curss="java";
        vbr.setText("java");

    }
    @FXML
    public void english (ActionEvent event) throws IOException {
        curss="english";
        vbr.setText("Англиский");

    }
    @FXML
    public void delitData(ActionEvent event) throws SQLException {
        String sqlh="SELECT * FROM "+curss;
        PreparedStatement statement = connection.prepareStatement(sqlh);
        ResultSet resultSet = statement.executeQuery();
        Student student;
        while (resultSet.next()) {
            student = new Student(resultSet.getInt(1),resultSet.getString(3),resultSet.getString(2),resultSet.getInt(4));
            if (fildName.getText().equals(student.getName())){
                String sqldel="delete from "+curss+" where id = '"+student.getId()+"'";
                PreparedStatement st=connection.prepareStatement(sqldel);
                st.executeLargeUpdate();
            }
        }
    }
    @FXML
    public void delitStudent(ActionEvent event) throws SQLException {
        String sql="delete from students where name = '"+fildName.getText()+"'";
        PreparedStatement statement2 = connection.prepareStatement(sql);
        statement2.executeUpdate();
        String sqlh="SELECT * FROM "+curss;
        PreparedStatement statement = connection.prepareStatement(sqlh);
        ResultSet resultSet = statement.executeQuery();
        Student student;
        while (resultSet.next()) {
            student = new Student(resultSet.getInt(1),resultSet.getString(3),resultSet.getString(2),resultSet.getInt(4));
            if (fildName.getText().equals(student.getName())){
                String sqldel="delete from "+curss+" where id = '"+student.getId()+"'";
                PreparedStatement st=connection.prepareStatement(sqldel);
                st.executeLargeUpdate();
            }
            }
        y();
    }
    @FXML
    public void doba(ActionEvent event) throws SQLException {
        String sql="INSERT INTO `java`.`"+curss+"` (`data`, `name`, `grade`) VALUES ('"+fildData.getText()+"', '"+fildName.getText()+"', '"+fildO.getText()+"');";
        PreparedStatement statement2 = connection.prepareStatement(sql);
        statement2.executeUpdate();
        int i=0;
        String sqlh="SELECT * FROM students";
            PreparedStatement statement = connection.prepareStatement(sqlh);
            ResultSet resultSet = statement.executeQuery();
            Student student;
            while (resultSet.next()) {
                student = new Student(resultSet.getString(1));
                if (fildName.getText().equals(student.getName())){
                    i=1;
                }
            }
            if (i==0){
                String sqlp="INSERT INTO `java`.`students` (`name`) VALUES ('"+fildName.getText()+"');";
                PreparedStatement statement32 = connection.prepareStatement(sqlp);
                statement32.executeUpdate();
                i=0;
            }
            y();
         }

    @FXML
    public void reda(ActionEvent event) throws SQLException {
        String sql = "SELECT * FROM students";
        try {
            int i=0;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Student student;
            while (resultSet.next()) {
                student = new Student(resultSet.getString(1));
                if (fildName.getText().equals(student.getName())){
                  i=1;
                }
            }
            if(i==1) {
                System.out.println("dfsdsf");
                String sqlo = "UPDATE `java`.`"+curss+"` SET `grade` = '" + fildO.getText()
                        + "',data='" + fildData.getText() + "',name ='" + fildName.getText() + "' WHERE (`id` = '" + studentsList.getId() + "');";
                PreparedStatement statement2 = connection.prepareStatement(sqlo);
                statement2.executeUpdate();
                i=0;
            }
            else{
                fildName.setText("нету такова студента");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        y();
    }

    public void initialize() {
        y();
        showPersonDetails(null);
        studentTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
        studentTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> fildName.setText(newValue.getName()));
        tableStudent.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> fildData.setText(newValue.getData()));
        tableStudent.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> fildO.setText(String.valueOf(newValue.getGrade())));
    }


    public void showPersonDetails(Student student) {
        if (student != null) {
            ObservableList<Student> list1 = FXCollections.observableArrayList();
            str = student.getName();
            String sql1 = "SELECT * FROM "+curss+" WHERE name=" + "'" + str + "'";
            try {
                PreparedStatement statement = connection.prepareStatement(sql1);
                ResultSet resultSet = statement.executeQuery();
                Student student1;
                while (resultSet.next()) {
                    student1 = new Student(resultSet.getInt(1),str, resultSet.getString(2), resultSet.getInt(4));
                    list1.add(student1);
                }
                tableDate.setCellValueFactory(new PropertyValueFactory<>("data"));
                tableGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
                tableStudent.setItems(list1);
                tableStudent.getSelectionModel().selectedItemProperty().addListener(
                        (observable, oldValue, newValue) ->pup(newValue) );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
    public void pup(Student student){
            studentsList=student;
    }
    public void y (){
        ObservableList<Student> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM students";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Student student;
            while (resultSet.next()) {
                student = new Student(resultSet.getString(1));
                list.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentTable.setItems(list);

    }



}





