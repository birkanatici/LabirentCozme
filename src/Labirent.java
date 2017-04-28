import com.sun.jmx.remote.security.JMXPluggableAuthenticator;

import javax.swing.*;
import java.awt.*;

/**
 * Created by birkan on 28.04.2017 : 03:00 PM
 */
public class Labirent extends JFrame {

    private static JFrame ekran;      // ana çerçevemiz
    private static int satir, sutun;  // labirentte kaç satır, sutun olacağı
    private static Hucre hucre[][];  // labirentteki tüm hücreler, hucre[satir][sutun]
    private static Point startHucre , endHucre;
    private static JPanel labPanel;
    private static JPanel menuPanel;
    private static JPanel anaPanel;

    public static void main(String args[]){

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                     Labirent g = new Labirent(25,25, new Point(0,0), new Point(24,24));
                     g.ekran.setVisible(true);
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

        ekran = new JFrame("Labirent Çözücü");
        ekran.setBounds(500,10,850, 900);
        ekran.setResizable(false);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        labPanel = new JPanel();
        menuPanel = new JPanel();
        anaPanel = new JPanel();


        GridLayout labLayout = new GridLayout(satir, sutun, 1, 1);
        GridBagLayout menuLayout = new GridBagLayout();
        GridBagLayout anaLayout = new GridBagLayout();
        GridBagConstraints bgc = new GridBagConstraints();

        anaPanel.setLayout(anaLayout);

        menuPanel.setLayout(menuLayout);
        menuPanel.setBackground(Color.GRAY);
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.ipadx = 60;
        bgc.ipady = 50;
        bgc.insets = new Insets(0,5,0,5);

        bgc.gridx = 0;
        bgc.gridy = 0;

        menuPanel.add(new Button("Çöz"), bgc);
        bgc.gridx = 1;
        bgc.gridy = 0;
        menuPanel.add(new Button("Sıfırla"), bgc);

        bgc.gridx = 2;
        bgc.gridy = 0;
        menuPanel.add(new Button("Boyut Değiştir"), bgc);


        labPanel.setBackground(Color.WHITE);
        labPanel.setLayout(labLayout);



        initHucre();

        bgc.fill = GridBagConstraints.VERTICAL;
        bgc.gridx = 0;
        bgc.gridy = 0;
        bgc.ipady = 850;
        bgc.ipadx = 850;
        anaPanel.add(labPanel, bgc);

        bgc.gridx = 0;
        bgc.gridy = 2;
        bgc.ipady = 25;
        bgc.ipadx = 500;

        anaPanel.add(menuPanel, bgc);

        ekran.add(anaPanel);
        ekran.pack();
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

                this.labPanel.add(hucre[i][j]);
        }
    }


}
