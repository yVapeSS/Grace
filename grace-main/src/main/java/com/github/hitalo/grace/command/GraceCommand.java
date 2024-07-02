package com.github.hitalo.grace.command;

import com.github.hitalo.grace.GraceConstants;
import com.github.hitalo.grace.GracePlugin;
import com.github.hitalo.grace.runnable.GraceRunnable;
import com.github.hitalo.grace.utils.ColorUtil;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class GraceCommand {

    @Command(
            name = "grace",
            target = CommandTarget.PLAYER
    )
    public void graceCommand(Context<Player> context) {

        Player player = context.getSender();

        if (!GraceConstants.getStatus()) {
            player.sendMessage(ColorUtil.colored(GracePlugin.getInstance().getConfig().getString("messages.not-in-grace")));
            return;
        }

        player.sendMessage(ColorUtil.colored(GracePlugin.getInstance().getConfig().getString("messages.in-grace")));
    }

    @Command(
            name = "grace.set",
            usage = "grace set <on/off>",
            permission = "grace.admin",
            target = CommandTarget.ALL
    )
    public void graceSetCommand(Context<CommandSender> context, String action) {

        switch (action) {

            case "on": {

                if (GraceConstants.getStatus()) {
                    context.sendMessage("§cO grace já está ativo.");
                    return;
                }

                GraceConstants.setStatus(true);

                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {

                    for (String message : GracePlugin.getInstance().getConfig().getStringList("messages.enabled-grace"))
                        onlinePlayers.sendMessage(ColorUtil.colored(message));
                }

                BukkitTask bukkitTask = Bukkit.getScheduler().runTaskLaterAsynchronously(
                        GracePlugin.getInstance(),
                        new GraceRunnable(),
                        GracePlugin.getInstance().getConfig().getLong("settings.timeToEnd")
                );

                GraceConstants.setTaskId(bukkitTask.getTaskId());

                break;
            }

            case "off": {

                if (!GraceConstants.getStatus()) {
                    context.sendMessage("§cO grace já está desativado.");
                    return;
                }

                GraceConstants.setStatus(false);

                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {

                    for (String message : GracePlugin.getInstance().getConfig().getStringList("messages.disabled-grace"))
                        onlinePlayers.sendMessage(ColorUtil.colored(message));
                }

                if (GraceConstants.getTaskId() != null) {
                    Bukkit.getScheduler().cancelTask(GraceConstants.getTaskId());
                }

                break;
            }

            default: {
                context.sendMessage("§cComando incorreto! use: /grace <on/off>.");
                return;
            }
        }
    }
}