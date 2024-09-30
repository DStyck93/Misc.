import java.sql.*;
import java.util.ArrayList;

public class Database {
    // Variables
    private static final String HOST = "mysql://localhost:3306/";
    private static final String DATABASE_NAME  = "my_database";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password123";


    public static void main(String[] args) throws SQLException {

        // Database Connection
        String URL = "jdbc:" + HOST + DATABASE_NAME;
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // Create Query
        String table_name = "my_table";
        String SQL_query = "SELECT * FROM " + table_name;
        Statement statement = connection.createStatement();

        // Run Query
        ResultSet result_set = statement.executeQuery(SQL_query);

        // Get Results
        // This will vary depending on the values queried and their datatypes.
        ArrayList<Person> people = new ArrayList<>(); // Create list to store values
        while (result_set.next()) {
            int id = result_set.getInt("id");
            String name = result_set.getString("name");
            boolean is_male = result_set.getBoolean("gender");
            Date date = result_set.getDate("date_added");
            Float income = result_set.getFloat("income");

            Person person = new Person(id, name, is_male, date, income);
            people.add(person);
        }

        // Display Results
        for (Person p : people) {
            System.out.println(p.toString());
        }
    }
}

class Person {
    private final int ID;
    private String name;
    private boolean is_male;
    private final Date DATE_ADDED;
    private Float income;

    Person(int id, String name, boolean is_male, Date date_added, Float income) {
        this.ID = id;
        this.name = name;
        this.is_male = is_male;
        this.DATE_ADDED = date_added;
        this.income = income;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", is_male=" + is_male +
                ", DATE_ADDED=" + DATE_ADDED +
                ", income=" + income +
                '}';
    }
}
