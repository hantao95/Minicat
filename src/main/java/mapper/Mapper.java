package mapper;

import java.util.ArrayList;
import java.util.List;


public class Mapper {
    private List<Host> hosts = new ArrayList<>();

    public Mapper(){

    }

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }
}
