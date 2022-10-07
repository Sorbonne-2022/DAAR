import matplotlib.pyplot as plt

plt.rcParams["figure.figsize"] = [7.50, 3.50]
plt.rcParams["figure.autolayout"] = True

f = open("./../../data/kmp.txt","r")
lines = f.readlines()

def remove_next_line(n):
    return n.replace("\n", "")

data = list(map(remove_next_line, lines))

x = []
y = []

for i in data:
    xy = i.split(' ')
    x.append(int(xy[0]) / 1000)
    y.append(int(xy[1]))


plt.plot(x, y, 'ro')

plt.axis([0, max(x) * 2, 0, max(y) * 2])

plt.show()