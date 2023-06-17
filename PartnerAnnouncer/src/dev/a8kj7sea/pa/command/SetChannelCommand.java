package dev.a8kj7sea.pa.command;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import dev.a8kj7sea.pa.PartnerAnnouncer;
import dev.a8kj7sea.pa.file.utils.FileUtils;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SetChannelCommand extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        if (!!(event.getAuthor().equals(event.getGuild().getOwner()))) {
            event.getChannel().sendMessage("This command is prepared for the server owner to use!")
                    .mentionRepliedUser(true)
                    .reference(event.getMessage())
                    .queue(message -> message.delete().queueAfter(5, TimeUnit.SECONDS));

            return;
        }

        String[] args = event.getMessage().getContentRaw().split(" ");

        if (args.length == 2 && args[0].equalsIgnoreCase("pa!setchannel")
                && event.getMessage().getContentRaw().startsWith("pa!setchannel")) {

            TextChannel channel = event.getChannel();
            HashMap<String, String> guilds = FileUtils.loadHashMapFromFile(PartnerAnnouncer.getFile().getStorageFile());

            guilds.put(event.getGuild().getId(), channel.getId());
            FileUtils.storeHashMapToFile(event.getGuild().getId(), channel.getId(), PartnerAnnouncer.getFile());

            event.getChannel().sendMessage("Channel has been set as the partner announcement channel successfully ✅!")
                    .mentionRepliedUser(true)
                    .reference(event.getMessage())
                    .queue(message -> {
                        message.addReaction("\u2705").queue();
                        message.delete().queueAfter(5, TimeUnit.SECONDS);
                    });

        } else {
            if (args[0].equalsIgnoreCase("pa!setchannel")
                    && event.getMessage().getContentRaw().startsWith("pa!setchannel")) {
                event.getChannel().sendMessage("Incorrect use please try to type `pa!setchannel <channel-id>`")
                        .mentionRepliedUser(true)
                        .reference(event.getMessage())
                        .queue(message -> {
                            message.addReaction("\u274C").queue();
                            message.delete().queueAfter(5, TimeUnit.SECONDS);
                        });

                return;
            }
        }
    }
}
