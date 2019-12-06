package tgrabins.performance.db;

import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DbExample {
    public static void main(String[] args) throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        Connection connection = null;
        try {
            // create a database connection
            String url = "jdbc:sqlite:sample.db";
            connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.


            statement.executeUpdate("drop table if exists person");
            statement.executeUpdate("create table person (id integer, name string)");
            statement.executeUpdate("insert into person values(1, 'leo')");

            startReadingThread(url);

            var readPS = connection.prepareStatement("select id,name from person where id=?");
            for (int j=2; j<1000000; j=j+1000){
                for (int i = j+2; i < j+500; i++) {
                    statement.executeUpdate("insert into person values("+i+", 'yui"+i+"')");
                                   if (i%144==0){
                        readPS.setInt(1, i);
                        ResultSet rs1 = readPS.executeQuery();
                        while (rs1.next()) {
                            // read the result set
                            System.out.println("name = " + rs1.getString("name") + " id = " + rs1.getInt("id"));
                        }
                    }

                }
                var insertPS = connection.prepareStatement("insert into person values(?, ?)");
                for (int i = j+502; i < j+999; i++) {
                    insertPS.setInt(1,i);
                    insertPS.setString(2,"huy"+i);
                    insertPS.execute();
                    if (i%144==0){
                        readPS.setInt(1, i);
                        ResultSet rs1 = readPS.executeQuery();
                        while (rs1.next()) {
                            // read the result set
                            System.out.println("name = " + rs1.getString("name") + " id = " + rs1.getInt("id"));
                        }
                    }

                }
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }

    private static void startReadingThread(String url) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(() -> {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30); // set timeout to 30 sec.
                for (int i = 0; i < 1000; i++) {

                    var rs1 = statement.executeQuery("select id,name from person");
                    while (rs1.next()) {
                        // read the result set
                    }
                    System.out.println("READ  " + i);
                    Thread.sleep(1000);
                }

            } catch (SQLException | InterruptedException e) {
                // if the error message is "out of memory",
                // it probably means no database file is found
                System.err.println(e.getMessage());
            } finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    // connection close failed.
                    System.err.println(e.getMessage());
                }
            }

        });
    }
}
