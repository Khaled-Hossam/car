public class Question2 {
     // this list is shared (HashMap)
     // volatile guarantees to see the changes that happens in other threads and prevent JVM from caching the value
     // (it won't be neccessary to use it while using synchronzied because only one thread will access the method at the time anyway)
     // (in this specfic case i think we can use one or the other)
     volatile Map<string, List<Integer>> data;

     // this method is used from different places and threads
     // here we used synchronized so the method can be used in mutiple threads safely
     synchronized SetValue(string key, int i) {
         List <integer> value = data.get(key);
         if (value == null){
             value = new List<Integer>();
             data.put(key, value );
         }
         value .add(i);
     }
}
