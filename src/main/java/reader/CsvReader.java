package reader;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import model.PointEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private static final String FILE_LOCATION = "C:\\Projects\\intelektika\\intelektika1\\src\\main\\resources\\data.csv";

    public List<PointEntity> getDataFormCsv() {
        File file = new File(FILE_LOCATION);
        List<PointEntity> points = new ArrayList<>();
        final CSVParser parser = new CSVParserBuilder().withSeparator(';')
                .build();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(file))
                .withSkipLines(1)
                .withCSVParser(parser)
                .build()) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                PointEntity point = new PointEntity(line);
                points.add(point);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return points;
    }

    public void writeToCsv(PointEntity newPoint) throws FileNotFoundException {
        File file = new File(FILE_LOCATION);
        List<String[]> data = new ArrayList<>();
        final CSVParser parser = new CSVParserBuilder().withSeparator(';')
                .build();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(file))
                .withSkipLines(0)
                .withCSVParser(parser)
                .build()) {
            data = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        String[] newLine = {"e" + (data.size()), Double.toString(newPoint.getX()), Double.toString(newPoint.getY()), newPoint.getKlass()};
        data.add(newLine);
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(file), ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        ) {
            csvWriter.writeAll(data);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
