package eu.claymc.lobbysystem.items.shop.sql;

import eu.claymc.api.ClayAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopSQL {

    public boolean exists(Player player) {
        try {
            PreparedStatement State = ClayAPI.getInstance().getSqlAdapter().getConnection().prepareStatement("SELECT * FROM claypass WHERE UUID='" + player.getUniqueId() + "';");
            ResultSet Result = State.executeQuery();
            boolean Contains = Result.next();
            State.close();
            Result.close();
            return Contains;
        } catch (SQLException exception) {
            Bukkit.getConsoleSender().sendMessage("" + exception.getErrorCode());
            Bukkit.getConsoleSender().sendMessage("" + exception.getMessage());
            return true;
        }
    }

    public void createPlayer(Player player) {
        if (!exists(player)) {
            try {
                PreparedStatement State = ClayAPI.getInstance().getSqlAdapter().getConnection().prepareStatement("INSERT INTO claypass VALUES ('" + player.getUniqueId() + "', 0);");
                State.execute();
                State.close();
            } catch (SQLException exception) {
                Bukkit.getConsoleSender().sendMessage("" + exception.getErrorCode());
                Bukkit.getConsoleSender().sendMessage("" + exception.getMessage());
            }
        }
    }

    public Integer getClayShop(String uuid) {
        ResultSet resultSet = ClayAPI.getInstance().getSqlAdapter().getSqlData("SELECT BUYED FROM claypass WHERE UUID ='" + uuid + "'");
        Integer o = 0;
        try {
            while (resultSet.next()) {
                o = resultSet.getInt("BUYED");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }

    public void addClayPass(String uuid) {
        ClayAPI.getInstance().getSqlAdapter().update("UPDATE claypass SET BUYED ='1' WHERE UUID='" + uuid + "'");
    }

}
