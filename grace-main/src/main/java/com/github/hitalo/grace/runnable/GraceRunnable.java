package com.github.hitalo.grace.runnable;

import com.github.hitalo.grace.GraceConstants;
import com.github.hitalo.grace.GracePlugin;
import com.github.hitalo.grace.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GraceRunnable implements Runnable {

    @Override
    public void run() {

        GraceConstants.setStatus(false);

        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {

            for (String message : GracePlugin.getInstance().getConfig().getStringList("messages.disabled-grace"))
                onlinePlayers.sendMessage(ColorUtil.colored(message));
        }
    }
}