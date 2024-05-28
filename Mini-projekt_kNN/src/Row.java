import java.util.Arrays;

public class Row {
    double[] wartosci;
    String atrybutDecyzyjny;

    public Row(String[] row) {
        double[] values = new double[row.length-1];
        for (int i = 0; i < row.length-1; i++) {
            values[i]=Double.parseDouble(row[i]);
        }
        this.wartosci = values;
        this.atrybutDecyzyjny =row[row.length-1];
    }

    public Row(String numbers){
        String[] row = numbers.split("\\s+");
        double[] values = new double[row.length];
        for (int i = 0; i < row.length-1; i++) {
            values[i]=Double.parseDouble(row[i]);
        }
        this.wartosci = values;
    }
    public static Double calcDistance(Row r1,Row r2){
        if (r1.wartosci.length!=r2.wartosci.length) return null;
        double sum = 0d;
        for (int i = 0; i < r1.wartosci.length; i++) {
            sum+=Math.pow(r1.wartosci[i]-r2.wartosci[i],2);
        }
        return Math.sqrt(sum);
    }

    @Override
    public String toString() {
        return  Arrays.toString(wartosci)
                 + atrybutDecyzyjny ;
    }

    public String getAtrybutDecyzyjny() {
        return atrybutDecyzyjny;
    }
}
