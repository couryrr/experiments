import sys
tFile = sys.argv[1]
j = ","
with open(sys.argv[1]) as input:
    for line in input:
        lLine = line.rstrip('\n').split(';')
        res = ""
        s1 = lLine[0].split(',')
        s2 = lLine[1].split(',')
        for i in range(len(s1)):
            for t in range(len(s2)):
                if(s1[i] == s2[t]):
                    res += s1[i] + ','
        print(res.strip(','))
