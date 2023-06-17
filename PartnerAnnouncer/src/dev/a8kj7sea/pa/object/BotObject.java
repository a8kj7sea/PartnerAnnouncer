package dev.a8kj7sea.pa.object;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class BotObject {

    public JDABuilder getJdaBuilder() {
        return jdaBuilder;
    }

    public JDA getJDA() {
        return this.jda;
    }

    private JDABuilder jdaBuilder;
    private JDA jda;

    public BotObject(String botToken) {
        try {
            this.jdaBuilder = JDABuilder.createDefault(botToken);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void build() {
        try {
            this.jda = this.jdaBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }

    public void enableIntents(GatewayIntent... gatewayIntents) {
        for (GatewayIntent gatewayIntent : gatewayIntents) {
            this.jdaBuilder.enableIntents(gatewayIntent);
        }
    }

    public void registerListeners(ListenerAdapter... listeners) {
        for (ListenerAdapter listener : listeners) {
            this.jdaBuilder.addEventListeners(listener);
        }
    }

}