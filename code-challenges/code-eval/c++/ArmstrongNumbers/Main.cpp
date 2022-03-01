#include <iostream>
#include <fstream>
#include <string>
#include <stdlib.h>
#include <math.h>

int main(int argc, const char *argv[]){
    std::string line;
    std::ifstream in_file(argv[1]);
    while(std::getline(in_file, line)){
        int power = line.length();
        int res = 0;
        for(char &c : line){
            int i = c - 48;
            
            res += pow(i,power);
        }
        int n = atoi(line.c_str());
        if(n == res) std::cout << "True" << '\n';
        else std::cout << "False" << '\n';
    }
}

