package dev.a8kj7sea.pa.command;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.awt.Color;

public class HelpCommand extends ListenerAdapter {

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

        if (args.length == 1 && args[0].equalsIgnoreCase("pa!help")
                && event.getMessage().getContentRaw().startsWith("pa!help")) {

            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("pa!Help")
                    .setDescription("List of available commands:")
                    .setColor(new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255),
                            new Random().nextInt(100) + 155))
                    .addField("pa!setchannel <channel-id>", "set the partner announcement channel", false)
                    .addField("pa!help", "Display this help message", false);

            event.getChannel().sendMessage(embed.build())
                    .mentionRepliedUser(true)
                    .reference(event.getMessage())
                    .queue(message -> {
                        message.addReaction("\u2705").queue();
                        message.delete().queueAfter(25, TimeUnit.SECONDS);
                    });
        } else {
            if (args[0].equalsIgnoreCase("pa!help") && event.getMessage().getContentRaw().startsWith("pa!help")) {
                event.getChannel().sendMessage("Incorrect use please try to type `pa!help`")
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
