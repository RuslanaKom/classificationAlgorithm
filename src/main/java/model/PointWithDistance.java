package model;

public class PointWithDistance {
    private String name;
    private String klass;
    private Double distance;

    public PointWithDistance(PointEntity pointEntity, Double distance) {
        this.name = pointEntity.getName();
        this.klass = pointEntity.getKlass();
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKlass() {
        return klass;
    }

    public void setKlass(String klass) {
        this.klass = klass;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
