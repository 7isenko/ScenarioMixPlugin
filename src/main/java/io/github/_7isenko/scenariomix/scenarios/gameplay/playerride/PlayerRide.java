package io.github._7isenko.scenariomix.scenarios.gameplay.playerride;

import io.github._7isenko.scenariomix.scenarios.Scenario;
import io.github._7isenko.scenariomix.scenarios.config.Configuration;
import org.bukkit.Material;

public class PlayerRide extends Scenario {
    private final Configuration<Boolean> allowMany = new Configuration<>("allowMany", false, Material.LEATHER_BOOTS, this, "Разрешает игрокам сидеть", "большой кучей");
    private final Configuration<Boolean> allowLeave = new Configuration<>("allowLeave", true, Material.IRON_BOOTS, this, "Разрешает слазить");
    public PlayerRide() {
        super("Погнали", "playerride", Material.SADDLE, "Теперь можно ездить на игроках");
        addListener(new PlayerRidingListener(allowMany, allowLeave));
        addConfig(allowMany);
        addConfig(allowLeave);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
