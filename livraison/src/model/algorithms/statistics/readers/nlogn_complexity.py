import pandas as pd
import os
import math
import matplotlib.pyplot as plt
import random
import numpy as np


# Get the current directory of the script
current_directory = os.path.dirname(os.path.realpath(__file__))

# Construct the path to the CSV file
csv_path = os.path.join(current_directory, '..', 'csv', 'complexity.csv')
csv_path = os.path.join(current_directory, '..', 'csv', 'complexity/Shell.csv')
csv_path = os.path.join(current_directory, '..', 'csv', 'complexity/FusionSort.csv')
csv_path = os.path.join(current_directory, '..', 'csv', 'complexity/Tim.csv')

# Charger le fichier CSV dans un DataFrame
df = pd.read_csv(csv_path)

size = df['size'].values
time = df['time'].values

test_x = list(range(1, 50))
test_y = list([x*5 + random.randint(1, 30) for x in test_x])

def show(x):
    n, i = len(x), 0
    while i < n:
        print(x[i])
        i = i +1

def moyenne(column):
    return sum(column)/len(column)

def variance(column):
    m = moyenne(column)
    n = len(column)
    v = sum((x - m) ** 2 for x in column) / n
    return v


def covariance(x, y):
    n = len(x)
    m = len(y)
    if(n == m):
        x_bar, y_bar = moyenne(x), moyenne(y)
        cov = (sum((x[i] - x_bar) * (y[i] - y_bar) for i in range(len(x)))) / len(x)
        return cov

def alpha(x, y):
    return covariance(x, y)/variance(x)

def beta(x, y):
    return moyenne(y) - alpha(x, y)*moyenne(x)

def ln(x):
    return [math.log(e) for e in x]

def xlogx(x):
    return [p*math.log(p) for p in x]

def new_y(x, alpha, beta):
    return [alpha*p + beta for p in x]

def equation(x, y):
    return "y = " + str(alpha(x, y)) + " xlogx + " + str(beta(x, y))


if __name__ == "__main__":
    x = test_x
    y = test_y
    #show(time)
    #print("x", x)
    #print("y", y)
    print("moyenne: size ", moyenne(size))
    print("moyenne: time ", moyenne(time))
    print("variance: size ", variance(size))
    print("covariance ", covariance(size, time))

    nlogn = xlogx(size)
    pente = alpha(nlogn, time)
    p_origin = beta(nlogn, time)

    fig, ax = plt.subplots(figsize=(20, 10), layout='constrained')


    fontsize = 20
    ax.scatter(size, time, label='linear')  # Plot some data on the axes.
    ax.plot(size, new_y(nlogn, pente, p_origin), label = "regression", color='red')


    ax.set_xlabel('size', fontsize=fontsize)  # Add an x-label to the axes.
    ax.set_ylabel('time', fontsize=fontsize)  # Add a y-label to the axes.
    ax.set_title("regression logarithmique", fontsize=fontsize)  # Add a title to the axes.
    ax.legend()  # Add a legend.

    posx, posy = max(size)/2-5000, max(time)
    ax.text(posx, posy,f'alpha = {pente}, beta = {p_origin}', fontsize=fontsize, color='blue')
    plt.show()
