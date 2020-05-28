package lr2;

import java.util.Scanner;



public class Lab2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("a");
        Point3d a = new Point3d(sc.nextInt(), sc.nextInt(), sc.nextInt());
        System.out.println("b");
        Point3d b = new Point3d(sc.nextInt(), sc.nextInt(), sc.nextInt());
        System.out.println("c");
        Point3d c = new Point3d(sc.nextInt(), sc.nextInt(), sc.nextInt());

        System.out.println(a.getX() + ";" + a.getY() + ";" + a.getZ());
        System.out.println(b.getX() + ";" + b.getY() + ";" + b.getZ());
        System.out.println(c.getX() + ";" + c.getY() + ";" + c.getZ());

        System.out.println("Расстояние от а до b: " + String.format("%.2f", Point3d.distanceTo(a, b)));
        System.out.println("Расстояние от b до c: " + String.format("%.2f", Point3d.distanceTo(b, c)));
        System.out.println("Расстояние от c до a: " + String.format("%.2f", Point3d.distanceTo(c, a)));

        if (a.equalsTo(b) | b.equalsTo(c) | c.equalsTo(a))  System.out.println("Координаты равны");
        else System.out.println("Площадь треугольника равна: " + String.format("%.2f", Point3d.computeArea(a, b, c)));
    }
}