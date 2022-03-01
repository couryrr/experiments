"""
 Coury Richards
 CodeEval
 For some fun
 22JUN2015
"""
class PrimeFinder:

    def __init__(self, largest):
       self. primeArr = []
       self.rangeArr = []
       self.largest = largest
    
    #Start Getters/Setters
    
    def get_num(self, i):
        return primeArr[i]

    def set_largest(self, val):
        self.largest =  val

    def get_largest(self):
        return self.largest
    
    def set_rangeArr(self):
        for num in range(self.get_largest()):
            self.rangeArr.append(num + 1)
    
    def get_rangeArr(self):
        return self.rangeArr

    def set_primeArr(self):
        for num in self.get_rangeArr():
            if self.check_prime(num):
                self.primeArr.append(num)

    def get_primeArr(self):
        return self.primeArr
    
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
        self.set_rangeArr()
        self.set_primeArr()

class PalindromFinder:

    def __init__(self, palArr, d = 'f'):
        self.direction = d
        self.palArr = palArr
        self.pals = []

    def set_palArr(self, palArr):
        self.palArr = palArr

    def get_palArr(self):
        return self.palArr
    
    @staticmethod
    def item_str(item):
    #Incase it is not a string
        if not type(item) == 'string':
            item = str(item)
        return item

    def iterate_palArr_forward(self):
        for item in self.palArr:
            item = self.item_str(item)
            if self.is_palindrome(item):
                self.pals.append(item)

    def iterate_palArr_reverse(self):
        for item in reversed(self.palArr):
            item = self.item_str(item)
            if self.is_palindrome(item):
                self.pals.append(item)

    def find_palindrome(self):
        if self.direction == 'r':
            self.iterate_palArr_reverse()
        else: 
            self.iterate_palArr_forward()
        return self.pals


    @staticmethod
    def is_palindrome(item):
        fP = 0
        eP = len(item)-1
        for c in item:
            if not fP == eP:
                if not item[fP] == item[eP]:
                    return False
                else:
                    fP += 1
                    eP -= 1
            elif (not fP > eP):
                return True
            else:
                return False
        return True
                
class PrimePalindromeFinder:
    def __init__(self, largest):
        self.largest = largest
        
        pf = PrimeFinder(self.largest)
        pf.find_prime()
        self.primeList = pf.get_primeArr()
        palF = PalindromFinder(self.primeList)
        self.palList = palF.find_palindrome()

    def get_palArr(self):
        return self.palList

    def get_largest_palindrome(self):
        tmp = self.get_palArr()
        l = len(tmp)
        return tmp[l-1]

pp = PrimePalindromeFinder(1000)
l = pp.get_largest_palindrome()
print(l)
