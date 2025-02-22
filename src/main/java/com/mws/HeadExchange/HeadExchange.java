package com.mws.HeadExchange;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class HeadExchange extends JavaPlugin {
  
    public void onEnable()
  {
    getLogger().info("已加载HeadExchange插件");
  }
  
    public void onDisable() {}
  
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        int amount;

        if (cmd.getName().equalsIgnoreCase("he")) { // 如果玩家输入了/he则执行如下内容...
        // 所需要执行的事（此处略）
    	    Player senp = (Player) sender;
            ItemStack itemInHead = senp.getInventory().getItemInMainHand(); // 获取玩家主手物品
    	    getLogger().info(itemInHead.getType().toString()); // 将该物品类型返回控制台日志
            amount = itemInHead.getAmount(); // 获取玩家主手物品数量

    	    if (args.length==0)
    	    {
    		    return true; // 若无参数返回true
    	    }

    	    if(isValidHead(itemInHead)) {
    		    senp.getInventory().clear(senp.getInventory().getHeldItemSlot()); // 清除玩家主手物品
                String command = "minecraft:give " + senp.getName() + " minecraft:player_head[minecraft:profile=" + args[0] + "] " + amount;
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
    	    }
            return true;
        } //如果以上内容成功执行，则返回true
        // 如果执行失败，则返回false.
        return false;
    }

    public boolean isValidHead(ItemStack item) {
        Material type = item.getType();
        return type == Material.SKELETON_SKULL || // 骷髅头颅
                type == Material.WITHER_SKELETON_SKULL || // 凋零骷髅头颅
                type == Material.PLAYER_HEAD || // 玩家的头
                type == Material.ZOMBIE_HEAD || // 僵尸的头
                type == Material.CREEPER_HEAD || // 苦力怕的头
                type == Material.PIGLIN_HEAD || // 猪灵的头
                type == Material.DRAGON_HEAD; // 龙首
    }
}
