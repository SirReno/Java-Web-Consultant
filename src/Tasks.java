import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class Tasks {
    private int counter;
    private ArrayList<String> urls_from_file = new ArrayList<String>();
    private String[] urls;
    private ArrayList<String> urls_status = new ArrayList<String>();
    private String[] final_statuses;

    public Tasks(){
        this.counter=0;
    }

    //get all the urls to be consulted by the threads and putting them into an array for
    //easier use
    public void getUrlsFromFile(){
        try {
            /*
            this.urls_from_file.add("http://www.google.com");
            this.urls_from_file.add("http://www.google.com.mx");
            this.urls_from_file.add("http://www.wikipedia.com");
            this.urls_from_file.add("https://sandbox.mienvio.mx/api/addresses;Content-Type:application/json;Authorization:Bearer ItPTCnqkFNlCLFabBBJvUOk2ModngFfdkJsgPVqnR5HcACR6L0thalVJGW2v;");
            */


            BufferedReader abc = new BufferedReader(new FileReader("urls.txt"));
            String line;
            while((line = abc.readLine()) != null){
                this.urls_from_file.add(line);
            }

            abc.close();

            this.urls=new String[this.urls_from_file.size()];
            this.urls=this.urls_from_file.toArray(this.urls);
            System.out.println("this is the amount of urls"+ this.urls.length);
        }catch(Exception e){
            System.out.println("Something went wrong on getURLS on Task.java");
        }
    }

    public synchronized int getCounter(){
        return this.counter;
    }
    //getTask, gives the url that the thread calling this function will have
    //to test
    public synchronized String getTask(String worker_name){
        if(this.counter < urls.length) {
            return urls[getCounter()];
        }else{
            return "done";
        }
    }

    public synchronized void increaseTask(){
        this.counter+=1;
    }

    //final_statuses prints out the result of all of the consulted urls
    public void final_statuses(){
        System.out.println("here are the results");
        this.final_statuses=this.urls_status.toArray(new String[0]);
        for(int i=0; i<final_statuses.length;i++){
            System.out.println(final_statuses[i]);
        }
    }

    //updateTask, will push the results of the consulted resource to
    //the urls_status array, for future consumption
    public synchronized void updateTask(String worker_name, String worker_task,String status){
        this.urls_status.add("worker name:"+worker_name+"; task done:"+worker_task+", gave status code "+status);
    }

    //here we will check if the next value in the array list is null
    //if it is, it means there are no more tasks
    public boolean hasFinished(){
        int aux1=this.counter;
        int aux2=this.urls.length;
        if(aux1== aux2){
            return true;
        }
        return false;
    }
}
