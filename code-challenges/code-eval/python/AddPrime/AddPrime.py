"""
 Coury Richards
 CodeEval
 For some fun
 22JUN2015
"""
class PrimeFinder:

    def __init__(self, largest):
       self. prime_list = []
       self.prime_list_len = 0
       self.largest = largest
    
    #Start Getters/Setters
    
    def get_num(self, i):
        return prime_list[i]

    def set_largest(self, val):
        self.largest =  val

    def get_largest(self):
        return self.largest

    def set_prime_list_len(self):
        self.prime_list_len = len(self.get_prime_list())
    
    def get_prime_list_len(self):
        return self.prime_list_len

    def set_prime_list(self):
        num = 0
        while (self.get_prime_list_len() < self.get_largest()):
            if self.check_prime(num):
                self.prime_list.append(num)
            num += 1
            self.set_prime_list_len()

    def get_prime_list(self):
        return self.prime_list
    
    #End Getters/Setters

    def check_prime(self,num):
        #0 and 1 are not prime
        if num > 1:
            #Check all the other exceptions
            if self.check_two(num):
                return False
            elif self.check_three(num):
                return False
            elif self.check_five(num):
                return False
            #Check all the numbers ractorials
            else:
                return self.check_factorial(num)

    @staticmethod
    def check_two(num):
        if num == 2:
            return False
        elif num % 2 == 0:
            return True
        else: 
            return False
            
    @staticmethod
    def check_three(num):
        if num == 3:
            return False
        elif num % 3 == 0:
            return True
        else: 
            return False
    
    @staticmethod
    def check_five(num):
        if num == 5:
            return False
        elif num % 5 == 0:
            return True
        else:
            return False
    
    @staticmethod
    def check_factorial(num):
        #Checks the range of factorial for this number
        #If divisible by one then not prime
        for n in range(5, int(num ** 0.5) + 1, 6):
            #These are for troubleshooting
            #print(str(num) + "%" + str(n) + "=" + str(num % n))
            #print(str(num) + "%" + str(n+2) + "=" + str(num % (n + 2)))
            if num % n == 0 or num % (n + 2) == 0:
                return False
        return True
    
    def find_prime(self):
        self.set_prime_list()

pp = PrimeFinder(1000)
pp.find_prime()
parr = pp.get_prime_list()
res = 0

for n in parr:
    res += n

print(res)


