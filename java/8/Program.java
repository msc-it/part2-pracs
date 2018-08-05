import java.sql.*;
 
public class Program {
 
    public static void main(String[] args) 
    throws SQLException, ClassNotFoundException {

        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MSC");
        
        // A. Create and Insert values
        Statement statement = conn.createStatement();
        statement.executeUpdate("CREATE TABLE Student(Firstname text)");
        statement.executeUpdate("INSERT INTO Student values('Blossom')");
        statement.executeUpdate("INSERT INTO Student values('Galdin')");
        System.out.println("Values inserted into the database.");
        System.out.println();

        // B. Alter table to add a new column
        statement.executeUpdate("ALTER TABLE Student ADD COLUMN Photo text");
        System.out.println("Tabled successfully altered.");
        System.out.println();

        // C. Retrieve values
        System.out.println("Retrieving data...");
        System.out.println();
        ResultSet result = statement.executeQuery("SELECT Firstname FROM Student");
        while(result.next()) {
            System.out.println(result.getString(1));
        }

        statement.close();
        conn.close();
    }
   
}
