package mapper;

import java.io.File;
import java.util.HashMap;

public class Context extends HashMap<String,Wrapper> {

    public void init(File file){
        //构件Wrapper对象
        String key = file.getName();
        Wrapper wrapper = new Wrapper();
        wrapper.init(file);
        this.put(key,wrapper);
    }

    public Context() {
        super();
    }
}
