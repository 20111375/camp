/**
 * Created with IntelliJ IDEA.
 * User: 20111375
 * Date: 05/11/2013
 * Time: 12:31
 */
package db;
import java.sql.*;

public class connection {
	protected ResultSet resultset = null;
	public connection() {
	}
	public ResultSet connect(String SQLString) {

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            Connection connect = DriverManager.getConnection("jdbc:derby:../~20111375/database");
            //Connection connect = DriverManager.getConnection("jdbc:derby:../20111375/database");
            PreparedStatement statement = connect.prepareStatement(SQLString);
            resultset = statement.executeQuery();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultset;
	}

    public  void ExecuteCustomerUpdate(String SQL){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            Connection connect = DriverManager.getConnection("jdbc:derby:../~20111375/database");
            PreparedStatement statement = connect.prepareStatement(SQL);
            statement.executeUpdate();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
