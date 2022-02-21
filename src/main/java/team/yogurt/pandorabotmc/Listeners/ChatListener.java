package team.yogurt.pandorabotmc.Listeners;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;
import team.yogurt.pandorabotmc.PandoraBot;
import team.yogurt.pandorabotmc.Utilities;

import java.util.List;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(ChatEvent e){
        String message = e.getMessage();
        if(!e.isCommand() || message.startsWith("/ac ") || message.startsWith("/helpop ")) {
            ProxiedPlayer player = (ProxiedPlayer) e.getSender();
            Configuration servers = PandoraBot.getConfig().getSection("questions");
            if(message.startsWith("/ac ") || message.startsWith("/helpop ")){
                if(player.hasPermission("pbot.bypass")){
                    return;
                }
            }
            if(player.hasPermission("pbot.bypass")){
                return;
            }
            for(String server : servers.getKeys()){
                if(player.getServer().getInfo().getName().equalsIgnoreCase(server) || server.equalsIgnoreCase("GLOBAL")){
                    Configuration questions_name = PandoraBot.getConfig().getSection("questions."+server);
                    for(String question_name : questions_name.getKeys()){
                        List<String> responses = PandoraBot.getConfig().getStringList("questions."+server+"."+question_name+".response");
                        List<String> aliases = PandoraBot.getConfig().getStringList("questions."+server+"."+question_name+".aliases");
                        for(String aliase : aliases){
                            if(message.toLowerCase().contains(aliase)){
                                for(String response : responses){
                                    //Utilities.sendMessage(response);
                                    player.sendMessage(Utilities.colorString(response.replace("%player%", player.getName())));
                                    for(ProxiedPlayer player1 : player.getServer().getInfo().getPlayers()){
                                        if(player1.hasPermission("pbot.alert")){
                                            player1.sendMessage(Utilities.colorString(PandoraBot.getConfig().getString("message")
                                                    .replace("%player%", player.getName())
                                                    .replace("%response%", response.replace("&5&lPandora &8» ", ""))));
                                        }
                                    }
                                    //System.out.println("Test1: " + response);
                                }
                                break;
                            }
                            //System.out.println("Test2: " + aliase);
                        }
                       //System.out.println("Test3: " + question_name);

                    }
                }
                //System.out.println("Test4: "+server);
            }
        }
        }
}
