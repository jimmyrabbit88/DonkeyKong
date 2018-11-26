import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.scripts.JO;

import javax.imageio.IIOException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class IntroScreen extends JFrame implements ActionListener, Serializable{
    private static JMenu fileMenu;
    private static JMenu helpMenu;
    public static String username = "";
    private static ArrayList<User> userArrayList = new ArrayList<>();
    private ImageIcon image;
    private static int fullWidth;
    private static int fullHeight;

    public static void main(String[] args) {
        loadHighScores();
        IntroScreen is = new IntroScreen();
        is.setVisible(true);

    }

    public IntroScreen() {
        Container container;
        //setBounds(0,0,1010,550);
        setTitle("Donkey Kong by Jason Dowling");
        setSize(1010, 550);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(Color.gray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        image = new ImageIcon("images\\homescreen.png");


        JLabel jLabel = new JLabel(image);
        jLabel.setBackground(Color.black);
        jLabel.setBounds(0,0, 1000, 500);



        container = getContentPane();
        container.setLayout(null);
        container.add(jLabel);




        createFileMenu();
        createHelpMenu();
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setBackground(new Color(0x009A9A));
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);


        JButton highScores = new JButton(new ImageIcon("images\\high.png"));
        highScores.setLocation(400, 200);
        highScores.setSize(250, 200);
        highScores.setBackground(new Color(0x465485));
        highScores.setBorder(null);
        jLabel.add(highScores);

        //image = new ImageIcon("\\images\\play.png");
        JButton newGame = new JButton(new ImageIcon("images\\start.png"));
        newGame.setLocation(375, 100);
        newGame.setBackground(new Color(0x465485));
        newGame.setBorder(null);
        newGame.setSize(300, 100);
        jLabel.add(newGame);

        // NEW GAME BUTTON
        newGame.addActionListener((ActionEvent e) -> {
                    if (!username.equals("")) {
                        this.setVisible(false);
                        Dk.main(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "you must login first");
                        login();
                    }
                }
        );
        //HIGH SCORES BUTTON
        highScores.addActionListener((ActionEvent e) -> {
            highScores h = new highScores();
        });


        repaint();



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

        helpMenu = new JMenu("Help");

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

    public static void addHighScore(User usr) throws IOException{
        userArrayList.add(usr);
        File file = new File("Scores.dat");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(userArrayList);
        oos.close();

    }

    public static void loadHighScores() {
        try {
            File file = new File("Scores.dat");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            userArrayList = (ArrayList<User>) ois.readObject();
            ois.close();
        }
        catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "FileNotFound: didn't work");
            e.printStackTrace();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "IOException: didn't work");
            e.printStackTrace();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "open didn't work");
            e.printStackTrace();
        }
    }

    public static ArrayList<User> sortedHighScores(){
        int highest = 0;
        int index = 0;
        User temp;

        for(int i=0;i<userArrayList.size()-1;i++){
            for(int j=i; j<userArrayList.size(); j++){
                if(userArrayList.get(j).getScore() > highest){
                    highest = userArrayList.get(j).getScore();
                    index = j;
                }
            }
            temp = userArrayList.get(i);
            userArrayList.set(i, userArrayList.get(index));
            userArrayList.set(index, temp);
            highest = 0;
            index = 0;
        }
        return userArrayList;
    }
}
