package com.shindhe.processor;

import com.shindhe.postgresql.entity.Student;
import org.springframework.stereotype.Component;


@Component
public class ItemProcessor implements org.springframework.batch.item.ItemProcessor<Student, com.shindhe.mysql.entity.Student> {

    @Override
    public com.shindhe.mysql.entity.Student process(Student item) throws Exception {

        System.out.println(item.getId());

        com.shindhe.mysql.entity.Student student = new
                com.shindhe.mysql.entity.Student();

        student.setId(item.getId());
        student.setFirstName(item.getFirstName());
        student.setLastName(item.getLastName());
        student.setEmail(item.getEmail());
        student.setDeptId(item.getDeptId());
        student.setIsActive(item.getIsActive() != null ?
                Boolean.valueOf(item.getIsActive()) : false);

        return student;

    }

}
