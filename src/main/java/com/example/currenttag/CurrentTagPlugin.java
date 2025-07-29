package com.example.currenttag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

//import me.clip.placeholderapi.PlaceholderAPI;
//import me.clip.deluxetags.tags.DeluxeTag;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.black_ixx.playerpoints.manager.DataManager;
import org.black_ixx.playerpoints.PlayerPoints;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.lang.Thread;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;


public class CurrentTagPlugin extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getLogger().info("CurrentTag 插件已启用");
        // 注册指令
        this.getCommand("currenttag").setExecutor(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("CurrentTag 插件已卸载");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("currenttag.use")) {
            sender.sendMessage("§c你没有权限使用这个指令。");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("§e用法: /currenttag <玩家名>");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            sender.sendMessage("§c找不到该玩家: " + args[0]);
            return true;
        }

        // 使用 PlaceholderAPI 获取玩家当前称号
        //String tag = DeluxeTag.getPlayerTagIdentifier(target.getUniqueId().toString());

        int points = ((DataManager) PlayerPoints.getInstance().getManager(DataManager.class)).getEffectivePoints(target.getUniqueId());
                        //PlayerPointsAPI.look(target.getUniqueId());

        sender.sendMessage("§a" + target.getName() + " §e当前的points: §b" + points);
        /*
        if (tag == null || tag.isEmpty()) {
            sender.sendMessage("§e玩家 §a" + target.getName() + " §e没有佩戴任何称号。");
        } else {
            sender.sendMessage("§a" + target.getName() + " §e当前的称号是: §b" + tag);
        }
        */
        return true;
    }
}
