package com.github.hitalo.grace;

import com.github.hitalo.grace.listeners.GraceListeners;
import com.github.hitalo.grace.command.GraceCommand;
import lombok.Getter;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class GracePlugin extends JavaPlugin {

    @Getter private static GracePlugin instance;

    @Override
    public void onLoad() {
        instance = this;
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {

        GraceConstants.setStatus(getConfig().getBoolean("settings.defaultStatus"));

        BukkitFrame bukkitFrame = new BukkitFrame(this);
        bukkitFrame.registerCommands(new GraceCommand());

        Bukkit.getPluginManager().registerEvents(new GraceListeners(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
