import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by birkan on 30.04.2017.
 */
public class OncelikliKuyruk extends PriorityQueue {

    private PriorityQueue<Dugum> kuyruk;

    public OncelikliKuyruk() {
        kuyruk = new PriorityQueue<>(new DugumKarsilastir());
    }

    public void dugumEkle(Dugum dugum){
        kuyruk.add(dugum);
    }

    public Dugum dugumGetir(){
        return kuyruk.poll();
    }

    public PriorityQueue<Dugum> getKuyruk() {
        return kuyruk;
    }

    public static class DugumKarsilastir implements Comparator<Dugum> {

        @Override
        public int compare(Dugum o1, Dugum o2) {                // Öncelik belirleme
            if(o1.getfCost() > o2.getfCost()){
                return 1;
            }else if(o1.getfCost() < o2.getfCost()){
                return -1;
            }else{                                      // eğer toplam maliyetleri eşit ise sezgisel maliyetlerine göre sırala.
                if(o1.gethCost() > o2.gethCost())
                    return 1;
                else if(o1.gethCost() > o2.gethCost())
                    return -1;
            }
                return 0;
        }
    }

}
