import pandas as pd
import os
import math
import matplotlib.pyplot as plt
import random
import numpy as np


# Get the current directory of the script
current_directory = os.path.dirname(os.path.realpath(__file__))

# Construct the path to the CSV file
csv_path = os.path.join(current_directory, '..', 'csv', 'complexity/Shell.csv')
#csv_path = os.path.join(current_directory, '..', 'csv', 'complexity/BubbleSort.csv')

# Charger le fichier CSV dans un DataFrame
df = pd.read_csv(csv_path)

size = df['size'].values
time = df['time'].values

# Fonction à ajuster
def nlogn_fit(x, alpha, beta):
    return alpha + beta * x

# Données d'exemple
x = size
y = time  # Exemple de temps d'exécution

# Transformation des données
log_x = [math.log(p) for p in x]
log_y = [math.log(p) for p in y]

# Calcul des coefficients de la régression linéaire
n = len(x)
sum_log_x = sum(log_x)
sum_log_y = sum(log_y)
sum_log_x_squared = sum(x_i ** 2 for x_i in log_x)
sum_log_x_times_log_y = sum(x_i * y_i for x_i, y_i in zip(log_x, log_y))

alpha = (sum_log_y * sum_log_x_squared - sum_log_x * sum_log_x_times_log_y) / (n * sum_log_x_squared - sum_log_x ** 2)
beta = (n * sum_log_x_times_log_y - sum_log_x * sum_log_y) / (n * sum_log_x_squared - sum_log_x ** 2)

print("Coefficient alpha:", alpha)
print("Coefficient beta:", beta)


# Dessiner les données originales
plt.scatter(x, y, color='blue', label='data')

# Dessiner la régression
x_values = [p for p in range(1, int(max(x) + 1))]
y_values = [math.exp(alpha + beta * math.log(p)) for p in x_values]

fontsize = 20
plt.plot(x_values, y_values, color='red', label='Regression')

plt.xlabel('size', fontsize=fontsize)
plt.ylabel('time', fontsize=fontsize)
plt.title('regression logarithmique', fontsize=fontsize)
posx, posy = max(size)/2 -5000, max(time)
plt.text(posx, posy,f'alpha = {beta}, beta = {alpha}', fontsize=fontsize, color='blue')

plt.legend()
plt.show()
