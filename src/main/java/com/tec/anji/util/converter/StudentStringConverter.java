package com.tec.anji.util.converter;

import com.tec.anji.model.Student;
import javafx.util.StringConverter;

public class StudentStringConverter extends StringConverter<Student>
{
    @Override
    public String toString(Student student)
    {
        if (null == student)
        {
            return "";
        }
        return student.getName();
    }

    @Override
    public Student fromString(String string)
    {
        return null;
    }
}
