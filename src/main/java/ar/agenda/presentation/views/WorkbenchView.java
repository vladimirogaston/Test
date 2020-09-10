package ar.agenda.presentation.views;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Singleton
public class WorkbenchView extends JFrame {
    public static final String DISPLAY_LOGIN = "DISPLAY_LOGIN";
    public static final String DISPLAY_USERS = "DISPLAY_USERS";
    public static final String DISPLAY_CONTACT = "DISPLAY_CONTACT";
    public static final String SEED_DB = "SEED_DB";
    public static final String DELETE_DB = "DELETE_DB";
    public static final String LOGOUT = "LOGOUT";

    JMenu mnMenu;
    JMenuItem mntmLogin;
    JMenuItem mntmUsers;
    JMenuItem mntmSeedDB;
    JMenuItem mntmDeleteDB;
    JMenuItem mntmLogout;
    JMenuItem mntmContact;

    @Inject
    private WorkbenchView() {
        setUp();
        createRootPanel();
        createMenu();
    }

    void setUp() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 400, 450, 450);
        setResizable(true);
    }

    void createRootPanel() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        JDesktopPane desktopPane = new JDesktopPane();
        contentPane.add(desktopPane, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
    }

    void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        mnMenu = new JMenu("Menu");
        menuBar.add(mnMenu);
        makeAdminMenu();
        mntmLogin = new JMenuItem("Login");
        mntmLogin.setActionCommand(DISPLAY_LOGIN);
        mnMenu.add(mntmLogin);
        mntmLogout = new JMenuItem("Logout");
        mntmLogout.setActionCommand(LOGOUT);
        mnMenu.add(mntmLogout);
    }

    void makeAdminMenu() {
        JMenu mnAdminMenu = new JMenu("Admin");
        mnMenu.add(mnAdminMenu);
        mntmSeedDB = new JMenuItem("SeedDB");
        mntmSeedDB.setActionCommand(SEED_DB);
        mntmDeleteDB = new JMenuItem("DeleteDB");
        mntmDeleteDB.setActionCommand(DELETE_DB);
        mnAdminMenu.add(mntmSeedDB);
        mnAdminMenu.add(mntmDeleteDB);
        mntmUsers = new JMenuItem("Users");
        mntmUsers.setActionCommand(DISPLAY_USERS);
        mnAdminMenu.add(mntmUsers);
        mntmContact = new JMenuItem("Contact");
        mntmContact.setActionCommand(DISPLAY_CONTACT);
        mnAdminMenu.add(mntmContact);
    }

    public void display() {
        setVisible(true);
    }

    public void displayError(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public boolean displayConfirmationDialog() {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to SEED DB?","Warning",dialogButton);
        return dialogResult == JOptionPane.YES_OPTION;
    }
}