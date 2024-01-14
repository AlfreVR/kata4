package software.ulpgc.kata4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqliteCarLoader implements CarLoader{
    private final Connection connection;

    public SqliteCarLoader(Connection connection) {
        this.connection = connection;
    }
    private final static String SQLQuery = "SELECT model_id, model_name, model_base_price\n" +
            "FROM Models\n" +
            "WHERE model_base_price < 100000";

    @Override
    public List<Car> loadCar() {
        try {
            return loadCar(resultSet(SQLQuery)) ;
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    private List<Car> loadCar(ResultSet resultSet) throws SQLException {
        List<Car> cars = new ArrayList<>();
        while(resultSet.next()){
            cars.add(newCar(resultSet));
        }
        return cars;
    }

    private Car newCar(ResultSet resultSet) throws SQLException {
        return new Car(
                resultSet.getInt("model_id"),
                resultSet.getString("model_name"),
                resultSet.getString("model_base_price")
        );
    }

    private ResultSet resultSet(String sqlQuery) throws SQLException {
        return connection.createStatement().executeQuery(sqlQuery);
    }
}
