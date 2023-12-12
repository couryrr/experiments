#include <iostream>
#include <string>

// There is no hoisting in c++?

int *memorySegFault(int a)
{
    // This is a local variable. When function return a is deallocated
    return &a;
}

void memoryLeakCreator()
{
    // There is a compiler warning
    //  This is created locally and with new.
    //   Since it is not deleted then it will just hang around
    int *ptr = new int;

    // If this is called then no memory leak!
    // delete ptr
}

int main()
{
    std::string test = "Hello World";

    // Create a variable
    int count = 5;

    // Use the reference operator and get the address of count
    int *countPtr = &count;

    count = 18;

    std::cout << countPtr << std::endl;
    std::cout << count << std::endl;
    *countPtr = 88;

    // This will show the value of the variable
    std::cout << count << std::endl;

    // This holds the address to the variable count
    std::cout << countPtr << std::endl;

    // This will deference the pointer address giving the value
    std::cout << *countPtr << std::endl;

    *countPtr = 5;

    // Create an array with 5 addresses???
    int nums[5];

    // Setting some numbers
    for (size_t i = 0; i < count; i++)
    {
        nums[i] = i * 2;
    }

    // Printing each indexs value out
    for (size_t i = 0; i < count; i++)
    {
        std::cout << nums[i] << std::endl;
    }

    // Create a pointer to the first element in the array
    int *arrPtra = nums;

    // Same thing as above
    int *arrPtrb = &nums[0];

    // Proof of same addresses
    std::cout << arrPtra << std::endl;
    std::cout << arrPtrb << std::endl;

    // Proof of same value
    std::cout << *arrPtra << std::endl;
    std::cout << *arrPtrb << std::endl;

    // Address increment index 1 value 2
    std::cout << *++arrPtra << std::endl;
    std::cout << *++arrPtrb << std::endl;

    // Move up 2 and print index 3 value 6
    std::cout << *(arrPtra + 2) << std::endl;
    std::cout << *(arrPtrb + 2) << std::endl;

    // Memory Allocation all bad things not to do.

    // Nothing bad yet
    int *r = memorySegFault(2);

    // This will segfault because that memory address has been deallocated.
    // The address is 0 ie null?
    // std::cout << *r << std::endl;

    return 0;
}
