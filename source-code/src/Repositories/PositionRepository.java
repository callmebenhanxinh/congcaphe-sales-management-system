/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import Database.ConnectDatabase;
import java.util.List;
import Models.Position;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ThanhNhan
 */
public class PositionRepository {
    private ConnectDatabase db = new ConnectDatabase();
    private List<Position> positions = new ArrayList<>();

    public PositionRepository() {
        loadPositionsFromDatabase();
    }

    private void loadPositionsFromDatabase() {
        String query = "SELECT MACV, TENCV FROM ChucVu"; 
        try {
            Connection conn = db.getConnect();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                String id = rs.getString("MACV"); 
                String name = rs.getString("TENCV");
                positions.add(new Position(id, name));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Lỗi khi lấy danh sách chức vụ: " + ex.getMessage());
        }
    }

    public List<String> getAllPositionsName() {
        List<String> positionNames = new ArrayList<>();
        for (Position position : positions) {
            positionNames.add(position.getName());
        }
        return positionNames;
    }

    public Position getPositionById(String positionId) {
        for (Position position : positions) {
            if (position.getID().equals(positionId)) {
                return position;
            }
        }
        return null;
    }

    public Map<String, String> getPositionNameIdMap() {
        Map<String, String> map = new HashMap<>();
        for (Position position : positions) {
            map.put(position.getName(), position.getID());
        }
        return map;
    }
}
