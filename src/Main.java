
import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
public class Main {

    static Menu m = new Menu();
    public static void main(String[] args) {

        /*
        JFrame frame = new JFrame("Processor");
        frame.setSize(600,400);
        frame.setVisible(true);
        */
        System.out.println("Hello!");
        Proccessor proc = new Proccessor();

        Scanner in = new Scanner(System.in);
        String input = "stop";

        do
        {
            //program menu
            m.showMenu();
            input = in.nextLine();
            switch (Integer.parseInt(input)){
                case 1: proc.showMemory(proc.getMemory()); //show memory
                    break;
                case 2: proc.setMemory(m.pasaran()); //insert program
                    break;
                case 3: proc.start(); //start
                    break;
                case 4: proc = new Proccessor(); //reset
                    break;
                case 5: proc.showAc();// show ac
                    break;
                default: System.out.println("No such comand: " + input); break;
            }
            if (input.equalsIgnoreCase("1")){

            } else {


            }
        }
        while (!input.equalsIgnoreCase("stop"));

    }
}


class Menu{

    public void showMenu() {
        System.out.println("\nChoose:\n 1(show memory),\n 2(insert program),\n 3(start)," +
                "\n 4 (reset),\n 5(show ac),\n or \"stop\" to exit");
    }


    // parse string to array of memory
    public int[] pasaran (){

        System.out.println("Write your program:");
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        ArrayList<String> ar = new ArrayList<String>();
        //delete spacebars
        text = text.replaceAll("\\s", "");
        //split String to array for 2 chars in String
        for (int i =0; i<text.length()/2;i++) {
            ar.add(text.substring(i*2,i*2+2));
        }
        //convert to int array
        int [] x = new int[text.length()/2];
        for(int i = 0; i < ar.size(); i++ ){
            x[i] = Integer.parseInt(ar.get(i),16);

        }
        return x;
    }

}

class Proccessor{

    int[] memory = new int[100];
    int pc = 0; //program counter
    int ac = 0; // variable for calculation
    int reg_H = 0;
    int reg_L = 0;
    int reg_P = 0;

    public void showMemory(int[] x){
        for (int i = 0; i<memory.length; i++){
            //HEX OUT in uppercase with leading zero
            String num = "0" + Integer.toHexString(i).toUpperCase();
            String mem = "0" + Integer.toHexString(memory[i]).toUpperCase();
            if (num.length()==3) num =num.substring(1);
            if (mem.length()==3) mem =mem.substring(1);
            System.out.println( num + "|" + mem);
        }
    }

    public void showAc(){System.out.println(Integer.toHexString(ac) +"|dec =" + ac);}

    public void setPc(int pc){
        this.pc = pc;
    }

    public void clearMemory(){
        int[] memory = new int[100];
    }

    public int getPc(){
        return pc;
    }

    public int getAc(){
        return ac;
    }



    public void setMemory(int[] memory) {
        this.memory = memory;
    }

    public int[] getMemory(){
        return memory;
    }

    public void start(){
        System.out.println(Arrays.toString(memory));
        boolean begin = false;
        for (; pc < memory.length; pc++) {
            if (begin) {
                task(memory[pc]);
            }else{
                    if (memory[pc] == 1) begin = true; //wait for begin
            }
        }
    }

    public void task(int x){

        switch (x)
        {
            // case 1: begin // START 01
            case 31:incMem();//1F
                break;
            case 22:decMem();//16
                break;
            case 51:jmpZ();//33
                break;
            case 52:jmp();//34
                break;
            case 172:movAc();//AC
                break;
            case 170: addAc();//AA
                break;
            case 175: subAc();//AF
                break;
            case 207: pc=memory.length-1;//CF end()
                break;
            default:System.out.println("NOP");
                break;




        }
    }



//tasks - microcommands
    public void begin(){}

    public void incAc(){ac++;}
    public void incMem(){memory[memory[pc+1]]++; pc++;} //1F
    public void incH(){reg_H++;}
    public void incL(){reg_L++;}

    public void decAc (){ac--;}
    public void decMem (){memory[memory[pc+1]]--; pc++;} //16
    public void decH (){reg_H--;}
    public void decL (){reg_L--;}

    public void addAc (){
        ac+=memory[memory[pc++]];
        System.out.println(ac);
    } //AA
    public void subAc (){pc++; ac-=memory[memory[pc++]];} //AF

    public void movAc (){memory[memory[pc++]]=ac;} //AC

    public void jmpZ () {if (memory[pc-1]==0) pc=memory[pc+1]-1; else pc++;} //33
    public void jmp () {pc=memory[pc+1]-1;} //34
    //01 01 begin
    //207 CF end

}

/*
Programs:
sum array of 10 elements (from 0A to 13):
01 34 04 0A 33 0D AA 0E 1F 07 16 03 34 04 CF 01 01 01 01 01 01 01 01 01 01


 */




/*
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