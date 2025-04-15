package com.example.demo.dto;

public class Employee {
    private int id;
    private int emp_code;
    private String name;

    public Employee() {}

    public Employee(int id, int emp_code, String name) {
        this.id = id;
        this.emp_code = emp_code;
        this.name = name;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getEmp_code() { return emp_code; }
    public void setEmp_code(int emp_code) { this.emp_code = emp_code; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", emp_code=" + emp_code +
                ", name='" + name + '\'' +
                '}';
    }
}
