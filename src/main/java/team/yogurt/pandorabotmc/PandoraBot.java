package team.yogurt.pandorabotmc;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import team.yogurt.pandorabotmc.Listeners.ChatListener;
import team.yogurt.pandorabotmc.Managers.ConfigManager;

public final class PandoraBot extends Plugin {
    private static PandoraBot instance;
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        ConfigManager.createFolder();
        getConfig();
        registerListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static PandoraBot getInstance(){
        return instance;
    }

    public static Configuration getConfig(){
        return ConfigManager.getConfigFile("config.yml");
    }
    private void registerListeners(){
        ProxyServer.getInstance().getPluginManager().registerListener(this, new ChatListener());
    }
}
