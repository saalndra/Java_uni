package lab5.DAO;

import lab5.Group;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO {
    private final Connection connection;
    static final String TABLE_NAME = "\"Group\"";

    public GroupDAO() {
        try {
            connection = DataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Group add(Group group) throws SQLException {
        String insertGroup = String.format("INSERT INTO %s (group_number, year_of_creation, department, curator_name) VALUES (?, ?, ?, ?);", TABLE_NAME);
        try (PreparedStatement ps = connection.prepareStatement(insertGroup)) {
            ps.setString(1, String.valueOf(group.getGroupNumber()));
            ps.setInt(2, group.getYearOfCreation());
            ps.setString(3, group.getDepartment());
            ps.setString(4, group.getCuratorName());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return group;
            } else {
                throw new IllegalArgumentException("Error while creating group " + group);
            }
        }
    }

    public List<Group> getAll() throws SQLException {
        String getAll = String.format("SELECT * FROM %s;", TABLE_NAME);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(getAll);
        List<Group> groups = new ArrayList<>();

        while (rs.next()) {
            groups.add(fromResultSet(rs));
        }
        return groups;
    }

    private Group fromResultSet(ResultSet rs) throws SQLException {
        return new Group.Builder()
                .setGroupNumber(Integer.parseInt(rs.getString("group_number")))
                .setYearOfCreation(rs.getInt("year_of_creation"))
                .setDepartment(rs.getString("department"))
                .setCuratorName(rs.getString("curator_name"))
                .build();
    }
}
