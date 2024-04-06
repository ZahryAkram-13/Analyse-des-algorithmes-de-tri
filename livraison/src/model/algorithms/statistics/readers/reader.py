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
# Charger le fichier CSV dans un DataFrame
df = pd.read_csv(csv_path)

size = df['size'].values
time = df['time'].values





nlogn = list(map(lambda x : x*np.log2(x), size))
n2 = list(map(lambda x : x**2, size))
n27 = list(map(lambda x : x**(2.7), size))

#f1 = plt.plot(size, time, marker='o',  linestyle='-', color='r', label='nlogn')
#f2 = plt.plot(size, nlogn, marker='o', color='g', label='n2')

# plot with various axes scales 
plt.figure() 

# real data
plt.subplot(221) 
plt.plot(size, time) 
#plt.yscale('linear')
plt.title('real data')
plt.xlabel('Size')
plt.ylabel('Time')
plt.grid(True)

# theoric data
plt.subplot(222)
plt.plot(size, nlogn)
#plt.yscale('log')
plt.title('theoric data')
plt.xlabel('Size')
plt.ylabel('Time')
plt.grid(True)


#fig, axs = plt.subplots(2)
#axs[0].plot(size, time, label='time')
#axs[1].plot(size, nlogn, label="nlogn")



#plt.title('Time vs. Size')
#plt.xlabel('Size')
#plt.ylabel('Time')
#plt.grid(True)

plt.show()

# Afficher le DataFrame
print(df)
