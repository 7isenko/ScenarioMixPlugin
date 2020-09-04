package io.github._7isenko.scenariomix.scenarios.tools.heightlimit;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.github._7isenko.scenariomix.scenarios.Configuration;
import io.github._7isenko.scenariomix.scenarios.Scenario;
import org.bukkit.Material;

public class HeightLimit extends Scenario {
    public HeightLimit() {
        super("Ограничение высоты", "height_limit", Material.BEDROCK, "Устанавливает максимальную", "и минимальную высоту", "установки блоков");
        addConfig(max);
        addConfig(min);
        addConfig(ignoreCreative);
        addConfig(allowBuild);
        addConfig(allowBreak);

    }

    private final Configuration<Integer> max = new Configuration<>("max", 126, Material.GLASS, this, "Максимальная высота");
    private final Configuration<Integer> min = new Configuration<>("min", 0, Material.STONE, this, "Минимальная высота");
    private final Configuration<Boolean> ignoreCreative = new Configuration<>("ignore_creative", true, Material.YELLOW_GLAZED_TERRACOTTA, this, "Игроки в креативе", "обходят ограничения");
    private final Configuration<Boolean> allowBuild = new Configuration<>("allow_build", false, Material.WOOD_BUTTON, this, "Разрешает строить", "на любой высоте");
    private final Configuration<Boolean> allowBreak = new Configuration<>("allow_break", false, Material.IRON_PICKAXE, this, "Разрешает ломать", "на любой высоте");

    @Override
    public void start() {
        addListener(new BlockEventsListener(this));
    }

    @Override
    public void stop() {

    }

    public int getMax() {
        return max.value();
    }

    public int getMin() {
        return min.value();
    }

    public boolean isIgnoreCreative() {
        return ignoreCreative.value();
    }

    public boolean isBuildAllowed() {
        return allowBuild.value();
    }

    public boolean isBreakAllowed() {
        return allowBreak.value();
    }
}
