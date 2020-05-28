package lr2;

public class Point2d {
     protected double xCoord; // координата X
     protected double yCoord;//координата Y

    public Point2d(double x, double y) {  //Конструктор инициализации
        xCoord = x;
        yCoord = y;
    }

    public Point2d() { //Конструктор по умолчанию .
        this(0, 0);
    }

    public double getX() { //Возвращение координаты x
        return xCoord;
    }

    public double getY() { //Возвращение координаты Y
        return yCoord;
    }

    public void setX(double val) { //Установка значения координаты X
        xCoord = val;
    }

    public void setY(double val) { //Установка значения координаты Y
        yCoord = val;
    }

    public static void main(String[] args) {
        Point2d myPoint = new Point2d();//создает точку (0,0)
        Point2d myOtherPoint = new Point2d(5, 3);//создает точку (5,3)
        Point2d aThirdPoint = new Point2d();

    }
}

