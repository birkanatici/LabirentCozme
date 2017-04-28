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

        GridLayout layout = new GridLayout(satir, sutun, 1, 1);

        ekran = new JFrame("Labirent Çözücü");
        ekran.setBounds(500,50,900, 900);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ekran.setResizable(false);
        ekran.getContentPane().setLayout(layout);

        initHucre();
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

                this.ekran.getContentPane().add(hucre[i][j]);
        }
    }


}
