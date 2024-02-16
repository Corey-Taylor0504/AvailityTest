package enrollment_processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class EnrollmentProcessor {
    public static void main(String[] args) {
        String csvFile = "enrollment.csv";
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            Map<String, List<Student>> studentsByCompany = new HashMap<>();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                String studentId = data[0];
                String fName = data[1];
                String lName = data[2];
                int v = Integer.parseInt(data[3]);
                String company = data[4];

                Student student = new Student(studentId, fName, lName, v, company);

                studentsByCompany.computeIfAbsent(company, k -> new ArrayList<>()).add(student);
            }

            for (Map.Entry<String, List<Student>> entry : studentsByCompany.entrySet()) {
                String companyName = entry.getKey();
                List<Student> students = entry.getValue();

                Collections.sort(students);

                Map<String, Student> uniqueStudents = new HashMap<>();
                for (Student student : students) {
                    String key = student.getStudentId();
                    if (!uniqueStudents.containsKey(key) || student.getVersion() > uniqueStudents.get(key).getVersion()) {
                        uniqueStudents.put(key, student);
                    }
                }

                try (FileWriter writer = new FileWriter(companyName + "_students.csv")) {
                    writer.write("Student Id,First Name,Last Name,Version,Company\n");
                    for (Student student : uniqueStudents.values()) {
                        writer.write(student.toCsvString() + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Student files processed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

