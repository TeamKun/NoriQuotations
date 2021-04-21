package lab.kunmc.net.noriquotations;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Random;

public final class NoriQuotations extends JavaPlugin implements Listener{

    public boolean  norinori = false;
    public Random rand = new Random();

    public Random getRandom(){
        return rand;
    }

    @Override
    public void onEnable() {
        this.getCommand("nori").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this,this);
        saveDefaultConfig();
        saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender,Command cmd,String Label,String[] args){
        if(cmd.getName().equals("nori")&&(sender.isOp()||sender.getName().equals("Yanaaaaa"))){
            if(norinori){
                sender.sendMessage(ChatColor.GREEN+"のりくん名言プラグインをオフにしました！");
                norinori = false;
            }else{
                sender.sendMessage(ChatColor.GREEN+"のりくん名言プラグインをオンにしました！");
                norinori = true;
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.sendTitle(ChatColor.GREEN+"のり君の迷言プラグイン","企画・制作 : KUNLab(Yanaaaaa)",10,100,10);
                });
            }

        }
        return false;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(norinori){
          if(!(e.getEntity() instanceof Player)){return;}
          if(!(e.getDamager() instanceof Player)){return;}
          Player p = (Player) e.getEntity();
          if(p.getName().equals("nori7232")){
              List<String> sl = getConfig().getStringList("List");
              int num = rand.nextInt(sl.size()-1);
              Bukkit.getOnlinePlayers().forEach(player -> {
                  player.sendMessage(ChatColor.AQUA + "<のり> " + sl.get(num));
              });
          }
        }
    }


}
