package twofour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
    public int orangesRotting(int[][] grid) {
        ArrayList<int []>points=new ArrayList();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==2)
                    points.add(new int[]{i,j});
            }
        }
        int time=-1;
        while(points.size()!=0){
            for(int[] point:points){
                grid[point[0]][point[1]]=2;
                if(point[0]>0&&grid[point[0]-1][point[1]]==1)
                    points.add(new int[]{point[0]-1,point[1]});
                if(point[0]<grid.length-1&&grid[point[0]+1][point[1]]==1)
                    points.add(new int[]{point[0]+1,point[1]});
                if(point[1]<grid[0].length-1&&grid[point[0]][point[1]+1]==1)
                    points.add(new int[]{point[0],point[1]+1});
                if(point[1]>0&&grid[point[0]][point[0]-1]==1)
                    points.add(new int[]{point[0],point[1]-1});
            }
            time++;
        }
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1)
                    return -1;
            }
        }
        return time;
    }

}