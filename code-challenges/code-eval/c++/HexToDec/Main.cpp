#include <iostream>
#include <fstream>
#include <string>
#include <math.h>

int main(int argc, const char *argv[]){
    std::string line;
    std::ifstream in_file(argv[1]);
    while(std::getline(in_file, line)){
        int power = line.length()-1;
        int res = 0;
        for(char c : line){
            switch(c){
                case 'a':
                    res += 10 * pow(16,power);
                    break;
                case 'b':
                    res += 11 * pow(16,power);
                    break;
                case 'c':
                    res += 12 * pow(16,power);
                    break;
                case 'd':
                    res += 13 * pow(16,power);
                    break;
                case 'e':
                    res += 14 * pow(16,power);
                    break;
                case 'f':
                    res += 15 * pow(16,power);
                    break;
                default:
                    int i = c - 48;
                    res += i * pow(16,power);
                    break;
            }
            --power;
        }
        std::cout << res << '\n';
    }
}
