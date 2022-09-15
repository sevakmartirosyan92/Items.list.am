package manager;

import db.DBConnectionProvider;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {

    private final Connection connection = DBConnectionProvider.getINSTANCE().getConnection();
    public Category getCategoryById(int id) {
        String sql = "SELECT * FROM category WHERE id = ? order by id desc limit 20";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return Category.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> getCategories() {
        List<Category> categoryList = new ArrayList<>();
        String sql = "SELECT * FROM category";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categoryList.add(Category.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build());
            }
            return categoryList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
