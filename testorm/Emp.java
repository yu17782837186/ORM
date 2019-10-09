package cn.com.testorm;

import java.sql.Date;

public class Emp { //表结构和类对应
    private Integer id;
    private String empname;
    private Double salary;
    private Date birthday;
    private int age;
    private Integer depId;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public Emp(String empname, Double salary) {
        this.empname = empname;
        this.salary = salary;

    }
    
    public Emp() {
    }
}
