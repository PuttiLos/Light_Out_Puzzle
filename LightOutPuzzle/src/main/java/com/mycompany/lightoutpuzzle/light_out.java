/*
    PUTTIMAIT   VIWATTHARA      6213130
    CHUTIWAT    WATEK           6213195
    NAPAT       CHEEPMUANGMAN   6213200
 */

import java.io.*;
import java.util.*;
import org.jgrapht.*;
import org.jgrapht.alg.shortestpath.*;
import org.jgrapht.graph.*;

class LightOut{
    private FloydWarshallShortestPaths<Integer,DefaultEdge> FWSP;
    private List<Integer> V;
    private char[][] board;
    private int n;
    private Graph<Integer,DefaultEdge> G;
    LightOut(){
        boolean closing = false;
        while (!closing){
            
        InputProcess();
        printBoard();
        DrawMap();
        PathFinding();
        
        Scanner KB = new Scanner(System.in);
        System.out.println("\n\n\t Do you want to close this program? (Press 0 key and Enter to close this program.)");
        if(KB.next().equals("0")) {closing = true;}
        }
    }
    
    int Reverse(int a,int b){
        String key_a = toBi(a);
        String key_b = toBi(b);
        char[][] board_a = new char[n][n];
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                board_a[i][j] = key_a.charAt(i*n+j);
            }
        }
        char[][] board_b = new char[n][n];
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                board_b[i][j] = key_b.charAt(i*n+j);
            }
        }
        char[][] checker = new char[n][n];
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                if(board_a[i][j]==board_b[i][j]){
                    checker[i][j]='0';
                }
                else{
                    checker[i][j]='1';
                }
            }
        }
        int change=0,i=0,j=0;
        for(i=0;i<n;++i){
            for(j=0;j<n;++j){
                change=0;
                if(checker[i][j]=='1'){
                if(i-1>=0){
                    if(checker[i-1][j]=='1'){
                        change+=1;
                    }
                }
                if(i+1<n){
                    if(checker[i+1][j]=='1'){
                        change+=1;
                    }
                }
                if(j-1>=0){
                    if(checker[i][j-1]=='1'){
                        change+=1;
                    }
                }
                if(j+1<n){
                    if(checker[i][j+1]=='1'){
                        change+=1;
                    }
                }
                //System.out.println(change);
                }
                if(change>=2){break;}
            
            
            }
            if(change>=2){break;}
        }
        return i*n+j;
    }
    
    void PathFinding(){
        int start = toValue(board);
        try{
            FWSP = new FloydWarshallShortestPaths<>(G);
            V = FWSP.getPath(start, 0).getVertexList();
            ArrayList<Integer> UniqueV = new ArrayList();
                for(int i=0;i<V.size();++i){
                    int v = V.get(i);
                    if(i+1<V.size()){
                        int indexPressing = Reverse(V.get(i), V.get(i+1));
                        if(UniqueV.contains(indexPressing))
                        {
                            UniqueV.removeIf(arg -> arg == indexPressing);
                        }
                        else { UniqueV.add(indexPressing); }
                    } 
                }
                System.out.println("\n\t" + UniqueV.size() + " move(s) to turn off all lights.");
                int move = 1;
                for(Integer u:UniqueV){
                    System.out.printf("\n\t >>> Move %d : turn on row %d, col %d (Button %d)"
                            , move, (u/n)+1, (u - (u/n)*n) + 1, u + 1);
                    ToggleLight(toBi(toValue(board)),u,0);
                    printBoard();
                    move++;
                }
            }
            catch(Exception e){
                System.out.println("That path didn't exist.\n" + e);
            }
    }
    String ToggleLight(String Data,int index,int mode){
        char[][] new_data ;
        if(mode==1){
            new_data = new char[n][n];
        }
        else{
            new_data = board;
        }
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                new_data[i][j] = Data.charAt(i*n+j);
            }
        }
        int x,y;
        x = index/n;
        y = index-(x*n);
        if(new_data[x][y]=='1'){
            new_data[x][y]='0';
        }
        else{
            new_data[x][y]='1';
        }//TargetIndex
        if(x-1>=0){
        if(new_data[x-1][y]=='1'){
            new_data[x-1][y]='0';
        }
        else {
            new_data[x-1][y]='1';
        }//UpTargetIndex
        }
        if(x+1<n){
        if(new_data[x+1][y]=='1'){
            new_data[x+1][y]='0';
        }
        else 
            new_data[x+1][y]='1';
        }//DownTargetIndex
        if(y+1<n){
        if(new_data[x][y+1]=='1'){
            new_data[x][y+1]='0';
        }
        else 
            new_data[x][y+1]='1';
        }//RightTargetIndex
        if(y-1>=0){
        if(new_data[x][y-1]=='1'){
            new_data[x][y-1]='0';
        }
        else 
            new_data[x][y-1]='1';
        }//LeftTargetIndex
        
        String new_node="";
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                new_node=new_node.concat(Character.toString(new_data[i][j]));
            }
        }
        return new_node;
    }
    void DrawMap(){
        G = new SimpleGraph<>(DefaultEdge.class);
        ArrayDeque<Integer> AllNode = new ArrayDeque<Integer>();
        AllNode.add(toValue(board));
        boolean finish = false;
        while(!AllNode.isEmpty()&&!finish){
            int node = AllNode.pollFirst();
            for(int i=0;i<n*n&&!finish;++i){
                String tar_node = ToggleLight(toBi(node), i,1);
                if(!G.containsVertex(toValue(tar_node))){AllNode.push(toValue(tar_node));}
                if(toValue(tar_node)==0){finish = true;}
                Graphs.addEdgeWithVertices(G, node, toValue(tar_node));
                
            }
            
        }
        /*int node = 0;
        for (int k = 0; k < Math.pow(2,n*n); ++k) {
            node = k;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    String tar_node = ToggleLight(toBi(node), i * n + j);
                    Graphs.addEdgeWithVertices(G, node, toValue(tar_node));
                }
            }
        }*/
        /*int node =0;
        for(int i=0;i<Math.pow(2, n*n);++i){
            G.addVertex(node);
            node+=1;
        }//All node added.
        
        for(int i=0;i<Math.pow(2, n*n);++i){
            for(int j=i+1;j<Math.pow(2, n*n);++j){
                for(int k=0;k<n*n;++k){
                    String cur_node = toBi(i);
                    String tar_node = ToggleLight(cur_node,k);
                    int tar_node_value = toValue(tar_node);
                    if(tar_node_value==j){
                        System.out.println(i+" "+j);
                        G.addEdge(i, j);
                        break;
                    }
                }
            }
        }//Self and next Other
        //We should get all possibility relation.
        
        */
    }
    void InputProcess(){
        Scanner KB = new Scanner(System.in);
        boolean finished = false;
        while (!finished) {
            System.out.println("Enter number of rows for square grid : ");
            try {
                n = KB.nextInt();
                if (n >= 3) { finished = true; }
                else {
                    System.out.println("\tThe number entered was " + n + ", but the number of rows for square grid must be more than or equal to 3.\n");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("\tInvalid input type (must be an integer)\n");
                KB.nextLine();
            }
        }
        board = new char[n][n];
        String Seq = "";
        boolean finished1 = false;
        while (!finished1) {
                finished1 = false;
                System.out.println("Initial states (" + n*n + " bits, left to righ, line by line, 0 is turn off state, and 1 is turn on state) : ");
                Seq = KB.next();
                if (Seq.length() == n*n) {
                    int checker = 0;
                    for(int i = 0; i < n*n; i++)
                    {
                        if(Seq.charAt(i) == '0' || Seq.charAt(i) == '1')
                        {checker++;}
                    }
                    if(checker == n*n) { finished1 = true; }
                    else { System.out.println("\tThe initial states is '" + Seq + "', but it needs to be 0 or 1 state only.\n"); }
                }
                else {
                    System.out.println("\tThe initial states is '" + Seq + "', but it needs to be " + n*n + " bits.\n");
                }
            }
        
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                board[i][j] = Seq.charAt(i*n+j);
            }
        }
    }
    void printBoard(){
        System.out.println("\n Bit string = " + toBi(toValue(board)) + ", decimal ID = " + toValue(board));
        System.out.println("      |  Col 1  |  Col 2  |  Col 3");
        for(int i=0;i<n;++i){
            System.out.printf("Row " + (i+1));
            for(int j=0;j<n;++j){
                System.out.printf(" |    %c   ",board[i][j]);
            }
            System.out.println("");
        }
    }
    
    int toValue(String key){
        int v = 0;
        char[][] new_data = new char[n][n];
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                new_data[i][j] = key.charAt(i*n+j);
            }
        }
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                v += Character.getNumericValue(new_data[i][j])*(Math.pow(2, (n*n-1)-(i*n+j) ));
            }
        }
        return v;
    }
    int toValue(char[][] key){
        int v = 0;
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                v += Character.getNumericValue(key[i][j])*(Math.pow(2, (n*n-1)-(i*n+j) ));
            }
        }
        return v;
    }
    String toBi(int v){
        
        String a = "";
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                if(v>=(Math.pow(2, (n*n-1)-(i*n+j) ))){
                    a=a.concat("1");
                    v -= (Math.pow(2, (n*n-1)-(i*n+j) ));
                }
                else{
                    a=a.concat("0");
                }
                
            }
        }
        return a;
    }
    
    
}

public class light_out{
    public static void main(String[] Arg){
        new LightOut();
    }
}