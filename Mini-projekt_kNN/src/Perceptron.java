import java.util.Random;

public class Perceptron {
    double[] wagi;
    double t;

    double alpha;

    String nazwa;

    public Perceptron(int rozmiar, double alpha,String nazwa) {
        this.alpha=alpha;
        double[] wagi =new double[rozmiar];
        Random random=new Random();
        for (int i = 0; i < rozmiar; i++) {
            wagi[i]=random.nextDouble()/1_________________________________________________________0-.5;

        }
        this.wagi=wagi;
        t=random.nextDouble()/10-.5;
        this.nazwa=nazwa;
    }
    public int zklasyfikuj(Row row){
        double sum = obliczacz(row.wartosci,this.wagi);
        sum-=t;
        if(sum>=0){
            return 1;
        }else{
            return 0;
        }
    }
    static double obliczacz(double[] tab,double[] tab2){
        double suma=0;
        for (int i = 0; i < tab.length; i++) {
            suma+=tab[i]*tab2[i];
        }
        return suma;
    }
    public void nauka(Row row){
        int werdykt=zklasyfikuj(row);
        if(row.atrybutDecyzyjny.equals(this.nazwa)){
            if (werdykt==0){
                for (int i = 0; i < wagi.length; i++) {
                    wagi[i]+=row.wartosci[i]*alpha;
                    t-=alpha;
                }
            }
        }else{
            if(werdykt==1){
                for (int i = 0; i < wagi.length; i++) {
                    wagi[i]-=row.wartosci[i]*alpha;
                    t+=alpha;
                }
            }
        }
    }
}
