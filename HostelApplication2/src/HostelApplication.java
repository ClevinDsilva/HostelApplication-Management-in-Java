import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HostelApplication {

    private static Scanner scanner = new Scanner(System.in);
    private static List<Student> acceptedStudents = new ArrayList<>();
    private static List<Student> rejectedStudents = new ArrayList<>();
    private static Map<String, List<String>> districtColleges = new HashMap<>();
    private static Map<String, List<String>> collegeMaleHostels = new HashMap<>();
    private static Map<String, List<String>> collegeFemaleHostels = new HashMap<>();
    private static Map<String, Integer> hostelCapacity = new HashMap<>();

    public static void main(String[] args) {
        initializeData();
        runApplication();
    }

    private static void initializeData() {
        // Initialize district to colleges mapping
        districtColleges.put("Bangalore", Arrays.asList("Bengaluru University", "Jain (Deemed-to-be University)","New Horizon College of Engineering","Dayananda Sagar University"));
        districtColleges.put("DAKSHINA KANNADA", Arrays.asList("St Aloysius College (Deemed to be university)", "Mangalore University","St Joseph's Engineering College","St. Agnes College(Autonomous)"));
        districtColleges.put("Mysore", Arrays.asList("Maharaja Institute Of Technology Thandavapura", "St Philomena's College","Amity University","University of Mysore","National Institute of Engineering, Mysore."));
        districtColleges.put("Hassan", Arrays.asList("St Joseph's College,Hassan", "Malnad College of Engineering, Hassan","Rajeev Institute of Technology, Hassan"));


        // Initialize male hostels mapping
        collegeMaleHostels.put("Bengaluru University", Arrays.asList("Krinaraj Male Hostel", "BCM Hostel for men"));
        collegeMaleHostels.put("Jain (Deemed-to-be University)", Arrays.asList("Minority men hostel", "Rajiv govt hostel"));
        collegeMaleHostels.put("New Horizon College of Engineering", Arrays.asList("SWF Men Hostel", "Govt men Hostel"));
        collegeMaleHostels.put("Dayananda Sagar University", Arrays.asList("Chamdui Men's Hostel", "MNC Men's hostel"));

        collegeMaleHostels.put("St Aloysius College (Deemed to be university)", Arrays.asList("Minority Boys Hostel,Joythi", "BCM Men Hostel,Natekal"));
        collegeMaleHostels.put("Mangalore University", Arrays.asList("Krishnaraj Men's Hostel,Nadupadav", "University Boys Hostel,Asegoli"));
        collegeMaleHostels.put("St Joseph's Engineering College", Arrays.asList("Govt Boy's Hostel Vamanjure", "Minority Boys Hostel,Karavali"));
        collegeMaleHostels.put("St. Agnes College(Autonomous)", Arrays.asList("Kadri Boys Hostel", "Minority Hostel,Kankanady"));

        collegeMaleHostels.put("Maharaja Institute Of Technology Thandavapura", Arrays.asList("Men's Hostel", "SCST Boys Hostel"));
        collegeMaleHostels.put("St Philomena's College", Arrays.asList("Govt Men's Hostel", "Male Govt Hostel"));
        collegeMaleHostels.put("Amity University", Arrays.asList("Sanjivini Men's Hostel", "Govt Hostel Men"));
        collegeMaleHostels.put("University of Mysore", Arrays.asList("Dhanajay Men Hostel", "Nithis Men's Hostel"));

        collegeMaleHostels.put("National Institute of Engineering", Arrays.asList("Men Govt Hostel", "Boys hostel"));
        collegeMaleHostels.put("St Joseph's College,Hassan", Arrays.asList("Soma Boys Hostel", "Vignesh Men Hostel"));
        collegeMaleHostels.put("Malnad College of Engineering, Hassan", Arrays.asList("Hassanaba Men Hostel", "Sharath Men Hostel"));
        collegeMaleHostels.put("Rajeev Institute of Technology, Hassan", Arrays.asList("Minority Men Hostel", "BCM Boys Hostel"));

        // Initialize female hostels mapping
        collegeFemaleHostels.put("Bengaluru University", Arrays.asList("Minority Hostel for Women", "University Women Hostel1"));
        collegeFemaleHostels.put("Jain (Deemed-to-be University)", Arrays.asList("BCM Women Hostel1","Minority Women Hostel1"));
        collegeFemaleHostels.put("New Horizon College of Engineering", Arrays.asList("BCM Girls Hostel2", "Minority Womne Hostel"));
        collegeFemaleHostels.put("Dayananda Sagar University", Arrays.asList("BCM Women Hostel3", "Minority Women Hostel2"));

        collegeFemaleHostels.put("St Aloysius College (Deemed to be university)", Arrays.asList("BCM Women Hostel,Joythi", "Minority Womne Hostel,Hampanakata"));
        collegeFemaleHostels.put("Mangalore University", Arrays.asList("BCM Women Hostel,Asegoli", "University women Hostel,Natekala"));
        collegeFemaleHostels.put("St Joseph's Engineering College", Arrays.asList("Minority Women Hostel,Vamanjure", "BCM Girls Hostel,Kadri"));
        collegeFemaleHostels.put("St. Agnes College(Autonomous)", Arrays.asList("Womens Hostel,Kanakanady", "Govt Womne Hostel,Nanthur"));

        collegeFemaleHostels.put("Maharaja Institute Of Technology Thandavapura", Arrays.asList("Girls Hostel1", "Hostel Girls BCM"));
        collegeFemaleHostels.put("St Philomena's College", Arrays.asList("University Women Hostel3", "Minority women Hostel Hostel6"));
        collegeFemaleHostels.put("Amity University", Arrays.asList("Nayana Womens Hostel", "BCM Women Hostel5"));
        collegeFemaleHostels.put("University of Mysore", Arrays.asList("Minority Women Hostel", "Girls Hostel"));

        collegeFemaleHostels.put("National Institute of Engineering", Arrays.asList("University Women Hostel", "Women Hostel"));
        collegeFemaleHostels.put("St Joseph's College,Hassan", Arrays.asList("Girls Hostel ", "BCM Girls Hostel"));
        collegeFemaleHostels.put("Malnad College of Engineering, Hassan", Arrays.asList("Minority Women Hostel", "BCM Women Hostel4"));
        collegeFemaleHostels.put("Rajeev Institute of Technology, Hassan", Arrays.asList("BCM Women Hostel6", "Minority Girls Hostel"));

        // Initialize hostel capacities
        hostelCapacity.put("Krinaraj Male Hostel", 10);
        hostelCapacity.put("BCM Hostel for men", 15);
        hostelCapacity.put("Minority men hostel", 5);
        hostelCapacity.put("Rajiv govt hostel", 8);
        hostelCapacity.put("SWF Men Hostel", 12);
        hostelCapacity.put("MNC Men's hostel", 7);
        hostelCapacity.put("Chamdui Men's Hostel", 12);
        hostelCapacity.put("Govt men Hostel", 7);

        hostelCapacity.put("Minority Boys Hostel,Joythi", 10);
        hostelCapacity.put("BCM Men Hostel,Natekal", 15);
        hostelCapacity.put("Krishnaraj Men's Hostel,Nadupadav", 5);
        hostelCapacity.put("University Boys Hostel,Asegoli", 8);
        hostelCapacity.put("Govt Boy's Hostel Vamanjure", 12);
        hostelCapacity.put( "Minority Boys Hostel,Karavali", 7);
        hostelCapacity.put("Kadri Boys Hostel", 12);
        hostelCapacity.put("Minority Hostel,Kankanady", 7);

        hostelCapacity.put("Men's Hostel", 10);
        hostelCapacity.put("BCM Men Hostel", 15);
        hostelCapacity.put("Govt Men's Hostel", 5);
        hostelCapacity.put("Male Govt Hostel", 8);
        hostelCapacity.put("Govt Boy's Hostel ", 12);
        hostelCapacity.put("Sanjivini Men's Hostel", 7);
        hostelCapacity.put("Dhanajay Men Hostel", 12);
        hostelCapacity.put("Nithis Men's Hostel", 7);

        hostelCapacity.put("Men Govt Hostel", 10);
        hostelCapacity.put("Boys hostel", 15);
        hostelCapacity.put("Soma Boys Hostel", 5);
        hostelCapacity.put("Vignesh Men Hostel", 8);
        hostelCapacity.put("Hassanaba Men Hostel", 12);
        hostelCapacity.put("Sanjivini Men's Hostel,Karavali", 7);
        hostelCapacity.put("Sharath Men Hostel", 12);
        hostelCapacity.put("Minority Men Hostel", 7);

        hostelCapacity.put("Minority Hostel for Women", 10);
        hostelCapacity.put("University Women Hostel1", 10);
        hostelCapacity.put("BCM Women Hostel1", 5);
        hostelCapacity.put("Minority Women Hostel1", 8);
        hostelCapacity.put("BCM Girls Hostel2", 12);
        hostelCapacity.put("Minority Womne Hostel", 7);
        hostelCapacity.put("BCM Women Hostel3", 12);
        hostelCapacity.put("Minority Women Hostel2" ,7);

        hostelCapacity.put("BCM Women Hostel,Joythi", 10);
        hostelCapacity.put("Minority Womne Hostel,Hampanakata", 15);
        hostelCapacity.put("BCM Women Hostel,Asegoli", 5);
        hostelCapacity.put("University women Hostel,Natekala", 8);
        hostelCapacity.put("Minority Women Hostel,Vamanjure", 12);
        hostelCapacity.put("BCM Girls Hostel,Kadri", 7);
        hostelCapacity.put("Womens Hostel,Kanakanady", 12);
        hostelCapacity.put("Govt Womne Hostel,Nanthur", 7);

        hostelCapacity.put("Girls Hostel1", 10);
        hostelCapacity.put("Hostel Girls BCM", 10);
        hostelCapacity.put("University Women Hostel3", 5);
        hostelCapacity.put("Minority women Hostel Hostel", 8);
        hostelCapacity.put("Nayana Womens Hostel", 12);
        hostelCapacity.put("BCM Women Hostel5", 7);
        hostelCapacity.put("Girls Hostel", 12);
        hostelCapacity.put("Minority Women Hostel6",7);

        hostelCapacity.put("University Women Hostel", 10);
        hostelCapacity.put("Women Hostel", 15);
        hostelCapacity.put("Girls Hostel ", 5);
        hostelCapacity.put("BCM Girls Hostel", 8);
        hostelCapacity.put("Minority Women Hostel", 12);
        hostelCapacity.put("BCM Women Hostel4", 7);
        hostelCapacity.put("BCM Women Hostel6", 12);
        hostelCapacity.put("Minority Girls Hostel", 7);

    }
    private static void runApplication() {
        Student student = new Student();
        System.out.println("Hostel Application For PG Student");
        System.out.println("__________________________________");

        inputStudentDetails(student);

        System.out.println("Enter Gender (M/F):");
        String gender = scanner.nextLine().toUpperCase();
        if (!gender.equals("M") && !gender.equals("F")) {
            System.out.println("Invalid gender entered.");
            System.exit(0);
        }

        System.out.println("Enter Course:");
        student.setCourse(scanner.nextLine());

        System.out.println("Enter Address:");
        student.setAddress(scanner.nextLine());

        System.out.println("Enter Father's Name:");
        student.setFatherName(scanner.nextLine());

        System.out.println("Enter Mother's Name:");
        student.setMotherName(scanner.nextLine());

        System.out.println("Enter Parent Occupation:");
        student.setOccupation(scanner.nextLine());

        System.out.println("Enter Income:");
        student.setIncome(Double.parseDouble(scanner.nextLine()));
        if (student.getIncome() > 100000) {
            System.out.println("Not eligible due to high income.");
            rejectedStudents.add(student);
            saveStudentDetailsToTextFile(student, "rejected_students.txt");
            System.exit(0);
        }

        System.out.println("Enter State:");
        student.setState(scanner.nextLine());
        if (!"Karnataka".equalsIgnoreCase(student.getState())) {
            System.out.println("Not eligible as the state is not Karnataka.");
            rejectedStudents.add(student);
            saveStudentDetailsToTextFile(student, "rejected_students.txt");
            System.exit(0);
        }

        System.out.println("Select District:");
        String district = selectOption(new ArrayList<>(districtColleges.keySet()));
        if (district == null) {
            System.out.println("Invalid district selected.");
            rejectedStudents.add(student);
            saveStudentDetailsToTextFile(student, "rejected_students.txt");
            System.exit(0);
        }
        student.setDistrict(district);

        System.out.println("Select College:");
        String college = selectOption(districtColleges.get(district));
        if (college == null) {
            System.out.println("Invalid college selected.");
            rejectedStudents.add(student);
            saveStudentDetailsToTextFile(student, "rejected_students.txt");
            System.exit(0);
        }
        student.setCollege(college);

        Map<String, List<String>> hostels;
        if (gender.equals("M")) {
            hostels = collegeMaleHostels;
        } else {
            hostels = collegeFemaleHostels;
        }

        System.out.println("Select Hostel:");
        String hostel = selectOption(hostels.get(college));
        if (hostel == null) {
            System.out.println("Invalid hostel selected.");
            rejectedStudents.add(student);
            saveStudentDetailsToTextFile(student, "rejected_students.txt");
            System.exit(0);
        }

        if (hostelCapacity.get(hostel) <= 0) {
            System.out.println("Hostel full. Not eligible.");
            rejectedStudents.add(student);
            saveStudentDetailsToTextFile(student, "rejected_students.txt");
            System.exit(0);
        } else {
            student.setHostel(hostel);
            hostelCapacity.put(hostel, hostelCapacity.get(hostel) - 1);
            acceptedStudents.add(student);
            System.out.println("Hostel allocated: " + hostel);
            System.out.println("Visit the hostel for further admission process.");
            System.out.println("______________________________________________--");
            saveStudentDetailsToTextFile(student, "accepted_students.txt");
            System.exit(0);
        }
    }

    private static void inputStudentDetails(Student student) {
        // Input student details here
        inputName(student);
        inputMobileNo(student);
        inputEmail(student);
        inputAadharNo(student);
        // Input other details
    }

    private static void inputName(Student student) {
        System.out.println("Enter Student name:");
        String name = scanner.nextLine();
        if (!isValidName(name)) {
            System.out.println("Invalid name format. Please enter a valid name.");
            inputName(student);
        } else {
            student.setName(name);
        }
    }

    private static boolean isValidName(String name) {
        // Validate name format using regular expression
        String namePattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
        return name.matches(namePattern);
    }

    private static void inputMobileNo(Student student) {
        System.out.println("Enter Mobile No:");
        String mobileNo = scanner.nextLine();
        if (!isValidMobileNo(mobileNo)) {
            System.out.println("Invalid mobile number. Please enter a valid 10-digit mobile number.");
            inputMobileNo(student);
        } else {
            student.setMobileNo(mobileNo);
        }
    }

    private static boolean isValidMobileNo(String mobileNo) {
        // Validate mobile number format
        String mobilePattern = "^[0-9]{10}$";
        return mobileNo.matches(mobilePattern);
    }

    private static void inputEmail(Student student) {
        System.out.println("Enter Email ID:");
        String email = scanner.nextLine();
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format. Please enter a valid email address.");
            inputEmail(student);
        } else {
            student.setEmail(email);
        }
    }

    private static boolean isValidEmail(String email) {
        // Validate email format using regular expression
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailPattern);
    }

    private static void inputAadharNo(Student student) {
        System.out.println("Enter Aadhar No:");
        String aadharNo = scanner.nextLine();
        if (!isValidAadharNo(aadharNo)) {
            System.out.println("Invalid Aadhar number. Please enter a valid 12-digit Aadhar number.");
            inputAadharNo(student);
        } else {
            student.setAadharNo(aadharNo);
        }
    }

    private static boolean isValidAadharNo(String aadharNo) {
        // Validate Aadhar number format
        String aadharPattern = "^[0-9]{12}$";
        return aadharNo.matches(aadharPattern);
    }

    private static String selectOption(List<String> options) {
        if (options == null || options.isEmpty()) {
            return null;
        }
        int index = 1;
        for (String option : options) {
            System.out.println(index++ + ". " + option);
        }
        System.out.print("Select an option (number): ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0 && choice <= options.size()) {
                return options.get(choice - 1);
            }
        } catch (NumberFormatException e) {
            // Invalid input
        }
        System.out.println("Invalid option selected. Please try again.");
        return selectOption(options);
    }

    private static void saveStudentDetailsToTextFile(Student student, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Name: " + student.getName() + "\n");
            writer.write("Course: " + student.getCourse() + "\n");
            writer.write("Address: " + student.getAddress() + "\n");
            writer.write("Email: " + student.getEmail() + "\n");
            writer.write("Mobile No: " + student.getMobileNo() + "\n");
            writer.write("Aadhar No: " + student.getAadharNo() + "\n");
            writer.write("Father's Name: " + student.getFatherName() + "\n");
            writer.write("Mother's Name: " + student.getMotherName() + "\n");
            writer.write("Occupation: " + student.getOccupation() + "\n");
            writer.write("Income: " + student.getIncome() + "\n");
            writer.write("State: " + student.getState() + "\n");
            writer.write("District: " + student.getDistrict() + "\n");
            writer.write("College: " + student.getCollege() + "\n");
            writer.write("Hostel: " + student.getHostel() + "\n");
            writer.write("----------------------------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}