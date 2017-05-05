def gameStart():
    pokers = gameIn()
    if pokers:
        sum = getPokersSum(pokers)
        judge(pokers, sum)
        # print(pokers)
        # print(sum)


def gameIn():
    print('what pokers ?')
    print('("0" for 10, joker not allowed)')
    gameIn = input()
    if not gameIn:
        print('empty pokers')
        return False
    # change to upper case
    gameIn = gameIn.upper()
    # delete wrong characters
    valid = '1234567890ajqkAJQK'
    for item in gameIn:
        if not item in valid:
            gameIn = gameIn.replace(item, '')
    # less than 5 is not right
    if len(gameIn) < 5:
        print('not enough pokers')
        return False
    # take the first 5
    gameIn = gameIn[:5]
    # change to array
    gameIn = list(gameIn)
    # change 1 to A
    while '1' in gameIn:
        del gameIn[gameIn.index('1')]
        gameIn.append('A')
    # change 0 to 10
    while '0' in gameIn:
        del gameIn[gameIn.index('0')]
        gameIn.append('10')
    return gameIn


def judge(pokers, sum):
    if not judgeBigBonus(pokers, sum):
        if not judgeOtherBonus(pokers, sum):
            print('LOSE')


def judgeBigBonus(pokers, sum):
    # bomb
    count = []
    for poker in set(pokers):
        count.append([poker, pokers.count(poker)])
    for item in count:
        if item[1] == 4:
            print('BOMB :', item[0])
            return True
    # five bulls
    if sum == 50:
        print('FIVE BULLS')
        return True
    # five small bulls
    if sum == 10:
        print('FIVE SMALL BULLS')
        return True
    return False


def judgeOtherBonus(pokers, sum):
    value = sum % 10
    if value == 0:
        value = 10
    if judge10(pokers, 0, 1, 2):
        showBull(pokers, value, 0, 1, 2)
        return True
    elif judge10(pokers, 0, 1, 3):
        showBull(pokers, value, 0, 1, 3)
        return True
    elif judge10(pokers, 0, 1, 4):
        showBull(pokers, value, 0, 1, 4)
        return True
    elif judge10(pokers, 0, 2, 3):
        showBull(pokers, value, 0, 2, 3)
        return True
    elif judge10(pokers, 0, 2, 4):
        showBull(pokers, value, 0, 2, 4)
        return True
    elif judge10(pokers, 0, 3, 4):
        showBull(pokers, value, 0, 3, 4)
        return True
    elif judge10(pokers, 1, 2, 3):
        showBull(pokers, value, 1, 2, 3)
        return True
    elif judge10(pokers, 1, 2, 4):
        showBull(pokers, value, 1, 2, 4)
        return True
    elif judge10(pokers, 1, 3, 4):
        showBull(pokers, value, 1, 3, 4)
        return True
    elif judge10(pokers, 2, 3, 4):
        showBull(pokers, value, 2, 3, 4)
        return True
    else:
        return False


def getPokersSum(pokers):
    sum = 0
    for item in pokers:
        sum += getPokerVal(item)
    return sum


def getPokerVal(poker):
    if poker == 'J' or poker == 'Q' or poker == 'K':
        return 10
    elif poker == 'A':
        return 1
    else:
        return int(poker)


def judge10(pokers, a, b, c):
    return (getPokerVal(pokers[a]) + getPokerVal(pokers[b]) + getPokerVal(pokers[c])) % 10 == 0


def showBull(pokers, value, a, b, c):
    d, e = -1, -1
    for i in range(5):
        if not (i == a or i == b or i == c):
            if d == -1:
                d = i
            else:
                e = i
    print('BULLS', value, ':')
    print('[', pokers[a], pokers[b], pokers[c], '] + (', pokers[d], pokers[e], ')')


if __name__ == '__main__':
    print("Welcome to the game:")
    while 1:
        gameStart()
