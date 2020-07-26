package io.github._7isenko.scenariomix.scenarios.snowballs;

import io.github._7isenko.scenariomix.scenarios.Scenario;
import org.bukkit.Material;

public class Snowballs extends Scenario {

    public Snowballs() {
        super("Снежки", new String[]{"Теперь только снежки", "могут наносить урон"}, Material.SNOW_BALL);
    }

    @Override
    public void start() {
        // TODO: засчитывать фраги, убрать другие источники урона, дать всем 2 блока снега и тыкву
    }

    @Override
    public void stop() {

    }
}
