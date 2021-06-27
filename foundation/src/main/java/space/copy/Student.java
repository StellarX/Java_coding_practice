package space.copy;

import lombok.Data;

@Data
public class Student implements Cloneable{
    private String name;
    public void copy() throws CloneNotSupportedException {
        super.clone();
    }
}
