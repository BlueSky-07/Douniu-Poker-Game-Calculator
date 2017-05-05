function run() {
    document.getElementById('result').innerHTML = '';
    var pokersArr = gameIn();
    var sum = getPokersSum(pokersArr);
    judge(pokersArr, sum);
}
function gameIn() {
    var toWrite = document.getElementById('result');
    var gameIn = document.getElementById('in').value;
    if (gameIn === '') {
        toWrite.innerHTML = 'empty pokers';
        return;
    }
    // delete wrong characters
    gameIn = gameIn.replace(/[^\dajqkAJQK]/g, '');
    // change to upper case
    gameIn = gameIn.toUpperCase();
    // take the first 5
    gameIn = gameIn.substr(0, 5);
    // less than 5 is not right
    if (gameIn.length < 5) {
        toWrite.innerHTML = 'not enough pokers';
        return;
    }
    // change to array
    gameIn = gameIn.split('');
    // change 1 to A, change 0 to 10
    for (var i = 0; i < 5; i++) {
        if (gameIn[i] === '1') {
            gameIn[i] = 'A';
        } else if (gameIn[i] === '0') {
            gameIn[i] = '10';
        }
    }
    return gameIn;
}
function judge(pokersArr, sum) {
    if (judgeBigBonus(pokersArr, sum) === false) {
        if (judgeOtherBonus(pokersArr, sum) === false) {
            var toWrite = document.getElementById('result');
            toWrite.innerHTML = 'LOSE';
        }
    }
}

function judgeBigBonus(pokersArr, sum) {
    var toWrite = document.getElementById('result');
    // bomb
    var pokerCount = [], arrPokers = [];
    for (var i = 0; i < 5; i++) {
        if (arrPokers.indexOf(pokersArr[i]) === -1) {
            arrPokers[arrPokers.length] = pokersArr[i];
            pokerCount[arrPokers.indexOf(pokersArr[i])] = 1;
        } else {
            pokerCount[arrPokers.indexOf(pokersArr[i])]++;
        }
    }
    if (pokerCount.indexOf(4) !== -1) {
        toWrite.innerHTML = 'BOMB : ' + arrPokers[pokerCount.indexOf(4)];
        return true;
    }
    // five bulls
    if (sum === 50) {
        toWrite.innerHTML = 'FIVE BULLS';
        return true;
    }
    // five small bulls
    if (sum === 10) {
        toWrite.innerHTML = 'FIVE SMALL BULLS';
        return true;
    }
    return false;
}

function judgeOtherBonus(pokersArr, sum) {
    sum = getPokersSum(pokersArr);
    value = sum % 10 === 0 ? 10 : sum % 10;
    arr2Calc = [];
    for (var i = 0; i < 5; i++) {
        arr2Calc[arr2Calc.length] = getPokerVal(pokersArr[i]);
    }
    if (judge10(arr2Calc, 0, 1, 2)) {
        showBull(pokersArr, value, 0, 1, 2);
        return true;
    } else if (judge10(arr2Calc, 0, 1, 3)) {
        showBull(pokersArr, value, 0, 1, 3);
        return true;
    } else if (judge10(arr2Calc, 0, 1, 4)) {
        showBull(pokersArr, value, 0, 1, 4);
        return true;
    } else if (judge10(arr2Calc, 0, 2, 3)) {
        showBull(pokersArr, value, 0, 2, 3);
        return true;
    } else if (judge10(arr2Calc, 0, 2, 4)) {
        showBull(pokersArr, value, 0, 2, 4);
        return true;
    } else if (judge10(arr2Calc, 0, 3, 4)) {
        showBull(pokersArr, value, 0, 3, 4);
        return true;
    } else if (judge10(arr2Calc, 1, 2, 3)) {
        showBull(pokersArr, value, 1, 2, 3);
    } else if (judge10(arr2Calc, 1, 2, 4)) {
        showBull(pokersArr, value, 1, 2, 4);
        return true;
    } else if (judge10(arr2Calc, 1, 3, 4)) {
        showBull(pokersArr, value, 1, 3, 4);
        return true;
    } else if (judge10(arr2Calc, 2, 3, 4)) {
        showBull(pokersArr, value, 2, 3, 4);
        return true;
    } else {
        return false;
    }
}
function getPokersSum(pokersArr) {
    var sum = 0;
    for (var i = 0; i < 5; i++) {
        sum += getPokerVal(pokersArr[i]) * 1;
    }
    return sum;
}

function getPokerVal(poker) {
    if (poker === 'A') {
        return 1;
    } else if (poker === 'J' || poker === 'Q' || poker === 'K') {
        return 10;
    } else {
        return poker;
    }
}
function judge10(arr2Calc, a, b, c) {
    return (arr2Calc[a] * 1 + arr2Calc[b] * 1 + arr2Calc[c] * 1) % 10 === 0;
}
function showBull(pokersArr, value, a, b, c) {
    var toWrite = document.getElementById('result');
    toWrite.innerHTML += 'BULLS ' + value + ':<br>';
    toWrite.innerHTML += '[ ';
    var d = -1, e;
    for (i = 0; i < 5; i++) {
        if (i === a || i === b || i === c) {
            toWrite.innerHTML += pokersArr[i] + ' ';
        } else if (d === -1) {
            d = i;
        } else {
            e = i;
        }
    }
    toWrite.innerHTML += '] + ( ' + pokersArr[d] + ' ' + pokersArr[e] + ' )';
}
function empty() {
    document.getElementById('in').value = '';
    document.getElementById('result').innerHTML = '';
}
