package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "triangulo")
public class Triangulo {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private float base;

    @Column(nullable = false)
    private float altura;

    @Column(name = "area_calculada") 
    private float area;

    public Triangulo() {
    }

    public Triangulo(int id, float base, float altura) {
        this.id = id;
        this.base = base;
        this.altura = altura;
        this.calcularArea();
    }

    private void calcularArea() {
        this.area = (this.base * this.altura) / 2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBase() {
        return base;
    }

    public void setBase(float base) {
        this.base = base;
        this.calcularArea(); 
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
        this.calcularArea(); 
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }
    
    public void setArea(float base, float altura) {
        this.base = base;
        this.altura = altura;
        this.calcularArea();
    }

    @Override
    public String toString() {
        return "Base : " + this.getBase() +
               "\nAltura: " + this.getAltura() +
               "\nÁrea : " + this.getArea();
    }
}