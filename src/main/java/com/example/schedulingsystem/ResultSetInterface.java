package com.example.schedulingsystem;

import java.sql.ResultSet;

/***
 * This is a lambda that i created! The code within the lambda executes a query and returns
 * a resultset! It passes in a string with is the SQL statement! My justification to using
 * the resultset for my lambda is because there are so queries that needs
 * to be made and this lambda saves me a lot of time in code writing!
 *
 */
public interface ResultSetInterface {

	ResultSet result(String sql);

}
