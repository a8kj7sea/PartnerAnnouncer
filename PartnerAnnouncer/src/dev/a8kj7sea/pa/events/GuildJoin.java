package dev.a8kj7sea.pa.events;

import java.util.HashMap;

import dev.a8kj7sea.pa.PartnerAnnouncer;
import dev.a8kj7sea.pa.file.utils.FileUtils;
import dev.a8kj7sea.pa.utils.GeneralUtils;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildJoin extends ListenerAdapter {

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        HashMap<String, String> guilds = FileUtils.loadHashMapFromFile(PartnerAnnouncer.getFile().getStorageFile());
     
            GeneralUtils.sendDirectMessage(
                    "Hello %user_mention% please complete setup steps to **%bot_name%** partner\n- To complete setup steps:\n - pa!setchannel <channel-id>\nHave Good day i hope this will be useful !",
                    event.getGuild().getOwner().getUser());

            guilds.put(event.getGuild().getId(),
                    event.getGuild().getDefaultChannel().getId());

            FileUtils.storeHashMapToFile(event.getGuild().getId(),
                    event.getGuild().getDefaultChannel().getId(), PartnerAnnouncer.getFile());
        }
    
}
