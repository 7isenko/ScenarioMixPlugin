package io.github._7isenko.scenariomix.scenarios.tools.nocrouchwithtag;

import io.github._7isenko.scenariomix.scenarios.Scenario;
import io.github._7isenko.scenariomix.scenarios.tools.nocrouchwithtag.CrouchListener;

/**
 * @author 7isenko
 */
public class NoCrouchWithTag extends Scenario {

    public NoCrouchWithTag() {
        super("Не шифтить с тэгом", "nocrouchwithtag", "FISHING_ROD", "Если игрок присядет, он умрёт", "если у него есть тэг nocrouch");
        addListener(new CrouchListener());
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
