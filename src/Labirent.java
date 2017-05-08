import com.sun.javafx.scene.layout.region.Margins;
import com.sun.jmx.remote.security.JMXPluggableAuthenticator;
import jdk.internal.util.xml.impl.Input;
import sun.plugin2.message.Message;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by birkan on 28.04.2017 : 03:00 PM
 */
public class Labirent extends JFrame {

    private static JFrame ekran;      // ana çerçevemiz
    private static int satir = 25, sutun=25;  // labirentte kaç satır, sutun olacağı
    private static Hucre hucre[][];  // labirentteki tüm hücreler, hucre[satir][sutun]
    private static Point startHucre = new Point(0,0), endHucre = new Point(satir-1,sutun-1);
    private static JPanel labPanel;
    private static JPanel anaPanel;
    private static JPanel menuPanel;
    private static Font font;
    private static Labirent lab;
    private AStar algo;
    private AStar algo2;

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                     lab = new Labirent(satir,sutun, startHucre, endHucre);
                     lab.ekran.setVisible(true);
                } catch (Exception e) {
                    System.out.println("Ekran Oluşturulamadı.");
                    e.printStackTrace();
                }
            }
        });

    }

    public Labirent(int _satir, int _sutun, Point s, Point e){
        satir = _satir;
        sutun = _sutun;
        hucre = new Hucre[satir][sutun];
        startHucre = new Point((int)s.getX(), (int)s.getY());
        endHucre = new Point((int)e.getX(), (int)e.getY());
        font = new Font("Nasalization Rg", Font.BOLD, 20);

        ekran = new JFrame("Labirent Çözücü");
        ekran.setBounds(500,10,920, 970);
        ekran.setResizable(false);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        labPanel = new JPanel();
        anaPanel = new JPanel();
        menuPanel = new JPanel();

        labPanel.setBackground(Color.DARK_GRAY);
        menuPanel.setBackground(Color.GRAY);

        GridLayout labLayout = new GridLayout(satir, sutun, 1, 1);

        GridBagConstraints gbc = new GridBagConstraints();

        menuPanel.setLayout(new GridBagLayout());
        gbc.insets = new Insets(0,10,0,10);

        labPanel.setLayout(labLayout);
        anaPanel.setLayout(new GridBagLayout());

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 0;

        gbc.gridy = 1;

        anaPanel.add(new JLabel(""), gbc);

        gbc.ipadx = 850;
        gbc.ipady = 850;
        gbc.gridy=2;


        anaPanel.add(labPanel, gbc);

        ekran.add(anaPanel);
        anaPanel.setBackground(Color.DARK_GRAY);
        initHucre();
        initMenuBar();
    }

    public void initHucre(){
        System.out.println("Çiz: satir : "+satir+" Sutün : "+ sutun);

        for(int i=0; i<satir; i++)
            for(int j=0; j<sutun; j++){
                Point p = new Point(i,j);
                hucre[i][j] = new Hucre();
                hucre[i][j].setOpaque(true);

                if(startHucre.equals(p))
                    hucre[i][j].setBackground(Color.RED);  // başlangıc nok. ise kırmızı
                else if(endHucre.equals(p))
                    hucre[i][j].setBackground(Color.GREEN); // bitiş ise yeşil
                else
                    hucre[i][j].setBackground(Color.LIGHT_GRAY);

                labPanel.add(hucre[i][j]);
        }
        algo2 = new AStar(hucre);

    }

    private void initMenuBar() {

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menü");
        JMenuItem cozBtn = new JMenuItem("Çöz");
        JMenuItem sifirlaBtn = new JMenuItem("Sıfırla");
        JMenuItem boyutBtn = new JMenuItem("Boyut Değiştir");

        menu.setFont(font);
        cozBtn.setFont(font);
        sifirlaBtn.setFont(font);
        boyutBtn.setFont(font);


        menu.add(cozBtn);
        menu.add(sifirlaBtn);
        menu.add(boyutBtn);
        menuBar.add(menu);
        ekran.setJMenuBar(menuBar);

        // menü listeners
        cozBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //    Hucre.MenuState = true;
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            cozumle();
                        } catch (Exception e) {
                            System.out.println("çözümleme hatası.");
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        sifirlaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // Hucre.MenuState = true;
                sifirla();
            }
        });

        boyutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Hucre.MenuState = true;
                ekran.dispose();
                boyutlandir();
            }
        });



    }

    private void sifirla(){
        for(int i=0; i<satir; i++)
            for(int j=0; j<sutun; j++){
                Point p = new Point(i,j);
                if(startHucre.equals(p))
                    hucre[i][j].setBackground(Color.RED);  // başlangıc nok. ise kırmızı
                else if(endHucre.equals(p))
                    hucre[i][j].setBackground(Color.GREEN); // bitiş ise yeşil
                else
                    hucre[i][j].setBackground(Color.LIGHT_GRAY);
            }
    }


    private static void boyutlandir() {
        JPanel panel = new JPanel();

        SpinnerModel rows = new SpinnerNumberModel(25, 2, 100, 1);
        JSpinner satirSp = new JSpinner(rows);
        SpinnerModel cols = new SpinnerNumberModel(25, 2, 100, 1);
        JSpinner sutunSp = new JSpinner(cols);
        GridBagConstraints gbs = new GridBagConstraints();

        gbs.gridy = 0;
        gbs.gridx = 0;
        gbs.insets = new Insets(10,10,10,10);

        panel.setLayout(new GridBagLayout());

        panel.add(new JLabel("Satır: "), gbs);
        gbs.gridx = 1;

        panel.add(satirSp, gbs);
        gbs.gridx = 2;
        panel.add(new JLabel("Sütun: "), gbs);
        gbs.gridx = 3;
        panel.add(sutunSp, gbs);
        gbs.gridy = 1;
        gbs.gridx = 0;

        JTextField sPoint = new JTextField();
        sPoint.setText("0,0");
        JTextField ePoint = new JTextField();
        ePoint.setText("24,24");

        gbs.ipadx = 20;
        gbs.ipady = 8;
        panel.add(new JLabel("Başlangıç Noktası: "), gbs);
        gbs.gridx = 1;
        panel.add(sPoint, gbs);
        gbs.gridx = 2;
        panel.add(new JLabel("Bitiş Noktası: "), gbs);
        gbs.gridx = 3;
        panel.add(ePoint, gbs);

        int result = JOptionPane.showConfirmDialog(null, panel, "Satır ve Sütun Giriniz..", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            int inputSatir = (int)satirSp.getValue();
            int inputSutun = (int)sutunSp.getValue();
            satir = inputSatir;
            sutun = inputSutun;
            String sX = sPoint.getText().split(",")[0];
            String sY = sPoint.getText().split(",")[1];
            String eX = ePoint.getText().split(",")[0];
            String eY = ePoint.getText().split(",")[1];


            startHucre.setLocation(Integer.parseInt(sX), Integer.parseInt(sY));
            endHucre.setLocation(Integer.parseInt(eX), Integer.parseInt(eY));

            main(null);
        }
    }

    public void cozumle() {
        algo  = new AStar(hucre);

        Dugum end = new Dugum((int) endHucre.getX(), (int) endHucre.getY());
        Dugum dugum = new Dugum((int)startHucre.getX(), (int)startHucre.getY());
        int count = 0;

        for(int i=0; i<satir; i++)
            for(int j=0; j<sutun; j++){
                if(hucre[i][j].getBackground() == Color.cyan || hucre[i][j].getBackground() == Color.orange){
                    hucre[i][j].setBackground(Color.LIGHT_GRAY);
            }
        }
        ArrayList<Dugum> komsular = new ArrayList<>();

        while (!end.equals(dugum)){
            komsular = algo.komsuEkle();
            dugum = algo.ilerle();
            if(dugum==null){
                JOptionPane.showMessageDialog(new JFrame(), "Çözüm Bulunamadı.", "Hata !!!",
                        JOptionPane.ERROR_MESSAGE);
                break;
            }

            System.out.println("X : " + dugum.getX()+" Y: "+dugum.getY() + " - Cost: gCost :"+dugum.getgCost() + " hCost:" + dugum.gethCost()+ " fCost:" + dugum.getfCost());
            count++;

            for(Dugum d : komsular)
                hucre[d.getX()][d.getY()].setBackground(Color.orange);

          //  hucre[dugum.getX()][dugum.getY()].setBackground(Color.MAGENTA);
        }

        Dugum parent = dugum;

        while (parent.getParentDugum() != null){
          try {
              parent =  parent.getParentDugum();
          }catch (Exception e){
              break;
          }
          hucre[parent.getX()][parent.getY()].setBackground(Color.cyan);
        }

        System.out.println("Adım : " + count);

        hucre[(int) startHucre.getX()][(int)startHucre.getY()].setBackground(Color.red);
        hucre[(int) endHucre.getX()][(int)endHucre.getY()].setBackground(Color.green);

    }

}
