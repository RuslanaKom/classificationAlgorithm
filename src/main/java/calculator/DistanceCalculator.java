package calculator;

import model.PointEntity;

public interface DistanceCalculator {

    Double calculateDistance(PointEntity newPoint, PointEntity knownPoint);
}
