package com.tec.anji.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student
{
    private String _name;

    private SimpleStringProperty name;

    private int _age;

    private SimpleIntegerProperty age;

    private double _score;

    private SimpleDoubleProperty score;

    public Student(String name, int age, double score)
    {
        setName(name);
        setAge(age);
        setScore(score);
    }

    public String getName()
    {
        if (name == null)
        {
            return _name;
        }
        return name.get();
    }

    public StringProperty nameProperty()
    {
        if (name == null)
        {
            name = new SimpleStringProperty();
        }
        return name;
    }

    public void setName(String name)
    {
        if (this.name == null)
        {
            this._name = name;
            return;
        }
        this.name.set(name);
    }

    public int getAge()
    {
        if (age == null)
        {
            return _age;
        }
        return age.get();
    }

    public IntegerProperty ageProperty()
    {
        if (age == null)
        {
            age = new SimpleIntegerProperty();
        }
        return age;
    }

    public void setAge(int age)
    {
        if (this.age == null)
        {
            this._age = age;
            return;
        }
        this.age.set(age);
    }

    public double getScore()
    {
        if (score == null)
        {
            return _score;
        }
        return score.get();
    }

    public DoubleProperty scoreProperty()
    {
        if (score == null)
        {
            score = new SimpleDoubleProperty();
        }
        return score;
    }

    public void setScore(double score)
    {
        if (this.score == null)
        {
            this._score = score;
            return;
        }
        this.score.set(score);
    }
}
