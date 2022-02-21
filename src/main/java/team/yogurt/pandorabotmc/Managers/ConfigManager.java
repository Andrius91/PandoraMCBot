package team.yogurt.pandorabotmc.Managers;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import team.yogurt.pandorabotmc.PandoraBot;
import team.yogurt.pandorabotmc.Utilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ConfigManager {


    public static void createFolder(){
        if (!PandoraBot.getInstance().getDataFolder().exists()){
            PandoraBot.getInstance().getDataFolder().mkdir();
        }
    }
    public static Configuration getConfigFile(String filename){

        File file = new File(PandoraBot.getInstance().getDataFolder(), filename);
        Configuration config = null;
        if (!file.exists()){
            try {
                InputStream in = PandoraBot.getInstance().getResourceAsStream(filename);
                Files.copy(in, file.toPath());
                PandoraBot.getInstance().getLogger().info(Utilities.color(filename + " &aregistered!"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class)
                    .load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }
}
