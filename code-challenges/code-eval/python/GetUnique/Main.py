import sys
tFile = sys.argv[1]
j = ","
with open(sys.argv[1]) as input:
    for line in input:
        lLine = list(set(line.rstrip('\n').split(',')))
        lLine = [int(i) for i in lLine]
        lLine.sort()
        print(j.join(str(n) for n in lLine))
