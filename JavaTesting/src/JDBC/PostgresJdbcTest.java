package JDBC;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

public class PostgresJdbcTest {
    public static void main(String[] args) {

        Properties props = new Properties();

        try (
            // load properties file
            InputStream is = PostgresJdbcTest.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties")
        ) {
            props.load(is);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to PostgreSQL");

            PreparedStatement ps = con.prepareStatement( "SELECT * FROM student WHERE name = ?");
            ps.setString(1,"Vardhan");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " " +
                    rs.getString("name") + " " +
                    rs.getInt("age")
                );
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
