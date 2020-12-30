package sample;

public class Student {
  private String name;
  private String data;
  private int grade;
  private int id;

    public Student(int id,String name,String data,int grade){
        this.id=id;
        this.name=name;
        this.data=data;
        this.grade=grade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Student(String name){
        this.name=name;

    }
    public Student(String data,int grade) {

        this.data = data;
        this.grade = grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public int getGrade() {
        return grade;
    }
}
