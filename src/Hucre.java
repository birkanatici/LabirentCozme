import com.sun.deploy.uitoolkit.DragListener;
import org.omg.PortableServer.THREAD_POLICY_ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by birkan on 28.04.2017.
 */
public class Hucre extends JLabel implements MouseListener {
    public static boolean MenuState ;  // eğer menu butonu tıklandıysa true et labirenti etki etmesin
    public Hucre() {
        addMouseListener(this);
        MenuState = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(!MenuState)
            changeBackground();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getModifiers() == MouseEvent.BUTTON1_MASK && !MenuState) {
            changeBackground();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void changeBackground(){
        if(this.getBackground() == Color.BLACK)
            this.setBackground(Color.LIGHT_GRAY);
        else if(this.getBackground() != Color.GREEN && this.getBackground() != Color.RED)
            this.setBackground(Color.BLACK);
    }
}
