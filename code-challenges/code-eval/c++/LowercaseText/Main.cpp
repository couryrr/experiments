#include <iostream>
#include <fstream>
#include <string>
int main(int argc, const char* argv[]){
    std::string line;
    std::ifstream in_file(argv[1]);
    std::string nLine = "";
    while(std::getline(in_file,line)){
        int i = 0;
        while (line[i] != '\0'){
            nLine += std::tolower(line[i]);
            ++i;
        }
        std::cout << nLine;
        nLine = "\n";
    } 
    in_file.close();
}
