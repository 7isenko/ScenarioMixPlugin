package io.github._7isenko.scenariomix.gui;

public class MenuHandler {
    private static MenuHandler instance;

    private Menu mainMenu;
    private Menu scenarioMenu;
    private Menu toolMenu;

    private MenuHandler() {
        this.mainMenu = new MainMenu();
        this.scenarioMenu = new ScenarioMenu();
        this.toolMenu = new ToolScenarioMenu();
    }

    public Menu getMainMenu() {
        return mainMenu;
    }

    public Menu getScenarioMenu() {
        return scenarioMenu;
    }

    public Menu getToolMenu() {
        return toolMenu;
    }

    public static MenuHandler getInstance() {
        if (instance == null)
            instance = new MenuHandler();
        return instance;
    }
}
