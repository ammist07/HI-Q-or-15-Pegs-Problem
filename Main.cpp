#include <iostream>
#include <string>
using namespace std;
class Main{
public:
    int sols[36][3] = {{0,1,3},{0,2,5},{1,3,6},{1,4,8},{2,4,7},{2,5,9},{3,1,0},{3,6,10},{3,4,5},{3,7,12},
                       {4,7,11},{4,8,13},{5,2,0},{5,9,14},{5,8,12},{5,4,3},{6,3,1},{6,7,8},{7,4,2},{7,8,9},
                       {8,4,1},{8,7,6},{9,5,2},{9,8,7},{10,11,12},{10,6,3},{11,7,4},{11,12,13},{12,7,3},
                       {12,11,10},{12,13,14},{12,8,5},{13,8,4},{13,12,11},{14,9,5},{14,13,12}};
    int pegs[15];
    int result[13][3];
    void fillArray(){
        for(int i = 0; i < 15;i++){
            pegs[i] = 0;
        }
    }
    void makeHole(int holeIndex){
        for(int i = 0; i < 15; i++){
            if(i == holeIndex){
                pegs[i] = -1;
            }
        }
    }
    bool legalMove(int r){
        int from = pegs[sols[r][0]];
        int over = pegs[sols[r][1]];
        int into = pegs[sols[r][2]];
        if(from==0 && over==0 && into == -1){
            return true;
        }
        else return false;
    }
    int pegsRemain(){
        int count = 0;
        for(int i = 0; i < 15; i++){
            if(pegs[i]==0){count++;}
        }
        return count;
    }
    void makeMove(int r){
        pegs[sols[r][2]] =0;
        pegs[sols[r][1]] =-1;
        pegs[sols[r][0]] =-1;
    }
    void revert(int r){
        pegs[sols[r][2]] =-1;
        pegs[sols[r][1]] =0;
        pegs[sols[r][0]] =0;
    }
    bool findsolutions(int goal,int r){
        if(pegsRemain()==goal){
            return true;
        }
        for (int i = 0; i < 36 ; i++){
            if(legalMove(i) == true){
                makeMove(i);
                if(findsolutions(goal,r+1)){
                    result[r][0] = sols[i][0];
                    result[r][1] = sols[i][1];
                    result[r][2] = sols[i][2];
                    return true;
                } else revert(i);
            }
        }
        return false;
    }
    void printResult(){
        for(int i = 0; i < 13; i ++){
            cout << std::to_string(result[i][0]) + " " + std::to_string(result[i][1]) + " " + std::to_string(result[i][2]) << endl;
        }
    }
};
int main() {
    Main m;
    int goal;
    cout << "Enter what Goal you want: " << endl;
    cin >> goal;
    cout << "Enter what holeIndex you want: " << endl;
    int holeIndex;
    cin >> holeIndex;
    m.fillArray();
    m.makeHole(holeIndex);
    m.findsolutions(goal,0);
    m.printResult();
    return 0;



}
