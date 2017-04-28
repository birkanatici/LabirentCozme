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

    public Hucre() {
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        changeBackground();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getModifiers() == MouseEvent.BUTTON1_MASK)
            changeBackground();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void changeBackground(){
        if(this.getBackground() == Color.LIGHT_GRAY)
            this.setBackground(Color.BLACK);
        else if(this.getBackground() == Color.BLACK)
            this.setBackground(Color.LIGHT_GRAY);
    }


}
