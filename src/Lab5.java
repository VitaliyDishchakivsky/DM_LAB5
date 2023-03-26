import java.util.*;
import java.io.*;
import java.util.Scanner;
public class Lab5 {
    
    // метод для визначення, чи є два графи ізоморфними
    private static boolean isIsomorphic(int[][] graph1, int[][] graph2) {
        
        // перевірка, чи мають графи однакову кількість вершин та ребер
        if (graph1.length != graph2.length || graph1[0].length != graph2[0].length) {
            return false;
        }
        
        int n = graph1.length; // кількість вершин
        
        // створюємо список вершин
        List<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vertices.add(i);
        }
        
        Set<List<Integer>> visited = new HashSet<>(); // множина відвіданих перестановок вершин
        
        // перебираємо всі можливі перестановки вершин
        do {
            if (!visited.contains(vertices)) {
                visited.add(new ArrayList<>(vertices)); // додаємо нову перестановку до множини відвіданих
                
                // перевіряємо, чи є даний варіант перестановки вершин допустимим ізоморфізмом
                Map<Integer, Integer> map = new HashMap<>(); // мапа для зберігання відображення між вершинами
                boolean isValid = true;
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        // перевіряємо, чи зберігається зв'язок між вершинами в графах
                        if (graph1[j][k] != graph2[vertices.get(j)][vertices.get(k)]) {
                            isValid = false;
                            break;
                        }
                    }
                    if (!isValid) {
                        break;
                    }
                    // додаємо відображення для вершини, яка співпадає сама з собою
                    map.putIfAbsent(graph1[j][j], graph2[vertices.get(j)][vertices.get(j)]);
                    // перевіряємо, чи зберігається відображення між вершинами
                    if (map.get(graph1[j][j]) != graph2[vertices.get(j)][vertices.get(j)]) {
                        isValid = false;
                        break;
                    }
                }
                if (isValid) {
                    return true;
                }
            }
        } while (nextPermutation(vertices)); // генеруємо наступну перестановку вершин
        
        return false;// графи не ізоморфні
    }
    
    // метод для генерації наступної перестановки за допомогою алгоритму Штейнгауза
    private static boolean nextPermutation(List<Integer> list) {
        int i = list.size() - 2;
        while (i >= 0 && list.get(i) >= list.get(i + 1)) {
            i--;
        }
        if (i < 0) {
            return false;
        }
        int j = list.size() - 1;
        while (list.get(j) <= list.get(i)) {
            j--;
        }
        Collections.swap(list, i, j);
        Collections.reverse(list.subList(i + 1, list.size()));
        return true;
    }
    
    public static void main(String[] args) throws IOException  {
    	// визначаємо два вхідних графи
       // int[][] graph1 = {{0, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 0, 1, 0}};
        //int[][] graph2 = {{0, 1, 0, 1}, {1, 0, 1, 1}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        
        // n - розмірність читаємої матриці
        int n;
        // Оголошення матриць ваг
        int graph1[][];
        int graph2[][];
        
        // Читання першої матриці з файлу
        File f1 = new File("c:\\matrix1.txt");
        Scanner sc1 = new Scanner(f1);
        n = sc1.nextInt();
        graph1  = new int[n][n];
        
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            	 graph1 [i][j] = sc1.nextInt();
        // Закриття файлу
        sc1.close();
        
        // Читання другої матриці з файлу
        File f2 = new File("c:\\matrix2.txt");
        Scanner sc2 = new Scanner(f2);
        n = sc2.nextInt();
        graph2  = new int[n][n];
        
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            	 graph2 [i][j] = sc2.nextInt();
        // Закриття файлу
        sc2.close();
        
      //Виведення першої матриці суміжності
        System.out.println("\nМатриця суміжності першого графа:");
        for (int i = 0; i < graph1.length; i++) {
            for (int j = 0; j < graph1[i].length; j++) {
                System.out.print(graph1[i][j] + "    ");
            }
            System.out.println();
        }
        
      //Виведення другої матриці суміжності
        System.out.println("\nМатриця суміжності другого графа:");
        for (int i = 0; i < graph2.length; i++) {
            for (int j = 0; j < graph2[i].length; j++) {
                System.out.print(graph2[i][j] + "    ");
            }
            System.out.println();
        }
        // Перевірити, чи є два графи ізоморфними, і надрукувати результат
        if (isIsomorphic(graph1, graph2)) {
            System.out.println("\nГрафи є ізоморфними");
        } else {
            System.out.println("\nГрафи не є ізоморфними");
        }
    }
}
