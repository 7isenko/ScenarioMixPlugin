package io.github._7isenko.scenariomix.scenarios.tools.nocrouch;

import io.github._7isenko.scenariomix.scenarios.Scenario;

/**
 * @author 7isenko
 */
public class NoCrouch extends Scenario {

    public NoCrouch() {
        super("Не шифтить", "nocrouch", "SPONGE", "Если игрок присядет, он умрёт");
        addListener(new CrouchListener());
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
