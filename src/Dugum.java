import java.awt.*;
import java.util.ArrayList;

/**
 * Created by birkan on 30.04.2017.
 */
public class Dugum {

    private int x;  // sutun
    private int y;  // satir
    private double hCost;  // sezgisel maliyet
    private double gCost;  // gerçek maliyet
    private double fCost;  // toplam maliyet
    private Dugum parentDugum;  // bi önceki düğüm

    private final double alfa = 1;
    private final double beta = 1;

    public Dugum(int x, int y) {
        this.x = x;
        this.y = y;
        this.fCost = -1;
        this.hCost = -1;
        this.gCost = -1;
    }

    public Dugum getParentDugum() {
        return parentDugum;
    }

    public void setParentDugum(Dugum parentDugum) {
        this.parentDugum = parentDugum;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double gethCost() {
        return hCost;
    }

    public void sethCost(double hCost) {
        this.hCost = hCost;
    }

    public double getgCost() {
        return gCost;
    }

    public void setgCost(double gCost) {
        this.gCost = gCost;
    }

    public double getfCost() {
        return alfa*gCost+beta*hCost;
    }

    public void setfCost(double fCost) {
        this.fCost = fCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dugum dugum = (Dugum) o;

        if (x != dugum.x) return false;
        return y == dugum.y;
    }

}
