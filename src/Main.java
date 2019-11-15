
import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
public class Main {
    static Proccessor proc = new Proccessor();
    static Menu m = new Menu();
    public static void main(String[] args) {
        /*
        JFrame frame = new JFrame("Processor");
        frame.setSize(600,400);
        frame.setVisible(true);
        */
        System.out.println("Hello!");


        Scanner in = new Scanner(System.in);
        String input = "stop";

        do
        {

            m.showMenu();
            input = in.nextLine();
            switch (Integer.parseInt(input)){
                case 1: m.showMemory(proc.getMemory());
                case 2: m.pasaran();
                case 3: break;
                case 4: break;
                case 5: break;
            }
            if (input.equalsIgnoreCase("1")){

            } else {


            }
            /* check state
            if (input.equalsIgnoreCase("")){

            } else {}
            */
        }
        while (!input.equalsIgnoreCase("stop"));

    }




}


class Menu{

    public void showMenu() {
        System.out.println("Choose:\n 1(show memory),\n 2(insert program),\n 3(start)," +
                "\n 4 (test),\n 5(show commands),\n or \"stop\" to exit");
    }

    public void showMemory(int[] x){
        System.out.println(Arrays.toString(x));
    }

    public void pasaran (){

        System.out.println("Write your program: \n");
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        ArrayList<String> ar = new ArrayList<String>();
        //split String to array for 2 chars in String
        for (int i =0; i<text.length()/2;i++) {
            ar.add(text.substring(i*2,i*2+2));
        }
        //convert to int array
        int [] x = new int[text.length()/2];
        for(int i = 0; i < ar.size(); i++ ){
            x[i] = Integer.parseInt(ar.get(i),16);

        }
        //out HEX
        for (int i:x) {System.out.println(Integer.toHexString(i));}
        System.out.println("\n");
    }

}

class Proccessor{

    int[] memory = new int[100];
    int pc = 0;
    int ac = 0;
    int reg_H = 0;
    int reg_L = 0;
    int reg_P = 0;


    public void setPc(int pc){
        this.pc = pc;
    }

    public void clearMemory(){
        int[] memory = new int[100];
    }

    public int getPc(){
        return pc;
    }

    public int[] getMemory(){
        return memory;
    }

    public void start(){
        for (; pc<memory.length;pc++) {
            task(memory[pc]);
        }
    }

    public void task(int x){
        switch (x)
        {
            case 1: begin();//01
            case 31:incMem();//1F
            case 22:decMem();//16
            case 51:jmpZ();//33

            default:break;
        }
    }


    public void begin(){}


    public void incAc(){ac++;}
    public void incMem(){memory[pc]++;}
    public void incH(){reg_H++;}
    public void incL(){reg_L++;}



    public void decAc (){ac--;}
    public void decMem (){memory[pc]--;}
    public void decH (){reg_H--;}
    public void decL (){reg_L--;}


    public void addAc (){ac+=memory[pc];}
    public void subAc (){ac-=memory[pc];}

    public void movAc (){memory[memory[pc++]]=ac;}

    public void jmpZ () {if (memory[pc-1]==0) pc=memory[pc+1]; else pc++;}
}

/*a
Tasks t = new Tasks();
class Tasks{

    public void task(int x){
        switch (x)
        {
            case 103: start();
            default:break;
        }
    }

    public void start(){}

    public void decmem (){}
}
*/