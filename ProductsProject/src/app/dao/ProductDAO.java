import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM products";
    
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
             ResultSet resultSet = statement.executeQuery()) {
            System.out.println("Executing query: " + SELECT_ALL_PRODUCTS);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                
                Product product = new Product(id, name, description, price, quantity);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
