/*
 * Problem:
 * Take in a file each line is space delm list of numbers
 * Sort that list is acending order
 * 
 * Solution:
 * Trivial most file input and casting
 * 
 *
 * */
#include <algorithm>
#include <fstream>
#include <iostream>
#include <iomanip>
#include <sstream>
#include <string>
#include <vector>

int main(int argc, const char *argv[]){
    std::string line;
    std::ifstream in_file(argv[1]);
    std::vector<double> list;
    while(std::getline(in_file, line)){
        std::istringstream linestream(line);
        std::string word;
        while(linestream >> word){
            list.push_back(std::stod(word));
        }
        std::sort (list.begin(), list.end());
        std::cout << std::fixed << std::setprecision(3);
        for(double d : list){
            std::cout << d << ' ';
        } 
        list.clear();
        std::cout << '\n'; 
    }
}
