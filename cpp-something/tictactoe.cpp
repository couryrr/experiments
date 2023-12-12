#include <fstream>
#include <iostream>
#include <string>

int main(int argc, char *argv[])
{
    for (int i = 0; i < argc; i++)
    {
        std::cout << argv[i] << std::endl;
    }
    // Read input from stdin or file
    std::ifstream file("tictactoe.csv");

    // Check if the file exists.
    if (!file.is_open())
    {
        std::cout << "Error: File could not be opened.\n";
        return 1;
    }

    // Read the contents of the file line by line.
    std::string line;
    while (std::getline(file, line))
    {
        if (!line.empty() && line.length() == 17)
        {
            // parse line and determine game board
            // null no player, 0 - x 1 - o
            // 9 long csv
            for (size_t i = 0; i < 18; i += 2)
            {
                std::cout << line[i] << std::endl;
            }
        }
        else
        {
            // std error
        }
    }

    // Close the file.
    file.close();

    std::string cli_line;
    std::getline(std::cin, cli_line);
    int board[9];

    if (!cli_line.empty() && cli_line.length() == 17)
    {
        // parse line and determine game board
        // null no player, 0 - x 1 - o
        // 9 long csv
        for (size_t i = 0; i < 18; i += 2)
        {
            std::cout << cli_line[i] << std::endl;
        }
    }
    else
    {
        // std error
    }

    // print to stdout or file
}