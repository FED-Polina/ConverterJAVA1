package fedulova.polina303.converter;

import androidx.annotation.NonNull;

public class MeasureUnit {
    //поля класса
    public Double coefficient;
    public String name;

    //конструктор класса
    public MeasureUnit(Double coefficient, String name) {
        this.coefficient = coefficient;
        this.name = name;
    }

    //переопределили метод toString чтобы выводить правильное название в спинере
    @NonNull
    @Override
    public String toString() {
        return  name;
    }
}
