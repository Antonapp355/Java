package org.example;

import java.io.*;
import java.util.Scanner;


public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Введите число:" );
        Scanner scn1 = new Scanner(System.in);
        float num1 = scn1.nextInt();
        System.out.println( "Введите степень:" );
        Scanner scn2 = new Scanner(System.in);
        float num2 = scn2.nextInt();
        String text = "";

        WriteFileInput(num1,num2); // 1.Метод записывающий в файл input.txt .
        text = UploadFile(text);   // 2.Метод считывающий из файла, конвертирующий из String в float и считающий результат.
        WriteFileOutput(text);     // 3.Метод записывающий результат в файл output.txt .
        System.out.println(text);  // 4.Показывает результат в консоли.
    }
    public static String WriteFileInput (float a, float b) {
        try (FileWriter fw = new FileWriter("input.txt", false)) {
            fw.write("a = " + a + ";\n" + "b = " + b + ";");
            fw.flush();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return "good";
    }           // 1.
    public static String UploadFile (String text) throws IOException {
        String a = "";
        String b = "";
        String res = "";
        float temp = 0.0f;
        try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                for (int i = 0;i < line.length(); i++){
                    if((line.charAt(0) == 'a')) {
                        if (line.charAt(i) == '-'){
                            a+='-';
                        }
                        if (Character.toString(line.charAt(i)).matches("[0-9]") || line.charAt(i) == '.') {
                            a += line.charAt(i);
                        }
                    }
                    else if((line.charAt(0) == 'b')) {
                        if (line.charAt(i) == '-'){
                            b+='-';
                        }
                        if (Character.toString(line.charAt(i)).matches("[0-9]") || line.charAt(i) == '.') {
                            b += line.charAt(i);
                        }
                    }
                }
                line = br.readLine();
            }

            float a1 = 0.0f;
            float b1 = 0.0f;
            try {
                a1 = Float.parseFloat(a);
                b1 = Float.parseFloat(b);
            } catch (NumberFormatException e) {
                System.out.println("numberStr is not a number");
            }

            if (b1==0 && a1>0) {
                text = "1";
            }
            else if ((a1==0 && b1==0) | (b1==0 && a1<0)){
                text = "Не определено.";
            }
            else if (b1<0 && a1>0) {
                temp = a1;
                b1 *= -1;
                for (int i=0;i<b1-1;i++) {
                    a1 *= temp;
                }
                temp = 1/a1;
                text += temp;
            }
            else if (a1>0 && b1>0) {
                temp = a1;
                for (int i=0;i<b1-1;i++){
                    a1 *= temp;
                }
                text += a1;
            }
        }
        return text;
    } // 2.
    public static String WriteFileOutput(String text) {
        try (FileWriter fw = new FileWriter("output.txt",false)) {
            fw.write(text);
            fw.flush();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return "Good";
    }                // 3.
}
