package software.ulpgc.kata4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try(Connection connection = DriverManager.getConnection("jdbc:Sqlite:Car_Database.db")){
            CarLoader carLoader = new SqliteCarLoader(connection);
            List<Car> cars= carLoader.loadCar();
            for (Car car :
                    cars) {
                System.out.println(car.model_name() + " - " + car.model_base_price());
            }
        }
    }
}
