import java.util.ArrayList;

public class Main {
    private int solutions[][] = { {0,1,3},{0,2,5},{1,3,6},{1,4,8},{2,4,7},{2,5,9},{3,1,0},{3,6,10},{3,4,5},{3,7,12},
                                  {4,7,11},{4,8,13},{5,2,0},{5,9,14},{5,8,12},{5,4,3},{6,3,1},{6,7,8},{7,4,2},{7,8,9},
                                  {8,4,1},{8,7,6},{9,5,2},{9,8,7},{10,11,12},{10,6,3},{11,7,4},{11,12,13},{12,7,3},
                                  {12,11,10},{12,13,14},{12,8,5},{13,8,4},{13,12,11},{14,9,5},{14,13,12} };
    private int[] pegs = new int[15];
    private int[][] result = new int[13][3];
    public Main(){
        for(int i = 0; i < pegs.length;i++){
            pegs[i] = 0;
        }
    }
    private void makeHole(int holeIndex){
        pegs[holeIndex] = -1;
    }
    private boolean legalMove(int r){
        int from = pegs[solutions[r][0]];
        int over = pegs[solutions[r][1]];
        int into = pegs[solutions[r][2]];
        if(from==0 && over==0 && into == -1){
            return true;
        }
        else return false;
    }
    private int pegsRemain(){
        int count = 0;
        for(int i: pegs){
            if(i == 0){count++;}
        }
        return count;
    }
    private void makeMove(int r){
        pegs[solutions[r][2]] =0;
        pegs[solutions[r][1]] =-1;
        pegs[solutions[r][0]] =-1;

    }
    private void revert(int r){
        pegs[solutions[r][2]] =-1;
        pegs[solutions[r][1]] =0;
        pegs[solutions[r][0]] =0;
    }
    public boolean findsolutions(int goal, int r){
        if (pegsRemain() == goal) {
            return true;
        }
        for (int i = 0; i < solutions.length; i++) {
            if (legalMove(i) == true) {
                makeMove(i);
                if(findsolutions(goal,r+1)){
                    result[r][0] = solutions[i][0];
                    result[r][1] = solutions[i][1];
                    result[r][2] = solutions[i][2];
                    return true;
                }else revert(i);
            }
        }
        return false;
    }
    public void printResult(){
        for(int[] i: result) {
            System.out.println(i[0] + "  " + i[1] + "  " + i[2]);
        }
    }
    public static void main(String[] args){
        Main m = new Main();
        if(args.length == 0){
            System.out.println("Hole is at position 0 and Goal is 1 peg remaining");
            m.makeHole(0);
            m.findsolutions(1, 0);
        }
        else if(args.length == 1){
            System.out.println("Hole is at position " + 0 +" Goal is " + ((Integer.parseInt(args[0])) < 1 ? 1 : (Integer.parseInt(args[0]))) + " peg remaining");
            m.makeHole(0);
            m.findsolutions((Integer.parseInt(args[0])) < 1 ? 1 : (Integer.parseInt(args[0])), 0);
        }
        else {
            System.out.println("Hole is at position " + ((Integer.parseInt(args[1])) < 1 ? 0 : (Integer.parseInt(args[1]))) +" Goal is " + ((Integer.parseInt(args[0])) < 1 ? 1 : (Integer.parseInt(args[0]))) + " peg remaining");
            m.makeHole((Integer.parseInt(args[1])) < 1 ? 0 : (Integer.parseInt(args[1])));
            m.findsolutions((Integer.parseInt(args[0])) < 1 ? 1 : (Integer.parseInt(args[0])), 0);
        }
        m.printResult();
    }
}
