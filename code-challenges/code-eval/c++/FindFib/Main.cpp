#include <iostream>
#include <fstream>
#include <string>

int FindFib(int v){
    if(v <= 1){
        return v;
    }else{
        return FindFib(v-1) + FindFib(v - 2);
    }
    return -1;
}
int main(int argc, const char* argv[]){
    
    int line; //int value of the line being read
    std::ifstream in_file(argv[1]);

    while(in_file >> line){//Loop the file til the end
         
        //Find Fib for line index
        std::cout << FindFib(line) << std::endl;
    } 
    in_file.close();
}
