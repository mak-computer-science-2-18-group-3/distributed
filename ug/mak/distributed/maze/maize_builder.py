from random import shuffle, randrange


def resolve_h(a):
    a = a.replace("+--", "1")
    a = a.replace("+  ", "0")
    return int(a)


def resolve_v(b):
    b = b.replace("|  ", "1")
    b = b.replace("|", "1")
    b = b.replace("   ", "0")
    return int(b)


def make_maze(w=20, h=8):
    vis = [[0] * w + [1] for _ in range(h)] + [[1] * (w + 1)]
    ver = [["|  "] * w + ['|'] for _ in range(h)] + [[]]
    hor = [["+--"] * w + ['+'] for _ in range(h + 1)]

    def walk(x, y):
        vis[y][x] = 1

        d = [(x - 1, y), (x, y + 1), (x + 1, y), (x, y - 1)]
        shuffle(d)
        for (xx, yy) in d:
            if vis[yy][xx]: continue
            if xx == x: hor[max(y, yy)][x] = "+  "
            if yy == y: ver[y][max(x, xx)] = "   "
            walk(xx, yy)

    walk(randrange(w), randrange(h))

    maze = []
    for i in range(0, h):
        row = []
        for j in range(0, w):
            row.append([resolve_h(hor[i][j]), resolve_v(ver[i][j + 1]), resolve_h(hor[i + 1][j]), resolve_v(ver[i][j])])
        maze.append(row)



    s = ""
    for (a, b) in zip(hor, ver):
        s += ''.join(a + ['\n'] + b + ['\n'])
    return s, maze


if __name__ == '__main__':
    s, maze = make_maze()
    # print(s)
    print(maze)
