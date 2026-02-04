import random

notes = ['C','C#','D','D#','E','F','F#','G','G#','A','A#','B']
random.shuffle(notes)

def scale():
    for note in notes:
        r = random.randint(0, 10)
        a = 3
        if r == 0:
            a = 6
        elif r <= 3:
            a = 5
        elif r <= 8:
            a = 4

        print(a, note, end=" ")

for i in range(12):
    scale()
    print()
