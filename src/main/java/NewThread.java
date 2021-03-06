public class NewThread extends Thread {
    int num;
    NewThread(int num){
        this.num = num;
    }
    public void run() {
        System.out.println(this.getName() + ": New Thread " + num + " is running...");

        int i = 0;

        while (i < 50) {
            System.out.println("Thread " + num + " print " + i);
            i++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}