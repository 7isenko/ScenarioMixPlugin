package io.github._7isenko.scenariomix.scenarios.tools.fightme;

import io.github._7isenko.scenariomix.scenarios.Scenario;
import org.bukkit.Material;

public class FightMe extends Scenario {
    public FightMe() {
        super("Все на меня!", "fight_me", Material.ARMOR_STAND, new String[]{"Все игроки могут бить только тех,", "у кого есть тег fight_me", "/scoreboard players tag <nick> add fight_me"});
    }

    public void start() {
        this.addListener(new AttackListener());
    }

    public void stop() {
    }
}
