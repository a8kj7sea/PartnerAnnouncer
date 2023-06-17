package dev.a8kj7sea.pa.command;

import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import dev.a8kj7sea.pa.object.AnnounceObject;
import dev.a8kj7sea.pa.utils.GeneralUtils;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class AnnounceCommand extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        if (!!(event.getAuthor().equals(GeneralUtils.getBotOwnerId(event.getJDA())))) {
            event.getChannel().sendMessage("This command is prepared for the bot owner to use!")
                    .mentionRepliedUser(true)
                    .reference(event.getMessage())
                    .queue(message -> message.delete().queueAfter(5, TimeUnit.SECONDS));

            return;
        }

        String[] args = event.getMessage().getContentRaw().split(" ");

        if (args.length == 3 && args[0].equalsIgnoreCase("pa!a")
                && event.getMessage().getContentRaw().startsWith("pa!a")) {

            String announcementType = args[1];
            String announcementMessage = String.join(" ", args).substring(args[0].length() + args[1].length() + 2);

            AnnounceObject announce = new AnnounceObject();

            switch (announcementType) {
                case "-text":
                    announce.setMessageType(AnnounceObject.MessageType.TEXT);
                    announce.setMessage(announcementMessage);
                    GeneralUtils.sendAnnouncement(GeneralUtils.setPlaceholders(announce), event.getJDA());
                    event.getChannel()
                            .sendMessage("You have been sent message to partner announcement channel successfully ✅!")
                            .mentionRepliedUser(true)
                            .reference(event.getMessage())
                            .queue(message -> {
                                message.addReaction("\u2705").queue();
                                message.delete().queueAfter(5, TimeUnit.SECONDS);
                            });
                    break;

                case "-embed":
                    announce.setMessageType(AnnounceObject.MessageType.EMBED);
                    announce.setMessage(announcementMessage);
                    GeneralUtils.sendAnnouncement(GeneralUtils.creatEmbedBuilder(announce), event.getJDA());
                    event.getChannel()
                            .sendMessage("You have been sent message to partner announcement channel successfully ✅!")
                            .mentionRepliedUser(true)
                            .reference(event.getMessage())
                            .queue(message -> {
                                message.addReaction("\u2705").queue();
                                message.delete().queueAfter(5, TimeUnit.SECONDS);
                            });
                    break;

                default:
                    announce.setMessageType(AnnounceObject.MessageType.TEXT);
                    announce.setMessage(announcementMessage);
                    GeneralUtils.sendAnnouncement(GeneralUtils.setPlaceholders(announce), event.getJDA());
                    event.getChannel()
                            .sendMessage("You have been sent message to partner announcement channel successfully ✅!")
                            .mentionRepliedUser(true)
                            .reference(event.getMessage())
                            .queue(message -> {
                                message.addReaction("\u2705").queue();
                                message.delete().queueAfter(5, TimeUnit.SECONDS);
                            });
                    break;
            }
        } else {
            if (args[0].equalsIgnoreCase("pa!a") && event.getMessage().getContentRaw().startsWith("pa!a")) {
                event.getChannel()
                        .sendMessage("Incorrect use please try to type `pa!a <-text/-embed> <announce-message>`")
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
