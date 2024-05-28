import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //-a
        //0.01
        //-train
        //perceptron.data
        //-test
        //perceptron.test.data
        //-epochs
        //100
        //-flower
        //Iris-virginica

        double a = 0;
        String training = "";
        String testpath = "";
        double epochs = 0;
        String flower = "";
        
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-a")) {
                a = Double.parseDouble(args[++i]);
            } else if (args[i].equals("-train")) {
                training = args[++i];
            } else if (args[i].equals("-test")) {
                testpath = args[++i];
            } else if (args[i].equals("-epochs")) {
                epochs = Double.parseDouble(args[++i]);
            } else if (args[i].equals("-flower")) {
                flower = args[++i];
            }
        }

        
        ArrayList<Row> obserwacje = new ArrayList<>();
        ArrayList<Row> czydobrze = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(testpath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Row newRow = new Row(row);
                obserwacje.add(newRow);
            }
        } catch (IOException e) {
            System.out.println("error");
        }
        try (BufferedReader br2 = new BufferedReader(new FileReader(training))) {
            String line;
            while ((line = br2.readLine()) != null) {
                String[] row = line.split(",");
                Row newRow = new Row(row);
                czydobrze.add(newRow);
            }
        } catch (IOException e) {
            System.out.println("error");
        }
        
        Perceptron perceptron=new Perceptron(obserwacje.get(0).wartosci.length,a,flower);
        for (int i = 0; i < epochs; i++) {
            for (int j = 0; j < obserwacje.size(); j++) {
                perceptron.nauka(obserwacje.get(j));
            }
        }
        // nauczony perceptron = szczesliwy perceptron
        double pomylki=0d;
        int ile=czydobrze.size();
        for (int i = 0; i < czydobrze.size(); i++) {
            if (perceptron.nazwa.equals(czydobrze.get(i).atrybutDecyzyjny)){ //ma wskazać 1
                if (perceptron.zklasyfikuj(czydobrze.get(i))!=1) pomylki+=1;

            }else {
                if (perceptron.zklasyfikuj(czydobrze.get(i))==1){
                    pomylki+=1;
                }
            }
        }
        double celnosc =(1-pomylki/ile)*100;
        System.out.printf("Celnosc dla %s wynosiła %.2f%% ",flower,celnosc);
        System.out.println();
        boolean run = true;
        while(run){
            System.out.println("Wprowadź wektory do sprawdzenia");
            Scanner scanner = new Scanner(System.in);
            String wektory = scanner.nextLine();
            ArrayList<Row> userinput = new ArrayList<>();
            String[] row = wektory.split(",");
            Row newRow = new Row(row);
            userinput.add(newRow);
            int decyzja=perceptron.zklasyfikuj(newRow);
            if(decyzja==1){
                System.out.println("zakwalifikowano jako "+flower);
            }else{
                if(flower.equals("Iris-versicolor")) {
                    System.out.println("zakwalifikowano jako "+"Iris-virginica");
                } else if (flower.equals("Iris-virginica")) {
                    System.out.println("zakwalifikowano jako "+ "Iris-versicolor");
                }
            }

            run=false;

        }



    }
}
