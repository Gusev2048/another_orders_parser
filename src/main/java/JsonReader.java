import java.io.File;

//TODO:
// - найти спринговый парсер
// - добавить фабрику объекстов
// - добавить очередь печати объектов (https://www.examclouds.com/ru/java/java-core-russian/interface-queue)
    // public class QueueExample {
    //    public static void main(String[] args) {
    //        Queue<String> queue = new LinkedList<>();
    //        queue.offer("Харьков");
    //        queue.offer("Киев");
    //        queue.offer("Кременчуг");
    //        queue.offer("Львов");
    //
    //        System.out.println(queue.peek());
    //
    //        String town;
    //        while ((town = queue.poll()) != null) {
    //            System.out.println(town);
    //        }
    //    }
    //}

public class JsonReader extends Thread {
    File file;

    JsonReader(File file) {
        this.file = file;
    }

    public void run() {
    }
}