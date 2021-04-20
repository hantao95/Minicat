package server;

import java.io.InputStream;
import java.net.Socket;

import mapper.Host;
import mapper.Mapper;
import mapper.Wrapper;

public class RequestProcessor extends Thread {

    private Socket socket;
    private Mapper mapper;

    public RequestProcessor(Socket socket, Mapper mapper) {
        this.socket = socket;
        this.mapper = mapper;
    }

    @Override
    public void run() {
        try{
            InputStream inputStream = socket.getInputStream();

            // 封装Request对象和Response对象
            Request request = new Request(inputStream);
            Response response = new Response(socket.getOutputStream());
            Wrapper wrapper = null ; 
            String appBase="";
          for(Host host:mapper.getHosts()) {
        	  if(host.getHostName().equals(request.getHost())) {
        		  wrapper = host.get(request.getContext()).get(request.getContext());
        		  appBase = host.getAppBase();
        	  }
          }
           
            // 静态资源处理
            if(wrapper.get(request.getUrl()) == null) {
                response.outputHtml(request,appBase);
            }else{
                // 动态资源servlet请求
                HttpServlet httpServlet = wrapper.get(request.getUrl());
                httpServlet.service(request,response);
            }

            socket.close();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
