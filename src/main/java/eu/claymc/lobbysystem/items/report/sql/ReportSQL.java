package eu.claymc.lobbysystem.items.report.sql;

import eu.claymc.api.ClayAPI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportSQL {

    public List<String> getReportedPlayers() {
        try {
            PreparedStatement ps = ClayAPI.getInstance().getSqlAdapter().getConnection().prepareStatement("SELECT * FROM reports");
            ResultSet rs = ps.executeQuery();
            ArrayList<String> req = new ArrayList<>();
            while (rs.next())
                req.add(rs.getString("suspect"));
            return req;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getFrom(String reportedplayername) {
        try {
            PreparedStatement ps = ClayAPI.getInstance().getSqlAdapter().getConnection().prepareStatement("SELECT reporter FROM reports WHERE suspect = ?");
            ps.setString(1, reportedplayername);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString("reporter");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getReason(String reportedplayername) {
        try {
            PreparedStatement ps = ClayAPI.getInstance().getSqlAdapter().getConnection().prepareStatement("SELECT reason FROM reports WHERE suspect = ?");
            ps.setString(1, reportedplayername);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString("reason");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
