import pandas as pd
import os
import math
import matplotlib.pyplot as plt
import random
import numpy as np


# Get the current directory of the script
current_directory = os.path.dirname(os.path.realpath(__file__))

# Construct the path to the CSV file
csv_path = os.path.join(current_directory, '..', 'csv', 'complexity/InsertionSort.csv')
csv_path = os.path.join(current_directory, '..', 'csv', 'complexity/BubbleSort.csv')

# Charger le fichier CSV dans un DataFrame
df = pd.read_csv(csv_path)

size = df['size'].values
time = df['time'].values


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
    v = (sum(x ** 2 for x in column) / n) - m**2
    return v


def covariance(x, y):
    n = len(x)
    m = len(y)
    if(n == m):
        x_bar, y_bar = moyenne(x), moyenne(y)
        cov = ((sum( (x[i]*y[i]) for i in range(n))) / n) - x_bar*y_bar
        return cov

def ecart_type(x):
    return math.sqrt(variance(x))

def correlation(x, y):
    return covariance(x, y)/(ecart_type(x)*ecart_type(y))


def alpha(x, y):
    return covariance(x, y)/variance(x)

def beta(x, y):
    return moyenne(y) - alpha(x, y)*moyenne(x)

def ln(x):
    return [math.log(p) for p in x]


def new_y(x, a, b):
    return [math.exp(b) * p**a for p in x]

def equation(alpha, beta):
    return "y = " + " x ** " + str(alpha) + " * " + " (" +str(beta) + ")"

def n_2(x):
    return [p**2 for p in x]


if __name__ == "__main__":

    #show(time)
    #print("x", x)
    #print("y", y)
    print("moyenne: size ", moyenne(size))
    print("moyenne: time ", moyenne(time))
    print("variance: size ", variance(size))
    print("covariance ", covariance(size, time))

    lnx, lny = ln(size), ln(time)
    pente = alpha(lnx, lny)
    p_origin = beta(lnx, lny)
    print("alpha: ", pente, " beta: ", p_origin)
    print("correlation: ", correlation(lnx, lny))


    fig, ax = plt.subplots(figsize=(20, 10), layout='constrained')

    ax.scatter(size, time, label='real data')  # Plot some data on the axes.
    ax.plot(size, new_y(size, pente, p_origin), label = "Regression", color='red')
    #ax.plot(size, n_2(size), label = "regression", color='green')


    fontsize = 20
    ax.set_xlabel('size', fontsize=fontsize)  # Add an x-label to the axes.
    ax.set_ylabel('time', fontsize=fontsize)  # Add a y-label to the axes.
    ax.set_title("regression quadratique", fontsize=20)  # Add a title to the axes.
    ax.legend()  # Add a legend.

    posx, posy = max(size)/2-5000, max(time)
    ax.text(posx, posy,f'alpha = {pente}, beta = {p_origin}', fontsize=fontsize, color='blue')
    plt.show()
