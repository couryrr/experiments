#include <iostream>
#include <string>
int main()
{
    std::string line;

    bool running = true;
    int loops = 0;
    int stop_at = 7;
    while (running)
    {
        std::getline(std::cin, line);
        if (!line.empty())
        {
            if (line == "exit")
            {
                running = false;
            }
            std::cout << line << std::endl;
        }
    }

    std::cout << "program ended" << std::endl;
    return 0;
}