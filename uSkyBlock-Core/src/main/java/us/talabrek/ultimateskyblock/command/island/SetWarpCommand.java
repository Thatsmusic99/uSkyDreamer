package us.talabrek.ultimateskyblock.command.island;

import dk.lockfuglsang.minecraft.po.I18nUtil;
import org.bukkit.entity.Player;
import us.talabrek.ultimateskyblock.island.IslandInfo;
import us.talabrek.ultimateskyblock.player.PlayerInfo;
import us.talabrek.ultimateskyblock.uSkyBlock;

import java.util.Map;

import static dk.lockfuglsang.minecraft.po.I18nUtil.marktr;

public class SetWarpCommand extends RequireIslandCommand {
    public SetWarpCommand(uSkyBlock plugin) {
        super(plugin, "setwarp|warpset", "usb.island.setwarp", marktr("set your island''s warp location"));
    }

    @Override
    protected boolean doExecute(String alias, Player player, PlayerInfo pi, IslandInfo island, Map<String, Object> data, String... args) {
        if (!island.hasPerm(player, "canChangeWarp")) {
            player.sendMessage(I18nUtil.tr("§9☀ §8» §7You do not have permission to set your island''s warp point!"));
        } else if (!plugin.playerIsOnOwnIsland(player)) {
            player.sendMessage(I18nUtil.tr("§9☀ §8» §7You need to be on your own island to set the warp!"));
        } else {
            island.setWarpLocation(player.getLocation());
            island.sendMessageToIslandGroup(true, marktr("§9☀ §8» §9{0} §7changed the island warp location."), player.getName());
        }
        return true;
    }
}
