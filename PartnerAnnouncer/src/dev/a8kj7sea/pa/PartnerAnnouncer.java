package dev.a8kj7sea.pa;

import dev.a8kj7sea.pa.command.AnnounceCommand;
import dev.a8kj7sea.pa.command.HelpCommand;
import dev.a8kj7sea.pa.command.SetChannelCommand;
// import dev.a8kj7sea.pa.events.GuildJoin;
// import dev.a8kj7sea.pa.events.GuildQuit;
import dev.a8kj7sea.pa.file.StorageFile;
import dev.a8kj7sea.pa.object.BotObject;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class PartnerAnnouncer {

    public static BotObject getBot() {
        return bot;
    }

    public static StorageFile getFile() {
        return file;
    }

    private static BotObject bot;
    private static StorageFile file;

    public static void main(String[] args) {
        bot = new BotObject(args[0]);

        try {
            if (args[0] != null) {

                bot.enableIntents(GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS);
                bot.registerListeners(/* new GuildJoin(), new GuildQuit(), */ new SetChannelCommand(),
                        new AnnounceCommand(),
                        new HelpCommand());
                bot.build();

                file = new StorageFile("storage.txt");
            } else {
                System.err.println(
                        "Token cannot be empty or null , please reopen jar file by following step java -jar file.jar <discord-token>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
