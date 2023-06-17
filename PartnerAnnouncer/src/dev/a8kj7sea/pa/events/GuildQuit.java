package dev.a8kj7sea.pa.events;

import java.util.HashMap;

import dev.a8kj7sea.pa.PartnerAnnouncer;
import dev.a8kj7sea.pa.file.utils.FileUtils;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildQuit extends ListenerAdapter {

    @Override
    public void onGuildLeave(GuildLeaveEvent event) {
        HashMap<String, String> guilds = FileUtils.loadHashMapFromFile(PartnerAnnouncer.getFile().getStorageFile());
        if (guilds.get(event.getGuild().getId()) == null) {
            return;
        } else {

            guilds.remove(event.getGuild().getId());
            
            for (String key : guilds.keySet()) {
                String value = guilds.get(key);
                FileUtils.storeHashMapToFile(key, value, PartnerAnnouncer.getFile());
            }

        }
    }
}
