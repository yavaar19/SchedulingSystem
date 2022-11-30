package Database;

import com.example.schedulingsystem.ResultSetInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Class.Type;
import java.sql.ResultSet;
import java.sql.Timestamp;

/***
 * This is the TypeCRUD class! This class performs a select statement to retrieve appointments from a
 * specific month and then returns the appointments to an observable list!
 */
public class TypeCRUD {

	static ResultSetInterface resultSet = sql -> {

		Query.makeQuery(sql);
		ResultSet rs = Query.getResult();

		return rs;

	};

	/***
	 * getAllTypes
	 * The getAllTypes is a method that gets all the appointments from a specific month from the database and adds
	 * it to an observable list!
	 * @param month A parameter of month is passed in!
	 * @return This method returns an observable list of Type!
	 * @throws Exception Exception is needed in case of failure!
	 */
	public static ObservableList<Type> getAllTypes(String month) throws Exception{

		ObservableList<Type> allTypes = FXCollections.observableArrayList();
		JDBC.openConnection();
		ResultSet result = resultSet.result("SELECT DISTINCT TYPE, COUNT(*) AS COUNT FROM APPOINTMENTS WHERE MONTHNAME(START) = '" + month + "' GROUP BY TYPE");

		while(result.next()){

			String type = result.getString("TYPE");
			int count = result.getInt("COUNT");

			Type typeResult= new Type(type, count);
			allTypes.add(typeResult);

		}

		JDBC.closeConnection();
		return allTypes;

	}

}
