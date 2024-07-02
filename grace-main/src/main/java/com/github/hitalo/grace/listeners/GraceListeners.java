package com.github.hitalo.grace.listeners;

import com.github.hitalo.grace.GraceConstants;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class GraceListeners implements Listener {

    @EventHandler
    void onEntityExplode(EntityExplodeEvent event) {

        if (GraceConstants.getStatus())
            event.setCancelled(true);
    }

    @EventHandler
    void onBlockPlace(BlockPlaceEvent event) {

        Block block = event.getBlock();

        if (block.getType().equals(Material.TNT)
                && GraceConstants.getStatus()) {

            event.setCancelled(true);
        }
    }

    @EventHandler
    void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        if (GraceConstants.getStatus())
            event.setCancelled(true);
    }
}