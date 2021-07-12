package com.space.copy;

/**
 * @comment: ǳ����
 */
public class CopyTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Student student = new Student();
        student.setName("jack");
        System.out.println(student);
//        Student student1 = (Student) student.clone();
    }
}
