import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.net.URL;

public class Worker extends Thread {
    private String worker_name;
    private Tasks task_given;
    static volatile boolean alive;

    public Worker(String worker_name, Tasks task_given ){
        this.worker_name=worker_name;
        this.task_given=task_given;
        this.alive=true;
    }

    public void terminate(){
        this.alive=false;
    }

    public void consult() throws IOException{

            String task= task_given.getTask(this.worker_name);
            task_given.increaseTask();
            String[] task_data =task.split(";");
            String result="Url given "+task_data[0]+" Response(if API):";
            if(task_data.length==1){
                URL url = new URL(task_data[0]);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int code = connection.getResponseCode();
                System.out.println(code);
                String status = Integer.toString(connection.getResponseCode());
                task_given.updateTask(worker_name,result,status);
            }else{
                try {
                    URL url = new URL(task_data[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod(task_data[1]);
                    for (int i = 2; i < task_data.length; i++) {
                        String[] property = task_data[i].split(":");
                        connection.setRequestProperty(property[0], property[1]);
                    }

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null)
                        result += inputLine;
                    in.close();
                    int code = connection.getResponseCode();
                    System.out.println(code);
                    String status = Integer.toString(connection.getResponseCode());
                    task_given.updateTask(worker_name, result, status);
                }catch (Exception e){
                    System.out.println("error in mienvio");
                }
            }
    }

    public void run(){
        while(task_given.getTask(this.worker_name)!="done"){
            try {
                consult();
            }catch (Exception e){
                System.out.println("error in run() on "+this.worker_name+" class");
            }
        }
    }

}
