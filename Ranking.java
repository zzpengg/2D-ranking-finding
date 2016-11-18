/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2d.ranking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.*;

/**
 *
 * @author user
 */
public class Ranking {

    /**
     * @param args the command line arguments
     */    
    public static float test[][] = null;
    // test[][0] = X;
    // test[][1] = Y;
    // test[][2] = rank;
    // test[][3] = index;
    
    public static int Max_rank=0;
    public static int Min_rank=0;
    public static float Averge_rank=0;
    public static void main(String[] args) {
        // TODO code application logic here                   
        int count = 0;
        int number = 0;
        try{
            FileReader fr = new FileReader("C:\\test1.txt");
	    BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();            
            while(line != null){
                System.out.println(line);
                if(count>2000){
                    System.out.println("data too much!!");
                    break;
                }
                else{
                    String[] temp = line.split("\\s");
                    System.out.println(temp[0]);
                    test[count][0] = Float.parseFloat(temp[0]);             
                    test[count][1] = Float.parseFloat(temp[1]);
                    test[count][2] = 0;
                    test[count][3] = count;
                    count++;
                }
                line = br.readLine();
                System.out.println(br.readLine());
            }            
            number = count-1;
        }catch(IOException e){
            System.out.println("read file error!!"+e.toString());
        }
        finally{            
            System.out.println("count = " + count);
            quickSort(test,0,number-1,1);
            int i = 0;
            while(i < number){                
                System.out.println(test[i][0]);
                i++;
            }
            DC(0,number-1);
            quickSort(test,0,number-1,2);
            int total_rank = 0;
            for(int j=0;j<number;j++){
                System.out.println(test[j][2]);
                if(test[j][2]>Max_rank){
                    Max_rank = Math.round(test[j][2]);
                }
                if(test[j][2]<Min_rank){
                    Min_rank = Math.round(test[j][2]);
                }
                total_rank += test[j][2];
            }
            Averge_rank = total_rank / (float)number;     
            BigDecimal b = new BigDecimal(Averge_rank);
            float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
            System.out.println("number=" + number);
            System.out.println("Max rank=" + Max_rank);
            System.out.println("Min rank=" + Min_rank);
            System.out.println("Average rank=" + f1);            
           
        }
    }
    public static void DCmerge(int l, int m, int r) {
        int idx1 = l, idx2 = m+1, top = 0;
        int d = 0, i;        
        while(idx1 <= m && idx2 <= r) {
            if(test[idx1][1] < test[idx2][1]){
                test[idx2][2] += 1;
                System.out.println("idx1=" + idx1 + "idx2=" + idx2 +  "rank=" + test[idx2][2]);
                idx1++;
            }else{
                idx1++;
            }
            if(idx1>m && idx2<=r){
                idx2++;
                idx1=l;
            }
        }
    }
    public static void DC(int l, int r) {
        if(l >= r)    return ;
        System.out.println("l+r/2 = " + (l+r)/2);
        int m = (l+r)/2;
        DC(l, m);
        DC(m+1, r);
        DCmerge(l, m, r);
    }
    public static int partition(float arr[][], int left, int right, int mode)
    {
        int i = left, j = right;
        float tmp1,tmp2,tmp3,tmp4;        
        if(mode==1){
            float pivot = arr[(left + right) / 2][0];
            while (i <= j) {
                while (arr[i][0] < pivot)
                    i++;
                while (arr[j][0] > pivot)
                    j--;
                if (i <= j) {
                    tmp1 = arr[i][0];
                    tmp2 = arr[i][1];
                    tmp3 = arr[i][2];
                    tmp4 = arr[i][3];
                    arr[i][0] = arr[j][0];
                    arr[i][1] = arr[j][1];
                    arr[i][2] = arr[j][2];
                    arr[i][3] = arr[j][3];
                    arr[j][0] = tmp1;
                    arr[j][1] = tmp2;
                    arr[j][2] = tmp3;
                    arr[j][3] = tmp4;
                    i++;
                    j--;
                }
            }
        }else if(mode==2){
            float pivot = arr[(left + right) / 2][3];
            while (i <= j) {
                while (arr[i][3] < pivot)
                    i++;
                while (arr[j][3] > pivot)
                    j--;
                if (i <= j) {
                    tmp1 = arr[i][0];
                    tmp2 = arr[i][1];
                    tmp3 = arr[i][2];
                    tmp4 = arr[i][3];
                    arr[i][0] = arr[j][0];
                    arr[i][1] = arr[j][1];
                    arr[i][2] = arr[j][2];
                    arr[i][3] = arr[j][3];
                    arr[j][0] = tmp1;
                    arr[j][1] = tmp2;
                    arr[j][2] = tmp3;
                    arr[j][3] = tmp4;
                    i++;
                    j--;
                }
            }
        }

        

        return i;
    }

    public static void quickSort(float arr[][], int left, int right, int mode) {
          int index = partition(arr, left, right, mode);
          if (left < index - 1)
                quickSort(arr, left, index - 1, mode);
          if (index < right)
                quickSort(arr, index, right, mode);
    }    
    
    
    
}

