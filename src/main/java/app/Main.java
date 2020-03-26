package app;

import calculator.DistanceCalculator;
import calculator.DistanceCalculatorImpl1;
import calculator.DistanceCalculatorImpl2;
import calculator.KlassCalculator;
import model.PointEntity;
import org.apache.commons.lang3.StringUtils;
import reader.CsvReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        Double newPointX = null;
        Double newPointY = null;
        Integer neighbours = null;

        //read inputs
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter new point x value: ");
        newPointX = Double.parseDouble(reader.readLine());
        System.out.println("Enter new point y value: ");
        newPointY = Double.parseDouble(reader.readLine());
        System.out.println("Enter number of neighbours: ");
        neighbours = Integer.parseInt(reader.readLine());

        // work
        PointEntity newPoint = new PointEntity(newPointX, newPointY);

        CsvReader csvReader = new CsvReader();
        List<PointEntity> knownPoints = csvReader.getDataFormCsv();

        DistanceCalculator distanceCalculator1 = new DistanceCalculatorImpl1();
        KlassCalculator klassCalculator1 = new KlassCalculator(distanceCalculator1);

        String klass1 = klassCalculator1.calculateClass(knownPoints, newPoint, neighbours);

        DistanceCalculator distanceCalculator2 = new DistanceCalculatorImpl2();
        KlassCalculator klassCalculator2 = new KlassCalculator(distanceCalculator2);

        String klass2 = klassCalculator2.calculateClass(knownPoints, newPoint, neighbours);

        if ((!StringUtils.isEmpty(klass1) | (!StringUtils.isEmpty(klass2))) && StringUtils.equals(klass1, klass2)) {
            newPoint.setKlass(klass1);
            System.out.println("New point is of class " + klass1);
            csvReader.writeToCsv(newPoint);
        } else {
            System.out.println("Cannot determine class of new point");
        }

    }
}
