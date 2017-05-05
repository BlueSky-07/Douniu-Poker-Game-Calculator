#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void gameStart(char *inChar, int *toInt, int *count);
void gameInit (char *inChar, int *toInt, int *count);
void gameIn (char *inChar);
void gameIn2int (char *inChar, int *toInt);
void gameIn2count (char *inChar, int *count);
int judge10 (int *toInt, int a, int b, int c);
void judgeGame (char *inChar, int *toInt, int *count);
void char2Poker (char in);
void int2Poker (int in);
void showBull (char *inChar, int *toInt, int a, int b, int c);
void check(char *inChar, int *toInt, int *count);
enum {
	SIZE_INCHAR = 5,
	SIZE_TOINT = 6,
	SIZE_COUNT = 15
};

int main(void) {
	char inChar[SIZE_INCHAR];
	// toInt[SIZE_INCHAR - 1] is sum of SIZE_INCHAR cards
	int toInt[SIZE_TOINT];
	// count[SIZE_COUNT - 1] is boolean for existence of four-of-a-kind
	int count[SIZE_COUNT];
	printf("Welcome to the game:");
	gameStart(inChar, toInt, count);
	return 0;
}

void gameStart(char *inChar, int *toInt, int *count) {
	while (1) {
		gameInit(inChar, toInt, count);
		gameIn(inChar);
		gameIn2int(inChar, toInt);
		gameIn2count(inChar, count);
		judgeGame(inChar, toInt, count);
		// function for debug:
		// check(inChar, toInt, count);
	}
}
void gameInit (char *inChar, int *toInt, int *count) {
	memset(inChar, 0, sizeof(char) * SIZE_INCHAR);
	memset(toInt, 0, sizeof(int) * SIZE_TOINT);
	memset(count, 0, sizeof(int) * SIZE_COUNT);
}
void gameIn (char *inChar) {
	printf("\nwhat pokers ?\n");
	printf("(\"0\" for 10, joker not allowed)\n");
	int i = 0;
	while (i < SIZE_INCHAR) {
		char consoleIn = getchar();
		switch (consoleIn) {
			case '\n': case ' ': case '\t': {
					continue;
				}
			case 'A': case 'a': case 'J': case 'j':
			case 'Q': case 'q': case 'K': case 'k':
			case '1': case '2': case '3': case '4': case '5':
			case '6': case '7': case '8': case '9': case '0': {
					inChar[i] = consoleIn;
					break;
				}
			default: {
					putchar(consoleIn);
					printf(" is not a poker, retry.\n");
					gameIn(inChar);
					return;
				}
		}
		i ++;
	}
	while (getchar() != '\n');
}
void gameIn2int (char *inChar, int *toInt) {
	int i;
	for (i = 0; i < SIZE_INCHAR; i ++) {
		switch (inChar[i]) {
			case '2': case '3': case '4': case '5':
			case '6': case '7': case '8': case '9': {
					toInt[i] = inChar[i] - '0';
					break;
				}
			case '1': case 'A': case 'a': {
					toInt[i] = 1;
					break;
				}
			case '0': case 'J': case 'j':
			case 'Q': case 'q': case 'K': case 'k': {
					toInt[i] = 10;
					break;
				}
		}
	}
	for (i = 0; i < SIZE_INCHAR; i++)
		toInt[SIZE_TOINT - 1] += toInt[i];
}
void gameIn2count (char *inChar, int *count) {
	int i;
	for (i = 0; i < SIZE_INCHAR; i ++) {
		switch (inChar[i]) {
			case '2': case '3': case '4': case '5':
			case '6': case '7': case '8': case '9': {
					count[inChar[i] - '0'] ++;
					break;
				}
			case '1': case 'A': case 'a': {
					count[1] ++;
					break;
				}
			case '0': {
					count[10] ++;
					break;
				}
			case 'J': case 'j': {
					count[11] ++;
					break;
				}
			case 'Q': case 'q': {
					count[12] ++;
					break;
				}
			case 'K': case 'k': {
					count[13] ++;
					break;
				}
		}
	}
	for (i = 0; i < SIZE_COUNT - 1; i ++) {
		if (count[i] == 4) {
			count[SIZE_COUNT - 1] = i;
			return;
		}
	}
}
int judge10 (int *toInt, int a, int b, int c) {
	return (toInt[a] + toInt[b] + toInt[c]) % 10 == 0 ? 1 : 0;
}
void judgeGame (char *inChar, int *toInt, int *count) {
	if (count[SIZE_COUNT - 1] != 0) {
		printf("========= BOMB =========\n");
		int2Poker(count[SIZE_COUNT - 1]);
		putchar('\n');
		return;
	}
	if (toInt[SIZE_TOINT - 1] == 50) {
		printf("====== FIVE BULLS ======\n");
		return;
	}
	if (toInt[SIZE_TOINT - 1] == 10) {
		printf("=== FIVE SMALL BULLS ===\n");
		return;
	}
	if (judge10(toInt, 0, 1, 2)) {
		showBull(inChar, toInt, 0, 1, 2);
		return;
	} else if (judge10(toInt, 0, 1, 3)) {
		showBull(inChar, toInt, 0, 1, 3);
		return;
	} else if (judge10(toInt, 0, 1, 4)) {
		showBull(inChar, toInt, 0, 1, 4);
		return;
	} else if (judge10(toInt, 0, 2, 3)) {
		showBull(inChar, toInt, 0, 2, 3);
		return;
	} else if (judge10(toInt, 0, 2, 4)) {
		showBull(inChar, toInt, 0, 2, 4);
		return;
	} else if (judge10(toInt, 0, 3, 4)) {
		showBull(inChar, toInt, 0, 3, 4);
		return;
	} else if (judge10(toInt, 1, 2, 3)) {
		showBull(inChar, toInt, 1, 2, 3);
		return;
	} else if (judge10(toInt, 1, 2, 4)) {
		showBull(inChar, toInt, 1, 2, 4);
		return;
	} else if (judge10(toInt, 1, 3, 4)) {
		showBull(inChar, toInt, 1, 3, 4);
		return;
	} else if (judge10(toInt, 2, 3, 4)) {
		showBull(inChar, toInt, 2, 3, 4);
		return;
	}
	printf("--------- LOSE ---------\n");
}
void char2Poker (char in) {
	switch (in) {
		case '1': case 'a': case 'A': {
				putchar('A');
				break;
			}
		case '2': case '3': case '4': case '5':
		case '6': case '7': case '8': case '9': {
				putchar(in);
				break;
			}
		case '0': {
				putchar('1');
				putchar('0');
				break;
			}
		case 'j': case 'J': {
				putchar('J');
				break;
			}
		case 'q': case 'Q': {
				putchar('Q');
				break;
			}
		case 'k': case 'K': {
				putchar('K');
				break;
			}
	}
}
void int2Poker (int in) {
	switch (in) {
		case 1: {
				putchar('A');
				break;
			}
		case 2: case 3: case 4: case 5:
		case 6: case 7: case 8: case 9: {
				putchar(in + '0');
				break;
			}
		case 10: case 0: {
				putchar('1');
				putchar('0');
				break;
			}
		case 11: {
				putchar('J');
				break;
			}
		case 12: {
				putchar('Q');
				break;
			}
		case 13: {
				putchar('K');
				break;
			}
	}
}
void showBull (char *inChar, int *toInt, int a, int b, int c) {
	printf("========= BULL =========\n");
	switch (toInt[SIZE_TOINT - 1] % 10) {
		case 0: {
				int2Poker(10);
				break;
			}
		case 1: {
				putchar('1');
				break;
			}
		default: {
				int2Poker(toInt[SIZE_TOINT - 1] % 10);
				break;
			}
	}
	printf(" : [ ");
	int i, d = -1, e;
	for (i = 0; i < SIZE_INCHAR; i ++) {
		if (i == a || i == b || i == c) {
			char2Poker(inChar[i]);
			putchar(' ');
			continue;
		} else if (d == -1) {
			d = i;
			continue;
		} else {
			e = i;
			continue;
		}
	}
	printf("] + ( ");
	char2Poker(inChar[d]);
	putchar(' ');
	char2Poker(inChar[e]);
	printf(" )\n");
}
void check(char *inChar, int *toInt, int *count) {
	int i;
	for (i = 0; i < SIZE_INCHAR; i ++)
		printf("inChar[%d]:%c\n", i, inChar[i]);
	for (i = 0; i < SIZE_TOINT; i ++)
		printf("toInt[%d]:%d\n", i, toInt[i]);
	for (i = 0; i < SIZE_COUNT; i ++)
		printf("count[%d]:%d\n", i, count[i]);
}