import pandas as pd
import os
import matplotlib.pyplot as plt
import numpy as np
""" Auteurs : Borie Baptiste, Benjamin Boutrois """

# Liste des algorithmes et des fichiers CSV correspondants
algorithms = [
    'BinaryTree',
    'QuickSort', 
    'TimSort', 
    'BitonicSort',
    'BubbleSort',
    'CombSort',
    'FusionSort',
    'HeapSort',
    'InsertionSort',
    'JavaSort', 
    'SelectionSort',
    'ShellSort',
    'StoogeSort']



csv_files = [
    'complexity_BinaryTreeSort.csv',
    'complexity_QuickSort.csv',
    'complexity_TimSort.csv',
    'complexity_BitonicSort.csv',
    'complexity_BubbleSort.csv',
    'complexity_CombSort.csv',
    'complexity_FusionSort.csv',
    'complexity_HeapSort.csv',
    'complexity_InsertionSort.csv',
    'complexity_JavaSort.csv',
    'complexity_SelectionSort.csv',
    'complexity_ShellSort.csv',
    'complexity_StoogeSort.csv'
]
"""
Fonction permettant de charger le contenu du fichier CSV spécifié en paramètre.
Elle va charger le fichier CSV, extraire les colonnes 'Generator' et 'Time' comme générateurs et temps, 
puis les renvoyer sous forme de variables.
"""

def load_data(csv_file):
    current_directory = os.path.dirname(os.path.realpath(__file__))
    csv_path = os.path.join(current_directory, '..', 'csv', csv_file)
    df = pd.read_csv(csv_path)
    generators = df['Generator']
    time = df['Time']
    return generators, time

def plot_algorithm(algorithm, index, generators, time, generator_colors):
    bar_width = 0.95
    index = np.arange(len(generators)) + index * (len(generators) + 0.5)
    plt.bar(index, time, bar_width, color=[generator_colors[gen] for gen in generators], label=algorithm)

# Créer un dictionnaire de couleurs pour chaque générateur
colors = ['skyblue', 'salmon', 'lightgreen', 'gold', 'orchid']
generator_colors = dict()

for i, algorithm in enumerate(algorithms):
    generators, _ = load_data(csv_files[i])
    generator_colors.update(dict(zip(generators, colors)))

plt.figure(figsize=(15, 8))

for i, algorithm in enumerate(algorithms):
    generators, time = load_data(csv_files[i])
    plot_algorithm(algorithm, i, generators, time, generator_colors)

# Afficher la légende avec les codes couleur pour chaque générateur
legend_patches = [plt.Rectangle((0,0),1,1, color=color, label=generator) for generator, color in generator_colors.items()]
plt.legend(handles=legend_patches)

# Définir les légendes en abscisse
plt.xticks(np.arange(len(algorithms)) * (len(generators) + 0.5) + 0.5, algorithms)

plt.title('Temps d\'exécution par algorithme et générateur')
plt.xlabel('Algorithme de tri')
plt.ylabel('Temps (ds)')

plt.show()
