package ar.agenda;

public class Application extends ApplicationStarter {

    public static void main(String[] args) {
        ApplicationStarter.disableWarnings();
        ApplicationStarter starter = new ApplicationStarter();
        starter.showActiveProfile()
                .seedDatabase()
                .enableSecurity()
                .setLookAndFeel()
                .init();
    }
}
