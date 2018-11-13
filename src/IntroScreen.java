import jdk.nashorn.internal.objects.Global;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class IntroScreen extends JFrame implements ActionListener {
    private static JMenu fileMenu;
    private static JMenu helpMenu;
    public static String username = "";
    private static ArrayList<User> userArrayList;

    public static void main(String[] args) {
        loadHighScores();
        IntroScreen is = new IntroScreen();
        is.setVisible(true);
    }

    public IntroScreen() {
        Container container;

        setTitle("Donkey Kong by Jason Dowling");
        setSize(500, 500);
        setLocation(400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        container = getContentPane();
        container.setLayout(null);

        createFileMenu();
        createHelpMenu();
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setBackground(new Color(0x009A9A));
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);


        Button newGame = new Button("Start Game");
        newGame.setLocation(100, 100);
        newGame.setSize(100, 100);
        container.add(newGame);

        Button highScores = new Button("High Scores");
        highScores.setLocation(300, 100);
        highScores.setSize(100, 100);
        container.add(highScores);


        newGame.addActionListener((ActionEvent e) -> {
                    if (!username.equals("")) {
                        this.setVisible(false);
                        Dk.main(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "you must login first");
                    }
                }
        );
        highScores.addActionListener((ActionEvent e) -> {
            for(User u:userArrayList) {
                System.out.println(u.getName() + " :: " + u.getScore());

            }
                }
        );

    }

    public static void createFileMenu() {
        JMenuItem item;

        fileMenu = new JMenu("File");

        item = new JMenuItem("Login");
        item.addActionListener((ActionEvent) -> {
            login();
        });
        fileMenu.add(item);


        item = new JMenuItem("Exit");
        item.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        fileMenu.add(item);

    }

    public static void createHelpMenu() {
        JMenuItem item;

        helpMenu = new JMenu("help");

        item = new JMenuItem("About");
        item.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(null, "Donkey Kong Remake. \nCreated by Jason Dowling.", "About", JOptionPane.INFORMATION_MESSAGE);

        });
        helpMenu.add(item);

        item = new JMenuItem("Controls");
        item.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(null, "Move Player :: Arrow keys.\nJump        :: SpaceBar.\nScore Points for jumping over barrels." +
                    "\nClimb up the top ladder to progress to the next level where each barrel will be worth 2x Points.\nHowever barrels will be created at a higher rate...\n\nEnjoy!!");

        });
        helpMenu.add(item);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void login() {
        username = JOptionPane.showInputDialog("Please enter your username");
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        IntroScreen.username = username;
    }

    public static void addHighScore(User usr) {
        userArrayList.add(usr);

        try {
            File file = new File("Scores.dat");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userArrayList);
            oos.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "FileNotFound: didn't work");
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "IOException: didn't work");
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "open didn't work");
            e.printStackTrace();

        }
    }

    public static void loadHighScores() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Scores.dat"));
            userArrayList = (ArrayList<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "FileNotFound: didn't work");
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "IOException: didn't work");
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "open didn't work");
            e.printStackTrace();
        }
    }
}
