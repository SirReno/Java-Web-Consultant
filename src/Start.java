//space for imports

public class Start {
    public static void main(String[] args) throws Exception {

        Tasks tasks = new Tasks();
        tasks.getUrlsFromFile();

        Worker threads[];
        threads = new Worker[2];

        threads[0]= new Worker("first worker ", tasks);
        threads[1]= new Worker("second worker ", tasks);

        for(int i=0; i<threads.length;i++){
            threads[i].start();
        }
        while(true){
            System.out.println("consulting, just wait a little");
            Thread.sleep(5000);
            if(tasks.hasFinished()==true){
                tasks.final_statuses();
                break;
            }else{
                System.out.println("the Workers havent finished");
            }
        }

    }

}