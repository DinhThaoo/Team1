/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buaan;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author THAO
 */
public class Timbuaan {
 public String[] FoodName={"Tao","Cherry","Cam","Quýt","Duahau","Chanh","Kiwi","Nho","Dualuoi","Daotien","Chuói","Dautay","Thom","Le","SuaBo","Cachua","CaRot","CaTim","HanhTay","Toi","KhoaiTay","Nam","BanhMy","PhoMai","Bia","Ngheu","Cua","Ca","Tom","Ga","Bo","Trung"};
    public int[][] Food={{0,0,1,1,0,0,0,0,0,0},{0,0,1,0,0,0,1,1,0,0},{0,0,1,0,0,0,0,0,1,1},{0,0,1,1,1,0,0,0,0,1},
                         {0,0,1,0,0,0,0,1,0,0},{0,0,0,1,0,0,1,0,0,1},{0,0,1,1,1,0,0,1,0,1},{0,0,1,0,0,1,0,0,0,0},
                         {0,0,0,0,0,1,1,0,1,0},{0,0,1,1,0,1,0,0,0,0},{0,0,1,0,0,1,0,0,0,1},{0,0,1,1,0,0,1,0,0,1},
                         {0,0,1,0,0,0,0,0,1,0},{0,0,1,0,0,0,0,0,0,0},{1,1,1,0,1,1,0,1,0,0},{0,0,1,0,0,1,0,1,0,1}};
    public int[] FoodValue={31,66,70,34,50,106,38,62,49,55,82,33,118,54,83,15,56,123,32,40,52,63,18,74,62,64,55,70,100};
    
    public static void main(String[] args){
        new Timbuaan();
    }
    int N=100;
    Random rand=new Random();
    int [][]nghiem = new int[N][32];
    int []f=new int[N];
    
    public Timbuaan(){
       Khoitao();
       for(int i=0; i<100;i++){
          Danhgia();
          PrintBest();
          Luachon();
          Laighep();
          DotBien();
       }
    }
   
    private void Khoitao(){
       for(int i=0; i<N; i++)
           for(int j=0 ;j<32; j++){
               nghiem[i][j]=rand.nextInt(2);
           }
    }
     private void PrintBest(){
        int [] f1=f.clone();
        Arrays.sort(f1);
        int best=f1[0];
        for(int i=0 ;i<N;i++){
            if(f[i]==best){
                 int tien=0;
            for(int j=0;j<32;j++)
                tien=nghiem[i][j]*FoodValue[j];
            int dinhduong=0;
            for(int k=0;k<10;k++){
                for(int j=0;j<32;j++){
                    if(nghiem[i][j]==1 && Food[j][k]==1){
                        dinhduong++;
                        break;
                    }
                }
                for(int j=0; j<32; j++)
                    if(nghiem[i][j]==1)
                        System.out.print(FoodName[j]+",");
                System.out.println(tien+","+dinhduong);
                break;
            }
                
                
                
            }
        }
    }
    
    private void DotBien(){
        int index=rand.nextInt(N);
        int k=rand.nextInt(32);
        nghiem[index][k]=1-nghiem[index][k];
    }
    private void Laighep(){
        for(int i=0; i<N*30/100;i++){
            int indexcha= rand.nextInt(N);
            int indexme=rand.nextInt(N);
            
            for(int j=0; i<32 ; j++){
                if(rand.nextBoolean()){
                    int tmp=nghiem[indexcha][j];
                    nghiem[indexcha][j]=nghiem[indexme][j];
                    nghiem[indexme][j]=tmp;
                }
            }
        }
    }
    private void Luachon(){
        int [] f1=f.clone();
        Arrays.sort(f1);
        int nguong=f1[N*80/100];
        for(int i=0 ;i<N;i++){
            if(f[i]>nguong){
                nghiem[i]=nghiem[rand.nextInt(N)].clone();
            }
        }
    }
    private void Danhgia(){
        for(int i=0; i<N; i++){
            int tien=0;
            for(int j=0;j<32;j++)
                tien +=nghiem[i][j]*FoodValue[j];
            int dinhduong=0;
            for(int k=0;k<10;k++){
                for(int j=0;j<32;j++){
                    if(nghiem[i][j]==1 && Food[j][k]==1){
                        dinhduong++;
                        break;
                    }
                }
                f[i]=tien-40*dinhduong;
            }
        }
    }
     
}