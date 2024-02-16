package enrollment_processor;

class Student implements Comparable<Student> {
    private String studentId;
    private String firstName;
    private String lastName;
    private int version;
    private String company;

    public Student(String studentId, String firstName, String lastName, int version, String company) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.version = version;
        this.company = company;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getVersion() {
        return version;
    }

    public String getCompany() {
        return company;
    }

    public String toCsvString() {
        return studentId + "," + firstName + "," + lastName + "," + version + "," + company;
    }

    @Override
    public int compareTo(Student other) {
        int result = this.lastName.compareTo(other.lastName);
        if (result == 0) {
            result = this.firstName.compareTo(other.firstName);
            if (result == 0) {
                result = Integer.compare(this.version, other.version);
            }
        }
        return result;
    }
}

