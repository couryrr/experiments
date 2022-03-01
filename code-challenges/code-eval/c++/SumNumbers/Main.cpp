#include <iostream>
#include <fstream>
#include <string>
int main(int argc, const char* argv[]){
    std::string line;
    std::ifstream in_file(argv[1]);
    while(std::getline(in_file,line)){
        int i = 0;
        int res = 0;
        while (line[i] != '\0'){
            int v = line[i] - '0';
            res += v;
            ++i;
        }
        std::cout << res << std::endl;
    } 
    in_file.close();
}
