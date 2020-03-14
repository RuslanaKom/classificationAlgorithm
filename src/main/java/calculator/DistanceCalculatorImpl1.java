package calculator;

import model.PointEntity;

public class DistanceCalculatorImpl1 implements DistanceCalculator {
    @Override
    public Double calculateDistance(PointEntity newPoint, PointEntity knownPoint) {
        return Math.sqrt(Math.pow(newPoint.getX() - knownPoint.getX(), 2) + Math.pow(newPoint.getY() - knownPoint.getY(), 2));
    }
}
