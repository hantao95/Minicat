package mapper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Host extends HashMap<String,Context>  {
    private int port = 8080;
    private String hostName = "localhost";
    private String appBase = "";

    public void init(){
        File f = new File(appBase);
        File[] fs = f.listFiles();
        for (File file:
             fs) {
            //构件Context对象
            String key = file.getName();
            Context context = new Context();
            context.init(file);
            this.put(key,context);
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getAppBase() {
        return appBase;
    }

    public void setAppBase(String appBase) {
        this.appBase = appBase;
    }

    public Host() {
        super();
    }
}
