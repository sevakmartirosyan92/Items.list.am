package manager;

import db.DBConnectionProvider;
import model.Category;
import model.Item;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private final CategoryManager categoryManager = new CategoryManager();
    private final Connection connection = DBConnectionProvider.getINSTANCE().getConnection();

    public void addItem(Item item) {
        String sql = "INSERT INTO item(title, price, category_id, pic_url, user_id) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, item.getTitle());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setInt(3, item.getCategory().getId());
            preparedStatement.setString(4, item.getPicUrl());
            preparedStatement.setInt(5, item.getUser().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getItems() {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT * FROM item order by id desc limit 20";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                itemList.add(getFromResultSet(resultSet));
            }
            return itemList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> getItemsByUserId(int userId) {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT * FROM item WHERE user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                itemList.add(getFromResultSet(resultSet));
            }
            return itemList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> getItemsByCategory(int category) {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT * FROM item WHERE category_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, category);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                itemList.add(getFromResultSet(resultSet));
            }
            return itemList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeItemById(int id) {
        String sql = "DELETE FROM item WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Item getFromResultSet(ResultSet resultSet) throws SQLException {
        return Item.builder()
                .id(resultSet.getInt(1))
                .title(resultSet.getString(2))
                .price(resultSet.getDouble(3))
                .category((Category) resultSet.getObject(4))
                .picUrl(resultSet.getString(5))
                .user((User) resultSet.getObject(6))
                .build();
    }


}
