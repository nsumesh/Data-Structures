package graph;
import java.util.ArrayList;
/**
 * This class implements general operations on a graph as specified by UndirectedGraphADT.
 * It implements a graph where data is contained in Vertex class instances.
 * Edges between verticies are unweighted and undirected.
 * A graph coloring algorithm determines the chromatic number. 
 * Colors are represented by integers. 
 * The maximum number of vertices and colors must be specified when the graph is instantiated.
 * You may implement the graph in the manner you choose. See instructions and course material for background.
 */
 
 public class UndirectedUnweightedGraph<T> implements UndirectedGraphADT<T> {
   // private class variables here.
   
   private int MAX_VERTICES;
   private int MAX_COLORS;
   ArrayList <Vertex<T>> vertices;
   T arr[];
   int array[][];
   int size, numEdges;

   
   /**
    * Initialize all class variables and data structures. 
   */   
   public UndirectedUnweightedGraph (int maxVertices, int maxColors){
      MAX_VERTICES = maxVertices;
      MAX_COLORS = maxColors; 
      vertices = new ArrayList<Vertex<T>>();
      array = new int[maxVertices][maxVertices];
      size  = 0;
      numEdges = 0;
   }

   /**
    * Add a vertex containing this data to the graph.
    * Throws Exception if trying to add more than the max number of vertices.
   */
   public void addVertex(T data) throws Exception {
    if(size>=MAX_VERTICES)
      throw new Exception();
    Vertex<T> obj = new Vertex<T>(data);
    vertices.add(obj);
    size = size + 1;
   }
   
   /**
    * Return true if the graph contains a vertex with this data, false otherwise.
   */   
   public boolean hasVertex(T data){
      boolean check = false;
      for(int i = 0; i<size; i++)
      {
        if(vertices.get(i).getData().equals(data))
        {
          check = true;
          break;
        }
        else
          continue;
      }
      return check;
   } 


   public void initialization()
   {
     for(int i = 0; i<MAX_VERTICES; i++)
     {
      for(int j = 0; j<MAX_VERTICES; j++)
      {
       array[i][j] = 0;
      }
     }
   }

   /**
    * Add an edge between the vertices that contain these data.
    * Throws Exception if one or both vertices do not exist.
   */   
   public void addEdge(T data1, T data2) throws Exception{
    if(hasVertex(data1)==false || hasVertex(data2)==false)
      throw new Exception();
    int ind1 = 0;
    int ind2 = 0;
    for(int i = 0; i<size; i++)
    {
      if(vertices.get(i).getData().equals(data1))
      {
        ind1 = i;
        break;
      }
      else
        continue;
    }

    for(int j = 0; j<size; j++)
    {
      if(vertices.get(j).getData().equals(data2))
      {
        ind2 = j;
        break;
      }
      else
        continue;
    }
    array[ind1][ind2] = 1;
    array[ind2][ind1] = 1;
    numEdges++;
   }

   /**
    * Get an ArrayList of the data contained in all vertices adjacent to the vertex that
    * contains the data passed in. Returns an ArrayList of zero length if no adjacencies exist in the graph.
    * Throws Exception if a vertex containing the data passed in does not exist.
   */   
   public ArrayList<T> getAdjacentData(T data) throws Exception{
      ArrayList <T> adjacentVertices = new ArrayList<T>();
      if(!hasVertex(data))
        throw new Exception();
      int index = 0;
      for(int i = 0; i<vertices.size(); i++)
      {
        if(vertices.get(i).getData().equals(data))
        {
          index = i;
          break;
        }
      }
      for(int j = 0; j<MAX_VERTICES; j++)
      {
        if(array[index][j]==1)
        {
          adjacentVertices.add(vertices.get(j).getData());
        }
      }

      return adjacentVertices;
   }
   
   /**
    * Returns the total number of vertices in the graph.
   */   
   public int getNumVertices(){
      return size;
   }

   /**
    * Returns the total number of edges in the graph.
   */   
   public int getNumEdges(){
      return numEdges;
   }

   /**
    * Returns the minimum number of colors required for this graph as 
    * determined by a graph coloring algorithm.
    * Throws an Exception if more than the maximum number of colors are required
    * to color this graph.
   */   
   public int getChromaticNumber() throws Exception{
      int highestColorUsed = -1;
      int colorToUse = -1;
      for(Vertex<T> obj : vertices)
      {
        if(obj.getColor()==-1)
        {
          colorToUse = getColorToUse(obj);
          obj.setColor(colorToUse);
        }

        if(colorToUse>highestColorUsed)
          highestColorUsed = colorToUse;
      }
      if(highestColorUsed>MAX_COLORS)
        throw new Exception();
      else
        return highestColorUsed;
   }

   private int getColorToUse(Vertex <T> obj)
   {
     int colorToUse = -1;
     boolean [] adjColorsUsed = new boolean[MAX_COLORS];
     ArrayList <Vertex<T>> adjVertices = getAdjacentVertices(obj);
     int i;
     for(i = 0; i<adjVertices.size(); i++)
     {
       if(adjVertices.get(i).getColor()!=-1)
       {
         adjColorsUsed[i] = true;
         break;
       }
     }
     colorToUse = i;
     return colorToUse;
   }

   private ArrayList<Vertex<T>> getAdjacentVertices(Vertex <T> obj)
   {
      ArrayList <Vertex<T>> adjacentVertices = new ArrayList<Vertex<T>>();
      int index = 0;
      for(int i = 0; i<vertices.size(); i++)
      {
        if(vertices.get(i).equals(obj))
        {
          index = i;
          break;
        }
      }

      for(int j = 0; j<MAX_VERTICES; j++)
      {
        if(array[index][j]==1)
        {
          adjacentVertices.add(vertices.get(j));
        }
      }

      return adjacentVertices;
   }
   
   
}