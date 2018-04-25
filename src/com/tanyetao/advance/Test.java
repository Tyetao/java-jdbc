package com.tanyetao.advance;

import com.tanyetao.pojo.Student;

public class Test {
    public static void main(String[] args) {
        StudentJdbc jdbc = new StudentJdbc();

        Student stu = new Student();
        stu.setName("谭业涛1");
        stu.setAddress("宝安区");
        stu.setGender("male");
        stu.setAge(20);
        jdbc.save(stu);


/*
jdbc.delete(6);
jdbc.queryId(7);
jdbc.queryAll();
*/

/*       Student stu = new Student();
        stu.setName("谭业涛");
        stu.setAddress("宝安区111111");
        stu.setAge(20);
        stu.setGender("male111111111");
        stu.setId(7);
        jdbc.update(stu);*/

    }
}
