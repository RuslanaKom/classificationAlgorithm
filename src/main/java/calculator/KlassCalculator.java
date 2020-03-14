package calculator;

import model.PointEntity;
import model.PointWithDistance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class KlassCalculator {

    private DistanceCalculator distanceCalculator;

    public KlassCalculator(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public String calculateClass(List<PointEntity> knownPoints, PointEntity newPoint, int numberOfNeighbours) {
        List<PointWithDistance> pointsWithDistances = calculateDistances(knownPoints, newPoint);
        List<PointWithDistance> neighbours = getNeighbours(numberOfNeighbours, pointsWithDistances);
        String klass = defineKlass(neighbours);
        return klass;
    }

    private List<PointWithDistance> calculateDistances(List<PointEntity> knownPoints, PointEntity newPoint) {
        List<PointWithDistance> points = knownPoints
                .stream()
                .map(p -> new PointWithDistance(p, distanceCalculator.calculateDistance(newPoint, p)))
                .collect(Collectors.toList());
        System.out.println("Distances:");
        points.forEach(p -> System.out.println(p.getName() + " : " + p.getDistance()));
        return points;
    }

    private List<PointWithDistance> getNeighbours(int numberOfSmallestDistances, List<PointWithDistance> knownPointsWithDistance) {
        List<Double> smallestDistances = knownPointsWithDistance
                    .stream()
                    .map(PointWithDistance::getDistance)
                    .distinct()
                    .sorted()
                .limit(numberOfSmallestDistances)
                .collect(Collectors.toList());

        System.out.println(numberOfSmallestDistances + " nearest distances are:");
        smallestDistances.forEach(d -> System.out.println(d));

        List<PointWithDistance> neighbours = new ArrayList<>();
        for (Double distance : smallestDistances) {
            neighbours.addAll(knownPointsWithDistance
                    .stream()
                    .filter(p -> Double.compare(p.getDistance(), distance) == 0)
                    .collect(Collectors.toList()));
        }
        System.out.println(" nearest neighbours are:");
        neighbours.forEach(n -> System.out.println(n.getName() + " : " + n.getKlass()));
        return neighbours;
    }

    private String defineKlass(List<PointWithDistance> neighbours) {
        Map<String, Integer> klassMap = createKlassMap(neighbours);
        for (PointWithDistance point : neighbours) {
            klassMap.put(point.getKlass(), klassMap.get(point.getKlass()) + 1);
        }
        Map.Entry<String, Integer> entry = klassMap
                .entrySet()
                .stream()
                .max(Comparator.comparing(e -> e.getValue()))
                .orElse(null);
        if (entry != null) {
            System.out.println("Class " + entry.getKey() + " repeats " + entry.getValue() + " times");
            return entry.getKey();
        }
        return null;
    }

    private Map<String, Integer> createKlassMap(List<PointWithDistance> neighbours) {
        Set<String> klasses = neighbours.stream()
                .map(PointWithDistance::getKlass)
                .collect(Collectors.toSet());
        Map<String, Integer> klassMap = new HashMap<>();
        for (String klass : klasses) {
            klassMap.put(klass, 0);
        }
        return klassMap;
    }
}
