package io.github._7isenko.scenariomix.scenarios.gameplay.pusher;

import io.github._7isenko.scenariomix.scenarios.Scenario;
import org.bukkit.Material;

public class Pusher extends Scenario {

    public Pusher() {
        super("Толкатель", "pusher", Material.PAPER, new String[]{"Каждую секунду толкает всех", "игроков в случайную сторону"});
    }

    @Override
    public void start() {
        addBukkitRunnable(new PusherRunnable(), 20);
        addListener(new AttackListener());
    }

    @Override
    public void stop() {

    }
}
