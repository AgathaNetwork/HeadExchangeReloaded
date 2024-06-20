package com.mws.HeadExchange;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class HeadExchange extends JavaPlugin {
    String prefix;
  
    public void onEnable()
  {
    getLogger().info("已加载HeadExchange插件");
  }
  
    public void onDisable() {}
  
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("he")) { // 如果玩家输入了/basic则执行如下内容...
        // 所需要执行的事（此处略）
    	    Player senp = (Player) sender;
            ItemStack itemInHead = senp.getInventory().getItemInMainHand();
    	    getLogger().info(itemInHead.getType().toString());

    	    if (args.length==0)
    	    {
    		    return true;
    	    }

    	    if(isValidHead(itemInHead)) {
    		    senp.getInventory().clear(senp.getInventory().getHeldItemSlot());
                String command = "give " + senp.getName() + " minecraft:player_head[minecraft:profile=" + args[0] + "] 1";
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
    	    }
            return true;
        } //如果以上内容成功执行，则返回true
        // 如果执行失败，则返回false.
        return false;
    }

    public boolean isValidHead(ItemStack item) {
        Material type = item.getType();
        return type == Material.SKELETON_SKULL ||
                type == Material.WITHER_SKELETON_SKULL ||
                type == Material.PLAYER_HEAD ||
                type == Material.ZOMBIE_HEAD ||
                type == Material.CREEPER_HEAD ||
                type == Material.PIGLIN_HEAD ||
                type == Material.DRAGON_HEAD;
    }
}