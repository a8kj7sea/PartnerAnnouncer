package dev.a8kj7sea.pa.utils;

import java.util.HashMap;
import java.util.Random;

import dev.a8kj7sea.pa.PartnerAnnouncer;
import dev.a8kj7sea.pa.file.utils.FileUtils;
import dev.a8kj7sea.pa.object.AnnounceObject;
import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class GeneralUtils {

    public static String getBotOwnerId(JDA jda) {
        User botOwner = jda.retrieveApplicationInfo().complete().getOwner();
        return botOwner.getId();
    }

    public static void sendDirectMessage(String message, User user) {
        PrivateChannel privateChannel = user.openPrivateChannel().complete();

        if (privateChannel != null) {
            privateChannel.sendMessage(setPlaceholders(user, message))
                    .queue();
        } else {
            for (Guild guild : PartnerAnnouncer.getBot().getJDA().getGuilds()) {
                if (guild.getOwnerId().equalsIgnoreCase(user.getId())) {
                    guild.getDefaultChannel().sendMessage(message).queue();
                    break;
                }
            }
        }
    }

    public static String setPlaceholders(User user, String text) {
        return text.replace("%user_mention%", user.getAsMention()).replace("%user_name%", user.getName())
                .replace("%bot_name%", PartnerAnnouncer.getBot().getJDA().getSelfUser().getName())
                .replace("%bot_mention%", PartnerAnnouncer.getBot().getJDA().getSelfUser().getAsMention());
    }

    public static String setPlaceholders(AnnounceObject announce) {
        return announce.getMessage().replace("%n", "")
                .replace("\n", "")
                .replace("%nl%", "\n")
                .replace("%d", "");
    }

    public static EmbedBuilder creatEmbedBuilder(AnnounceObject announceObject) {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Patner Announcements !")
                .setColor(new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255),
                        new Random().nextInt(100) + 155))
                .setDescription(setPlaceholders(announceObject));
        return embed;

    }

    public static void sendAnnouncement(String message, JDA jda) {
        HashMap<String, String> guilds = FileUtils.loadHashMapFromFile(PartnerAnnouncer.getFile().getStorageFile());
        for (Guild partnerGuild : jda.getGuilds()) {
            String channelID = guilds.get(partnerGuild.getId());
            if (channelID != null) {
                TextChannel channel = jda.getTextChannelById(channelID);
                if (channel != null) {
                    channel.sendMessage(message).queue(
                            success -> System.out.println("Message sent to channel: " + channel.getId()),
                            error -> System.out.println("Failed to send message to channel: " + channel.getId()));
                } else {
                    System.out.println("Invalid channel ID for guild: " + partnerGuild.getId());
                }
            } else {
                System.out.println("No partner channel ID found for guild: " + partnerGuild.getId());
            }
        }
    }

    public static void sendAnnouncement(EmbedBuilder embed, JDA jda) {
        HashMap<String, String> guilds = FileUtils.loadHashMapFromFile(PartnerAnnouncer.getFile().getStorageFile());
        for (Guild partnerGuild : jda.getGuilds()) {
            String channelID = guilds.get(partnerGuild.getId());
            if (channelID != null) {
                TextChannel channel = jda.getTextChannelById(channelID);
                if (channel != null) {
                    channel.sendMessage(embed.build()).queue(
                            success -> System.out.println("Message sent to channel: " + channel.getId()),
                            error -> System.out.println("Failed to send message to channel: " + channel.getId()));
                } else {
                    System.out.println("Invalid channel ID for guild: " + partnerGuild.getId());
                }
            } else {
                System.out.println("No partner channel ID found for guild: " + partnerGuild.getId());
            }

        }
    }

}
