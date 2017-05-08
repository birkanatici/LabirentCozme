import java.awt.*;
import java.util.ArrayList;

/**
 * Created by birkan on 30.04.2017.
 */
public class AStar {

    private OncelikliKuyruk openList;
    private ArrayList<Dugum> closedList;
    private ArrayList<Dugum> path;

    private Dugum start, end, current;
    private int satir, sutun;

    private double stepCost = 1;

    public AStar(Hucre[][] hucreler) {
        openList = new OncelikliKuyruk();
        closedList =new ArrayList<>();
        path = new ArrayList<>();
        satir = hucreler.length;
        sutun = hucreler[0].length;
        closedList.add(start);
        init(hucreler);
    }

    private void init(Hucre[][] hucre){                      // GUI dan gelen bilgileri setliyoruz
        for(int i=0; i<hucre.length; i++)
            for(int j= 0; j< hucre[i].length; j++){
                Hucre h = hucre[i][j];

                if(h.getBackground() == Color.BLACK){        // siyah ise duvardır closedListe ekliyoruz
                    closedList.add(new Dugum(i,j));
                }else if(h.getBackground() == Color.RED){    // kırmızı ise başlangıç düğümüdür.
                    start = new Dugum(i,j);
                    start.setgCost(0);
                    current = start;
                }else if(h.getBackground() == Color.GREEN) {  // yeşil ise hedef düğümdür.
                    end = new Dugum(i,j);
                }
            }
    }

    public ArrayList<Dugum> komsuEkle(){
        int x = current.getX();
        int y = current.getY();
        double gCost = current.getgCost();

        ArrayList<Dugum> komsular = new ArrayList<>();

        Point komsuKoord[] = { new Point(-1,-1), new Point(-1,0), new Point(-1,1),
                          new Point(0,-1),   /* current düğüm*/   new Point(0,1),
                          new Point(1,-1),  new Point(1,0),  new Point(1,1)
        };

        for (int say = 0; say<komsuKoord.length ; say++){

            int kordX =(int) komsuKoord[say].getX();
            int kordY =(int) komsuKoord[say].getY();

            if(x+kordX >= 0 && x+kordX < sutun && y+kordY >=0 && y+kordY <satir){
                Dugum d = new Dugum(x+kordX, y+kordY);
                if(!closedList.contains(d) && !openList.getKuyruk().contains(d)){
                    d.setParentDugum(current);                // eklenecek dugumun parent ını şuanki düğüm olarak ekliyoruz.
                    d.setgCost(gCost + stepCost);             // gerçek maliyeti setliyoruz
                    d.sethCost(distance(d, end));             // sezgisel maliyet
                    d.setfCost(d.getgCost() + d.gethCost());  // toplam maliyet
                    openList.dugumEkle(d);
                    komsular.add(d);
                }
            }
        }
        return komsular;
    }

    public Dugum ilerle(){
        Dugum dugum = openList.dugumGetir();          // en iyi kazançlı düğümü 'poll' ile getirdik, openlist listesinden silindi.
        closedList.add(dugum);                        // openlistten sildiğimiz düğümü closed liste ekliyoruz.
        path.add(dugum);                              // geçtiğimiz düğümleri path listesine ekliyoruz.
        current = dugum;                              // şimdiki düğümü güncelliyoruz.
        return dugum;
    }

    private double distance(Dugum a, Dugum b){        // sezgisel maliyet hesaplamak için son düğüme uzaklığını alıyoruz.
      //  Manhattan distance
        double uzaklik = 0;
        uzaklik = Math.abs(a.getX() - b.getY())+ Math.abs(a.getY() - b.getY());
        return uzaklik;


    }
}
