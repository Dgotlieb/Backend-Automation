import java.sql.*;

public class DBTest {
    private static final String USER_NAME = "XXXXXXXXXXXXXXX";
    private static final String DATABASE_NAME = "XXXXXXXXXXXXXX";
    private static final String PASSWORD = "XXXXXXXXXXXXXX";
    private static final String PORT = "3306";
    private static final String SERVER = "remotemysql.com";

    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://"+SERVER+":"+PORT, USER_NAME, PASSWORD);

        createTable(con);
//        insertUser(con, 123, "daniel");
//        getTableContent(con);
//        deleteUserByName(con, "daniel");
//        updateUserName(con,1234, "John");
        con.close();
    }

    private static void createTable(Connection con) throws SQLException {
        String statementToExecute = "CREATE TABLE " + DATABASE_NAME + ".`users`(`id` INT NOT NULL,`name` VARCHAR(45) NOT NULL, PRIMARY KEY (`id`));";
        con.createStatement().execute(statementToExecute);
    }

    private static void insertUser(Connection con, int id, String name) throws SQLException {
        String statementToExecute = "INSERT INTO " + DATABASE_NAME + ".users (`id`, `name`) VALUES ('" + id + "', '" + name + "');";
        con.createStatement().execute(statementToExecute);
    }

    private static void getTableContent(Connection con) throws SQLException {
        String statementToExecute = "SELECT * FROM " + DATABASE_NAME + ".users;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(statementToExecute);
        while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("id");
            String name = rs.getString("name");

            //Display values
            System.out.print("ID: " + id);
            System.out.print(", Name: " + name);
        }
        rs.close();
    }
    private static void deleteUserByName(Connection con, String name) throws SQLException {
        String statementToExecute = "DELETE FROM `" + DATABASE_NAME + "`.`users` WHERE `name`='"+name+"';";
        con.createStatement().execute(statementToExecute);
    }

    private static void updateUserName(Connection con, int id, String name) throws SQLException {
        String statementToExecute = "UPDATE `" + DATABASE_NAME + "`.`users` SET `name`='"+name+"' WHERE `id`='"+id+"';";
        con.createStatement().executeUpdate(statementToExecute);
    }
}