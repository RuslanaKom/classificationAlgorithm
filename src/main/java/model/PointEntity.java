package model;

public class PointEntity {
    private String name;
    private Double x;
    private Double y;
    private String klass;

    public PointEntity(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public PointEntity(String[] inputLine) {
        this.name = inputLine[0];
        this.x = Double.parseDouble(inputLine[1]);
        this.y = Double.parseDouble(inputLine[2]);;
        this.klass = inputLine[3];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getKlass() {
        return klass;
    }

    public void setKlass(String klass) {
        this.klass = klass;
    }
}
