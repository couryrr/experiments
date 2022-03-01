import sys


class FizzBuzz(object):

    def __init__(self):
        self.fizz = -1
        self.buzz = -1
        self.set = []
        self.output = ""

    def iterate_line(self, line):
        self.set_parameters(line)
        for num in self.set:
            self.result(num)

    def set_parameters(self, line):
        line.strip("\n")
        param = line.split(" ")
        self.fizz = int(param[0].strip("\n"))
        self.buzz = int(param[1].strip("\n"))
        n = int(param[2].strip("\n"))
        n_range = list(range(n))
        self.set = [x + 1 for x in n_range]

    def check_fizz(self, num):
        if num % self.fizz == 0:
            return True
        else:
            return False

    def check_buzz(self, num):
        if num % self.buzz == 0:
            return True
        else:
            return False

    def result(self, num):
        if self.check_fizz(num) and self.check_buzz(num):
            self.set_output("FB")
        elif self.check_fizz(num):
            self.set_output("F")
        elif self.check_buzz(num):
            self.set_output("B")
        else:
            self.set_output(str(num))

    def set_output(self, val):
        self.output += " " + val

    def get_output(self):
        return self.output

    def run(self, line):
        self.iterate_line(line)
        print(self.get_output())
        return 0

test = "5 3 25" 

# ignore test if it is an empty line
# 'test' represents the test case, do something with it
fb = FizzBuzz()
fb.run(test)
    

