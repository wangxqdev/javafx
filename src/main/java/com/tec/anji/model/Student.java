package com.tec.anji.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student
{
    private SimpleStringProperty name = new SimpleStringProperty();

    private SimpleIntegerProperty age = new SimpleIntegerProperty();

    private SimpleIntegerProperty score = new SimpleIntegerProperty();

    public Student(String name, int age, int score)
    {
        setName(name);
        setAge(age);
        setScore(score);
    }

    public String getName()
    {
        return name.get();
    }

    public SimpleStringProperty nameProperty()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public int getAge()
    {
        return age.get();
    }

    public SimpleIntegerProperty ageProperty()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age.set(age);
    }

    public int getScore()
    {
        return score.get();
    }

    public SimpleIntegerProperty scoreProperty()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score.set(score);
    }
}
