package calculator;

import model.PointEntity;

public class DistanceCalculatorImpl2 implements DistanceCalculator {
    @Override
    public Double calculateDistance(PointEntity newPoint, PointEntity knownPoint) {
        return Math.max(Math.abs(newPoint.getX() - knownPoint.getX()), Math.abs(newPoint.getY() - knownPoint.getY()));
    }
}
